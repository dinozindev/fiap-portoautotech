package br.com.fiap.view;

import java.util.List;
import java.util.Scanner;

import br.com.fiap.controller.DiagnosticoController;
import br.com.fiap.controller.ServicoController;
import br.com.fiap.controller.VeiculoController;
import br.com.fiap.model.vo.Diagnostico;
import br.com.fiap.model.vo.Servico;
import br.com.fiap.model.vo.Veiculo;

public class DiagnosticoView {
    private DiagnosticoController diagnosticoController;
    private	VeiculoController veiculoController;
    private ServicoController servicoController;
    private Scanner scanner;

    public DiagnosticoView() {
        this.diagnosticoController = new DiagnosticoController();
        this.veiculoController = new VeiculoController();
        this.servicoController = new ServicoController();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("=== Menu de Diagnóstico ===");
            System.out.println("1. Adicionar Diagnóstico");
            System.out.println("2. Atualizar Diagnóstico");
            System.out.println("3. Deletar Diagnóstico");
            System.out.println("4. Listar Diagnósticos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarDiagnostico();
                    break;
                case 2:
                    atualizarDiagnostico();
                    break;
                case 3:
                    deletarDiagnostico();
                    break;
                case 4:
                    listarDiagnosticos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void adicionarDiagnostico() {
        System.out.print("Descrição do Diagnóstico: ");
        String descricao = scanner.nextLine();
        System.out.print("Categoria do Problema: ");
        String categoria = scanner.nextLine();
        System.out.print("Solução do Problema: ");
        String solucao = scanner.nextLine();
        System.out.print("Status do Diagnóstico: ");
        String status = scanner.nextLine();
        System.out.print("Veículo do Diagnósticoa: ");
        String veiculo_placa = scanner.nextLine();
        Veiculo veiculo = veiculoController.obterVeiculo(veiculo_placa);
        System.out.print("Serviço do Diagnóstico: ");
        String servico_id = scanner.nextLine();
        Servico servico = servicoController.obterServico(servico_id);

        Diagnostico diagnostico = new Diagnostico(veiculo, descricao, servico, categoria, status, solucao);
        String resultado = diagnosticoController.adicionarDiagnostico(diagnostico);
        System.out.println(resultado);
    }

    private void atualizarDiagnostico() {
        System.out.print("ID do Diagnóstico a ser atualizado: ");
        String idDiagnostico = scanner.nextLine();
        System.out.print("Novo Status: ");
        String status = scanner.nextLine();

        String resultado = diagnosticoController.atualizarDiagnostico(idDiagnostico, status);
        System.out.println(resultado);
    }

    private void deletarDiagnostico() {
        System.out.print("ID do Diagnóstico a ser deletado: ");
        String idDiagnostico = scanner.nextLine();
        String resultado = diagnosticoController.deletarDiagnostico(idDiagnostico);
        System.out.println(resultado);
    }

    private void listarDiagnosticos() {
        List<Diagnostico> diagnosticos = diagnosticoController.obterTodosDiagnosticos();
        if (diagnosticos != null && !diagnosticos.isEmpty()) {
            System.out.println("=== Lista de Diagnósticos ===");
            for (Diagnostico diagnostico : diagnosticos) {
                System.out.printf("ID: %s | Descrição: %s | Status: %s%n",
                        diagnostico.getIdDiagnostico(), diagnostico.getDescricaoSintomas(),
                        diagnostico.getStatus());
            }
        } else {
            System.out.println("Nenhum Diagnóstico encontrado.");
        }
    }

    public static void main(String[] args) {
        DiagnosticoView view = new DiagnosticoView();
        view.exibirMenu();
    }
}

