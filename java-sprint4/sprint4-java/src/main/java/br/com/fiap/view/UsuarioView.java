package br.com.fiap.view;

import java.util.List;
import java.util.Scanner;

import br.com.fiap.controller.UsuarioController;
import br.com.fiap.model.vo.Usuario;

public class UsuarioView {
    private UsuarioController UsuarioController;
    private Scanner scanner;

    public UsuarioView() {
        this.UsuarioController = new UsuarioController();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("=== Menu de Usuarios ===");
            System.out.println("1. Adicionar Usuario");
            System.out.println("2. Atualizar Usuario");
            System.out.println("3. Deletar Usuario");
            System.out.println("4. Listar Usuarios");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarUsuario();
                    break;
                case 2:
                    atualizarUsuario();
                    break;
                case 3:
                    deletarUsuario();
                    break;
                case 4:
                    listarUsuarios();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void adicionarUsuario() {
    	System.out.print("CPF do Usuario: ");
        String cpf = scanner.nextLine();
        System.out.print("Nome do Usuario: ");
        String nome = scanner.nextLine();
        System.out.print("Email do Usuario: ");
        String email = scanner.nextLine();
        System.out.print("Telefone do Usuario: ");
        String telefone = scanner.nextLine();
        System.out.print("Senha do Usuario: ");
        String senha = scanner.nextLine();
        scanner.nextLine(); 

        Usuario Usuario = new Usuario(cpf, nome, senha, email, telefone);
        String resultado = UsuarioController.adicionarUsuario(Usuario);
        System.out.println(resultado);
    }

    private void atualizarUsuario() {
        System.out.print("CPF do Usuario a ser atualizado: ");
        String cpf = scanner.nextLine();
        System.out.print("Novo Nome do Usuario: ");
        String nome = scanner.nextLine();
        System.out.print("Novo Email do Usuario: ");
        String email = scanner.nextLine();
        System.out.print("Novo Telefone do Usuario: ");
        String telefone = scanner.nextLine();
        System.out.print("Nova Senha do Usuario: ");
        String senha = scanner.nextLine();
        scanner.nextLine();

        Usuario Usuario = new Usuario(cpf, nome, senha, email, telefone);
        String resultado = UsuarioController.atualizarUsuario(Usuario);
        System.out.println(resultado);
    }

    private void deletarUsuario() {
        System.out.print("CPF do Usuario a ser deletado: ");
        String cpf = scanner.nextLine();
        String resultado = UsuarioController.deletarUsuario(cpf);
        System.out.println(resultado);
    }

    private void listarUsuarios() {
        List<Usuario> Usuarios = UsuarioController.obterTodosUsuarios();
        if (Usuarios != null && !Usuarios.isEmpty()) {
            System.out.println("=== Lista de Usuarios ===");
            for (Usuario Usuario : Usuarios) {
                System.out.printf("CPF: %s | Nome: %s | Senha: %s | E-mail: %s | Telefone: %.2f%n",
                        Usuario.getCpfUsuario(), Usuario.getNomeUsuario(), Usuario.getSenha(),
                        Usuario.getEmail(), Usuario.getTelefone());
            }
        } else {
            System.out.println("Nenhum Usuario encontrado.");
        }
    }

    public static void main(String[] args) {
        UsuarioView view = new UsuarioView();
        view.exibirMenu();
    }
}
