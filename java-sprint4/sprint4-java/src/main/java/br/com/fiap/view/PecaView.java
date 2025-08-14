package br.com.fiap.view;

import java.util.List;
import java.util.Scanner;

import br.com.fiap.controller.PecaController;
import br.com.fiap.model.vo.Peca;

public class PecaView {
    private PecaController pecaController;
    private Scanner scanner;

    public PecaView() {
        this.pecaController = new PecaController();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("=== Menu de Peças ===");
            System.out.println("1. Adicionar Peça");
            System.out.println("2. Atualizar Peça");
            System.out.println("3. Deletar Peça");
            System.out.println("4. Listar Peças");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarPeca();
                    break;
                case 2:
                    atualizarPeca();
                    break;
                case 3:
                    deletarPeca();
                    break;
                case 4:
                    listarPecas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void adicionarPeca() {
        System.out.print("ID da Peça: ");
        String idPeca = scanner.nextLine();
        System.out.print("Disponibilidade da Peça: ");
        int disponibilidadePeca = scanner.nextInt();
        System.out.print("Nome da Peça: ");
        String nomePeca = scanner.nextLine();
        System.out.print("Preço da Peça: ");
        double precoPeca = scanner.nextDouble();
        scanner.nextLine();

        Peca peca = new Peca(idPeca, disponibilidadePeca, nomePeca, precoPeca);
        String resultado = pecaController.adicionarPeca(peca);
        System.out.println(resultado);
    }

    private void atualizarPeca() {
        System.out.print("ID da Peça a ser atualizada: ");
        String idPeca = scanner.nextLine();
        System.out.print("Nova Disponibilidade da Peça: ");
        int disponibilidadePeca = scanner.nextInt();
        System.out.print("Novo Nome da Peça: ");
        String nomePeca = scanner.nextLine();
        System.out.print("Novo Preço da Peça: ");
        double precoPeca = scanner.nextDouble();
        scanner.nextLine();

        Peca peca = new Peca(idPeca, disponibilidadePeca, nomePeca, precoPeca);
        String resultado = pecaController.atualizarPeca(peca);
        System.out.println(resultado);
    }

    private void deletarPeca() {
        System.out.print("ID da Peça a ser deletada: ");
        String idPeca = scanner.nextLine();
        String resultado = pecaController.deletarPeca(idPeca);
        System.out.println(resultado);
    }

    private void listarPecas() {
        List<Peca> pecas = pecaController.obterTodosPecas();
        if (pecas != null && !pecas.isEmpty()) {
            System.out.println("=== Lista de Peças ===");
            for (Peca peca : pecas) {
                System.out.println(peca);
            }
        } else {
            System.out.println("Nenhuma Peça encontrada.");
        }
    }

    public static void main(String[] args) {
        PecaView view = new PecaView();
        view.exibirMenu();
    }
}
