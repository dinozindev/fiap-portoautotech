package br.com.fiap.model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import br.com.fiap.model.dao.FuncionarioDAO;
import br.com.fiap.model.vo.Funcionario;

public class FuncionarioBO {

    FuncionarioDAO funcionarioDAO = null;
    CargoBO cargoBO = null;
    CentroBO centroBO = null;

    public boolean validarMatriculaExistente(String matricula) throws ClassNotFoundException, SQLException {
    	funcionarioDAO = new FuncionarioDAO();
    	return funcionarioDAO.buscarPorId(matricula) != null;
    }
    private boolean validarMatricula(String matricula) {
    	return Pattern.matches("M\\d{5}", matricula);
    }
    private boolean validarNome(String nome) {
    	return nome != null && !nome.trim().isEmpty() && nome.length() <= 70;
    }
    private boolean validarHorarioTrabalho(String horarioTrabalho) {
        return Pattern.matches("^(?:[01]\\d|2[0-3]):[0-5]\\d\\s*-\\s*(?:[01]\\d|2[0-3]):[0-5]\\d$", horarioTrabalho);
    }
    
    private boolean validarDisponibilidade(String disponibilidade) {
    	return "S".equals(disponibilidade) || "N".equals(disponibilidade);
    }
    
    public String inserirBO(Funcionario funcionario) throws ClassNotFoundException, SQLException {
    	
    	funcionarioDAO = new FuncionarioDAO();
    	centroBO = new CentroBO();
    	cargoBO = new CargoBO();
    	
        if (funcionario == null) {
            return "Funcionário não pode ser nulo.";
        }
        if (!validarMatricula(funcionario.getMatriculaFuncionario())) {
        	return "Matrícula com formato inválido."; 
        }
        if (validarMatriculaExistente(funcionario.getMatriculaFuncionario())) {
        	return "Funcionário já existente.";
        }
        if (!validarNome(funcionario.getNomeFuncionario())) {
            return "Nome do funcionário é inválido.";
        }
        if (!validarHorarioTrabalho(funcionario.getHorarioTrabalho())) {
            return "Horário de trabalho é inválido.";
        }
        if (!validarDisponibilidade(funcionario.getDisponibilidade())) {
            return "Opção inválida para disponibilidade ('S' ou 'N').";
        }
        if (!cargoBO.validarIdExistente(funcionario.getCargo().getIdCargo())) {
            return "Cargo inexistente.";
        }
        if (!centroBO.validarIdExistente(funcionario.getCentroAutomotivo().getIdCentro())) {
            return "Centro Automotivo inexistente.";
        }
			return funcionarioDAO.inserir(funcionario) ? "OK" : "Falha ao inserir o funcionário";
    }


    public String atualizarBO(Funcionario funcionario) throws ClassNotFoundException, SQLException {
    	
    	funcionarioDAO = new FuncionarioDAO();
    	centroBO = new CentroBO();
    	cargoBO = new CargoBO();
    	
        if (funcionario == null) {
            return "Funcionário não pode ser nulo.";
        }
        if (!validarMatricula(funcionario.getMatriculaFuncionario())) {
        	return "Matrícula com formato inválido."; 
        }
        if (!validarMatriculaExistente(funcionario.getMatriculaFuncionario())) {
        	return "Funcionário inexistente.";
        }
        if (!validarNome(funcionario.getNomeFuncionario())) {
            return "Nome do funcionário é inválido.";
        }
        if (!validarHorarioTrabalho(funcionario.getHorarioTrabalho())) {
            return "Horário de trabalho é inválido.";
        }
        if (!validarDisponibilidade(funcionario.getDisponibilidade())) {
            return "Opção inválida para disponibilidade ('S' ou 'N').";
        }
        if (!cargoBO.validarIdExistente(funcionario.getCargo().getIdCargo())) {
            return "Cargo inexistente.";
        }
        if (!centroBO.validarIdExistente(funcionario.getCentroAutomotivo().getIdCentro())) {
            return "Centro Automotivo inexistente.";
        }
			return funcionarioDAO.atualizar(funcionario) ? "OK" : "Falha ao atualizar o funcionário";
		
    }


    public String deletarBO(String matricula) throws ClassNotFoundException, SQLException {
    	
    	funcionarioDAO = new FuncionarioDAO();
    	
        if (!validarMatricula(matricula)) {
            return "Matrícula com formato inválido.";
        }
        if (!validarMatriculaExistente(matricula)) {
            return "Matrícula não existe.";
        }
        
			return funcionarioDAO.deletar(matricula) ? "OK" : "Falha ao deletar o funcionário";
    }

    public ArrayList<Funcionario> selecionarBO() throws ClassNotFoundException, SQLException {
    	
    	funcionarioDAO = new FuncionarioDAO();
        
		return (ArrayList<Funcionario>)funcionarioDAO.selecionar();
		} 
    

    }