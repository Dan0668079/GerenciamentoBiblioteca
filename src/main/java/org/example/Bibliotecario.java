package org.example;

public final class Bibliotecario extends Usuario {
    private String departamento;

    public Bibliotecario(String nome, String id, String departamento) {
        super(nome, id);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    @Override
    public void exibirDetalhes() {
        System.out.printf("""
            --- Detalhes do Bibliotecário ---
            Nome: %s
            ID: %s
            Departamento: %s
            """, getNome(), getId(), departamento);
    }
}