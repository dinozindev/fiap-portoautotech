package br.com.fiap.model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import br.com.fiap.model.dao.PecaDAO;
import br.com.fiap.model.vo.Peca;

public class PecaBO {

    PecaDAO pecaDAO = null;

    public boolean validarIdExistente(String id) throws ClassNotFoundException, SQLException {
    	pecaDAO = new PecaDAO();
    	return pecaDAO.buscarPorId(id) != null;
    }
    private boolean validarIdPeca(String id) {
    	return Pattern.matches("P\\d{5}", id);
    }
    private boolean validarDisponibilidadePeca(int disponibilidadePeca) {
    	return disponibilidadePeca > 0 && disponibilidadePeca <= 99999999;
    }
    private boolean validarNomePeca(String nomePeca) {
        return nomePeca != null && !nomePeca.trim().isEmpty() && nomePeca.length() <= 255;
    }
    
    private boolean validarNomeExistente(String nomePeca) {
    	return pecaDAO.buscarPorNome(nomePeca) != null;
    }
    
    private boolean validarPrecoPeca(double precoPeca) {
     	String valorStr = String.valueOf(precoPeca);
        return precoPeca > 0 && Pattern.matches("^\\d{1,7}(\\.\\d{1,2})?$", valorStr);
    }

    public String inserirBO(Peca peca) throws ClassNotFoundException, SQLException {
    	
    	pecaDAO = new PecaDAO();
    	
        if (peca == null) {
            return "Peça não pode ser nula.";
        }
        if (!validarIdPeca(peca.getIdPeca())) {
        	return "ID com formato inválido."; 
        }
        if (validarIdExistente(peca.getIdPeca())) {
        	return "ID já existente.";
        }
        if (!validarNomePeca(peca.getNomePeca())) {
            return "Nome da peça é inválido.";
        }
        if (!validarNomeExistente(peca.getNomePeca())) {
            return "Nome da peça já existente.";
        }
        if (!validarPrecoPeca(peca.getPrecoPeca())) {
        	return "Preço deve ser maior que zero e deve ter no máximo 7 casas inteiras e 2 casas decimais.";
        }
        if (!validarDisponibilidadePeca(peca.getDisponibilidadePeca())) {
            return "Disponibilidade deve ser maior que zero e deve ter no máximo 4 dígitos.";
        }
			return pecaDAO.inserir(peca) ? "OK" : "Falha ao inserir a peça";
    }


    public String atualizarBO(Peca peca) throws ClassNotFoundException, SQLException {
    	
    	pecaDAO = new PecaDAO();
    	
    	if (peca == null) {
            return "Peça não pode ser nula.";
        }
        if (!validarIdPeca(peca.getIdPeca())) {
        	return "ID com formato inválido."; 
        }
        if (!validarIdExistente(peca.getIdPeca())) {
        	return "ID não existente.";
        }
        if (!validarNomePeca(peca.getNomePeca())) {
            return "Nome da peça é inválido.";
        }
        if (!validarNomeExistente(peca.getNomePeca())) {
            return "Nome da peça já existente.";
        }
        if (!validarPrecoPeca(peca.getPrecoPeca())) {
        	return "Preço deve ser maior que zero e deve ter no máximo 7 casas inteiras e 2 casas decimais.";
        }
        if (!validarDisponibilidadePeca(peca.getDisponibilidadePeca())) {
            return "Disponibilidade deve ser maior que zero e deve ter no máximo 4 dígitos.";
        }
			return pecaDAO.atualizar(peca) ? "OK" : "Falha ao atualizar a peça";
		
    }


    public String deletarBO(String id) throws ClassNotFoundException, SQLException {
    	
    	pecaDAO = new PecaDAO();
    	
        if (!validarIdPeca(id)) {
            return "ID com formato inválido.";
        }
        if (!validarIdExistente(id)) {
            return "ID não existe.";
        }
        
			return pecaDAO.deletar(id) ? "OK" : "Falha ao deletar a peça";
    }

    public ArrayList<Peca> selecionarBO() throws ClassNotFoundException, SQLException {
    	
    	pecaDAO = new PecaDAO();
        
		return (ArrayList<Peca>)pecaDAO.selecionar();
		} 
    }
