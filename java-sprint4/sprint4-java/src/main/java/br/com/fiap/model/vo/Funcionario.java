package br.com.fiap.model.vo;

public class Funcionario {
    private String matriculaFuncionario;
    private String nomeFuncionario;
    private Cargo cargo;
    private CentroAutomotivo centroAutomotivo;
    private String disponibilidade;
    private String horarioTrabalho;

    public Funcionario() {
    	
    }
    
    public Funcionario(String matriculaFuncionario, String nomeFuncionario, Cargo cargo, CentroAutomotivo centroAutomotivo, String disponibilidade, String horarioTrabalho) {
        this.matriculaFuncionario = matriculaFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.cargo = cargo;
        this.centroAutomotivo = centroAutomotivo;
        this.disponibilidade = disponibilidade;
        this.horarioTrabalho = horarioTrabalho;
    }


    public String getMatriculaFuncionario() {
		return matriculaFuncionario;
	}

	public void setMatriculaFuncionario(String matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
	}

	public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public CentroAutomotivo getCentroAutomotivo() {
        return centroAutomotivo;
    }

    public void setCentroAutomotivo(CentroAutomotivo centroAutomotivo) {
        this.centroAutomotivo = centroAutomotivo;
    }


	public String getDisponibilidade() {
		return disponibilidade;
	}


	public void setDisponibilidade(String disponibilidade) {
		this.disponibilidade = disponibilidade;
	}


	public String getHorarioTrabalho() {
		return horarioTrabalho;
	}


	public void setHorarioTrabalho(String horarioTrabalho) {
		this.horarioTrabalho = horarioTrabalho;
	}
    
}