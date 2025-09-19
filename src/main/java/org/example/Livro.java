package org.example;

//public class Livro implements Emprestavel, Catalogavel {
//    private String titulo;
//    private String codigo;
//    private int anoPublicacao;
//    private Autor autor;
//    private Categoria categoria;
//    private boolean emprestado = false;
//
//    public Livro(String titulo, String codigo, int anoPublicacao, Autor autor, Categoria categoria) {
//        this.titulo = titulo;
//        this.codigo = codigo;
//        this.anoPublicacao = anoPublicacao;
//        this.autor = autor;
//        this.categoria = categoria;
//    }
//
//    @Override
//    public void emprestar() {
//        if (!this.emprestado) {
//            this.emprestado = true;
//            System.out.println("O livro '" + this.titulo + "' foi emprestado.");
//        } else {
//            System.out.println("O livro '" + this.titulo + "' já está emprestado.");
//        }
//    }
//
//    @Override
//    public void devolver() {
//        if (this.emprestado) {
//            this.emprestado = false;
//            System.out.println("O livro '" + this.titulo + "' foi devolvido.");
//        } else {
//            System.out.println("O livro '" + this.titulo + "' já está disponível.");
//        }
//    }
//
//    @Override
//    public boolean estaEmprestado() {
//        return this.emprestado;
//    }
//
//    @Override
//    public String getCodigo() {
//        return this.codigo;
//    }
//
//    @Override
//    public String getTitulo() {
//        return this.titulo;
//    }
//
//    public int getAnoPublicacao() {
//        return anoPublicacao;
//    }
//
//    public Autor getAutor() {
//        return autor;
//    }
//
//    public Categoria getCategoria() {
//        return categoria;
//    }
//
//    public void exibirInformacoes() {
//        System.out.println("Código: " + this.codigo);
//        System.out.println("Título: " + this.titulo);
//        System.out.println("Autor: " + this.autor.getNome());
//        System.out.println("Ano de Publicação: " + this.anoPublicacao);
//        System.out.println("Categoria: " + this.categoria.getNome());
//    }
//}
import java.io.Serializable;

public class Livro implements Emprestavel, Catalogavel, Serializable {
    private String titulo;
    private String codigo;
    private int anoPublicacao;
    private Autor autor;
    private Categoria categoria;
    private EstadoEmprestimo estado = EstadoEmprestimo.DISPONIVEL;

    public Livro(String titulo, String codigo, int anoPublicacao, Autor autor, Categoria categoria) {
        this.titulo = titulo;
        this.codigo = codigo;
        this.anoPublicacao = anoPublicacao;
        this.autor = autor;
        this.categoria = categoria;
    }

    public String verificarStatus() {
        return switch (this.estado) {
            case EMPRESTADO -> "O livro esta atualmente emprestado.";
            case DISPONIVEL -> "O livro esta disponivel para emprestimo.";
            case EM_MANUTENCAO -> "O livro esta em manutencao e nao pode ser emprestado.";
        };
    }

    @Override
    public void emprestar() {
        switch (this.estado) {
            case DISPONIVEL -> {
                this.estado = EstadoEmprestimo.EMPRESTADO;
                System.out.println("O livro '" + this.titulo + "' foi emprestado.");
            }
            default -> System.out.println("O livro '" + this.titulo + "' nao pode ser emprestado. Status: " + this.verificarStatus());
        }
    }

    @Override
    public void devolver() {
        if (this.estado == EstadoEmprestimo.EMPRESTADO) {
            this.estado = EstadoEmprestimo.DISPONIVEL;
            System.out.println("O livro '" + this.titulo + "' foi devolvido.");
        } else {
            System.out.println("O livro '" + this.titulo + "' ja esta disponivel ou em outro estado.");
        }
    }

    @Override
    public boolean estaEmprestado() {
        return this.estado == EstadoEmprestimo.EMPRESTADO;
    }

    @Override
    public String getCodigo() {
        return this.codigo;
    }

    @Override
    public String getTitulo() {
        return this.titulo;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public Autor getAutor() {
        return autor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void exibirInformacoes() {
        System.out.println("Codigo: " + this.codigo);
        System.out.println("Titulo: " + this.titulo);
        System.out.println("Autor: " + this.autor.nome());
        System.out.println("Ano de Publicacao: " + this.anoPublicacao);
        System.out.println("Categoria: " + this.categoria.nome());
    }
}