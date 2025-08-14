package br.com.fiap.controller;

import br.com.fiap.model.bo.OrcamentoBO;
import br.com.fiap.model.vo.Orcamento;

import java.sql.SQLException;
import java.util.List;

public class OrcamentoController {
    private OrcamentoBO orcamentoBO;

    public OrcamentoController() {
       this.orcamentoBO = new OrcamentoBO();  
    }

    public String adicionarOrcamento(String idDiagnostico, Orcamento orcamento) {
        try {
            return orcamentoBO.inserirBO(idDiagnostico, orcamento);
        } catch (SQLException e) {
            return "Erro ao adicionar Orcamento: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao adicionar Orcamento: " + e.getMessage();
			
		}
    }

    public String atualizarOrcamento(String idOrcamento, String status) {
        try {
            return orcamentoBO.atualizarBO(idOrcamento, status);
        } catch (SQLException e) {
            return "Erro ao atualizar Orcamento: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao atualizar Orcamento: " + e.getMessage();
		}
    }

    public String deletarOrcamento(String idOrcamento) {
        try {
            return orcamentoBO.deletarBO(idOrcamento);
        } catch (SQLException e) {
            return "Erro ao deletar Orcamento: " + e.getMessage();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "Erro ao deletar Orcamento: " + e.getMessage();
		}
    }

    public List<Orcamento> obterTodosOrcamentos() {
        try {
            return orcamentoBO.selecionarBO();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public Orcamento obterOrcamento(String idOrcamento) {
        try {
            return orcamentoBO.buscarOrcamentoBO(idOrcamento);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
}