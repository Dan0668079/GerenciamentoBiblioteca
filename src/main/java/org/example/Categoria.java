package org.example;

import java.io.Serializable;

//public class Categoria {
//    private String nome;
//
//    public Categoria(String nome) {
//        this.nome = nome;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//}
public record Categoria(String nome) implements Serializable { }