package br.com.fiap.view;

import java.util.List;
import java.util.Scanner;

import br.com.fiap.controller.ServicoController;
import br.com.fiap.model.vo.Servico;

public class ServicoView {
    private ServicoController servicoController;
    private Scanner scanner;

    public ServicoView() {
        this.servicoController = new ServicoController();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("=== Menu de Serviços ===");
            System.out.println("1. Adicionar Serviço");
            System.out.println("2. Atualizar Serviço");
            System.out.println("3. Deletar Serviço");
            System.out.println("4. Listar Serviços");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarServico();
                    break;
                case 2:
                    atualizarServico();
                    break;
                case 3:
                    deletarServico();
                    break;
                case 4:
                    listarServicos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void adicionarServico() {
        System.out.print("ID do Serviço: ");
        String idServico = scanner.nextLine();
        System.out.print("Tipo de Serviço: ");
        String tipo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        System.out.print("Duração (em minutos): ");
        int duracao = scanner.nextInt();
        scanner.nextLine(); 

        Servico servico = new Servico(idServico, tipo, descricao, preco, duracao);
        String resultado = servicoController.adicionarServico(servico);
        System.out.println(resultado);
    }

    private void atualizarServico() {
        System.out.print("ID do Serviço a ser atualizado: ");
        String idServico = scanner.nextLine();
        System.out.print("Novo Tipo de Serviço: ");
        String tipo = scanner.nextLine();
        System.out.print("Nova Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Novo Preço: ");
        double preco = scanner.nextDouble();
        System.out.print("Nova Duração (em minutos): ");
        int duracao = scanner.nextInt();
        scanner.nextLine();  

        Servico servico = new Servico(idServico, tipo, descricao, preco, duracao);
        String resultado = servicoController.atualizarServico(servico);
        System.out.println(resultado);
    }

    private void deletarServico() {
        System.out.print("ID do Serviço a ser deletado: ");
        String idServico = scanner.nextLine();
        String resultado = servicoController.deletarServico(idServico);
        System.out.println(resultado);
    }

    private void listarServicos() {
        List<Servico> servicos = servicoController.obterTodosServicos();
        if (servicos != null && !servicos.isEmpty()) {
            System.out.println("=== Lista de Serviços ===");
            for (Servico servico : servicos) {
                System.out.printf("ID: %s | Tipo: %s | Descrição: %s | Preço: %.2f | Duração: %d minutos%n",
                        servico.getIdServico(), servico.getTipoServico(), servico.getDescricaoServico(),
                        servico.getPrecoServico(), servico.getDuracaoServico());
            }
        } else {
            System.out.println("Nenhum Serviço encontrado.");
        }
    }

    public static void main(String[] args) {
        ServicoView view = new ServicoView();
        view.exibirMenu();
    }
}


