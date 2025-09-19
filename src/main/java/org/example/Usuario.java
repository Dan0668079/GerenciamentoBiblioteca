package org.example;

public abstract sealed class Usuario permits Bibliotecario, UsuarioComum {
    private String nome;
    private String id;

    public Usuario(String nome, String id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }

    // Método abstrato para demonstrar polimorfismo
    public abstract void exibirDetalhes();
}