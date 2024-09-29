package blockchain.utils;

import cdblockchain.Evento;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Block implements Serializable {

    String previousHash; // Hash do bloco anterior
    List<Evento> eventos; // Lista de eventos como dados do bloco
    int nonce; // Prova de trabalho
    String currentHash; // Hash do bloco atual
    MerkleTreeBytes<Evento> merkleTree; // Árvore de Merkle para organizar os eventos

    public Block(String previousHash, List<Evento> eventos, int nonce) {
        this.previousHash = previousHash;
        this.eventos = eventos != null ? eventos : new ArrayList<>(); // Garantir que não seja nulo
        this.nonce = nonce;

        // Verifique se a lista de eventos não está vazia antes de criar a MerkleTree
        if (!this.eventos.isEmpty()) {
            // Cria uma Merkle Tree para os eventos
            this.merkleTree = new MerkleTreeBytes<>(this.eventos);
        } else {
            // Adicione uma mensagem de erro ou inicialização alternativa
            System.out.println("Aviso: Lista de eventos vazia ao criar o bloco.");
            this.merkleTree = null;
        }

        // Calcula o hash do bloco
        this.currentHash = calculateHash();
    }

    /**
     * Calcula o hash do bloco usando SHA-256.
     */
    public String calculateHash() {
        String merkleRoot = byteArrayToHex(merkleTree.getRoot());
        return Hash.getHash(nonce + previousHash + merkleRoot);
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    /**
     * Obtém a raiz da Merkle Tree associada aos eventos.
     */
    public String getMerkleRoot() {
        return byteArrayToHex(merkleTree.getRoot());
    }

    /**
     * Gera uma prova de Merkle para um evento específico.
     */
    // Método para obter a prova de Merkle para um evento
    // Método para obter a prova de Merkle para um evento
    /**
     * Gera uma prova de Merkle para um evento específico.
     */
    public List<String> getMerkleProof(Evento evento) {
        if (this.merkleTree == null) {
            System.out.println("Erro: MerkleTree está nulo para este bloco.");
            return new ArrayList<>(); // Retorna uma lista vazia ou lança uma exceção
        }
        // Use o método `getProof` da classe `MerkleTreeBytes`
        List<byte[]> proofBytes = merkleTree.getProof(evento);

        // Converta `List<byte[]>` para `List<String>`
        List<String> proofStrings = new ArrayList<>();
        for (byte[] proof : proofBytes) {
            proofStrings.add(byteArrayToHex(proof));
        }

        return proofStrings;
    }

    // Função utilitária para converter byte array para hexadecimal
    private static String byteArrayToHex(byte[] val) {
        BigInteger bigInt = new BigInteger(1, val);
        return bigInt.toString(16).toUpperCase();
    }

    @Override
    public String toString() {
        return String.format("[ %8s <- %s %7d ] = %8s", previousHash, eventos.toString(), nonce, currentHash);
    }

    private static final long serialVersionUID = 202208220923L;
}
