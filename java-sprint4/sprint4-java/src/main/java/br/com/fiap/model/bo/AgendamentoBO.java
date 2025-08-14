package br.com.fiap.model.bo;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import br.com.fiap.model.dao.AgendamentoDAO;
import br.com.fiap.model.vo.Agendamento;

public class AgendamentoBO {

   AgendamentoDAO agendamentoDAO = null;
   ServicoBO servicoBO = null;
   CentroBO centroBO = null;
   VeiculoBO veiculoBO = null;
   
   private boolean validarIdExistente(String id) {
	   return agendamentoDAO.buscarPorId(id) != null;
   }
   
   /*
    private boolean validarPlaca(String placa) {
        String regex = "^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$"; 
        return placa != null && Pattern.matches(regex, placa);
    }
    
    private boolean validarData(String data) {
    	return Pattern.matches("^(0[1-9]|[12][0-9]|3[01])-(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)-\\d{4}$", data);
    }
    */
    
    public java.sql.Date obterData(String dataString) {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        try {
            java.util.Date dataUtil = sdf.parse(dataString);
            return new java.sql.Date(dataUtil.getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Erro ao converter data.");
        }
    }

    private boolean validarDataFutura(Date dataAgendamento) {
        return dataAgendamento != null && dataAgendamento.after(new java.util.Date());
    }
    
    private boolean validarHorario(String horario) {
        if (horario == null || horario.isEmpty()) return false;
        int hora = Integer.parseInt(horario.split(":")[0]);
        int minuto = Integer.parseInt(horario.split(":")[1]);
        return (hora >= 8 && hora <= 22) && (minuto >= 0 && minuto < 60);
    }

    private boolean validarDescricao(String descricao) {
    	return descricao != null && !descricao.trim().isEmpty() && descricao.length() <= 255;
    }
    
    public String inserirBO(Agendamento agendamento) throws SQLException, ClassNotFoundException {
    	
    	agendamentoDAO = new AgendamentoDAO();
    	servicoBO = new ServicoBO();
    	centroBO = new CentroBO();
    	veiculoBO = new VeiculoBO();
    	
    	agendamento.setIdAgendamento(UUID.randomUUID().toString());
    	
        if (validarIdExistente(agendamento.getIdAgendamento())) {
        	return "ID já existente.";
        }
        if (!validarDataFutura(agendamento.getData())) {
            return "Data do agendamento deve ser futura.";
        }
        if (!validarHorario(agendamento.getHora())) {
            return "Horário do agendamento deve estar entre 08:00 e 22:00.";
        }
        if (!validarDescricao(agendamento.getDescricao())) {
            return "Descrição do agendamento é inválida.";
        }
        if (!veiculoBO.validarVeiculoExistente(agendamento.getVeiculo().getPlaca())) {
            return "Veículo inexistente.";
        }
        if (!centroBO.validarIdExistente(agendamento.getCentro().getIdCentro())) {
        	return "Centro Automotivo inexistente";
        }
        if (!servicoBO.validarIdExistente(agendamento.getServico().getIdServico())) {
        	return "Serviço inexistente";
        }

        return agendamentoDAO.inserir(agendamento) ? "OK" : "Falha ao inserir o agendamento";
    }

    public String atualizarBO(Agendamento agendamento) throws SQLException, ClassNotFoundException {
    	
    	agendamentoDAO = new AgendamentoDAO();
    	servicoBO = new ServicoBO();
    	centroBO = new CentroBO();
    	veiculoBO = new VeiculoBO();
    	
    	 if (!validarIdExistente(agendamento.getIdAgendamento())) {
         	return "ID inexistente.";
         }
         if (!validarDataFutura(agendamento.getData())) {
             return "Data do agendamento deve ser futura.";
         }
         if (!validarHorario(agendamento.getHora())) {
             return "Horário do agendamento deve estar entre 08:00 e 22:00.";
         }
         if (!validarDescricao(agendamento.getDescricao())) {
             return "Descrição do agendamento é inválida.";
         }
         if (!veiculoBO.validarVeiculoExistente(agendamento.getVeiculo().getPlaca())) {
             return "Veículo inexistente.";
         }
         if (!centroBO.validarIdExistente(agendamento.getCentro().getIdCentro())) {
         	return "Centro Automotivo inexistente";
         }
         if (!servicoBO.validarIdExistente(agendamento.getServico().getIdServico())) {
         	return "Serviço inexistente";
         }
        return agendamentoDAO.atualizar(agendamento) ? "OK" : "Falha ao atualizar o agendamento";
    }

    public String deletarBO(String idAgendamento) throws SQLException, ClassNotFoundException {
    	
    	agendamentoDAO = new AgendamentoDAO();
    	
    	if (!validarIdExistente(idAgendamento)) {
    		return "ID inexistente.";
    	}
    	
        return agendamentoDAO.deletar(idAgendamento) ? "OK" : "Falha ao deletar o agendamento";
    }

    public List<Agendamento> selecionarBO() throws SQLException, ClassNotFoundException {
    	
    	agendamentoDAO = new AgendamentoDAO();
    	
        return (ArrayList<Agendamento>)agendamentoDAO.selecionar();
    }
    
    public List<Agendamento> selecionarAgendamentosUsuarioBO(String cpf) throws SQLException, ClassNotFoundException {
    	
    	agendamentoDAO = new AgendamentoDAO();
    	
        return (ArrayList<Agendamento>)agendamentoDAO.buscarAgendamentosPorCPF(cpf);
    }
}
