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

    ArrayList<Block> chain = new ArrayList<>();
    private final String fileName = "curriculos.txt";

    public String getLastBlockHash() {
        // Gera o hash do último bloco ou o hash da origem (bloco gênese)
        if (chain.isEmpty()) {
            return String.format("%08d", 0);
        }
        return chain.get(chain.size() - 1).currentHash;
    }

    /**
     * Adiciona um novo bloco de dados à blockchain.
     */
    public void addBlock(List<Evento> eventos, int difficulty) {
        if (eventos == null || eventos.isEmpty()) {
            System.out.println("Erro: Tentativa de adicionar um bloco com eventos nulos ou vazios.");
            return; // Ou lance uma exceção, dependendo da lógica de negócios
        }

        String prevHash = getLastBlockHash();
        // Faz o "mining" do bloco (ajuste a função de acordo com sua lógica de dificuldade)
        int nonce = Miner.getNonce(prevHash + eventos.toString(), difficulty);
        Block newBlock = new Block(prevHash, eventos, nonce);
        chain.add(newBlock);
    }

    public List<Block> getChain() {
        return chain;
    }

    public Block get(int index) {
        return chain.get(index);
    }

    public void save() throws Exception {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(chain);
        }
    }

    public void load() throws Exception {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            this.chain = (ArrayList<Block>) in.readObject();
        }
    }

    @Override
    public String toString() {
        StringBuilder txt = new StringBuilder();
        txt.append("Blockchain size = " + chain.size() + "\n");
        for (Block block : chain) {
            txt.append(block.toString() + "\n");
        }
        return txt.toString();
    }

    private static final long serialVersionUID = 202208221009L;
}
