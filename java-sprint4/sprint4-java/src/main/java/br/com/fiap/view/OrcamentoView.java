package br.com.fiap.view;

import java.util.List;
import java.util.Scanner;

import br.com.fiap.controller.OrcamentoController;
import br.com.fiap.model.vo.Orcamento;

public class OrcamentoView {
    private OrcamentoController orcamentoController;
    private Scanner scanner;

    public OrcamentoView() {
        this.orcamentoController = new OrcamentoController();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("=== Menu de Orçamentos ===");
            System.out.println("1. Adicionar Orçamento");
            System.out.println("2. Atualizar Orçamento");
            System.out.println("3. Deletar Orçamento");
            System.out.println("4. Listar Orçamentos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarOrcamento();
                    break;
                case 2:
                    atualizarOrcamento();
                    break;
                case 3:
                    deletarOrcamento();
                    break;
                case 4:
                    listarOrcamentos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void adicionarOrcamento() {
        System.out.print("ID do Diagnóstico: ");
        String idDiagnostico = scanner.nextLine();
        System.out.print("ID do Orçamento: ");
        String idOrcamento = scanner.nextLine();
        Orcamento orcamento = orcamentoController.obterOrcamento(idOrcamento);
        String resultado = orcamentoController.adicionarOrcamento(idDiagnostico, orcamento);
        System.out.println(resultado);
    }

    private void atualizarOrcamento() {
        System.out.print("ID do Orçamento a ser atualizado: ");
        String idOrcamento = scanner.nextLine();
        System.out.print("Novo Status do Orçamento: ");
        String status = scanner.nextLine();

        String resultado = orcamentoController.atualizarOrcamento(idOrcamento, status);
        System.out.println(resultado);
    }

    private void deletarOrcamento() {
        System.out.print("ID do Orçamento a ser deletado: ");
        String idOrcamento = scanner.nextLine();
        String resultado = orcamentoController.deletarOrcamento(idOrcamento);
        System.out.println(resultado);
    }

    private void listarOrcamentos() {
        List<Orcamento> orcamentos = orcamentoController.obterTodosOrcamentos();
        if (orcamentos != null && !orcamentos.isEmpty()) {
            System.out.println("=== Lista de Orçamentos ===");
            for (Orcamento orcamento : orcamentos) {
                System.out.printf("ID: %s | Descrição: %s | Valor Total: %.2f | Status: %s%n",
                        orcamento.getIdOrcamento(), orcamento.getDescricaoOrcamento(),
                        orcamento.getValorTotal(), orcamento.getStatusOrcamento());
            }
        } else {
            System.out.println("Nenhum Orçamento encontrado.");
        }
    }

    public static void main(String[] args) {
        OrcamentoView view = new OrcamentoView();
        view.exibirMenu();
    }
}
