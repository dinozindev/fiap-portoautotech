package br.com.fiap.model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import br.com.fiap.model.dao.DiagnosticoDAO;
import br.com.fiap.model.vo.Diagnostico;
import br.com.fiap.model.vo.Servico;

public class DiagnosticoBO {

   DiagnosticoDAO diagnosticoDAO = null;
   ServicoBO servicoBO = null;
   VeiculoBO veiculoBO = null;
   
   public boolean validarIdExistente(String id) throws ClassNotFoundException, SQLException {
	   diagnosticoDAO = new DiagnosticoDAO();
	   return diagnosticoDAO.buscarPorId(id) != null;
   }

    private boolean validarDescricaoSintomas(String descricao) {
    	return descricao != null && !descricao.trim().isEmpty() && descricao.length() <= 255;
    }
    
    private boolean validarCategoria(String categoria) {
    	return categoria != null && !categoria.trim().isEmpty() && categoria.length() <= 50;
    }

    private boolean validarSolucao(String solucao) {
    	return solucao != null && !solucao.trim().isEmpty() && solucao.length() <= 255;
    }
    
    private boolean validarStatusDiagnostico(String status) {
    	return status.equals("EM ANÁLISE") || status.equals("ANALISADO");
    }
    
    
    
    public String inserirBO(Diagnostico diagnostico) throws SQLException, ClassNotFoundException {
    	
    	diagnosticoDAO = new DiagnosticoDAO();
    	servicoBO = new ServicoBO();
    	veiculoBO = new VeiculoBO();
    	
    	diagnostico.setIdDiagnostico(UUID.randomUUID().toString());
    	Servico servico = servicoBO.buscarServicoSolucaoBO(diagnostico.getSolucao());
    	diagnostico.setStatus("ANALISADO");
    	
    	if (servico == null) {
    		return "Nenhum serviço encontrado para o diagnóstico.";
    	} else {
    		diagnostico.setServico(servico);
    	}
    	
        if (validarIdExistente(diagnostico.getIdDiagnostico())) {
        	return "ID já existente.";
        }
        if (!validarDescricaoSintomas(diagnostico.getDescricaoSintomas())) {
            return "Descrição dos sintomas é inválida.";
        }
        if (!validarCategoria(diagnostico.getCategoria())) {
        	return "Categoria é inválida";
        }
        if (!validarSolucao(diagnostico.getSolucao())) {
        	return "Solução do problema é inválida.";
        }
        if (!veiculoBO.validarVeiculoExistente(diagnostico.getVeiculo().getPlaca())) {
            return "Veículo inexistente.";
        }
        if (!servicoBO.validarIdExistente(diagnostico.getServico().getIdServico())) {
        	return "Serviço inexistente";
        }
        if (!validarStatusDiagnostico(diagnostico.getStatus())) {
        	return "Status inválido.";
        }

        return diagnosticoDAO.inserir(diagnostico) ? "OK" : "Falha ao inserir o diagnóstico.";
    }

    public String atualizarBO(String idDiagnostico, String status) throws SQLException, ClassNotFoundException {
    	
    	diagnosticoDAO = new DiagnosticoDAO();
    	
    	 if (!validarStatusDiagnostico(status)) {
         	return "Status inválido.";
         }
    	
        return diagnosticoDAO.atualizarStatusDiagnostico(idDiagnostico, status) ? "OK" : "Falha ao atualizar o diagnóstico";
    }

    public String deletarBO(String idDiagnostico) throws SQLException, ClassNotFoundException {
    	
    	diagnosticoDAO = new DiagnosticoDAO();
    	
    	if (!validarIdExistente(idDiagnostico)) {
    		return "ID inexistente.";
    	}
    	
        return diagnosticoDAO.deletar(idDiagnostico) ? "OK" : "Falha ao deletar o diagnóstico.";
    }
    
    public Diagnostico buscarDiagnosticoBO(String idDiagnostico) throws ClassNotFoundException, SQLException {
    	diagnosticoDAO = new DiagnosticoDAO();
    	
    	if (!validarIdExistente(idDiagnostico)) {
    		return null;
    	} 
    	
    	return diagnosticoDAO.buscarPorId(idDiagnostico);
    }

    public List<Diagnostico> selecionarBO() throws SQLException, ClassNotFoundException {
    	
    	diagnosticoDAO = new DiagnosticoDAO();
    	
        return (ArrayList<Diagnostico>)diagnosticoDAO.selecionar();
    }
    
    public List<Diagnostico> selecionarDiagnosticosUsuarioBO(String cpf) throws SQLException, ClassNotFoundException {
    	
    	diagnosticoDAO = new DiagnosticoDAO();
    	
        return (ArrayList<Diagnostico>)diagnosticoDAO.buscarDiagnosticosPorCPF(cpf);
    }
    
    public String adicionarOrcamentoAoDiagnosticoBO(String idDiagnostico, String idOrcamento) throws SQLException, ClassNotFoundException {
    	
    	diagnosticoDAO = new DiagnosticoDAO();
    	
    	 if (!validarIdExistente(idDiagnostico)) {
         	return "ID inexistente.";
         }
    	 if (buscarDiagnosticoBO(idDiagnostico).getOrcamento() != null) {
    		 return "Diagnóstico já possui orçamento atrelado.";
    	 }
    	
        return diagnosticoDAO.inserirOrcamentoNoDiagnostico(idDiagnostico, idOrcamento) ? "OK" : "Falha ao adicionar o orçamento ao diagnóstico";
    }
    
}