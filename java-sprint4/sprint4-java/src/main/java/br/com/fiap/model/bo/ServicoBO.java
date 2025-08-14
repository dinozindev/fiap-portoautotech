package br.com.fiap.model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import br.com.fiap.model.dao.ServicoDAO;
import br.com.fiap.model.vo.Servico;

public class ServicoBO {

    ServicoDAO servicoDAO = null;

    public boolean validarIdExistente(String id) throws ClassNotFoundException, SQLException {
    	servicoDAO = new ServicoDAO();
    	return servicoDAO.buscarPorId(id) != null;
    }
    private boolean validarIdServico(String id) {
    	return Pattern.matches("S\\d{5}", id);
    }
    private boolean validarTipoServico(String tipoServico) {
    	return tipoServico != null && !tipoServico.trim().isEmpty() && tipoServico.length() <= 50;
    }
    private boolean validarDescricaoServico(String descricaoServico) {
        return descricaoServico != null && !descricaoServico.trim().isEmpty() && descricaoServico.length() <= 255;
    }
    private boolean validarPrecoServico(double precoServico) {
     	String valorStr = String.valueOf(precoServico);
        return precoServico > 0 && Pattern.matches("^\\d{1,7}(\\.\\d{1,2})?$", valorStr);
    }
    private boolean validarDuracaoServico(int duracaoServico) {
        return duracaoServico > 0 && duracaoServico <= 9999;
    }

    public String inserirBO(Servico servico) throws ClassNotFoundException, SQLException {
    	
    	servicoDAO = new ServicoDAO();
    	
        if (servico == null) {
            return "Usuário não pode ser nulo.";
        }
        if (!validarIdServico(servico.getIdServico())) {
        	return "ID com formato inválido."; 
        }
        if (validarIdExistente(servico.getIdServico())) {
        	return "ID já existente.";
        }
        if (!validarTipoServico(servico.getTipoServico())) {
            return "Tipo do serviço é inválido.";
        }
        if (!validarDescricaoServico(servico.getDescricaoServico())) {
            return "Descrição do serviço é inválida.";
        }
        if (!validarPrecoServico(servico.getPrecoServico())) {
            return "Preço do serviço é inválido.";
        }
        if (!validarDuracaoServico(servico.getDuracaoServico())) {
            return "Duração do serviço é inválido.";
        }
			return servicoDAO.inserir(servico) ? "OK" : "Falha ao inserir o serviço";
    }


    public String atualizarBO(Servico servico) throws ClassNotFoundException, SQLException {
    	
    	servicoDAO = new ServicoDAO();
    	
    	 if (servico == null) {
             return "Usuário não pode ser nulo.";
         }
         if (!validarIdServico(servico.getIdServico())) {
         	return "ID com formato inválido."; 
         }
         if (!validarIdExistente(servico.getIdServico())) {
         	return "ID não existente.";
         }
         if (!validarTipoServico(servico.getTipoServico())) {
             return "Tipo do serviço é inválido.";
         }
         if (!validarDescricaoServico(servico.getDescricaoServico())) {
             return "Descrição do serviço é inválida.";
         }
         if (!validarPrecoServico(servico.getPrecoServico())) {
             return "Preço do serviço é inválido.";
         }
         if (!validarDuracaoServico(servico.getDuracaoServico())) {
             return "Duração do serviço é inválido.";
         }
			return servicoDAO.atualizar(servico) ? "OK" : "Falha ao atualizar o serviço";
		
    }


    public String deletarBO(String id) throws ClassNotFoundException, SQLException {
    	
    	servicoDAO = new ServicoDAO();
    	
        if (!validarIdServico(id)) {
            return "ID com formato inválido.";
        }
        if (!validarIdExistente(id)) {
            return "ID não existe.";
        }
        
			return servicoDAO.deletar(id) ? "OK" : "Falha ao deletar o serviço";
    }

    public ArrayList<Servico> selecionarBO() throws ClassNotFoundException, SQLException {
    	
    	servicoDAO = new ServicoDAO();
        
		return (ArrayList<Servico>)servicoDAO.selecionar();
		} 
    
	public Servico buscarServicoSolucaoBO(String solucao) throws ClassNotFoundException, SQLException {
	    	
	    	servicoDAO = new ServicoDAO();
	        
			return servicoDAO.buscarPorSolucao(solucao);
			}
	
	public Servico buscarServicoBO(String id) throws ClassNotFoundException, SQLException {
    	
    	servicoDAO = new ServicoDAO();
        
		return servicoDAO.buscarPorId(id);
		}
    
    }
