package org.example;

//public class Autor {
//    private String nome;
//    private String nacionalidade;
//
//    public Autor(String nome, String nacionalidade) {
//        this.nome = nome;
//        this.nacionalidade = nacionalidade;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public String getNacionalidade() {
//        return nacionalidade;
//    }
//}

import java.io.Serializable;

public record Autor(String nome, String nacionalidade) implements Serializable { }