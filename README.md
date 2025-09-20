# Sistema de Gerenciamento de Biblioteca

Este é um projeto de exemplo em Java que simula um sistema de gerenciamento de biblioteca. Ele foi desenvolvido com o objetivo de demonstrar diversos conceitos da programação orientada a objetos (POO), como:

- **Encapsulamento** e **Composição**
- **Herança** e **Polimorfismo**
- Uso de **Interfaces**
- Classes **Seladas (`sealed classes`)**
- Tipos de Dados Imutáveis (`records`)
- **`Switch Expressions`** e **`Pattern Matching`**
- Persistência de Dados via Serialização
- Interação com o usuário via console

## Como Compilar e Executar o Projeto

O projeto utiliza o **Gradle** para gerenciar as dependências e a compilação. Para rodar a aplicação, siga os passos abaixo no terminal:

1.  Clone o repositório ou baixe o projeto:
    `git clone https://github.com/SeuUsuario/GerenciamentoBiblioteca.git`

2.  Navegue até o diretório do projeto:
    `cd GerenciamentoBiblioteca`

3.  Execute a aplicação usando o Gradle:
    `./gradlew run`

## Funcionalidades do Sistema

O sistema oferece diferentes funcionalidades baseadas no tipo de usuário que faz o login.

### Login de Usuário

Ao iniciar o programa, você será solicitado a inserir um nome de usuário e senha.

- **Para logar como Bibliotecário:**
    - **Usuário:** `admin`
    - **Senha:** `123`

- **Para logar como Usuário Comum:**
    - Insira qualquer outro nome de usuário e senha (o sistema não tem um cadastro formal, então qualquer combinação diferente de `admin` e `123` funcionará).

### Menu do Bibliotecário

- Adicionar novo item (Livro Físico, Livro Digital, Revista)
- Remover item
- Exibir detalhes de todos os itens
- Listar itens emprestados

### Menu do Usuário Comum

- Exibir detalhes de todos os itens
- Buscar item por código
- Emprestar item
- Devolver item
- Visualizar itens emprestados

## Persistência de Dados

As informações da biblioteca são salvas automaticamente em um arquivo chamado `dados_biblioteca.ser` ao sair do programa. Isso garante que os dados persistam entre as execuções. Ao iniciar o programa novamente, os dados serão carregados do arquivo.