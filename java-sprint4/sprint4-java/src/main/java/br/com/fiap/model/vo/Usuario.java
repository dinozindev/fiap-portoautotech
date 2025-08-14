package br.com.fiap.model.vo;

public class Usuario {
	
	private String cpfUsuario;
    private String nomeUsuario;
    private String senha;
    private String email;
    private String telefone;
    
    public Usuario() {
    	
    }

    public Usuario(String cpfUsuario, String nomeUsuario, String email, String telefone, String senha) {
    	this.cpfUsuario = cpfUsuario;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}