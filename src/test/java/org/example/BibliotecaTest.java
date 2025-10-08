package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testes de Regras de Negócio da Biblioteca")
class BibliotecaTest {

    private Biblioteca biblioteca;
    private Autor autorTeste;
    private Categoria categoriaTeste;
    private UsuarioComum usuarioComum; // Novo objeto de usuário para o teste

    // O método @BeforeEach é executado antes de cada teste
    @BeforeEach
    void setUp() {
        // Inicializa uma nova biblioteca vazia para cada teste
        biblioteca = new Biblioteca();
        autorTeste = new Autor("Autor Teste", "Ficticio");
        categoriaTeste = new Categoria("Teste");
        // Inicializa um novo usuário comum (contador de empréstimos = 0)
        usuarioComum = new UsuarioComum("User Teste", "U100");

        // Adiciona livros básicos para os testes de empréstimo/limite
        biblioteca.adicionarItem(new LivroFisico("Livro 1", "L001", 2000, autorTeste, categoriaTeste, 100));
        biblioteca.adicionarItem(new LivroFisico("Livro 2", "L002", 2001, autorTeste, categoriaTeste, 100));
        biblioteca.adicionarItem(new LivroFisico("Livro 3", "L003", 2002, autorTeste, categoriaTeste, 100));
        biblioteca.adicionarItem(new LivroFisico("Livro 4", "L004", 2003, autorTeste, categoriaTeste, 100));
    }

    // --- Cenário de Código Único (Mantido da etapa anterior) ---

    @Test
    @DisplayName("Deve impedir a adição de um item com código já existente")
    void testAdicionarItemComCodigoDuplicado() {
        // O item "L004" já existe na configuração inicial (setUp)
        assertEquals(4, biblioteca.getAcervo().size(), "O acervo deve ter 4 itens iniciais.");

        // Cria uma revista com o MESMO CÓDIGO "L004"
        Revista itemDuplicado = new Revista("Revista Duplicada", "L004", 5);

        // Tenta adicionar o item duplicado
        biblioteca.adicionarItem(itemDuplicado);

        // O acervo deve continuar com 4 itens
        assertEquals(4, biblioteca.getAcervo().size(), "O acervo NÃO deve ter aumentado de tamanho após a tentativa de duplicidade.");

        // Verifica se o item no acervo continua sendo o LivroFisico original
        assertTrue(biblioteca.buscarItemPorCodigo("L004").get() instanceof LivroFisico,
                "O item no acervo deve ser o LivroFisico original.");
    }

    // --- NOVO CENÁRIO: Limite de Empréstimos ---

    @Test
    @DisplayName("Deve permitir até 3 empréstimos e bloquear o quarto item")
    void testLimiteMaximoDeEmprestimos() {
        // --- 1. PREPARAÇÃO: Emprestar 3 itens (o limite) ---

        // Empresta o 1º item
        biblioteca.emprestarItem("L001", usuarioComum);
        // Empresta o 2º item
        biblioteca.emprestarItem("L002", usuarioComum);
        // Empresta o 3º item (Limite alcançado)
        biblioteca.emprestarItem("L003", usuarioComum);

        // Verifica se os 3 primeiros empréstimos foram bem-sucedidos
        assertTrue(biblioteca.buscarItemPorCodigo("L003").get() instanceof Emprestavel emprestavel3 && emprestavel3.estaEmprestado(),
                "O 3º item deve estar emprestado.");

        // Verifica o estado do usuário
        assertFalse(usuarioComum.podeEmprestar(), "O usuário não deve mais poder emprestar (3/3).");


        // --- 2. AÇÃO: Tentar emprestar o 4º item ---

        // Tenta emprestar o 4º item ("L004")
        biblioteca.emprestarItem("L004", usuarioComum);

        // --- 3. VERIFICAÇÃO: Garantir que o 4º item não foi emprestado ---

        // O 4º item deve permanecer DISPONÍVEL
        assertTrue(biblioteca.buscarItemPorCodigo("L004").get() instanceof Emprestavel emprestavel4 && !emprestavel4.estaEmprestado(),
                "O 4º item (L004) deve permanecer disponível, pois o limite foi atingido.");

        // O acervo deve ter apenas 3 itens emprestados no total
        assertEquals(3, biblioteca.getAcervo().stream()
                        .filter(item -> item instanceof Emprestavel e && e.estaEmprestado())
                        .count(),
                "A contagem total de itens emprestados no acervo deve ser 3.");
    }
}