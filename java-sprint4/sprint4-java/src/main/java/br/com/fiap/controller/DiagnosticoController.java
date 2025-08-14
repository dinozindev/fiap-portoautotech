package br.com.fiap.controller;

import br.com.fiap.model.bo.DiagnosticoBO;
import br.com.fiap.model.vo.Diagnostico;

import java.sql.SQLException;
import java.util.List;

public class DiagnosticoController {
    private DiagnosticoBO diagnosticoBO;

    public DiagnosticoController() {
       this.diagnosticoBO = new DiagnosticoBO();  
    }

    public String adicionarDiagnostico(Diagnostico diagnostico) {
        try {
            return diagnosticoBO.inserirBO(diagnostico);
        } catch (SQLException e) {
            return "Erro ao adicionar Diagnostico: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao adicionar Diagnostico: " + e.getMessage();
			
		}
    }

    public String atualizarDiagnostico(String idDiagnostico, String status) {
        try {
            return diagnosticoBO.atualizarBO(idDiagnostico, status);
        } catch (SQLException e) {
            return "Erro ao atualizar Diagnostico: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao atualizar Diagnostico: " + e.getMessage();
		}
    }

    public String deletarDiagnostico(String idDiagnostico) {
        try {
            return diagnosticoBO.deletarBO(idDiagnostico);
        } catch (SQLException e) {
            return "Erro ao deletar Diagnostico: " + e.getMessage();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "Erro ao deletar Diagnostico: " + e.getMessage();
		}
    }

    public List<Diagnostico> obterTodosDiagnosticos() {
        try {
            return diagnosticoBO.selecionarBO();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public List<Diagnostico> obterTodosDiagnosticosUsuario(String cpf) {
        try {
            return diagnosticoBO.selecionarDiagnosticosUsuarioBO(cpf);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
}

