package br.com.fiap.controller;

import br.com.fiap.model.bo.VeiculoBO;
import br.com.fiap.model.vo.Veiculo;

import java.sql.SQLException;
import java.util.List;

public class VeiculoController {
    private VeiculoBO veiculoBO;

    public VeiculoController() {
       this.veiculoBO = new VeiculoBO();  
    }

    public String adicionarVeiculo(Veiculo veiculo) {
        try {
            return veiculoBO.inserirBO(veiculo);
        } catch (SQLException e) {
            return "Erro ao adicionar veiculo: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao adicionar veiculo: " + e.getMessage();
			
		}
    }

    public String atualizarVeiculo(Veiculo veiculo) {
        try {
            return veiculoBO.atualizarBO(veiculo);
        } catch (SQLException e) {
            return "Erro ao atualizar veiculo: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao atualizar veiculo: " + e.getMessage();
		}
    }

    public String deletarVeiculo(String idveiculo) {
        try {
            return veiculoBO.deletarBO(idveiculo);
        } catch (SQLException e) {
            return "Erro ao deletar veiculo: " + e.getMessage();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "Erro ao deletar veiculo: " + e.getMessage();
		}
    }

    public List<Veiculo> obterTodosVeiculos() {
        try {
            return veiculoBO.selecionarBO();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public List<Veiculo> obterTodosVeiculosCPF(String cpf) {
        try {
            return veiculoBO.selecionarVeiculosUsuarioBO(cpf);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public Veiculo obterVeiculo(String placa) {
    	try {
            return veiculoBO.buscarBO(placa);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
}

