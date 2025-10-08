package org.example;

import java.util.Scanner;

public class MenuManager {

    public static void iniciar(Biblioteca biblioteca, Scanner scanner) {
        
        System.out.println("Bem-vindo(a) ao Sistema de Gerenciamento de Biblioteca!");

        boolean loginValido = false;
        boolean ehBibliotecario = false;
        UsuarioComum usuarioLogado = null;
        
        // Loop de Autenticação
        while (!loginValido) {
            System.out.print("Digite seu nome de usuario: ");
            String nomeUsuario = scanner.nextLine();
            System.out.print("Digite sua senha: ");
            String senha = scanner.nextLine();

            // Uso do SistemaAutenticacao para validação de formato
            if (!SistemaAutenticacao.isValidUsername(nomeUsuario)) {
                System.out.println("Nome de usuario invalido. Deve ter no minimo 3 caracteres e conter apenas letras.");
            } else if (!SistemaAutenticacao.isValidPassword(senha)) {
                System.out.println("Senha invalida. Deve ter no minimo 3 caracteres e conter apenas numeros.");
            } else {
                // Uso do SistemaAutenticacao para autenticação
                ehBibliotecario = SistemaAutenticacao.autenticarBibliotecario(nomeUsuario, senha);
                loginValido = true;
                
                if (!ehBibliotecario) {
                    usuarioLogado = new UsuarioComum(nomeUsuario, "id-gerado-automaticamente");
                }
            }
        }

        System.out.printf("Logado como: %s%n", ehBibliotecario ? "Bibliotecario" : "Usuario Comum");

        if (ehBibliotecario) {
            menuBibliotecario(biblioteca, scanner);
        } else {
            menuUsuarioComum(biblioteca, scanner, usuarioLogado);
        }
    }
    
    public static void menuBibliotecario(Biblioteca biblioteca, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- Menu do Bibliotecario ---");
            System.out.println("1. Adicionar novo item");
            System.out.println("2. Remover item");
            System.out.println("3. Exibir detalhes de todos os itens");
            System.out.println("4. Listar itens emprestados");
            System.out.println("5. Sair e Salvar");
            System.out.print("Escolha uma opcao: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarItem(biblioteca, scanner);
                    break;
                case 2:
                    System.out.print("Digite o codigo do item que deseja remover: ");
                    String codigoRemover = scanner.nextLine();
                    biblioteca.removerItem(codigoRemover);
                    break;
                case 3:
                    biblioteca.exibirDetalhesDosItens();
                    break;
                case 4:
                    biblioteca.listarItensEmprestados();
                    break;
                case 5:
                    System.out.println("Saindo e salvando a biblioteca. Ate mais!");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    public static void menuUsuarioComum(Biblioteca biblioteca, Scanner scanner, UsuarioComum usuario) {
        int opcao;
        do {
            System.out.println("\n--- Menu do Usuario Comum ---");
            System.out.println("1. Exibir detalhes de todos os itens");
            System.out.println("2. Buscar item por codigo");
            System.out.println("3. Emprestar item");
            System.out.println("4. Devolver item");
            System.out.println("5. Visualizar itens emprestados");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    biblioteca.exibirDetalhesDosItens();
                    break;
                case 2:
                    System.out.print("Digite o codigo do item que deseja buscar: ");
                    String codigoBusca = scanner.nextLine();
                    var itemEncontrado = biblioteca.buscarItemPorCodigo(codigoBusca);
                    itemEncontrado.ifPresentOrElse(
                            item -> System.out.println("Item encontrado: " + item.getTitulo()),
                            () -> System.out.println("Item nao encontrado.")
                    );
                    break;
                case 3:
                    System.out.print("Digite o codigo do item que deseja emprestar: ");
                    String codigoEmprestimo = scanner.nextLine();
                    biblioteca.emprestarItem(codigoEmprestimo, usuario);
                    break;
                case 4:
                    System.out.print("Digite o codigo do item que deseja devolver: ");
                    String codigoDevolucao = scanner.nextLine();
                    biblioteca.devolverItem(codigoDevolucao, usuario);
                    break;
                case 5:
                    biblioteca.listarItensEmprestados();
                    break;
                case 6:
                    System.out.println("Saindo do programa. Ate mais!");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (opcao != 6);
    }

    public static void adicionarItem(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("Que tipo de item deseja adicionar?");
        System.out.println("1. Livro Fisico");
        System.out.println("2. Livro Digital");
        System.out.println("3. Revista");
        System.out.print("Escolha uma opcao: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o codigo do item: ");
        String codigo = scanner.nextLine();
        System.out.print("Digite o titulo: ");
        String titulo = scanner.nextLine();

        switch (tipo) {
            case 1:
                System.out.print("Digite o ano de publicacao: ");
                int ano = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Digite o nome do autor: ");
                String autorNome = scanner.nextLine();
                System.out.print("Digite a nacionalidade do autor: ");
                String autorNacionalidade = scanner.nextLine();
                System.out.print("Digite a categoria: ");
                String categoriaNome = scanner.nextLine();
                System.out.print("Digite o numero de paginas: ");
                int paginas = scanner.nextInt();
                scanner.nextLine();

                var autor = new Autor(autorNome, autorNacionalidade);
                var categoria = new Categoria(categoriaNome);
                var livro = new LivroFisico(titulo, codigo, ano, autor, categoria, paginas);
                biblioteca.adicionarItem(livro);
                break;
            case 2:
                System.out.print("Digite o ano de publicacao: ");
                int anoLivroDigital = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Digite o nome do autor: ");
                String autorNomeLivroDigital = scanner.nextLine();
                System.out.print("Digite a nacionalidade do autor: ");
                String autorNacionalidadeLivroDigital = scanner.nextLine();
                System.out.print("Digite a categoria: ");
                String categoriaNomeLivroDigital = scanner.nextLine();
                System.out.print("Digite o tamanho do arquivo em MB: ");
                double tamanhoArquivo = scanner.nextDouble();
                scanner.nextLine();

                var autorDigital = new Autor(autorNomeLivroDigital, autorNacionalidadeLivroDigital);
                var categoriaDigital = new Categoria(categoriaNomeLivroDigital);
                var livroDigital = new LivroDigital(titulo, codigo, anoLivroDigital, autorDigital, categoriaDigital, tamanhoArquivo);
                biblioteca.adicionarItem(livroDigital);
                break;
            case 3:
                System.out.print("Digite o numero da edicao: ");
                int edicao = scanner.nextInt();
                scanner.nextLine();
                var revista = new Revista(titulo, codigo, edicao);
                biblioteca.adicionarItem(revista);
                break;
            default:
                System.out.println("Tipo de item invalido.");
        }
    }
}