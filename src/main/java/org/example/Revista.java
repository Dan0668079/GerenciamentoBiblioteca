package org.example;

import java.io.Serializable;

public class Revista implements Emprestavel, Catalogavel, Serializable {
    private String titulo;
    private String codigo;
    private int numeroEdicao;
    private boolean emprestado = false;

    public Revista(String titulo, String codigo, int numeroEdicao) {
        this.titulo = titulo;
        this.codigo = codigo;
        this.numeroEdicao = numeroEdicao;
    }

    @Override
    public void emprestar() {
        if (!this.emprestado) {
            this.emprestado = true;
            System.out.println("A revista '" + this.titulo + "' (Edição " + this.numeroEdicao + ") foi emprestada.");
        } else {
            System.out.println("A revista '" + this.titulo + "' já está emprestada.");
        }
    }

    @Override
    public void devolver() {
        if (this.emprestado) {
            this.emprestado = false;
            System.out.println("A revista '" + this.titulo + "' foi devolvida.");
        } else {
            System.out.println("A revista '" + this.titulo + "' já está disponível.");
        }
    }

    @Override
    public boolean estaEmprestado() {
        return this.emprestado;
    }

    @Override
    public String getCodigo() {
        return this.codigo;
    }

    @Override
    public String getTitulo() {
        return this.titulo;
    }

    public int getNumeroEdicao() {
        return numeroEdicao;
    }

    public void exibirInformacoes() {
        System.out.println("Código: " + this.codigo);
        System.out.println("Título: " + this.titulo);
        System.out.println("Edição: " + this.numeroEdicao);
    }
}