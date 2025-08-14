package br.com.fiap.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.conexao.ConexaoFactory;
import br.com.fiap.model.vo.Cargo;

public class CargoDAO {
	private Connection minhaConexao;
	
	// construtor 
		public CargoDAO() throws ClassNotFoundException, SQLException {
			this.minhaConexao = ConexaoFactory.getConnection();
		}

    public boolean inserir(Cargo cargo) throws SQLException {
        String sql = "INSERT INTO cargo (id_cargo, nome_cargo, area_cargo, descricao_cargo, salario_cargo) VALUES (?, ?, ?, ?, ?)";
        try (
             PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, cargo.getIdCargo());
            stmt.setString(2, cargo.getNomeCargo());
            stmt.setString(3, cargo.getAreaCargo());
            stmt.setString(4, cargo.getDescricaoCargo());
            stmt.setDouble(5, cargo.getSalarioCargo());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public boolean atualizar(Cargo cargo) throws SQLException {
        String sql = "UPDATE cargo SET nome_cargo = ?, area_cargo = ?, descricao_cargo = ?, salario_cargo = ? WHERE id_cargo = ?";
        try (
             PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, cargo.getNomeCargo());
            stmt.setString(2, cargo.getAreaCargo());
            stmt.setString(3, cargo.getDescricaoCargo());
            stmt.setDouble(4, cargo.getSalarioCargo());
            stmt.setString(5, cargo.getIdCargo());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public boolean deletar(String idCargo) throws SQLException {
        String sql = "DELETE FROM cargo WHERE id_cargo = ?";
        try (
             PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, idCargo);

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public List<Cargo> selecionar() throws SQLException {
        List<Cargo> cargos = new ArrayList<>();
        String sql = "SELECT * FROM cargo ORDER BY 1";
        try (
             PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cargo cargo = new Cargo(
                        rs.getString("id_cargo"),
                        rs.getString("nome_cargo"),
                        rs.getString("area_cargo"),
                        rs.getString("descricao_cargo"),
                        rs.getDouble("salario_cargo")
                );
                cargos.add(cargo);
            }
        }
        return cargos;
    }
    
    public Cargo buscarPorId(String idCargo) {
        String sql = "SELECT * FROM cargo WHERE id_cargo = ?";
        Cargo cargo = null;
        try (
        		PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
            ps.setString(1, idCargo);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String id = resultSet.getString("id_cargo");
                    String nome = resultSet.getString("nome_cargo");
                    String area = resultSet.getString("area_cargo");
                    String descricao = resultSet.getString("descricao_cargo");
                    double salario = resultSet.getDouble("salario_cargo");

                    cargo = new Cargo(id, nome, area, descricao, salario);
                } 
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao obter cargo:");
            e.printStackTrace();
        } 
        return cargo;
    }
}
