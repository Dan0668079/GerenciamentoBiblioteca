package org.example;

import java.io.Serializable;

public non-sealed class UsuarioComum extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L; // Adicionado para estabilidade
    private static final int LIMITE_EMPRESTIMOS = 3;
    private int livrosEmprestados;

    public UsuarioComum(String nome, String id) {
        super(nome, id);
        this.livrosEmprestados = 0;
    }

    // Novo método para verificar se o usuário pode pegar mais um item emprestado
    public boolean podeEmprestar() {
        return this.livrosEmprestados < LIMITE_EMPRESTIMOS;
    }

    // Método para incrementar o contador de empréstimos
    public void adicionarEmprestimo() {
        this.livrosEmprestados++;
    }

    // Método para decrementar o contador de empréstimos
    public void removerEmprestimo() {
        if (this.livrosEmprestados > 0) {
            this.livrosEmprestados--;
        }
    }

    @Override
    public void exibirDetalhes() {
        System.out.printf("""
            --- Detalhes do Usuário Comum ---
            Nome: %s
            ID: %s
            Livros Emprestados: %d
            """, getNome(), getId(), livrosEmprestados);
    }
}