package br.com.fiap.model.dao;

import br.com.fiap.conexao.ConexaoFactory;
import br.com.fiap.model.vo.CentroAutomotivo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CentroDAO {
    private Connection minhaConexao;

    public CentroDAO() throws ClassNotFoundException, SQLException {
    	this.minhaConexao = ConexaoFactory.getConnection();
    }

    public boolean inserir(CentroAutomotivo centro) throws SQLException {
        String sql = "INSERT INTO centro_automotivo (id_centro, nome_centro, endereco_centro, telefone_centro, horario_funcionamento) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, centro.getIdCentro());
            stmt.setString(2, centro.getNomeCentro());
            stmt.setString(3, centro.getEnderecoCentro());
            stmt.setString(4, centro.getTelefoneCentro());
            stmt.setString(5, centro.getHorarioFuncionamento());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean atualizar(CentroAutomotivo centro) throws SQLException {
        String sql = "UPDATE centro_automotivo SET nome_centro = ?, endereco_centro = ?, telefone_centro = ?, horario_funcionamento = ? WHERE id_centro = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, centro.getNomeCentro());
            stmt.setString(2, centro.getEnderecoCentro());
            stmt.setString(3, centro.getTelefoneCentro());
            stmt.setString(4, centro.getHorarioFuncionamento());
            stmt.setString(5, centro.getIdCentro());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deletar(String idCentro) throws SQLException {
        String sql = "DELETE FROM centro_automotivo WHERE id_centro = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, idCentro);
            return stmt.executeUpdate() > 0;
        }
    }

    public List<CentroAutomotivo> selecionarTodos() throws SQLException {
        List<CentroAutomotivo> centros = new ArrayList<>();
        String sql = "SELECT * FROM centro_automotivo ORDER BY 1";
        try (Statement stmt = minhaConexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                CentroAutomotivo centro = new CentroAutomotivo(
                        rs.getString("id_centro"),
                        rs.getString("nome_centro"),
                        rs.getString("endereco_centro"),
                        rs.getString("telefone_centro"),
                        rs.getString("horario_funcionamento")
                );
                centros.add(centro);
            }
        }
        return centros;
    }
    
    public CentroAutomotivo buscarPorId(String idCentro) {
        String sql = "SELECT * FROM centro_automotivo WHERE id_centro = ?";
        CentroAutomotivo centro = null;
        try (
        	 PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
            ps.setString(1, idCentro);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    idCentro = resultSet.getString("id_centro");
                    String nomeCentro = resultSet.getString("nome_centro");
                    String enderecoCentro = resultSet.getString("endereco_centro");
                    String telefoneCentro = resultSet.getString("telefone_centro");
                    String horarioFuncionamento = resultSet.getString("horario_funcionamento");

                    centro = new CentroAutomotivo(idCentro, nomeCentro, enderecoCentro, telefoneCentro, horarioFuncionamento);
                } 
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao buscar centro:");
            e.printStackTrace();
        } 
        return centro;
    }
}
