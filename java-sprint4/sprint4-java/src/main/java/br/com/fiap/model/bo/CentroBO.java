package br.com.fiap.model.bo;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import br.com.fiap.model.dao.CentroDAO;
import br.com.fiap.model.vo.CentroAutomotivo;

public class CentroBO {

    private CentroDAO centroDAO = null;

    private boolean validarIdCentro(String idCentro) {
    	return Pattern.matches("C\\d{3}", idCentro);
    }
    public boolean validarIdExistente(String idCentro) throws ClassNotFoundException, SQLException {
    	centroDAO = new CentroDAO();
    	return centroDAO.buscarPorId(idCentro) != null;
    }
    private boolean validarNomeCentro(String nomeCentro) {
        return nomeCentro != null && !nomeCentro.trim().isEmpty() && nomeCentro.length() <= 155;
    }

    private boolean validarEnderecoCentro(String enderecoCentro) {
        return enderecoCentro != null && !enderecoCentro.trim().isEmpty() && enderecoCentro.length() > 5 && enderecoCentro.length() <= 255;
    }

    private boolean validarTelefoneCentro(String telefoneCentro) {
    	return telefoneCentro != null && Pattern.matches("^\\d{11}$", telefoneCentro);
    }

    private boolean validarHorarioFuncionamento(String horarioFuncionamento) {
        return horarioFuncionamento != null && Pattern.matches("^(?:[01]\\d|2[0-3]):[0-5]\\d\\s*-\\s*(?:[01]\\d|2[0-3]):[0-5]\\d$", horarioFuncionamento);
    }


    public String inserirBO(CentroAutomotivo centro) throws SQLException, ClassNotFoundException {
    	
    	centroDAO = new CentroDAO();
    	
        if (!validarIdCentro(centro.getIdCentro())) {
            return "ID com formato inválido.";
        }
        if (validarIdExistente(centro.getIdCentro())) {
            return "ID já existente.";
        }
        if (!validarNomeCentro(centro.getNomeCentro())) {
            return "Nome do Centro inválido.";
        }
        if (!validarEnderecoCentro(centro.getEnderecoCentro())) {
            return "Endereço do Centro inválido.";
        }
        if (!validarTelefoneCentro(centro.getTelefoneCentro())) {
        	return "Telefone do Centro inválido.";
        }
        if (!validarHorarioFuncionamento(centro.getHorarioFuncionamento())) {
        	return "Horário de Funcionamento inválido";
        }
        return centroDAO.inserir(centro) ? "OK" : "Falha ao inserir o centro";
    }

    public String atualizarBO(CentroAutomotivo centro) throws SQLException, ClassNotFoundException {
    	
    	centroDAO = new CentroDAO();
    	
    	if (!validarIdCentro(centro.getIdCentro())) {
            return "ID com formato inválido.";
        }
        if (!validarIdExistente(centro.getIdCentro())) {
            return "ID não existente.";
        }
        if (!validarNomeCentro(centro.getNomeCentro())) {
            return "Nome do Centro inválido.";
        }
        if (!validarEnderecoCentro(centro.getEnderecoCentro())) {
            return "Endereço do Centro inválido.";
        }
        if (!validarTelefoneCentro(centro.getTelefoneCentro())) {
        	return "Telefone do Centro inválido.";
        }
        if (!validarHorarioFuncionamento(centro.getHorarioFuncionamento())) {
        	return "Horário de Funcionamento inválido";
        }
        return centroDAO.atualizar(centro) ? "OK" : "Falha ao atualizar o centro";
    }

    public String deletarBO(String idCentro) throws SQLException, ClassNotFoundException {
    	
    	centroDAO = new CentroDAO();
    	
    	if (!validarIdCentro(idCentro)) {
            return "ID com formato inválido.";
        }
        if (!validarIdExistente(idCentro)) {
            return "ID não existente.";
        }
        return centroDAO.deletar(idCentro) ? "OK" : "Falha ao deletar o centro";
    }

    public List<CentroAutomotivo> selecionarBO() throws SQLException, ClassNotFoundException {
    	
    	centroDAO = new CentroDAO();
    	
        return centroDAO.selecionarTodos();
    }
    
    public CentroAutomotivo buscarBO(String id) throws ClassNotFoundException, SQLException {
		centroDAO = new CentroDAO();
		
		return centroDAO.buscarPorId(id);
}
}
