package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SistemaAutenticacaoTest {

    // --- Testes para Validação do Username (isValidUsername) ---

    @Test
    void testUsernamesValidos() {
        // Testa se nomes de usuário com 3 ou mais letras são aceitos.
        assertTrue(SistemaAutenticacao.isValidUsername("admin"));
        assertTrue(SistemaAutenticacao.isValidUsername("JoaoSilva"));
        assertTrue(SistemaAutenticacao.isValidUsername("ABC"));
        assertTrue(SistemaAutenticacao.isValidUsername("abc"));
    }

    @Test
    void testUsernamesInvalidos() {
        // Deve falhar por ser curto, conter números, caracteres especiais ou só números.
        assertFalse(SistemaAutenticacao.isValidUsername("ab"));    // Curto (min 3)
        assertFalse(SistemaAutenticacao.isValidUsername("j12"));   // Contém número
        assertFalse(SistemaAutenticacao.isValidUsername("J.S"));   // Caractere especial
        assertFalse(SistemaAutenticacao.isValidUsername(""));      // Vazio
        assertFalse(SistemaAutenticacao.isValidUsername("1234"));  // Só números
    }

    @Test
    void testUsernameNulo() {
        // Deve falhar se a entrada for nula.
        assertFalse(SistemaAutenticacao.isValidUsername(null));
    }

    // --- Testes para Validação do Password (isValidPassword) ---

    @Test
    void testSenhasValidas() {
        // Testa se senhas com 3 ou mais dígitos são aceitas.
        assertTrue(SistemaAutenticacao.isValidPassword("123"));
        assertTrue(SistemaAutenticacao.isValidPassword("987654"));
        assertTrue(SistemaAutenticacao.isValidPassword("000"));
    }

    @Test
    void testSenhasInvalidas() {
        // Deve falhar por ser curta, conter letras ou ser vazia.
        assertFalse(SistemaAutenticacao.isValidPassword("12"));    // Curta (min 3)
        assertFalse(SistemaAutenticacao.isValidPassword("1a2"));   // Mistura letras
        assertFalse(SistemaAutenticacao.isValidPassword("abc"));   // Só letras
        assertFalse(SistemaAutenticacao.isValidPassword(""));      // Vazia
        assertFalse(SistemaAutenticacao.isValidPassword("12 "));   // Contém espaço
    }

    @Test
    void testSenhaNula() {
        // Deve falhar se a entrada for nula.
        assertFalse(SistemaAutenticacao.isValidPassword(null));
    }

    // --- Testes para Autenticação (autenticarBibliotecario) ---

    @Test
    void testAutenticacaoBibliotecarioValida() {
        // Credenciais corretas (admin/123)
        assertTrue(SistemaAutenticacao.autenticarBibliotecario("admin", "123"));
    }

    @Test
    void testAutenticacaoBibliotecarioInvalida() {
        // Credenciais incorretas
        assertFalse(SistemaAutenticacao.autenticarBibliotecario("Admin", "123")); // Case sensitive
        assertFalse(SistemaAutenticacao.autenticarBibliotecario("admin", "1234")); // Senha errada
        assertFalse(SistemaAutenticacao.autenticarBibliotecario("joao", "123")); // Usuário errado
    }
}