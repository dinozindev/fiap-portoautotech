
package br.com.fiap.model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import br.com.fiap.model.dao.UsuarioDAO;
import br.com.fiap.model.vo.Usuario;

public class UsuarioBO {

    UsuarioDAO usuarioDAO = null;

    public boolean validarCpfExistente(String cpf) throws ClassNotFoundException, SQLException {
    	usuarioDAO = new UsuarioDAO();
    	return usuarioDAO.buscarPorCPF(cpf) != null;
    }
    private boolean validarCpf(String cpf) {
    	return Pattern.matches("^\\d{11}$", cpf);
    }
    private boolean validarNomeExistente(String nomeUsuario) {
    	return usuarioDAO.buscarPorNomeUsuario(nomeUsuario) != null;
    }
    private boolean validarNomeUsuario(String nomeUsuario) {
        return nomeUsuario != null && !nomeUsuario.trim().isEmpty() && nomeUsuario.length() <= 80;
    }
    private boolean validarEmailUsuario(String emailUsuario) {
        return emailUsuario != null && Pattern.matches("^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", emailUsuario) && emailUsuario.length() <= 255;
    }
    private boolean validarTelefoneUsuario(String telefoneUsuario) {
        return telefoneUsuario != null && Pattern.matches("^\\d{11}$", telefoneUsuario);
    }

    private boolean validarSenha(String senhaUsuario) {
        return senhaUsuario.length() > 6;
    }

    public String inserirBO(Usuario usuario) throws ClassNotFoundException, SQLException {
    	
    	usuarioDAO = new UsuarioDAO();
    	
        if (usuario == null) {
            return "Usuário não pode ser nulo.";
        }
        if (!validarCpf(usuario.getCpfUsuario())) {
        	return "CPF com formato inválido."; 
        }
        if (validarCpfExistente(usuario.getCpfUsuario())) {
        	return "CPF já existente.";
        }
        if (!validarNomeUsuario(usuario.getNomeUsuario())) {
            return "Nome do usuário é inválido.";
        }
        if (validarNomeExistente(usuario.getNomeUsuario())) {
            return "Nome do usuário já está cadastrado.";
        }
        if (!validarEmailUsuario(usuario.getEmail())) {
            return "Formato do email é inválido.";
        }
        if (!validarTelefoneUsuario(usuario.getTelefone())) {
            return "Formato do telefone é inválido.";
        }
        if (!validarSenha(usuario.getSenha())) {
            return "Senha deve ter mais de 6 caracteres.";
        }
			return usuarioDAO.inserir(usuario) ? "OK" : "Falha ao inserir o usuário";
    }


    public String atualizarBO(Usuario usuario) throws ClassNotFoundException, SQLException {
    	
    	usuarioDAO = new UsuarioDAO();
    	
    	if (usuario == null) {
            return "Usuário não pode ser nulo.";
        }
        if (!validarCpf(usuario.getCpfUsuario())) {
        	return "CPF com formato inválido."; 
        }
        if (!validarCpfExistente(usuario.getCpfUsuario())) {
        	return "CPF não existe.";
        }
        if (!validarNomeUsuario(usuario.getNomeUsuario())) {
            return "Nome do usuário é inválido.";
        }
        Usuario usuarioAtual = usuarioDAO.buscarPorCPF(usuario.getCpfUsuario());
        
        if (!usuarioAtual.getNomeUsuario().equals(usuario.getNomeUsuario())) {
            if (validarNomeExistente(usuario.getNomeUsuario())) {
                return "Nome do usuário já está cadastrado.";
            }
        }
        if (!validarEmailUsuario(usuario.getEmail())) {
            return "Formato do email é inválido.";
        }
        if (!validarTelefoneUsuario(usuario.getTelefone())) {
            return "Formato do telefone é inválido.";
        }
        if (!validarSenha(usuario.getSenha())) {
            return "Senha deve ter mais de 6 caracteres.";
        }
			return usuarioDAO.atualizar(usuario) ? "OK" : "Falha ao atualizar o usuário";
		
    }


    public String deletarBO(String cpf) throws ClassNotFoundException, SQLException {
    	
    	usuarioDAO = new UsuarioDAO();
    	
        if (!validarCpf(cpf)) {
            return "CPF com formato inválido.";
        }
        if (!validarCpfExistente(cpf)) {
            return "CPF não existe.";
        }
        
			return usuarioDAO.deletar(cpf) ? "OK" : "Falha ao deletar o usuário";
    }

    public ArrayList<Usuario> selecionarBO() throws ClassNotFoundException, SQLException {
    	
    	usuarioDAO = new UsuarioDAO();
        
		return (ArrayList<Usuario>)usuarioDAO.selecionar();
		} 
    
    public Usuario buscarBO(String cpf) throws ClassNotFoundException, SQLException {
		usuarioDAO = new UsuarioDAO();
		
		return usuarioDAO.buscarPorCPF(cpf);
}
    }

