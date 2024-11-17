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

    private List<Evento> currentBuffer; // Buffer de eventos aguardando mineração
    private BlockChain blockchain; // Blockchain que armazena os blocos
    private String fileName; // Caminho do ficheiro onde a blockchain será armazenada
    public static final int DIFFICULTY = 4; // Dificuldade para mineração dos blocos
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
        
    }

    // Gera um bloco com os eventos do buffer e adiciona à blockchain
    public void gerarBloco() throws Exception {
        if (currentBuffer.isEmpty()) {
            throw new Exception("O buffer de eventos está vazio. Adicione eventos antes de gerar um bloco.");
        }

        // Cria a Merkle Tree a partir dos eventos no buffer
        MerkleTree mt = new MerkleTree(currentBuffer);

        // Serializa a Merkle Tree para armazenamento no bloco
        String serializedMerkleTree = ObjectUtils.convertObjectToBase64(mt);

        // Adiciona o bloco à blockchain
        blockchain.add(serializedMerkleTree, DIFFICULTY);

        // Limpa o buffer após adicionar à blockchain
        currentBuffer.clear();

        // Salva o estado atualizado da blockchain
        save(fileName);
    }

    // Retorna todos os eventos armazenados na blockchain
    public List<Evento> getEventos() throws Exception {
        List<Evento> eventos = new ArrayList<>();

        for (Block block : blockchain.getChain()) {
            // Desserializa a Merkle Tree armazenada no bloco
            MerkleTree mt = (MerkleTree) ObjectUtils.convertBase64ToObject(block.getData());

            // Adiciona todos os eventos da Merkle Tree à lista de eventos
            eventos.addAll(mt.getElements());
        }

        return eventos;
    }
    

    // Verifica se a blockchain está íntegra
    public boolean isBlockchainValida() {
        return blockchain.isValid();
    }

    // Salva o estado da blockchain no ficheiro
    public void save(String fileName) throws Exception {
        blockchain.save(fileName);
    }

    // Carrega o estado da blockchain do ficheiro
    public void load(String fileName) throws Exception {
        blockchain.load(fileName);
        currentBuffer.clear(); // Limpa o buffer após carregar
    }
}
