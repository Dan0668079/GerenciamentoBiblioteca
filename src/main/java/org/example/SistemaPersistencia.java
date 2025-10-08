// SistemaPersistencia.java
package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SistemaPersistencia {
    private static final String ARQUIVO_DADOS = "dados_biblioteca.ser";
    // Nota: Se você usou o caminho absoluto, mantenha:
    // private static final String ARQUIVO_DADOS = System.getProperty("user.home") + "/dados_biblioteca.ser";


    public static void salvarBiblioteca(Biblioteca biblioteca) {
        try (var fos = new FileOutputStream(ARQUIVO_DADOS);
             var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(biblioteca);
            System.out.println("Dados da biblioteca salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public static Biblioteca carregarBiblioteca() {
        try (var fis = new FileInputStream(ARQUIVO_DADOS);
             var ois = new ObjectInputStream(fis)) {
            Biblioteca biblioteca = (Biblioteca) ois.readObject();
            System.out.println("Dados da biblioteca carregados com sucesso!");
            return biblioteca;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nenhum dado salvo encontrado. Criando uma nova biblioteca.");
            return new Biblioteca();
        }
    }
}