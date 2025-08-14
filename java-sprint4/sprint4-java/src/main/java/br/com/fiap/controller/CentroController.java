package br.com.fiap.controller;

import br.com.fiap.model.bo.CentroBO;
import br.com.fiap.model.vo.CentroAutomotivo;

import java.sql.SQLException;
import java.util.List;

public class CentroController {
    private CentroBO centroBO;

    public CentroController() {
       this.centroBO = new CentroBO();  
    }

    public String adicionarCentro(CentroAutomotivo centro) {
        try {
            return centroBO.inserirBO(centro);
        } catch (SQLException e) {
            return "Erro ao adicionar Centro: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao adicionar Centro: " + e.getMessage();
			
		}
    }

    public String atualizarCentro(CentroAutomotivo centro) {
        try {
            return centroBO.atualizarBO(centro);
        } catch (SQLException e) {
            return "Erro ao atualizar Centro: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao atualizar Centro: " + e.getMessage();
		}
    }

    public String deletarCentro(String idCentro) {
        try {
            return centroBO.deletarBO(idCentro);
        } catch (SQLException e) {
            return "Erro ao deletar Centro: " + e.getMessage();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "Erro ao deletar Centro: " + e.getMessage();
		}
    }

    public List<CentroAutomotivo> obterTodosCentros() {
        try {
            return centroBO.selecionarBO();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public CentroAutomotivo obterCentro(String id) {
    	try {
            return centroBO.buscarBO(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
}
