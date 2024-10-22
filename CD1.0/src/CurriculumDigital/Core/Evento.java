/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CurriculumDigital.Core;

/**
 *
 * @author cristiane
 */
import blockchain.utils.SecurityUtils;
import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

// Tornar a classe pública para que possa ser acessada fora do pacote
public class Evento implements Serializable {

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }
    private String entidadePub;

    private String signature;
    private String nomePessoa;
    private String descricao;
    private String entidade;

    public Evento(String nomePessoa, String descricao, String entidade) {
        this.nomePessoa = nomePessoa;
        this.descricao = descricao;
        this.entidade = entidade;
    }

    public Evento(User from, String nomePessoa, String descricao) throws Exception {
        this.entidade = from.getName();
        this.entidadePub = Base64.getEncoder().encodeToString(from.getPub().getEncoded());
        this.nomePessoa = nomePessoa;
        this.descricao = descricao;
        sign(from.getPriv());
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    /**
     * @return the entidade
     */
    public String getEntidade() {
        return entidade;
    }

    @Override
    public String toString() {
        return getNomePessoa() + " | " + getDescricao() + " |  " + getEntidade();
    }

    public void sign(PrivateKey priv) throws Exception {
        byte[] dataSign = SecurityUtils.sign(
                (entidadePub + nomePessoa + descricao).getBytes(),
                priv);
        this.signature = Base64.getEncoder().encodeToString(dataSign);
    }

    public boolean isValid() {
        try {
            PublicKey pub = SecurityUtils.getPublicKey(Base64.getDecoder().decode(entidadePub));
            byte[] data = (entidadePub + nomePessoa + descricao).getBytes();
            byte[] sign = Base64.getDecoder().decode(signature);
            return SecurityUtils.verifySign(data, sign, pub);
        } catch (Exception ex) {
            // Log de erro, se necessário
            System.err.println("Erro ao validar a assinatura: " + ex.getMessage());
            return false;
        }
    }
    

}
