package br.com.fiap.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.conexao.ConexaoFactory;
import br.com.fiap.model.vo.Usuario;
import br.com.fiap.model.vo.Veiculo;

public class VeiculoDAO {
	private Connection minhaConexao;
	private UsuarioDAO usuarioDAO;
	
	// construtor 
		public VeiculoDAO() throws ClassNotFoundException, SQLException {
			this.minhaConexao = ConexaoFactory.getConnection();
			this.usuarioDAO = new UsuarioDAO();
		}

    public boolean inserir(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO veiculo (placa, marca, modelo, ano, quilometragem, usuario_cpf_usuario) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setInt(4, veiculo.getAno());
            stmt.setDouble(5, veiculo.getQuilometragem());
            stmt.setString(6, veiculo.getUsuario().getCpfUsuario());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public boolean atualizar(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE veiculo SET marca = ?, modelo = ?, ano = ?, quilometragem = ?, usuario_cpf_usuario = ? WHERE placa = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getMarca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());
            stmt.setDouble(4, veiculo.getQuilometragem());
            stmt.setString(5, veiculo.getUsuario().getCpfUsuario());
            stmt.setString(6, veiculo.getPlaca());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public boolean deletar(String placaVeiculo) throws SQLException {
        String sql = "DELETE FROM veiculo WHERE placa = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, placaVeiculo);

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public List<Veiculo> selecionar() throws SQLException {
        List<Veiculo> veiculos = new ArrayList<Veiculo>();
        String sql = "SELECT * FROM veiculo";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("ano"),
                        rs.getString("placa"),
                        rs.getDouble("quilometragem"),
                        usuarioDAO.buscarPorCPF(rs.getString("usuario_cpf_usuario"))
                );
                veiculos.add(veiculo);
            }
        }
        return veiculos;
    }
    
    public Veiculo buscarPorPlaca(String placa) {
        String sql = "SELECT * FROM veiculo WHERE placa = ?";
        Veiculo veiculo = null; 
        try (
        		PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
            ps.setString(1, placa);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String marca = resultSet.getString("marca");
                    String modelo = resultSet.getString("modelo");
                    int ano = resultSet.getInt("ano");
                    double quilometragem = resultSet.getDouble("quilometragem");
                    Usuario usuario = usuarioDAO.buscarPorCPF(resultSet.getString("usuario_cpf_usuario"));
                    veiculo = new Veiculo(marca, modelo, ano, placa, quilometragem, usuario);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao retornar veículo:");
            e.printStackTrace();
        } 
        return veiculo; 
    }
    
    public List<Veiculo> selecionarVeiculosUsuario(String cpf) throws SQLException {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculo WHERE usuario_cpf_usuario = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Veiculo veiculo = new Veiculo(
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("ano"),
                        rs.getString("placa"),
                        rs.getDouble("quilometragem"),
                        usuarioDAO.buscarPorCPF(rs.getString("usuario_cpf_usuario"))
                    );
                    veiculos.add(veiculo);
                }
            }
        }
        return veiculos;
    }
}
