package blockchain.utils;

import cdblockchain.Evento;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Adaptada para integrar com a aplicação de blockchain para eventos.
 */
public final class MerkleTree implements Serializable {

    // Merkle tree hashes
    private List<List<String>> hashTree;
    // Elements of the tree
    List<Object> elements;

    /**
     * Builds a Merkle tree with an array of data.
     * 
     * @param arrayOfData Array of data (elements).
     */
    public MerkleTree(Object[] arrayOfData) {
        this(Arrays.asList(arrayOfData));
    }

    /**
     * Builds a Merkle tree with a list of data.
     * 
     * @param listOfData List of data (elements).
     */
    public MerkleTree(List<Object> listOfData) {
        this(); // Build lists
        // Save data in elements
        elements.addAll(listOfData);
        // Calculate list of hash of elements
        List<String> hashT = new ArrayList<>();
        for (Object elem : listOfData) {
            // Convert to string and hash the data
            hashT.add(getHashValue(elem.toString()));
        }
        // Build the Merkle tree
        makeTree(hashT);
    }

    /**
     * Builds an empty Merkle tree.
     */
    public MerkleTree() {
        // Initialize lists
        hashTree = new ArrayList<>();
        elements = new ArrayList<>();
    }

    /**
     * Root of the Merkle tree.
     * 
     * @return Root hash of the tree.
     */
    public String getRoot() {
        // Top of the tree
        return hashTree.get(0).get(0);
    }

    /**
     * Builds the Merkle tree.
     * 
     * @param hashList List of hashes.
     */
    public void makeTree(List<String> hashList) {
        // Add hash list to the beginning of the tree
        hashTree.add(0, hashList);
        // Top of the tree -> terminate
        if (hashList.size() <= 1) {
            return; // Top of the tree
        }
        // Build the next level
        List<String> newLevel = new ArrayList<>();
        // Iterate through list 2 by 2
        for (int i = 0; i < hashList.size(); i += 2) {
            // First element
            String data = hashList.get(i);
            // If there is another element
            if (i + 1 < hashList.size()) {
                // Concatenate right element
                data = data + hashList.get(i + 1);
            }
            // Calculate hash of concatenated elements
            String hash = getHashValue(data);
            // Add hash to the new level
            newLevel.add(hash);
        }
        // Recursively build the next level
        makeTree(newLevel);
    }

    /**
     * Calculate the proof of the element.
     * 
     * @param data Element to prove.
     * @return List of hashes for proof.
     */
    public List<String> getProof(Object data) {
        // List of proofs
        List<String> proof = new ArrayList<>();
        // Index of element
        int index = elements.indexOf(data);
        if (index < 0) { // Element not found
            return proof; // Empty proof
        }
        // Calculate proof
        return getProof(index, hashTree.size() - 1, proof);
    }

    /**
     * Calculate the proof of the element by traversing the tree.
     * 
     * @param index Index of element.
     * @param level Level of the tree.
     * @param proof List of proofs.
     * @return List of proofs.
     */
    private List<String> getProof(int index, int level, List<String> proof) {
        if (level > 0) { // Not the top
            if (index % 2 == 0) { // Even index [index or index+1]
                if (index + 1 < hashTree.get(level).size()) {
                    // Add right element
                    proof.add(hashTree.get(level).get(index + 1));
                } else {
                    // Add the hash of element
                    proof.add(hashTree.get(level).get(index));
                }
            } else { // Odd index [index - 1]
                proof.add(hashTree.get(level).get(index - 1));
            }
            // Calculate top level
            return getProof(index / 2, level - 1, proof);
        } else {
            // Add root of the tree
            proof.add(getRoot());
            return proof;
        }
    }

    /**
     * Calculate hash value securely using SHA-256.
     * 
     * @param data Data to hash.
     * @return Hash value as a hex string.
     */
    public static String getHashValue(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verify if the proof is valid.
     * 
     * @param data Data to verify.
     * @param proof List of hashes for proof.
     * @return True if the proof is valid.
     */
    public static boolean isProofValid(Object data, List<String> proof) {
        if (proof.isEmpty()) {
            return false;
        }
        // Hash of element
        String currentHash = getHashValue(data.toString());
        return isProofValid(currentHash, proof, 0);
    }

    public static boolean isProofValid(String currentHash, List<String> proof, int indexOfList) {
        // Top of the tree
        if (indexOfList == proof.size() - 1) {
            return currentHash.equals(proof.get(proof.size() - 1));
        }
        // Concatenate to the right
        String newHash = getHashValue(currentHash + proof.get(indexOfList));
        if (isProofValid(newHash, proof, indexOfList + 1)) {
            return true;
        }
        // Concatenate to the left
        newHash = getHashValue(proof.get(indexOfList) + currentHash);
        return isProofValid(newHash, proof, indexOfList + 1);
    }

    /**
     * Verify if the Merkle tree is valid.
     * 
     * @return True if the tree is valid.
     */
    public boolean isValid() {
        // Verify hashes of elements at the bottom of the tree
        for (int i = 0; i < this.elements.size(); i++) {
            if (!getHashValue(this.elements.get(i).toString()).equals(hashTree.get(hashTree.size() - 1).get(i))) {
                return false;
            }
        }
        // Verify the levels of the tree
        for (int level = 0; level < hashTree.size() - 1; level++) {
            for (int index = 0; index < hashTree.get(level).size(); index++) {
                // Left leaf
                String dataLeafs = hashTree.get(level + 1).get(index * 2);
                // If right leaf exists
                if (index * 2 + 1 < hashTree.get(level + 1).size()) {
                    dataLeafs = dataLeafs + hashTree.get(level + 1).get(index * 2 + 1);
                }
                // Calculate hash of leaves
                String hash = getHashValue(dataLeafs);
                // Verify the hash of leaves
                if (!hashTree.get(level).get(index).equals(hash)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Other methods like saveToFile, loadFromFile, toString are kept the same.

    private static final long serialVersionUID = 202209131142L;
}
