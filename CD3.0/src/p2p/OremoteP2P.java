//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
//::                                                                         ::
//::     Antonio Manuel Rodrigues Manso                                      ::
//::                                                                         ::
//::     I N S T I T U T O    P O L I T E C N I C O   D E   T O M A R        ::
//::     Escola Superior de Tecnologia de Tomar                              ::
//::     e-mail: manso@ipt.pt                                                ::
//::     url   : http://orion.ipt.pt/~manso                                  ::
//::                                                                         ::
//::     This software was build with the purpose of investigate and         ::
//::     learning.                                                           ::
//::                                                                         ::
//::                                                               (c)2024   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////
package p2p;

import CurriculumDigital.Core.Evento;
import blockchain.utils.Block;
import blockchain.utils.BlockChain;
import CurriculumDigital.Core.User;
import blockchain.utils.MerkleTree;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import miner.Miner;
import blockchain.utils.RMI;
import java.io.File;

/**
 * Created on 27/11/2024, 17:48:32
 *
 * @author manso - computer
 */
public class OremoteP2P extends UnicastRemoteObject implements IremoteP2P {

    final static String BLOCHAIN_FILENAME = "blockchain.obj";
    String address;
    CopyOnWriteArrayList<IremoteP2P> network;
    // Set - conjunto de elementos não repetidos para acesso concorrente
    CopyOnWriteArraySet<String> transactions;
    P2Plistener p2pListener;
    //objeto mineiro concorrente e distribuido
    public Miner myMiner;
    //objeto da blockchain preparada para cesso concorrente
    BlockChain myBlockchain;

    public OremoteP2P(String address, P2Plistener listener) throws RemoteException {
        super(RMI.getAdressPort(address));
        this.address = address;
        this.network = new CopyOnWriteArrayList<>();
        transactions = new CopyOnWriteArraySet<>();
        this.myMiner = new Miner(listener);
        this.myBlockchain = new BlockChain(BLOCHAIN_FILENAME);
        this.p2pListener = listener;

        listener.onStartRemote("Object " + address + " listening");

    }

    @Override
    public String getAdress() throws RemoteException {
        return address;
    }

    /**
     * Método que verifica se um no está na rede e elmina os que não responderem
     *
     * @param adress endereço do no
     * @return true se estiver na rede falso caso contrario
     */
    private boolean isInNetwork(String adress) {
        //fazer o acesso iterado pelo fim do array para remover os nos inativos
        for (int i = network.size() - 1; i >= 0; i--) {
            try {
                //se o no responder e o endereço for igaul
                if (network.get(i).getAdress().equals(adress)) {
                    // no esta na rede 
                    return true;
                }
            } catch (RemoteException ex) {
                //remover os nós que não respondem
                network.remove(i);
            }
        }
        return false;
    }

    @Override
    public void addNode(IremoteP2P node) throws RemoteException {
        //se já tiver o nó  ---  não faz nada
        if (isInNetwork(node.getAdress())) {
            return;
        }
        p2pListener.onMessage("Network addNode ", node.getAdress());
        //adicionar o no á rede
        network.add(node);

        p2pListener.onConect(node.getAdress());
        // pedir ao no para nos adicionar
        node.addNode(this);
        //propagar o no na rede
        for (IremoteP2P iremoteP2P : network) {
            iremoteP2P.addNode(node);
        }

        //sicronizar as transaçoes
        synchronizeTransactions(node);
        //sincronizar a blockchain
        synchnonizeBlockchain();

    }

    @Override
    public List<IremoteP2P> getNetwork() throws RemoteException {
        return new ArrayList<>(network);
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //::::::::            T R A N S A C T I O N S       ::::::::::::::::::
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public int getTransactionsSize() throws RemoteException {
        return transactions.size();
    }

    public void addTransaction(String data) throws RemoteException {
        //seja tiver a transacao não faz nada
        if (transactions.contains(data)) {
            p2pListener.onTransaction("Transaçao repetida " + data);
            //sair
            return;
        }
        //Adicionar a transaçao ao no local
        transactions.add(data);
        //Adicionar a transacao aos nos da rede
        for (IremoteP2P iremoteP2P : network) {
            iremoteP2P.addTransaction(data);
        }

    }

    @Override
    public List<String> getTransactions() throws RemoteException {
        return new ArrayList<>(transactions);
    }

    @Override
    public void synchronizeTransactions(IremoteP2P node) throws RemoteException {
        //tamanho anterior
        int oldsize = transactions.size();
        p2pListener.onMessage("sinchronizeTransactions", node.getAdress());
        // juntar as transacoes todas (SET elimina as repetidas)
        this.transactions.addAll(node.getTransactions());
        int newSize = transactions.size();
        //se o tamanho for incrementado
        if (oldsize < newSize) {
            p2pListener.onMessage("sinchronizeTransactions", "tamanho diferente");
            //pedir ao no para sincronizar com as nossas
            node.synchronizeTransactions(this);
            p2pListener.onTransaction(address);
            p2pListener.onMessage("sinchronizeTransactions", "node.sinchronizeTransactions(this)");
            //pedir á rede para se sincronizar
            for (IremoteP2P iremoteP2P : network) {
                //se o tamanho for menor
                if (iremoteP2P.getTransactionsSize() < newSize) {
                    //cincronizar-se com o no actual
                    p2pListener.onMessage("sinchronizeTransactions", " iremoteP2P.sinchronizeTransactions(this)");
                    iremoteP2P.synchronizeTransactions(this);
                }
            }
        }

    }

    @Override
    public void removeTransactions(List<String> myTransactions) throws RemoteException {
        //remover as transações da lista atural
        transactions.removeAll(myTransactions);
        p2pListener.onTransaction("remove " + myTransactions.size() + "transactions");
        //propagar as remoções
        for (IremoteP2P iremoteP2P : network) {
            //se houver algum elemento em comum nas transações remotas
            if (iremoteP2P.getTransactions().retainAll(transactions)) {
                //remover as transaçoies
                iremoteP2P.removeTransactions(myTransactions);
            }
        }

    }
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //:::::::::::::::::      M I N E R   :::::::::::::::::::::::::::::::::::::::
    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //////////////////////////////////////////////////////////////////////////////

    @Override
    public void startMining(String msg, int zeros) throws RemoteException {
        try {
            //colocar a mineiro a minar
            myMiner.startMining(msg, zeros);
            p2pListener.onStartMining(msg, zeros);
            //mandar minar a rede
            for (IremoteP2P iremoteP2P : network) {
                //se o nodo nao estiver a minar
                if (!iremoteP2P.isMining()) {
                    p2pListener.onStartMining(iremoteP2P.getAdress() + " mining", zeros);
                    //iniciar a mineracao no nodo
                    iremoteP2P.startMining(msg, zeros);
                }
            }
        } catch (Exception ex) {
            p2pListener.onException(ex, "startMining");
        }

    }

    @Override
    public void stopMining(int nonce) throws RemoteException {
        //parar o mineiro e distribuir o nonce
        myMiner.stopMining(nonce);
        //mandar parar a rede
        for (IremoteP2P iremoteP2P : network) {
            //se o nodo estiver a minar   
            if (iremoteP2P.isMining()) {
                //parar a mineração no nodo 
                iremoteP2P.stopMining(nonce);
            }
        }
    }

    @Override
    public int mine(String msg, int zeros) throws RemoteException {
        try {
            //começar a minar a mensagem
            startMining(msg, zeros);
            //esperar que o nonce seja calculado
            return myMiner.waitToNonce();
        } catch (InterruptedException ex) {
            p2pListener.onException(ex, "Mine");
            return -1;
        }

    }

    @Override
    public boolean isMining() throws RemoteException {
        return myMiner.isMining();
    }

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //::::::::::::::::: B L O C K C H A I N :::::::::::::::::::::::::::::::::::::::
    //:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //////////////////////////////////////////////////////////////////////////////
    @Override
    public void addBlock(Block b) throws RemoteException {
        try {
            //se não for válido
            if (!b.isValid()) {
                throw new RemoteException("invalid block");
            }
            //se encaixar adicionar o bloco
            if (myBlockchain.getLastBlockHash().equals(b.getPreviousHash())) {
                myBlockchain.add(b);
                //guardar a blockchain
                myBlockchain.save(BLOCHAIN_FILENAME);
                p2pListener.onBlockchainUpdate(myBlockchain);
            }
            //propagar o bloco pela rede
            for (IremoteP2P iremoteP2P : network) {
                //se encaixar na blockcahin dos nodos remotos
                if (!iremoteP2P.getBlockchainLastHash().equals(b.getPreviousHash())
                        || //ou o tamanho da remota for menor
                        iremoteP2P.getBlockchainSize() < myBlockchain.getSize()) {
                    //adicionar o bloco ao nodo remoto
                    iremoteP2P.addBlock(b);
                }
            }
            //se não encaixou)
            if (!myBlockchain.getLastBlockHash().equals(b.getCurrentHash())) {
                //sincronizar a blockchain
                synchnonizeBlockchain();
            }
        } catch (Exception ex) {
            p2pListener.onException(ex, "Add bloco " + b);
        }
    }

    @Override
    public int getBlockchainSize() throws RemoteException {
        return myBlockchain.getSize();
    }

    @Override
    public String getBlockchainLastHash() throws RemoteException {
        return myBlockchain.getLastBlockHash();
    }

    @Override
    public BlockChain getBlockchain() throws RemoteException {
        return myBlockchain;
    }

    @Override
    public void synchnonizeBlockchain() throws RemoteException {
        //para todos os nodos da rede
        for (IremoteP2P iremoteP2P : network) {
            //se a blockchain for maior
            if (iremoteP2P.getBlockchainSize() > myBlockchain.getSize()) {
                BlockChain remote = iremoteP2P.getBlockchain();
                //e a blockchain for válida
                if (remote.isValid()) {
                    //atualizar toda a blockchain
                    myBlockchain = remote;
                    //deveria sincronizar apenas os blocos que faltam
                    p2pListener.onBlockchainUpdate(myBlockchain);
                }
            }
        }
    }

    @Override
    public List<String> getBlockchainTransactions() throws RemoteException {
        ArrayList<String> allTransactions = new ArrayList<>();
        for (Block b : myBlockchain.getChain()) {
            allTransactions.addAll(b.transactions());
        }
        return allTransactions;
    }

    //    LOGIN  
    public synchronized boolean register(String username, String password) throws RemoteException {
        try {
            // Verificar se o utilizador já existe
            File publicKeyFile = new File("keys/" + username + ".pub");
            if (publicKeyFile.exists()) {
                return false; // Utilizador já registrado
            }

            // Criar um novo utilizador
            User newUser = new User(username);

            // Gerar as chaves do utilizador
            newUser.generateKeys();

            // Salvar as chaves com a senha fornecida
            newUser.save(password);

            return true; // Registro concluído com sucesso
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Falha no registro
        }
    }

    @Override
    public boolean authenticate(String username, String password) throws RemoteException {
        try {
            // Criar instância do utilizador
            User existingUser = new User(username);

            // Tentar carregar as chaves do utilizador
            existingUser.load(password);

            return true; // Login bem-sucedido
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Falha no login
        }
    }

    public Object getMyMiner() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Evento> getEventosParaPessoaAutenticada(String identificadorPessoaAutenticada) throws RemoteException {
        if (identificadorPessoaAutenticada == null || identificadorPessoaAutenticada.isEmpty()) {
            throw new IllegalArgumentException("Identificador da pessoa autenticada não pode ser nulo ou vazio.");
        }

        List<Evento> eventosParaPessoa = new ArrayList<>();

        try {
            // Iterar sobre todas as transações na blockchain
            for (Block block : myBlockchain.getChain()) {
                for (String transaction : block.transactions()) {
                    // Processar a transação para extrair informações
                    String[] parts = transaction.split(" \\| ");
                    if (parts.length == 3) {
                        String nomePessoa = parts[0].trim();
                        String descricao = parts[1].trim();
                        String entidade = parts[2].trim();

                        // Verificar se o nome da pessoa coincide com o identificador autenticado
                        if (nomePessoa.equals(identificadorPessoaAutenticada)) {
                            // Criar um objeto Evento com os dados extraídos
                            Evento evento = new Evento(nomePessoa, descricao, entidade);
                            eventosParaPessoa.add(evento);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RemoteException("Erro ao obter eventos para a pessoa autenticada: " + e.getMessage(), e);
        }

        return eventosParaPessoa;
    }

    public List<String> getMerkleTrees() throws Exception {
        List<String> merkleTrees = new ArrayList<>();

        for (Block block : myBlockchain.getChain()) {
            MerkleTree merkleTree = new MerkleTree(block.getTransactions()); // Recria a árvore Merkle a partir das transações
            merkleTrees.add("Block Hash: " + block.getCurrentHash() + "\nMerkle Root: " + merkleTree.getRoot() + "\n" + merkleTree);
        }
        return merkleTrees;
    }

    public MerkleTree getMerkleTreeByBlock(String blockHash) throws Exception {
        for (Block block : myBlockchain.getChain()) {
            if (block.getCurrentHash().equals(blockHash)) {
                return new MerkleTree(block.getTransactions());
            }
        }
        throw new Exception("Bloco não encontrado para o hash fornecido: " + blockHash);
    }

}
