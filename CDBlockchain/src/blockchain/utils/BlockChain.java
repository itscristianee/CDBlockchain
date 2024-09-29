package blockchain.utils;

import cdblockchain.Evento;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BlockChain implements Serializable {

    private List<Block> chain; // A sequência de blocos
    private final String fileName = "curriculos.txt"; // Nome do ficheiro para salvar a blockchain

    public BlockChain() {
        this.chain = new ArrayList<>();
    }

    /**
     * Obtém o hash do último bloco da cadeia, ou "00000000" se não houver
     * blocos.
     */
    public String getLastBlockHash() {
        if (chain.isEmpty()) {
            return "00000000";
        }
        return chain.get(chain.size() - 1).getCurrentHash();
    }

    /**
     * Adiciona um novo bloco à blockchain.
     */
    public void addBlock(List<Evento> eventos, int difficulty) {
        String prevHash = getLastBlockHash();
        // Realiza o "mining" do bloco
        int nonce = Miner.getNonce(prevHash + eventos.toString(), difficulty);
        Block newBlock = new Block(prevHash, eventos, nonce);
        chain.add(newBlock);
    }

    public List<Block> getChain() {
        return chain;
    }

    public void save() throws Exception {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            // Serializar toda a lista de blocos `chain`
            out.writeObject(chain);
        }
    }

    public void load() throws Exception {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            // Deserializar toda a lista de blocos `chain`
            this.chain = (ArrayList<Block>) in.readObject();
        }
    }

    @Override
    public String toString() {
        StringBuilder txt = new StringBuilder();
        txt.append("Blockchain size = ").append(chain.size()).append("\n");
        for (Block block : chain) {
            txt.append(block.toString()).append("\n");
        }
        return txt.toString();
    }

    private static final long serialVersionUID = 202208221009L;
}
