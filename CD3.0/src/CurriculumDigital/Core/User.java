package CurriculumDigital.Core;

import blockchain.utils.SecurityUtils;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Classe que representa um usuário com chaves de segurança.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private PublicKey pub;   // Chave pública
    private transient PrivateKey priv; // Chave privada (transient para não ser serializada)
    private transient Key sim;         // Chave simétrica (transient para não ser serializada)
    private String passwordHash;

    // Construtores
    public User(String name) {
        this.name = name;
    }

    public User() {
        this.name = "noName";
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Gera as chaves do usuário.
     *
     * @throws Exception Em caso de erro na geração.
     */
    public void generateKeys() throws Exception {
        this.sim = SecurityUtils.generateAESKey(256);
        KeyPair kp = SecurityUtils.generateECKeyPair(256);
        this.pub = kp.getPublic();
        this.priv = kp.getPrivate();
    }

    /**
     * Salva as chaves do usuário em ficheiros.
     *
     * @param password Senha para proteger as chaves privada e simétrica.
     * @throws Exception Em caso de erro ao salvar.
     */
    public void save(String password) throws Exception {
        //this.passwordHash = blockchain .utils.hashPassword(password);
        // Cria o diretório "keys" se não existir
        Path dir = Paths.get("keys");
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }

        // Caminhos para os ficheiros
        Path privPath = Paths.get("keys", this.name + ".priv");
        Path simPath = Paths.get("keys", this.name + ".sim");
        Path pubPath = Paths.get("keys", this.name + ".pub");

        // Salva as chaves
        Files.write(privPath, SecurityUtils.encrypt(priv.getEncoded(), password));
        Files.write(simPath, SecurityUtils.encrypt(sim.getEncoded(), password));
        Files.write(pubPath, pub.getEncoded());
    }

    /**
     * Carrega as chaves do usuário de ficheiros.
     *
     * @param password Senha para descriptografar as chaves.
     * @throws Exception Em caso de erro ao carregar ou descriptografar.
     */
    public void load(String password) throws Exception {
        Path privPath = Paths.get("keys/", this.name + ".priv");
        Path simPath = Paths.get("keys/", this.name + ".sim");
        Path pubPath = Paths.get("keys/", this.name + ".pub");

        // Verifica se os ficheiros existem
        if (!Files.exists(privPath) || !Files.exists(simPath) || !Files.exists(pubPath)) {
            throw new Exception("Os ficheiros de chaves não existem para o usuário: " + this.name);
        }

        // Carrega e descriptografa as chaves
        this.priv = SecurityUtils.getPrivateKey(SecurityUtils.decrypt(Files.readAllBytes(privPath), password));
        this.sim = SecurityUtils.getAESKey(SecurityUtils.decrypt(Files.readAllBytes(simPath), password));
        this.pub = SecurityUtils.getPublicKey(Files.readAllBytes(pubPath));
    }

    
    
    /**
     * Carrega apenas a chave pública do ficheiro.
     *
     * @throws Exception Em caso de erro ao carregar.
     */
    public  void loadPublic() throws Exception {
        Path pubPath = Paths.get("keys", this.name + ".pub");

        if (!Files.exists(pubPath)) {
            throw new Exception("O ficheiro da chave pública não existe para o usuário: " + this.name);
        }

        this.pub = SecurityUtils.getPublicKey(Files.readAllBytes(pubPath));
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PublicKey getPubKey() {
        return pub;
    }

    public void setPubKey(PublicKey pub) {
        this.pub = pub;
    }

    public PrivateKey getPrivKey() {
        return priv;
    }

    public void setPrivKey(PrivateKey priv) {
        this.priv = priv;
    }

    public Key getSimKey() {
        return sim;
    }

    public void setSimKey(Key sim) {
        this.sim = sim;
    }
}
