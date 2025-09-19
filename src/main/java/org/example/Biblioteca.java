package org.example;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Biblioteca implements Serializable {
    private List<Catalogavel> acervo;

    public Biblioteca() {
        this.acervo = new ArrayList<>();
    }

    public void adicionarItem(Catalogavel item) {
        this.acervo.add(item);
        System.out.println("Item com codigo '" + item.getCodigo() + "' adicionado ao acervo.");
    }

    public void removerItem(String codigo) {
        boolean removido = this.acervo.removeIf(item -> item.getCodigo().equals(codigo));
        if (removido) {
            System.out.println("Item com codigo '" + codigo + "' removido com sucesso.");
        } else {
            System.out.println("Item com codigo '" + codigo + "' nao encontrado.");
        }
    }

    public Optional<Catalogavel> buscarItemPorCodigo(String codigo) {
        return this.acervo.stream()
                .filter(item -> item.getCodigo().equals(codigo))
                .findFirst();
    }

    // Novo metodo para emprestar um item
    public void emprestarItem(String codigo) {
        var itemOptional = buscarItemPorCodigo(codigo);
        if (itemOptional.isPresent()) {
            var item = itemOptional.get();
            if (item instanceof Emprestavel emprestavel) {
                emprestavel.emprestar();
            } else {
                System.out.println("O item '" + item.getTitulo() + "' nao pode ser emprestado.");
            }
        } else {
            System.out.println("Item com codigo '" + codigo + "' nao encontrado.");
        }
    }

    // Novo metodo para devolver um item
    public void devolverItem(String codigo) {
        var itemOptional = buscarItemPorCodigo(codigo);
        if (itemOptional.isPresent()) {
            var item = itemOptional.get();
            if (item instanceof Emprestavel emprestavel) {
                emprestavel.devolver();
            } else {
                System.out.println("O item '" + item.getTitulo() + "' nao pode ser devolvido.");
            }
        } else {
            System.out.println("Item com codigo '" + codigo + "' nao encontrado.");
        }
    }

    public void exibirDetalhesDosItens() {
        System.out.println("\n--- Detalhes dos Itens do Acervo ---");
        for (Catalogavel item : this.acervo) {
            if (item instanceof Livro livro) {
                System.out.printf("Livro: %s | Autor: %s | Status: %s%n",
                        livro.getTitulo(), livro.getAutor().nome(), livro.verificarStatus());
            } else if (item instanceof Revista revista) {
                System.out.printf("Revista: %s | Edicao: %d | Status de Emprestimo: %s%n",
                        revista.getTitulo(), revista.getNumeroEdicao(), (revista.estaEmprestado() ? "Emprestada" : "Disponivel"));
            } else {
                System.out.printf("Item: %s | Tipo Desconhecido%n", item.getTitulo());
            }
        }
    }

    public void listarItens() {
        if (this.acervo.isEmpty()) {
            System.out.println("A biblioteca esta vazia.");
        } else {
            System.out.println("--- Acervo da Biblioteca ---");
            for (Catalogavel item : this.acervo) {
                System.out.println("Codigo: " + item.getCodigo() + " | Titulo: " + item.getTitulo());
            }
        }
    }

    public void listarItensEmprestados() {
        System.out.println("--- Itens Emprestados ---");
        boolean algumEmprestado = false;
        for (Catalogavel item : this.acervo) {
            if (item instanceof Emprestavel) {
                Emprestavel emprestavel = (Emprestavel) item;
                if (emprestavel.estaEmprestado()) {
                    System.out.println("Codigo: " + item.getCodigo() + " | Titulo: " + item.getTitulo());
                    algumEmprestado = true;
                }
            }
        }
        if (!algumEmprestado) {
            System.out.println("Nenhum item emprestavel esta atualmente emprestado.");
        }
    }

    public List<Catalogavel> getAcervo() {
        return acervo;
    }

    public String listarItensString() {
        if (this.acervo.isEmpty()) {
            return "Nenhum item no acervo.";
        }
        return this.acervo.stream()
                .map(item -> "    - " + item.getTitulo() + " (" + item.getCodigo() + ")")
                .collect(Collectors.joining("\n"));
    }
}