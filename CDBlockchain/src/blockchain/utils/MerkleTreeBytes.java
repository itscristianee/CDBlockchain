package blockchain.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public final class MerkleTreeBytes<T> implements Serializable {

    // Merkle tree hashes
    List<List<byte[]>> hashTree;
    // Elements of the tree
    List<T> elements;
    private final String fileName = "provas.txt"; // Nome do ficheiro para salvar a prova da merkletree


    /**
     * Builds a merkle tree with an array of data
     *
     * @param arrayOfData list of data
     */
    public MerkleTreeBytes(T[] arrayOfData) {
        this(Arrays.asList(arrayOfData));
    }

    /**
     * Builds a merkle tree with a list of data
     *
     * @param listOfData list of data
     */
    public MerkleTreeBytes(List<T> listOfData) {
        this(); // Build lists
        // Save data
        elements.addAll(listOfData);
        // Calculate list of hash of elements
        List<byte[]> hashT = new ArrayList<>();
        for (T elem : listOfData) {
            // Convert T to byte arrays and hash byte array
            hashT.add(getHashValue(objectToBytes(elem)));
        }
        // Build merkle tree
        makeTree(hashT);
    }

    /**
     * Builds an empty merkle tree
     */
    public MerkleTreeBytes() {
        // Build lists
        hashTree = new ArrayList<>();
        elements = new ArrayList<>();
    }

    /**
     * Root of tree
     *
     * @return root of tree
     */
    public byte[] getRoot() {
        // Top of list
        return hashTree.get(0).get(0);
    }

    /**
     * Gets the merkle tree
     *
     * @return tree
     */
    public List<List<byte[]>> getMerkleTree() {
        return hashTree;
    }

    /**
     * Gets the elements in the merkle tree
     *
     * @return elements
     */
    public List<T> getElements() {
        return elements;
    }

    /**
     * Builds a merkle tree
     *
     * @param hashList list of hashes
     */
    public void makeTree(List<byte[]> hashList) {
        // Add hash list to the beginning of the tree
        hashTree.add(0, hashList);
        // Top of tree -> terminate
        if (hashList.size() <= 1) {
            return;
        }
        // New level
        List<byte[]> newLevel = new ArrayList<>();
        // Iterate list 2 by 2
        for (int i = 0; i < hashList.size(); i += 2) {
            // First element
            byte[] data = hashList.get(i);
            // If there is another element
            if (i + 1 < hashList.size()) {
                // Concatenate element    
                data = concatenate(data, hashList.get(i + 1));
            }
            // Calculate hash of the elements
            byte[] hash = getHashValue(data);
            // Add hash to the new level
            newLevel.add(hash);
        }
        // Call the makeTree with new level
        makeTree(newLevel);
    }

    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    //::::::::::::::::::::      A D D   E L E M E N T        :::::::::::::::::::
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Add an element to the merkle tree
     *
     * @param newData new data
     */
    public void add(T newData) {
        // If tree is empty
        if (hashTree.isEmpty()) {
            // Create first level
            hashTree.add(new ArrayList<>());
        }
        // Save data in elements list
        this.elements.add(newData);

        int level = hashTree.size() - 1;
        // Convert new data to byte array
        // Calculate hash of byte array
        // Add hash of new data to the last level
        hashTree.get(level).add(getHashValue(objectToBytes(newData)));
        // Until the top of tree
        while (level > 0) {
            // Number of elements in level
            int sizeOfLevel = hashTree.get(level).size();
            // Last element            
            byte[] dataHash = hashTree.get(level).get(sizeOfLevel - 1);
            // If size is even - replace the top element             
            if (sizeOfLevel % 2 == 0) {
                // Concatenate the two last hashes
                dataHash = concatenate(hashTree.get(level).get(sizeOfLevel - 2), dataHash);
                // Replace the element in top                 
                // top = level.size / 2
                hashTree.get(level - 1).set((sizeOfLevel - 1) / 2, getHashValue(dataHash));
            } // If size is odd and exists the level                           
            else if (hashTree.get(level - 1).size() < sizeOfLevel / 2.0) {
                // Add the hash element in the top
                hashTree.get(level - 1).add((sizeOfLevel - 1) / 2, getHashValue(dataHash));
            } else {
                // Replace the hash element in the top
                hashTree.get(level - 1).set((sizeOfLevel - 1) / 2, getHashValue(dataHash));
            }
            // Next level (top)
            level--;
        }

        // Create a new level if necessary
        if (hashTree.get(0).size() > 1) { // Last level has two elements
            // Concatenate elements
            // Calculate hash
            byte[] hash = getHashValue(concatenate(hashTree.get(0).get(0), hashTree.get(0).get(1)));
            // Create list with the hash
            List<byte[]> lst = new ArrayList<>();
            lst.add(hash);
            // Add list to the top
            hashTree.add(0, lst);
        }
    }

    /**
     * Calculate the proof of the element
     *
     * @param data element
     * @return list of proofs
     */
    public List<byte[]> getProof(T data) {
        // List of proofs
        List<byte[]> proof = new ArrayList<>();
        // Index of element
        int index = elements.indexOf(data);
        if (index < 0) { // Element not found
            return proof; // Empty proof
        }
        // Calculate proof
        return getProof(index, hashTree.size() - 1, proof);
    }

    /**
     * Calculate the proof of the element
     *
     * @param index index of element
     * @param level level of the tree
     * @param proof list of proofs
     * @return list of proofs
     */
    private List<byte[]> getProof(int index, int level, List<byte[]> proof) {
        if (level > 0) { // Not the top
            // Even index
            if (index % 2 == 0) {
                // If there's a right sibling
                if (index + 1 < hashTree.get(level).size()) {
                    proof.add(hashTree.get(level).get(index + 1));
                }
            } else { // Odd index
                proof.add(hashTree.get(level).get(index - 1));
            }
            // Calculate top level
            return getProof(index / 2, level - 1, proof);
        } else {
            // Add root of tree
            proof.add(getRoot());
            return proof;
        }
    }

    /**
     * Calculates the hash value of data using SHA-256
     *
     * @param data data
     * @return hash value
     */
    public static byte[] getHashValue(byte[] data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts an object to a byte array
     */
    public static byte[] objectToBytes(Object object) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        } catch (IOException e) {
            // Handle the exception
            throw new RuntimeException("Error converting object to bytes", e);
        }
    }

    /**
     * Concatenates two byte arrays
     */
    public static byte[] concatenate(byte[] array1, byte[] array2) {
        byte[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }
    
    public void saveToFile() throws IOException {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
        out.writeObject(this);
    }
}
    public MerkleTreeBytes loadFromFile() throws IOException, ClassNotFoundException {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
        return (MerkleTreeBytes) in.readObject();
    }
}


    // Other utility methods (byteArrayToHex, concatenate, objectToBytes, etc.) remain the same as in your original code.
    private static final long serialVersionUID = 202209131142L;
}
