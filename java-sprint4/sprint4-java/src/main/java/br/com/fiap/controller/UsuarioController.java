package br.com.fiap.controller;

import br.com.fiap.model.bo.UsuarioBO;
import br.com.fiap.model.vo.Usuario;
import java.sql.SQLException;
import java.util.List;

public class UsuarioController {
    private UsuarioBO usuarioBO;

    public UsuarioController() {
       this.usuarioBO = new UsuarioBO();  
    }

    public String adicionarUsuario(Usuario usuario) {
        try {
            return usuarioBO.inserirBO(usuario);
        } catch (SQLException e) {
            return "Erro ao adicionar usuario: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao adicionar usuario: " + e.getMessage();
			
		}
    }

    public String atualizarUsuario(Usuario usuario) {
        try {
            return usuarioBO.atualizarBO(usuario);
        } catch (SQLException e) {
            return "Erro ao atualizar usuario: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao atualizar usuario: " + e.getMessage();
		}
    }

    public String deletarUsuario(String cpf) {
        try {
            return usuarioBO.deletarBO(cpf);
        } catch (SQLException e) {
            return "Erro ao deletar usuario: " + e.getMessage();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "Erro ao deletar usuario: " + e.getMessage();
		}
    }

    public List<Usuario> obterTodosUsuarios() {
        try {
            return usuarioBO.selecionarBO();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public Usuario obterUsuario(String cpf) {
    	try {
            return usuarioBO.buscarBO(cpf);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
}

