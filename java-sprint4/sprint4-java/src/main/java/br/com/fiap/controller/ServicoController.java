package br.com.fiap.controller;

import br.com.fiap.model.bo.ServicoBO;
import br.com.fiap.model.vo.Servico;

import java.sql.SQLException;
import java.util.List;

public class ServicoController {
    private ServicoBO servicoBO;

    public ServicoController() {
       this.servicoBO = new ServicoBO();  
    }

    public String adicionarServico(Servico servico) {
        try {
            return servicoBO.inserirBO(servico);
        } catch (SQLException e) {
            return "Erro ao adicionar Servico: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao adicionar Servico: " + e.getMessage();
			
		}
    }

    public String atualizarServico(Servico servico) {
        try {
            return servicoBO.atualizarBO(servico);
        } catch (SQLException e) {
            return "Erro ao atualizar Servico: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao atualizar Servico: " + e.getMessage();
		}
    }

    public String deletarServico(String idServico) {
        try {
            return servicoBO.deletarBO(idServico);
        } catch (SQLException e) {
            return "Erro ao deletar Servico: " + e.getMessage();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "Erro ao deletar Servico: " + e.getMessage();
		}
    }

    public List<Servico> obterTodosServicos() {
        try {
            return servicoBO.selecionarBO();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public Servico obterServico(String id) {
    	try {
            return servicoBO.buscarServicoBO(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
}