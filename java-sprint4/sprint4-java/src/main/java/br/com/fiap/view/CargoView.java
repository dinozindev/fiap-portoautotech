package br.com.fiap.view;

import java.util.List;
import java.util.Scanner;

import br.com.fiap.controller.CargoController;
import br.com.fiap.model.vo.Cargo;

public class CargoView {
    private CargoController cargoController;
    private Scanner scanner;

    public CargoView() {
        this.cargoController = new CargoController();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("=== Menu de Cargos ===");
            System.out.println("1. Adicionar Cargo");
            System.out.println("2. Atualizar Cargo");
            System.out.println("3. Deletar Cargo");
            System.out.println("4. Listar Cargos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarCargo();
                    break;
                case 2:
                    atualizarCargo();
                    break;
                case 3:
                    deletarCargo();
                    break;
                case 4:
                    listarCargos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void adicionarCargo() {
    	System.out.print("ID do Cargo: ");
        String id = scanner.nextLine();
        System.out.print("Nome do Cargo: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição do Cargo: ");
        String descricao = scanner.nextLine();
        System.out.print("Área do Cargo: ");
        String area = scanner.nextLine();
        System.out.print("Salário do Cargo: ");
        double salario = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer

        Cargo cargo = new Cargo(id, nome, descricao, area, salario);
        String resultado = cargoController.adicionarCargo(cargo);
        System.out.println(resultado);
    }

    private void atualizarCargo() {
        System.out.print("ID do Cargo a ser atualizado: ");
        String id = scanner.nextLine();
        System.out.print("Novo Nome do Cargo: ");
        String nome = scanner.nextLine();
        System.out.print("Nova Descrição do Cargo: ");
        String descricao = scanner.nextLine();
        System.out.print("Nova Área do Cargo: ");
        String area = scanner.nextLine();
        System.out.print("Novo Salário do Cargo: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();

        Cargo cargo = new Cargo(id, nome, descricao, area, salario);
        String resultado = cargoController.atualizarCargo(cargo);
        System.out.println(resultado);
    }

    private void deletarCargo() {
        System.out.print("ID do Cargo a ser deletado: ");
        String id = scanner.nextLine();
        String resultado = cargoController.deletarCargo(id);
        System.out.println(resultado);
    }

    private void listarCargos() {
        List<Cargo> cargos = cargoController.obterTodosCargos();
        if (cargos != null && !cargos.isEmpty()) {
            System.out.println("=== Lista de Cargos ===");
            for (Cargo cargo : cargos) {
                System.out.printf("ID: %s | Nome: %s | Descrição: %s | Área: %s | Salário: %.2f%n",
                        cargo.getIdCargo(), cargo.getNomeCargo(), cargo.getDescricaoCargo(),
                        cargo.getAreaCargo(), cargo.getSalarioCargo());
            }
        } else {
            System.out.println("Nenhum cargo encontrado.");
        }
    }

    public static void main(String[] args) {
        CargoView view = new CargoView();
        view.exibirMenu();
    }
}
