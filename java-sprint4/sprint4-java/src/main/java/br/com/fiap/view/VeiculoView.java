package br.com.fiap.view;

import java.util.List;
import java.util.Scanner;

import br.com.fiap.controller.UsuarioController;
import br.com.fiap.controller.VeiculoController;
import br.com.fiap.model.vo.Usuario;
import br.com.fiap.model.vo.Veiculo;

public class VeiculoView {
    private VeiculoController veiculoController;
    private UsuarioController usuarioController;
    private Scanner scanner;

    public VeiculoView() {
        this.veiculoController = new VeiculoController();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("=== Menu de Veiculos ===");
            System.out.println("1. Adicionar Veiculo");
            System.out.println("2. Atualizar Veiculo");
            System.out.println("3. Deletar Veiculo");
            System.out.println("4. Listar Veiculos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarVeiculo();
                    break;
                case 2:
                    atualizarVeiculo();
                    break;
                case 3:
                    deletarVeiculo();
                    break;
                case 4:
                    listarVeiculos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void adicionarVeiculo() {
        System.out.print("Placa do Veiculo: ");
        String placa = scanner.nextLine();
        System.out.print("Marca do Veiculo: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo do Veiculo: ");
        String modelo = scanner.nextLine();
        System.out.print("Ano do Veiculo: ");
        int ano = scanner.nextInt();
        System.out.print("Quilometragem do Veiculo: ");
        double quilometragem = scanner.nextDouble();
        System.out.print("CPF do Proprietário do Veiculo: ");
        String cpf_proprietario = scanner.nextLine();
        Usuario usuario = usuarioController.obterUsuario(cpf_proprietario);
        scanner.nextLine(); 

        Veiculo veiculo = new Veiculo(marca, modelo, ano, placa, quilometragem, usuario);
        String resultado = veiculoController.adicionarVeiculo(veiculo);
        System.out.println(resultado);
    }

    private void atualizarVeiculo() {
        System.out.print("Placa do Veiculo a ser atualizado: ");
        String placa = scanner.nextLine();
        System.out.print("Nova Marca do Veiculo: ");
        String marca = scanner.nextLine();
        System.out.print("Novo Modelo do Veiculo: ");
        String modelo = scanner.nextLine();
        System.out.print("Novo Ano do Veiculo: ");
        int ano = scanner.nextInt();
        System.out.print("Nova Quilometragem do Veiculo: ");
        double quilometragem = scanner.nextDouble();
        System.out.print("CPF do Proprietário do Veiculo: ");
        String cpf_proprietario = scanner.nextLine();
        Usuario usuario = usuarioController.obterUsuario(cpf_proprietario);
        scanner.nextLine();

        Veiculo veiculo = new Veiculo(marca, modelo, ano, placa, quilometragem, usuario);
        String resultado = veiculoController.atualizarVeiculo(veiculo);
        System.out.println(resultado);
    }

    private void deletarVeiculo() {
        System.out.print("Placa do Veiculo a ser deletado: ");
        String placa = scanner.nextLine();
        String resultado = veiculoController.deletarVeiculo(placa);
        System.out.println(resultado);
    }

    private void listarVeiculos() {
        List<Veiculo> veiculos = veiculoController.obterTodosVeiculos();
        if (veiculos != null && !veiculos.isEmpty()) {
            System.out.println("=== Lista de Veiculos ===");
            for (Veiculo veiculo : veiculos) {
                System.out.printf("Placa: %s | Marca: %s | Modelo: %s | Ano: %d | Quilometragem: %.2f%n",
                        veiculo.getPlaca(), veiculo.getMarca(), veiculo.getModelo(),
                        veiculo.getAno(), veiculo.getQuilometragem());
            }
        } else {
            System.out.println("Nenhum Veiculo encontrado.");
        }
    }

    public static void main(String[] args) {
        VeiculoView view = new VeiculoView();
        view.exibirMenu();
    }
}
