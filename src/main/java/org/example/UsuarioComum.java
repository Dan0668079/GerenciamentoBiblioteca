package org.example;

public non-sealed class UsuarioComum extends Usuario {
    private int livrosEmprestados;

    public UsuarioComum(String nome, String id) {
        super(nome, id);
        this.livrosEmprestados = 0;
    }
    
    // Método para simular o empréstimo
    public void emprestarLivro() {
        this.livrosEmprestados++;
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