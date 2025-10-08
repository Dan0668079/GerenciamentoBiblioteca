// SistemaAutenticacao.java
package org.example;

public class SistemaAutenticacao {
    private static final String USUARIO_BIBLIOTECARIO = "admin";
    private static final String SENHA_BIBLIOTECARIO = "123";

    // Métodos de validação movidos de Main.java
    public static boolean isValidUsername(String username) {
        return username != null && username.length() >= 3 && username.matches("[a-zA-Z]+");
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 3 && password.matches("\\d+");
    }

    // Novo método para encapsular a lógica de verificação de credenciais
    public static boolean autenticarBibliotecario(String nomeUsuario, String senha) {
        return nomeUsuario.equals(USUARIO_BIBLIOTECARIO) && senha.equals(SENHA_BIBLIOTECARIO);
    }
}