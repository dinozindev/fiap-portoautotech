package br.com.fiap.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.conexao.ConexaoFactory;
import br.com.fiap.model.vo.Usuario;

public class UsuarioDAO {
	private Connection minhaConexao;
	
	// construtor 
		public UsuarioDAO() throws ClassNotFoundException, SQLException {
			this.minhaConexao = ConexaoFactory.getConnection();
		}

    public boolean inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (cpf_usuario, nome_usuario, email, telefone, senha) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getCpfUsuario());
            stmt.setString(2, usuario.getNomeUsuario());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getSenha());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public boolean atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nome_usuario = ?, email = ?, telefone = ?, senha = ? WHERE cpf_usuario = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNomeUsuario());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getCpfUsuario());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public boolean deletar(String cpfUsuario) throws SQLException {
        String sql = "DELETE FROM usuario WHERE cpf_usuario = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, cpfUsuario);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } 
    }

    public List<Usuario> selecionar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        String sql = "SELECT * FROM usuario";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("cpf_usuario"),
                        rs.getString("nome_usuario"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("senha")
                );
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
    
    public Usuario buscarPorCPF(String cpf) {
        String sql = "SELECT * FROM usuario WHERE cpf_usuario = ?";
        Usuario usuario = null;
        try (
        		PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
            ps.setString(1, cpf);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String cpfUsuario = resultSet.getString("cpf_usuario");
                    String nomeUsuario = resultSet.getString("nome_usuario");
                    String email = resultSet.getString("email");
                    String telefone = resultSet.getString("telefone");
                    String senha = resultSet.getString("senha");
                    usuario =  new Usuario(cpfUsuario, nomeUsuario, email, telefone, senha);
                } 
            }  
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao retornar usuário:");
            e.printStackTrace();
        } 
        return usuario;  
    }
    
    public Usuario buscarPorNomeUsuario(String nome) {
    	String sql = "SELECT * FROM usuario WHERE nome_usuario = ?";
        Usuario usuario = null;
        try (
        		PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
            ps.setString(1, nome);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String cpfUsuario = resultSet.getString("cpf_usuario");
                    String nomeUsuario = resultSet.getString("nome_usuario");
                    String email = resultSet.getString("email");
                    String telefone = resultSet.getString("telefone");
                    String senha = resultSet.getString("senha");
                    usuario =  new Usuario(cpfUsuario, nomeUsuario, email, telefone, senha);
                } 
            }  
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao retornar usuário:");
            e.printStackTrace();
        } 
        return usuario; 
    }
}
