/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cdblockchain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author cristiane
 */
public class GestorEventos {

    private List<Evento> eventos;
    private final String caminho = "curriculos.txt";

    public GestorEventos() {
        eventos = new ArrayList<>();
    }

    public void adicionarEvento(String nomePessoa, String descricao, String entidade) {
        eventos.add(new Evento(nomePessoa, descricao, entidade));
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public Set<String> listarNomesPessoas() {
        Set<String> nomes = new LinkedHashSet<>();
        for (Evento evento : eventos) {
            nomes.add(evento.getNomePessoa());
        }
        return nomes;
    }

    public List<Evento> listarCurriculo(String nomePessoa) {
        List<Evento> curriculo = new ArrayList<>();
        for (Evento evento : eventos) {
            if (evento.getNomePessoa().equalsIgnoreCase(nomePessoa)) {
                curriculo.add(evento);
            }
        }
        return curriculo;
    }

    public void salvarParaArquivo() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, true))) { // Adiciona 'true' para modo de append
            for (Evento evento : eventos) {
                writer.write(evento.toString() + "\n");
            }
        }
    }

    public void carregarDeArquivo() throws IOException {
        eventos.clear(); // Limpa os eventos atuais antes de carregar
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                // Assumindo formato: "Nome: nomePessoa | Descrição: descricao | Entidade: entidade"
                String[] dados = linha.split(" \\| ");
                if (dados.length == 3) {
                    String nomePessoa = dados[0].substring(6); // Remove "Nome: "
                    String descricao = dados[1].substring(11); // Remove "Descrição: "
                    String entidade = dados[2].substring(10); // Remove "Entidade: "
                    eventos.add(new Evento(nomePessoa, descricao, entidade));
                }
            }
        }
    }

    public void limparArquivo() throws IOException {
        // Abre o arquivo no modo de escrita sem 'append', o que limpa o conteúdo existente
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, false))) {
            // Escreve uma string vazia para limpar o conteúdo
            writer.write("");
        }
    }

}
