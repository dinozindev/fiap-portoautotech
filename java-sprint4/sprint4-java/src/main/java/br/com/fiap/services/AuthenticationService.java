package br.com.fiap.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.conexao.ConexaoFactory;
import br.com.fiap.model.vo.Usuario;

public class AuthenticationService {
	
	private Connection minhaConexao;
	
	public AuthenticationService() {
		try {
			this.minhaConexao = ConexaoFactory.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Usuario autenticarUsuario(Usuario usuario) {
	    String sql = "SELECT * FROM usuario WHERE nome_usuario = ? AND senha = ?";

	    try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
	        stmt.setString(1, usuario.getNomeUsuario());
	        stmt.setString(2, usuario.getSenha());

	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) { // Usuário encontrado
	            Usuario usuarioAutenticado = new Usuario();
	            usuarioAutenticado.setCpfUsuario(rs.getString("cpf_usuario"));
	            usuarioAutenticado.setNomeUsuario(rs.getString("nome_usuario"));
	            usuarioAutenticado.setEmail(rs.getString("email"));
	            usuarioAutenticado.setSenha(rs.getString("senha"));
	            usuarioAutenticado.setTelefone(rs.getString("telefone"));

	            return usuarioAutenticado;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null; // Usuário não encontrado ou erro na consulta
	}
}
