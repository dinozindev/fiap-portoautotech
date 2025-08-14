package br.com.fiap.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.conexao.ConexaoFactory;
import br.com.fiap.model.vo.Peca;

public class PecaDAO {
	private Connection minhaConexao;
	
	// construtor 
		public PecaDAO() throws ClassNotFoundException, SQLException {
			this.minhaConexao = ConexaoFactory.getConnection();
		}

    public boolean inserir(Peca peca) throws SQLException {
        String sql = "INSERT INTO peca (id_peca, disponibilidade_peca, nome_peca, preco_peca) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, peca.getIdPeca());
            stmt.setInt(2, peca.getDisponibilidadePeca());
            stmt.setString(3, peca.getNomePeca());
            stmt.setDouble(4, peca.getPrecoPeca());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public boolean atualizar(Peca peca) throws SQLException {
        String sql = "UPDATE peca SET disponibilidade_peca = ?, nome_peca = ?, preco_peca = ? WHERE id_peca = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

             stmt.setInt(1, peca.getDisponibilidadePeca());
             stmt.setString(2, peca.getNomePeca());
             stmt.setDouble(3, peca.getPrecoPeca());
             stmt.setString(4, peca.getIdPeca());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public boolean deletar(String id) throws SQLException {
        String sql = "DELETE FROM peca WHERE id_peca = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, id);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } 
    }

    public List<Peca> selecionar() throws SQLException {
        List<Peca> pecas = new ArrayList<Peca>();
        String sql = "SELECT * FROM peca ORDER BY 1";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Peca peca = new Peca(
                        rs.getString("id_peca"),
                        rs.getInt("disponibilidade_peca"),
                        rs.getString("nome_peca"),
                        rs.getDouble("preco_peca")
                );
                pecas.add(peca);
            }
        }
        return pecas;
    }
    
    public Peca buscarPorId(String id) {
        String sql = "SELECT * FROM peca WHERE id_peca = ?";
        Peca peca = null;

        try (
             PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String idPeca = rs.getString("id_peca");
                    int disponibilidadePeca = rs.getInt("disponibilidade_peca");
                    String nomePeca = rs.getString("nome_peca");
                    double precoPeca = rs.getDouble("preco_peca");

                    peca = new Peca(idPeca, disponibilidadePeca, nomePeca, precoPeca);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao buscar peça:");
            e.printStackTrace();
        } 
        return peca;
    }
    
    public Peca buscarPorNome(String nomePeca) {
        String sql = "SELECT * FROM peca WHERE nome_peca = ?";
        Peca peca = null;

        try (
             PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
            ps.setString(1, nomePeca);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String idPeca = rs.getString("id_peca");
                    int disponibilidadePeca = rs.getInt("disponibilidade_peca");
                    String nome = rs.getString("nome_peca");
                    double precoPeca = rs.getDouble("preco_peca");

                    peca = new Peca(idPeca, disponibilidadePeca, nome, precoPeca);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao buscar peça:");
            e.printStackTrace();
        } 
        return peca;
    }
}