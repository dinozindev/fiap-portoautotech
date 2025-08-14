package br.com.fiap.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.conexao.ConexaoFactory;
import br.com.fiap.model.vo.Servico;

public class ServicoDAO {
	private Connection minhaConexao;
	
	// construtor 
		public ServicoDAO() throws ClassNotFoundException, SQLException {
			this.minhaConexao = ConexaoFactory.getConnection();
		}

    public boolean inserir(Servico servico) throws SQLException {
        String sql = "INSERT INTO servico (id_servico, tipo_servico, descricao_servico, preco_servico, duracao) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, servico.getIdServico());
            stmt.setString(2, servico.getTipoServico());
            stmt.setString(3, servico.getDescricaoServico());
            stmt.setDouble(4, servico.getPrecoServico());
            stmt.setInt(5, servico.getDuracaoServico());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public boolean atualizar(Servico servico) throws SQLException {
        String sql = "UPDATE servico SET tipo_servico = ?, descricao_servico = ?, preco_servico = ?, duracao = ? WHERE id_servico = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

             stmt.setString(1, servico.getTipoServico());
             stmt.setString(2, servico.getDescricaoServico());
             stmt.setDouble(3, servico.getPrecoServico());
             stmt.setInt(4, servico.getDuracaoServico());
             stmt.setString(5, servico.getIdServico());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public boolean deletar(String id) throws SQLException {
        String sql = "DELETE FROM servico WHERE id_servico = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, id);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } 
    }

    public List<Servico> selecionar() throws SQLException {
        List<Servico> servicos = new ArrayList<Servico>();
        String sql = "SELECT * FROM servico ORDER BY 1";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Servico servico = new Servico(
                        rs.getString("id_servico"),
                        rs.getString("tipo_servico"),
                        rs.getString("descricao_servico"),
                        rs.getDouble("preco_servico"),
                        rs.getInt("duracao")
                );
                servicos.add(servico);
            }
        }
        return servicos;
    }
    
    public Servico buscarPorId(String id) {
        String sql = "SELECT * FROM servico WHERE id_servico = ?";
        Servico servico = null;
        try (
        		PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
            ps.setString(1, id);  
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String idServico = resultSet.getString("id_servico");
                    String tipoServico = resultSet.getString("tipo_servico");
                    String descricaoServico = resultSet.getString("descricao_servico");
                    double precoServico = resultSet.getDouble("preco_servico");
                    int duracao = resultSet.getInt("duracao");

                    servico = new Servico(idServico, tipoServico, descricaoServico, precoServico, duracao);
                } 
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao obter serviço:");
            e.printStackTrace();
        } 
        return servico;
    }
    
    public Servico buscarPorSolucao(String solucao) {
    	String sql = "SELECT * FROM servico WHERE descricao_servico = ?";
        Servico servico = null;
        try (
        		PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
            ps.setString(1, solucao);  
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String idServico = resultSet.getString("id_servico");
                    String tipoServico = resultSet.getString("tipo_servico");
                    String descricaoServico = resultSet.getString("descricao_servico");
                    double precoServico = resultSet.getDouble("preco_servico");
                    int duracao = resultSet.getInt("duracao");

                    servico = new Servico(idServico, tipoServico, descricaoServico, precoServico, duracao);
                } 
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao obter serviço:");
            e.printStackTrace();
        } 
        return servico;
    }
    
    
}