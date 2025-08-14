package br.com.fiap.view;

import java.util.List;
import java.util.Scanner;

import br.com.fiap.controller.CentroController;
import br.com.fiap.model.vo.CentroAutomotivo;

public class CentroAutomotivoView {
    private CentroController centroController;
    private Scanner scanner;

    public CentroAutomotivoView() {
        this.centroController = new CentroController();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("=== Menu de Centros Automotivos ===");
            System.out.println("1. Adicionar Centro Automotivo");
            System.out.println("2. Atualizar Centro Automotivo");
            System.out.println("3. Deletar Centro Automotivo");
            System.out.println("4. Listar Centros Automotivos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarCentroAutomotivo();
                    break;
                case 2:
                    atualizarCentroAutomotivo();
                    break;
                case 3:
                    deletarCentroAutomotivo();
                    break;
                case 4:
                    listarCentrosAutomotivos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void adicionarCentroAutomotivo() {
        System.out.print("ID do Centro Automotivo: ");
        String idCentro = scanner.nextLine();
        System.out.print("Nome do Centro Automotivo: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Horário de Funcionamento: ");
        String horario = scanner.nextLine();

        CentroAutomotivo centro = new CentroAutomotivo(idCentro, nome, endereco, telefone, horario);
        String resultado = centroController.adicionarCentro(centro);
        System.out.println(resultado);
    }

    private void atualizarCentroAutomotivo() {
        System.out.print("ID do Centro Automotivo a ser atualizado: ");
        String idCentro = scanner.nextLine();
        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Novo Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Novo Horário de Funcionamento: ");
        String horario = scanner.nextLine();

        CentroAutomotivo centro = new CentroAutomotivo(idCentro, nome, endereco, telefone, horario);
        String resultado = centroController.atualizarCentro(centro);
        System.out.println(resultado);
    }

    private void deletarCentroAutomotivo() {
        System.out.print("ID do Centro Automotivo a ser deletado: ");
        String idCentro = scanner.nextLine();
        String resultado = centroController.deletarCentro(idCentro);
        System.out.println(resultado);
    }

    private void listarCentrosAutomotivos() {
        List<CentroAutomotivo> centros = centroController.obterTodosCentros();
        if (centros != null && !centros.isEmpty()) {
            System.out.println("=== Lista de Centros Automotivos ===");
            for (CentroAutomotivo centro : centros) {
                System.out.printf("ID: %s | Nome: %s | Endereço: %s | Telefone: %s | Horário: %s%n",
                        centro.getIdCentro(), centro.getNomeCentro(), centro.getEnderecoCentro(),
                        centro.getTelefoneCentro(), centro.getHorarioFuncionamento());
            }
        } else {
            System.out.println("Nenhum Centro Automotivo encontrado.");
        }
    }

    public static void main(String[] args) {
        CentroAutomotivoView view = new CentroAutomotivoView();
        view.exibirMenu();
    }
}
