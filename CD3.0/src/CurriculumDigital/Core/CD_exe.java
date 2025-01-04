package CurriculumDigital.Core;

import blockchain.utils.Block;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import miner.Miner;
import blockchain.utils.MerkleTree;
import blockchain.utils.BlockChain;
import blockchain.utils.ObjectUtils;

public class CD_exe implements Serializable {

    private List<Evento> currentBuffer; // Buffer de eventos aguardando mineração
    private BlockChain blockchain;     // Blockchain que armazena os blocos
    private String fileName;           // Caminho do ficheiro onde a blockchain será armazenada
    private static final long serialVersionUID = 1L;
    private transient Miner miner;     // Minerador (não será serializado)

    // Construtor que inicializa o sistema
    public CD_exe(String fileName) {
        this.fileName = fileName;
        this.currentBuffer = new ArrayList<>();
        this.blockchain = new BlockChain();
        this.miner = new Miner(null); // Instância do minerador sem listener

        // Verifica se o arquivo blockchain existe e tenta carregá-lo
        File file = new File(fileName);
        if (file.exists()) {
            try {
                load(fileName);
            } catch (Exception e) {
                System.err.println("Erro ao carregar blockchain existente: " + e.getMessage());
            }
        }
    }

    // Adiciona um evento ao buffer de mineração
    public void addEvento(Evento evento) {
        currentBuffer.add(evento);
        System.out.println("Evento adicionado ao buffer: " + evento);
    }

    // Gera um bloco com os eventos do buffer e adiciona à blockchain
    public void gerarBloco(int DIFFICULTY) throws Exception {
        if (currentBuffer.isEmpty()) {
            throw new Exception("O buffer de eventos está vazio. Adicione eventos antes de gerar um bloco.");
        }

        // Cria a Merkle Tree a partir dos eventos no buffer
        MerkleTree mt = new MerkleTree(currentBuffer);

        // Serializa a Merkle Tree para armazenamento no bloco
        String serializedMerkleTree = ObjectUtils.convertObjectToBase64(mt);

        // Cria um novo bloco
        Block newBlock = new Block(blockchain.getLastBlockHash(), List.of(serializedMerkleTree));

        // Inicia a mineração usando o minerador
        int nonce = miner.mine(newBlock.getMinerData(), DIFFICULTY);
        newBlock.setNonce(nonce, DIFFICULTY);

        // Adiciona o bloco à blockchain
        blockchain.add(newBlock);

        // Salva a Merkle Tree em um arquivo
        mt.saveToFile(blockchain.getLastBlockHash() + ".mkt");

        // Limpa o buffer após adicionar à blockchain
        currentBuffer.clear();

        // Salva o estado atualizado da blockchain
        save(fileName);
        System.out.println("Bloco gerado e salvo com sucesso.");
    }

    // Retorna todos os eventos armazenados na blockchain
    public List<Evento> getEventos() throws Exception {
        List<Evento> eventos = new ArrayList<>();

        for (Block block : blockchain.getChain()) {
            // Desserializa a Merkle Tree armazenada no bloco
            MerkleTree mt = (MerkleTree) ObjectUtils.convertBase64ToObject(block.getTransactions().get(0));

            // Adiciona todos os eventos da Merkle Tree à lista de eventos
            eventos.addAll(mt.getElements());
        }

        return eventos;
    }

    public List<Evento> getEventosIncluindoBuffer() throws Exception {
        List<Evento> eventos = new ArrayList<>(currentBuffer); // Adiciona eventos do buffer
        eventos.addAll(getEventos()); // Adiciona eventos minerados
        return eventos;
    }

    public List<Evento> getEventosPorEntidade(String entidadeAssinante) throws Exception {
        List<Evento> eventos = new ArrayList<>();

        for (Block block : blockchain.getChain()) {
            // Desserializa a Merkle Tree armazenada no bloco
            MerkleTree mt = (MerkleTree) ObjectUtils.convertBase64ToObject(block.getTransactions().get(0));

            // Filtra os eventos que foram assinados pela entidade específica
            for (Object obj : mt.getElements()) {
                if (obj instanceof Evento) { // Verifica se o objeto é do tipo Evento
                    Evento evento = (Evento) obj;

                    if (evento.getEntidade().equals(entidadeAssinante)) {
                        eventos.add(evento);
                    }
                } else {
                    throw new Exception("Objeto na MerkleTree não é do tipo Evento.");
                }
            }
        }

        return eventos;
    }

    public List<Evento> getEventosParaPessoaAutenticada(String identificadorPessoaAutenticada) throws Exception {
        if (identificadorPessoaAutenticada == null || identificadorPessoaAutenticada.isEmpty()) {
            throw new IllegalArgumentException("Identificador da pessoa autenticada não pode ser nulo ou vazio.");
        }

        List<Evento> eventosParaPessoa = new ArrayList<>();

        // Adicionar eventos do buffer que foram assinados para a pessoa autenticada
        for (Evento evento : currentBuffer) {
            if (evento.getNomePessoa().equals(identificadorPessoaAutenticada)) {
                eventosParaPessoa.add(evento);
            }
        }

        // Adicionar eventos minerados que foram assinados para a pessoa autenticada
        for (Block block : blockchain.getChain()) {
            // Desserializar a Merkle Tree armazenada no bloco
            MerkleTree mt = (MerkleTree) ObjectUtils.convertBase64ToObject(block.getTransactions().get(0));

            // Filtrar eventos assinados para a pessoa autenticada
            for (Object obj : mt.getElements()) {
                if (obj instanceof Evento) {
                    Evento evento = (Evento) obj;
                    if (evento.getNomePessoa().equals(identificadorPessoaAutenticada)) {
                        eventosParaPessoa.add(evento);
                    }
                } else {
                    throw new Exception("Objeto na MerkleTree não é do tipo Evento.");
                }
            }
        }

        return eventosParaPessoa;
    }

    // Verifica se a blockchain está íntegra
    public boolean isBlockchainValida() {
        return blockchain.isValid();
    }

    public void save(String fileName) throws Exception {
        File file = new File(fileName);
        if (!file.canWrite()) {
            throw new Exception("Não é possível salvar no arquivo: " + fileName);
        }
        blockchain.save(fileName);
    }

    public void load(String fileName) throws Exception {
        File file = new File(fileName);
        if (!file.canRead()) {
            throw new Exception("Não é possível ler o arquivo: " + fileName);
        }
        blockchain.load(fileName);
        currentBuffer.clear();
    }

    // Getter para o blockchain
    public BlockChain getBlockchain() {
        return blockchain;
    }
}
