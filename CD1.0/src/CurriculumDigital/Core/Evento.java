/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CurriculumDigital.Core;

/**
 *
 * @author cristiane
 */
import java.io.Serializable;

// Tornar a classe pública para que possa ser acessada fora do pacote
public class Evento implements Serializable {

    private String nomePessoa;
    private String descricao;
    private String entidade;

    public Evento(String nomePessoa, String descricao, String entidade) {
        this.nomePessoa = nomePessoa;
        this.descricao = descricao;
        this.entidade = entidade;
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
        return nomePessoa + " | " + descricao + " |  " + getEntidade();
    }
}
