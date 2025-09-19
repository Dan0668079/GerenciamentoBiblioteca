package org.example;

import java.io.Serializable;

public class LivroDigital extends Livro implements Serializable {
    private double tamanhoArquivoMB;

    public LivroDigital(String titulo, String codigo, int anoPublicacao, Autor autor, Categoria categoria, double tamanhoArquivoMB) {
        super(titulo, codigo, anoPublicacao, autor, categoria);
        this.tamanhoArquivoMB = tamanhoArquivoMB;
    }

    public double getTamanhoArquivoMB() {
        return tamanhoArquivoMB;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Tamanho do Arquivo: " + this.tamanhoArquivoMB + " MB");
    }
}