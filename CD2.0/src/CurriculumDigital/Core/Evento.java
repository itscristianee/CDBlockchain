package CurriculumDigital.Core;

import blockchain.utils.SecurityUtils;
import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class Evento implements Serializable {

    private String entidadePub; // Chave pública da entidade certificadora
    private String signature; // Assinatura do evento
    private String nomePessoa; // Nome da pessoa relacionada ao evento
    private String descricao; // Descrição do evento
    private String entidade; // Nome da entidade que certifica o evento

    // Construtor simples
    public Evento(String nomePessoa, String descricao, String entidade) {
        this.nomePessoa = nomePessoa;
        this.descricao = descricao;
        this.entidade = entidade;
    }

    // Construtor que cria e assina o evento com a chave privada de uma entidade
    public Evento(User from, String nomePessoa, String descricao) throws Exception {
        this.entidade = from.getName();
        this.entidadePub = Base64.getEncoder().encodeToString(from.getPubKey().getEncoded());
        this.nomePessoa = nomePessoa;
        this.descricao = descricao;
        sign(from.getPrivKey()); // Assina o evento
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEntidade() {
        return entidade;
    }

    // Converte o evento para string legível
    @Override
    public String toString() {
        return nomePessoa + " | " + descricao + " | " + entidade;
    }

    // Gera a assinatura do evento usando a chave privada da entidade
    public void sign(PrivateKey priv) throws Exception {
        byte[] dataToSign = (entidadePub + nomePessoa + descricao).getBytes();
        byte[] signedData = SecurityUtils.sign(dataToSign, priv);
        this.signature = Base64.getEncoder().encodeToString(signedData);
    }

    // Verifica se a assinatura do evento é válida usando a chave pública da entidade
    public boolean isValid() {
        try {
            PublicKey pubKey = SecurityUtils.getPublicKey(Base64.getDecoder().decode(entidadePub));
            byte[] data = (entidadePub + nomePessoa + descricao).getBytes();
            byte[] signData = Base64.getDecoder().decode(signature);
            return SecurityUtils.verifySign(data, signData, pubKey);
        } catch (Exception ex) {
            System.err.println("Erro ao validar a assinatura: " + ex.getMessage());
            return false;
        }
    }
}
