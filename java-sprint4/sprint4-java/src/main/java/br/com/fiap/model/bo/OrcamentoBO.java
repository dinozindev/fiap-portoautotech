package br.com.fiap.model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import br.com.fiap.model.dao.OrcamentoDAO;
import br.com.fiap.model.vo.Diagnostico;
import br.com.fiap.model.vo.Orcamento;

public class OrcamentoBO {

   OrcamentoDAO orcamentoDAO = null;
   DiagnosticoBO diagnosticoBO = null;
   
   public boolean validarIdExistente(String id) throws ClassNotFoundException, SQLException {
	   orcamentoDAO = new OrcamentoDAO();
	   return orcamentoDAO.buscarPorId(id) != null;
   }
    
    private boolean validarStatusOrcamento(String status) {
    	return status.equals("PENDENTE") || status.equals("APROVADO") || status.equals("REJEITADO");
    }
    
    private boolean validarDescricao(String descricao) {
    	return descricao != null && !descricao.trim().isEmpty() && descricao.length() <= 255;
    }
    
    private boolean validarValorTotal(double valorTotal) {
     	String valorStr = String.valueOf(valorTotal);
        return valorTotal > 0 && Pattern.matches("^\\d{1,7}(\\.\\d{1,2})?$", valorStr);
    }
    
    public String inserirBO(String idDiagnostico, Orcamento orcamento) throws SQLException, ClassNotFoundException {
        orcamentoDAO = new OrcamentoDAO();
        diagnosticoBO = new DiagnosticoBO();

        if (!diagnosticoBO.validarIdExistente(idDiagnostico)) {
            return "Diagnóstico inexistente.";
        }
        
        Diagnostico diagnostico = diagnosticoBO.buscarDiagnosticoBO(idDiagnostico);

        orcamento.setIdOrcamento(UUID.randomUUID().toString());
    	orcamento.setValorTotal(diagnostico.getServico().getPrecoServico());
    	orcamento.setDescricaoOrcamento(diagnostico.getSolucao());
    	orcamento.setStatusOrcamento("PENDENTE");


        if (!validarDescricao(orcamento.getDescricaoOrcamento())) {
            return "Descrição é inválida.";
        }
        if (!validarValorTotal(orcamento.getValorTotal())) {
            return "Valor deve ter no máximo 7 casas inteiras e 2 casas decimais.";
        }
        if (!validarStatusOrcamento(orcamento.getStatusOrcamento())) {
            return "Status do orçamento é inválido.";
        }

        if (!orcamentoDAO.inserir(orcamento)) {
            return "Falha ao inserir o orçamento.";
        }

        String resultadoAssociacao = diagnosticoBO.adicionarOrcamentoAoDiagnosticoBO(idDiagnostico, orcamento.getIdOrcamento());
        if (!resultadoAssociacao.equals("OK")) {
            return resultadoAssociacao;
        }

        return "OK";
    }

    public String atualizarBO(String idOrcamento, String status) throws SQLException, ClassNotFoundException {
    	
    	orcamentoDAO = new OrcamentoDAO();
    	
    	if(!validarIdExistente(idOrcamento)) {
    		return "Orçamento inexistente.";
    	}
    	
    	 if (!validarStatusOrcamento(status)) {
         	return "Status inválido.";
         }
    	
        return orcamentoDAO.atualizarStatus(idOrcamento, status) ? "OK" : "Falha ao atualizar o status do orçamento";
    }

    public String deletarBO(String idOrcamento) throws SQLException, ClassNotFoundException {
    	
    	orcamentoDAO = new OrcamentoDAO();
    	
    	if (!validarIdExistente(idOrcamento)) {
    		return "ID inexistente.";
    	}
    	
        return orcamentoDAO.deletar(idOrcamento) ? "OK" : "Falha ao deletar o orçamento.";
    }
    

    public List<Orcamento> selecionarBO() throws SQLException, ClassNotFoundException {
    	
    	orcamentoDAO = new OrcamentoDAO();
    	
        return (ArrayList<Orcamento>)orcamentoDAO.selecionar();
    }
    
    public Orcamento buscarOrcamentoBO(String idOrcamento) throws ClassNotFoundException, SQLException {
    	orcamentoDAO = new OrcamentoDAO();
    	
    	if (!validarIdExistente(idOrcamento)) {
    		return null;
    	} 
    	
    	return orcamentoDAO.buscarPorId(idOrcamento);
    }
}