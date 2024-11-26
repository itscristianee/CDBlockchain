package CurriculumDigital.Core;

import blockchain.utils.Block;
import blockchain.utils.BlockChain;
import blockchain.utils.MerkleTree;
import blockchain.utils.ObjectUtils;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CurriculumDigital implements Serializable {

    /**
     * @return the blockchain
     */
    public BlockChain getBlockchain() {
        return blockchain;
    }

    private List<Evento> currentBuffer; // Buffer de eventos aguardando mineração
    private BlockChain blockchain; // Blockchain que armazena os blocos
    private String fileName; // Caminho do ficheiro onde a blockchain será armazenada
    private static final long serialVersionUID = 1L;

    // Construtor que inicializa o sistema
    public CurriculumDigital(String fileName) {
        this.fileName = fileName;
        this.currentBuffer = new ArrayList<>();
        this.blockchain = new BlockChain();

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
    public void addEvento(Evento evento) throws Exception {
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

        // Adiciona o bloco à blockchain
        getBlockchain().add(serializedMerkleTree, DIFFICULTY);

        // Salva a Merkle Tree em um arquivo (mantém esta funcionalidade)
        mt.saveToFile(getBlockchain().getLastBlockHash() + ".mkt");

        // Limpa o buffer após adicionar à blockchain
        currentBuffer.clear();

        // Salva o estado atualizado da blockchain
        save(fileName);
        System.out.println("Bloco gerado e salvo com sucesso.");
    }

    // Retorna todos os eventos armazenados na blockchain
    public List<Evento> getEventos() throws Exception {
        List<Evento> eventos = new ArrayList<>();

        for (Block block : getBlockchain().getChain()) {
            // Desserializa a Merkle Tree armazenada no bloco
            MerkleTree mt = (MerkleTree) ObjectUtils.convertBase64ToObject(block.getData());

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

        for (Block block : getBlockchain().getChain()) {
            // Desserializa a Merkle Tree armazenada no bloco
            MerkleTree mt = (MerkleTree) ObjectUtils.convertBase64ToObject(block.getData());

            // Filtra os eventos que foram assinados pela entidade específica
            for (Object obj : mt.getElements()) {
                if (obj instanceof Evento) { // Verifica se o objeto é do tipo Evento
                    Evento evento = (Evento) obj;

                    if (evento.getEntidade().equals(entidadeAssinante)) {
                        eventos.add(evento);
                    }
                } else {
                    throw new Exception("Objeto no MerkleTree não é do tipo Evento.");
                }
            }
        }

        return eventos;
    }

    

    // Verifica se a blockchain está íntegra
    public boolean isBlockchainValida() {
        return getBlockchain().isValid();
    }

    public void save(String fileName) throws Exception {
        File file = new File(fileName);
        if (!file.canWrite()) {
            throw new Exception("Não é possível salvar no arquivo: " + fileName);
        }
        getBlockchain().save(fileName);
    }

    public void load(String fileName) throws Exception {
        File file = new File(fileName);
        if (!file.canRead()) {
            throw new Exception("Não é possível ler o arquivo: " + fileName);
        }
        getBlockchain().load(fileName);
        currentBuffer.clear();
    }

}
