package br.com.fiap.view;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.controller.AgendamentoController;
import br.com.fiap.controller.CentroController;
import br.com.fiap.controller.ServicoController;
import br.com.fiap.controller.VeiculoController;
import br.com.fiap.model.vo.Agendamento;
import br.com.fiap.model.vo.CentroAutomotivo;
import br.com.fiap.model.vo.Servico;
import br.com.fiap.model.vo.Veiculo;

public class AgendamentoView {
    private AgendamentoController agendamentoController;
    private	VeiculoController veiculoController;
    private ServicoController servicoController;
    private CentroController centroController;
    private Scanner scanner;

    public AgendamentoView() {
        this.agendamentoController = new AgendamentoController();
        this.veiculoController = new VeiculoController();
        this.servicoController = new ServicoController();
        this.centroController = new CentroController();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("=== Menu de Agendamentos ===");
            System.out.println("1. Adicionar Agendamento");
            System.out.println("2. Atualizar Agendamento");
            System.out.println("3. Deletar Agendamento");
            System.out.println("4. Listar Agendamentos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarAgendamento();
                    break;
                case 2:
                    atualizarAgendamento();
                    break;
                case 3:
                    deletarAgendamento();
                    break;
                case 4:
                    listarAgendamentos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void adicionarAgendamento() {
        System.out.print("Data: ");
        String data = scanner.nextLine();
        Date dataFormatada = agendamentoController.obterData(data);
        System.out.print("Hora: ");
        String hora = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("ID do Serviço: ");
        String idServico = scanner.nextLine();
        Servico servico = servicoController.obterServico(idServico);
        System.out.print("ID do Centro: ");
        String idCentro = scanner.nextLine();
        CentroAutomotivo centro = centroController.obterCentro(idCentro);
        System.out.print("Placa do Carro: ");
        String placa = scanner.nextLine();
        Veiculo veiculo = veiculoController.obterVeiculo(placa);

        Agendamento agendamento = new Agendamento(dataFormatada, hora, descricao, centro, servico, veiculo);
        String resultado = agendamentoController.adicionarAgendamento(agendamento);
        System.out.println(resultado);
    }

    private void atualizarAgendamento() {
    	 System.out.print("Id do agendamento: ");
         String idAgendamento = scanner.nextLine();
    	 System.out.print("Data: ");
         String data = scanner.nextLine();
         Date dataFormatada = agendamentoController.obterData(data);
         System.out.print("Hora: ");
         String hora = scanner.nextLine();
         System.out.print("Descrição: ");
         String descricao = scanner.nextLine();
         System.out.print("ID do Serviço: ");
         String idServico = scanner.nextLine();
         Servico servico = servicoController.obterServico(idServico);
         System.out.print("ID do Centro: ");
         String idCentro = scanner.nextLine();
         CentroAutomotivo centro = centroController.obterCentro(idCentro);
         System.out.print("Placa do Carro: ");
         String placa = scanner.nextLine();
         Veiculo veiculo = veiculoController.obterVeiculo(placa);

         Agendamento agendamento = new Agendamento(idAgendamento, dataFormatada, hora, descricao, centro, servico, veiculo);
        String resultado = agendamentoController.atualizarAgendamento(agendamento);
        System.out.println(resultado);
    }

    private void deletarAgendamento() {
        System.out.print("ID do Agendamento a ser deletado: ");
        String idAgendamento = scanner.nextLine();
        String resultado = agendamentoController.deletarAgendamento(idAgendamento);
        System.out.println(resultado);
    }

    private void listarAgendamentos() {
        List<Agendamento> agendamentos = agendamentoController.obterTodosAgendamentos();
        if (agendamentos != null && !agendamentos.isEmpty()) {
            System.out.println("=== Lista de Agendamentos ===");
            for (Agendamento agendamento : agendamentos) {
                System.out.printf("ID: %s | Data e Hora: %s | ID do Serviço: %s%n",
                        agendamento.getIdAgendamento(), agendamento.getData(),
                        agendamento.getServico().getIdServico());
            }
        } else {
            System.out.println("Nenhum Agendamento encontrado.");
        }
    }

    public static void main(String[] args) {
        AgendamentoView view = new AgendamentoView();
        view.exibirMenu();
    }
}