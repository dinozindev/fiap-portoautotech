package br.com.fiap.controller;

import br.com.fiap.model.bo.PecaBO;
import br.com.fiap.model.vo.Peca;

import java.sql.SQLException;
import java.util.List;

public class PecaController {
    private PecaBO pecaBO;

    public PecaController() {
       this.pecaBO = new PecaBO();  
    }

    public String adicionarPeca(Peca peca) {
        try {
            return pecaBO.inserirBO(peca);
        } catch (SQLException e) {
            return "Erro ao adicionar Peca: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao adicionar Peca: " + e.getMessage();
			
		}
    }

    public String atualizarPeca(Peca peca) {
        try {
            return pecaBO.atualizarBO(peca);
        } catch (SQLException e) {
            return "Erro ao atualizar Peca: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao atualizar Peca: " + e.getMessage();
		}
    }

    public String deletarPeca(String idPeca) {
        try {
            return pecaBO.deletarBO(idPeca);
        } catch (SQLException e) {
            return "Erro ao deletar Peca: " + e.getMessage();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "Erro ao deletar Peca: " + e.getMessage();
		}
    }

    public List<Peca> obterTodosPecas() {
        try {
            return pecaBO.selecionarBO();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
}