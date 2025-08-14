package br.com.fiap.view;

import java.util.List;
import java.util.Scanner;

import br.com.fiap.controller.CargoController;
import br.com.fiap.controller.CentroController;
import br.com.fiap.controller.FuncionarioController;
import br.com.fiap.model.vo.Cargo;
import br.com.fiap.model.vo.CentroAutomotivo;
import br.com.fiap.model.vo.Funcionario;

public class FuncionarioView {
    private FuncionarioController funcionarioController;
    private CentroController centroController;
    private CargoController cargoController;
    private Scanner scanner;

    public FuncionarioView() {
        this.funcionarioController = new FuncionarioController();
        this.centroController = new CentroController();
        this.cargoController = new CargoController();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("=== Menu de Funcionários ===");
            System.out.println("1. Adicionar Funcionário");
            System.out.println("2. Atualizar Funcionário");
            System.out.println("3. Deletar Funcionário");
            System.out.println("4. Listar Funcionários");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarFuncionario();
                    break;
                case 2:
                    atualizarFuncionario();
                    break;
                case 3:
                    deletarFuncionario();
                    break;
                case 4:
                    listarFuncionarios();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    private void adicionarFuncionario() {
        System.out.print("Matrícula do Funcionário: ");
        String matricula = scanner.nextLine();
        System.out.print("Nome do Funcionário: ");
        String nome = scanner.nextLine();
        System.out.print("Cargo do Funcionário: ");
        String cargo_id = scanner.nextLine();
        Cargo cargo = cargoController.obterCargo(cargo_id);
        System.out.print("Centro Automotivo do Funcionário: ");
        String centroAutomotivo = scanner.nextLine();
        CentroAutomotivo centro = centroController.obterCentro(centroAutomotivo);
        System.out.print("Disponibilidade (S/N): ");
        String disponibilidade = scanner.nextLine();
        System.out.print("Horário de Trabalho: ");
        String horario = scanner.nextLine();

        Funcionario funcionario = new Funcionario(matricula, nome, cargo, centro, disponibilidade, horario);
        String resultado = funcionarioController.adicionarFuncionario(funcionario);
        System.out.println(resultado);
    }

    private void atualizarFuncionario() {
        System.out.print("Matrícula do Funcionário a ser atualizado: ");
        String matricula = scanner.nextLine();
        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo Cargo: ");
        String cargo_id = scanner.nextLine();
        Cargo cargo = cargoController.obterCargo(cargo_id);
        System.out.print("Novo Centro Automotivo: ");
        String centroAutomotivo = scanner.nextLine();
        CentroAutomotivo centro = centroController.obterCentro(centroAutomotivo);
        System.out.print("Disponibilidade (S/N): ");
        String disponibilidade = scanner.nextLine();
        System.out.print("Novo Horário de Trabalho: ");
        String horario = scanner.nextLine();

        Funcionario funcionario = new Funcionario(matricula, nome, cargo, centro, disponibilidade, horario);
        String resultado = funcionarioController.atualizarFuncionario(funcionario);
        System.out.println(resultado);
    }

    private void deletarFuncionario() {
        System.out.print("Matrícula do Funcionário a ser deletado: ");
        String matricula = scanner.nextLine();
        String resultado = funcionarioController.deletarFuncionario(matricula);
        System.out.println(resultado);
    }

    private void listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioController.obterTodosFuncionarios();
        if (funcionarios != null && !funcionarios.isEmpty()) {
            System.out.println("=== Lista de Funcionários ===");
            for (Funcionario funcionario : funcionarios) {
                System.out.printf("Matrícula: %s | Nome: %s | Cargo: %s | Centro Automotivo: %s | Disponibilidade: %s | Horário de Trabalho: %s%n",
                        funcionario.getMatriculaFuncionario(), funcionario.getNomeFuncionario(),
                        funcionario.getCargo(), funcionario.getCentroAutomotivo(), funcionario.getDisponibilidade(),
                        funcionario.getHorarioTrabalho());
            }
        } else {
            System.out.println("Nenhum Funcionário encontrado.");
        }
    }

    public static void main(String[] args) {
        FuncionarioView view = new FuncionarioView();
        view.exibirMenu();
    }   

}


