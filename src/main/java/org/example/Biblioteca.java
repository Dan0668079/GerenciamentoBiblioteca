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
    private static final long serialVersionUID = 1L; // Adicionado para estabilidade
    private List<Catalogavel> acervo;

    public Biblioteca() {
        this.acervo = new ArrayList<>();
    }

//    public void adicionarItem(Catalogavel item) {
//        // Melhoria futura: Adicionar verificação se o código já existe aqui.
//        this.acervo.add(item);
//        System.out.println("Item com codigo '" + item.getCodigo() + "' adicionado ao acervo.");
//    }

    public void adicionarItem(Catalogavel item) {
        if (buscarItemPorCodigo(item.getCodigo()).isPresent()) {
            // Logica de rejeição
            System.out.println("ERRO: Item com codigo '" + item.getCodigo() + "' ja existe no acervo. Operacao cancelada.");
            return;
        }
        this.acervo.add(item);
        System.out.println("Item com codigo '" + item.getCodigo() + "' adicionado ao acervo.");
    }

    public void removerItem(String codigo) {
        boolean removido = this.acervo.removeIf(item -> item.getCodigo().equals(codigo));
        if (removido) {
            System.out.println("Item com codigo '" + codigo + "' removido com sucesso.");
        } else {
            // Mensagem de erro para o cenário "Remover item, deve exibir uma mensagem dizendo que não existe"
            System.out.println("Item com codigo '" + codigo + "' nao encontrado.");
        }
    }

    public Optional<Catalogavel> buscarItemPorCodigo(String codigo) {
        return this.acervo.stream()
                .filter(item -> item.getCodigo().equals(codigo))
                .findFirst();
    }

    // Método atualizado para emprestar um item com a verificação do limite do usuário
    public void emprestarItem(String codigo, UsuarioComum usuario) {
        // Validação do limite de empréstimos
        if (!usuario.podeEmprestar()) {
            System.out.println("Voce ja atingiu o limite de 3 emprestimos. Por favor, devolva um item antes de pegar outro.");
            return;
        }

        var itemOptional = buscarItemPorCodigo(codigo);
        if (itemOptional.isPresent()) {
            var item = itemOptional.get();
            if (item instanceof Emprestavel emprestavel) {
                emprestavel.emprestar();
                if (emprestavel.estaEmprestado()) {
                    // Se o empréstimo foi realizado (não estava já emprestado), atualiza o contador do usuário
                    usuario.adicionarEmprestimo();
                }
            } else {
                System.out.println("O item '" + item.getTitulo() + "' nao pode ser emprestado.");
            }
        } else {
            // Mensagem de erro para o cenário "Emprestar item, caso não tenha exiba uma mensagem..."
            System.out.println("Item com codigo '" + codigo + "' nao encontrado.");
        }
    }

    // Método atualizado para devolver um item, informando o usuário
    public void devolverItem(String codigo, UsuarioComum usuario) {
        var itemOptional = buscarItemPorCodigo(codigo);
        if (itemOptional.isPresent()) {
            var item = itemOptional.get();
            if (item instanceof Emprestavel emprestavel) {
                if (emprestavel.estaEmprestado()) {
                    emprestavel.devolver();
                    // Se a devolução foi realizada, atualiza o contador do usuário
                    usuario.removerEmprestimo();
                } else {
                    System.out.println("O item '" + item.getTitulo() + "' ja esta disponivel ou nao pode ser devolvido.");
                }
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