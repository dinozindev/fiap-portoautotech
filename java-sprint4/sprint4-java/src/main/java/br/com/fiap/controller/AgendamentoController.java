package br.com.fiap.controller;

import br.com.fiap.model.bo.AgendamentoBO;
import br.com.fiap.model.vo.Agendamento;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class AgendamentoController {
    private AgendamentoBO agendamentoBO;

    public AgendamentoController() {
       this.agendamentoBO = new AgendamentoBO();  
    }

    public String adicionarAgendamento(Agendamento agendamento) {
        try {
            return agendamentoBO.inserirBO(agendamento);
        } catch (SQLException e) {
            return "Erro ao adicionar Agendamento: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao adicionar Agendamento: " + e.getMessage();
			
		}
    }

    public String atualizarAgendamento(Agendamento agendamento) {
        try {
            return agendamentoBO.atualizarBO(agendamento);
        } catch (SQLException e) {
            return "Erro ao atualizar Agendamento: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao atualizar Agendamento: " + e.getMessage();
		}
    }

    public String deletarAgendamento(String idAgendamento) {
        try {
            return agendamentoBO.deletarBO(idAgendamento);
        } catch (SQLException e) {
            return "Erro ao deletar Agendamento: " + e.getMessage();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "Erro ao deletar Agendamento: " + e.getMessage();
		}
    }

    public List<Agendamento> obterTodosAgendamentos() {
        try {
            return agendamentoBO.selecionarBO();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public List<Agendamento> obterTodosAgendamentosUsuario(String cpf) {
        try {
            return agendamentoBO.selecionarAgendamentosUsuarioBO(cpf);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public Date obterData(String data) {
            return agendamentoBO.obterData(data); 
    }
}
