package br.com.fiap.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.conexao.ConexaoFactory;
import br.com.fiap.model.vo.Orcamento;

public class OrcamentoDAO {

	private Connection minhaConexao;
	
	public OrcamentoDAO() throws ClassNotFoundException, SQLException {
        this.minhaConexao = ConexaoFactory.getConnection();
        }
	
	public boolean inserir(Orcamento orcamento) throws SQLException {
        String sql = "INSERT INTO orcamento (id_orcamento, descricao_orcamento, valor_total, status_orcamento) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, orcamento.getIdOrcamento());
            stmt.setString(2, orcamento.getDescricaoOrcamento());
            stmt.setDouble(3, orcamento.getValorTotal());
            stmt.setString(4, orcamento.getStatusOrcamento());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }
	
	 public boolean atualizarStatus(String idOrcamento, String status) throws SQLException {
	    		String sql = "UPDATE orcamento SET status_orcamento = ? WHERE id_orcamento = ?";
	            try (PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
	            	ps.setString(1, status);
	                ps.setString(2, idOrcamento);
	                int rows = ps.executeUpdate();
	                return rows > 0;
	            } 
	    	}
	 
	 public boolean deletar(String id) throws SQLException {
	        String sql = "DELETE FROM orcamento WHERE id_orcamento = ?";
	        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
	            stmt.setString(1, id);
	            int rows = stmt.executeUpdate();
	            return rows > 0;
	        } 
	    }

	    public List<Orcamento> selecionar() throws SQLException {
	        List<Orcamento> orcamentos = new ArrayList<Orcamento>();
	        String sql = "SELECT * FROM orcamento ORDER BY 1";
	        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Orcamento orcamento = new Orcamento(
	                        rs.getString("id_orcamento"),
	                        rs.getString("descricao_orcamento"),
	                        rs.getDouble("valor_total"),
	                        rs.getString("status_orcamento")
	                );
	                orcamentos.add(orcamento);
	            }
	        }
	        return orcamentos;
	    }

	
	public Orcamento buscarPorId(String id)  {
        String sql = "SELECT * FROM orcamento WHERE id_orcamento = ?";
        Orcamento orcamento = null;
        try (
        		PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String idOrcamento = rs.getString("id_orcamento");
                String descricao = rs.getString("descricao_orcamento");
                double valorTotal = rs.getDouble("valor_total");
                String status = rs.getString("status_orcamento");

                orcamento = new Orcamento(idOrcamento, descricao, valorTotal, status);
            }
          }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao buscar orçamento:");
            e.printStackTrace();
        } 
        return orcamento;
    }
}
