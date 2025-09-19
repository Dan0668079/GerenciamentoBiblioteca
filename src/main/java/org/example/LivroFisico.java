package org.example;

import java.io.Serializable;

public class LivroFisico extends Livro implements Serializable {
    private int numeroPaginas;

    public LivroFisico(String titulo, String codigo, int anoPublicacao, Autor autor, Categoria categoria, int numeroPaginas) {
        super(titulo, codigo, anoPublicacao, autor, categoria);
        this.numeroPaginas = numeroPaginas;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Número de Páginas: " + this.numeroPaginas);
    }
}