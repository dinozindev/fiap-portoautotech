package br.com.fiap.controller;

import br.com.fiap.model.bo.FuncionarioBO;
import br.com.fiap.model.vo.Funcionario;

import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {
    private FuncionarioBO funcionarioBO;

    public FuncionarioController() {
       this.funcionarioBO = new FuncionarioBO();  
    }

    public String adicionarFuncionario(Funcionario funcionario) {
        try {
            return funcionarioBO.inserirBO(funcionario);
        } catch (SQLException e) {
            return "Erro ao adicionar Funcionario: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao adicionar Funcionario: " + e.getMessage();
			
		}
    }

    public String atualizarFuncionario(Funcionario funcionario) {
        try {
            return funcionarioBO.atualizarBO(funcionario);
        } catch (SQLException e) {
            return "Erro ao atualizar Funcionario: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao atualizar Funcionario: " + e.getMessage();
		}
    }

    public String deletarFuncionario(String idFuncionario) {
        try {
            return funcionarioBO.deletarBO(idFuncionario);
        } catch (SQLException e) {
            return "Erro ao deletar Funcionario: " + e.getMessage();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "Erro ao deletar Funcionario: " + e.getMessage();
		}
    }

    public List<Funcionario> obterTodosFuncionarios() {
        try {
            return funcionarioBO.selecionarBO();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
}