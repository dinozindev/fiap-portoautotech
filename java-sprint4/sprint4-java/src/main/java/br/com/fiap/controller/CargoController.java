package br.com.fiap.controller;

import br.com.fiap.model.bo.CargoBO;
import br.com.fiap.model.vo.Cargo;

import java.sql.SQLException;
import java.util.List;

public class CargoController {
    private CargoBO cargoBO;

    public CargoController() {
       this.cargoBO = new CargoBO();  
    }

    public String adicionarCargo(Cargo cargo) {
        try {
            return cargoBO.inserirBO(cargo);
        } catch (SQLException e) {
            return "Erro ao adicionar cargo: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao adicionar cargo: " + e.getMessage();
			
		}
    }

    public String atualizarCargo(Cargo cargo) {
        try {
            return cargoBO.atualizarBO(cargo);
        } catch (SQLException e) {
            return "Erro ao atualizar cargo: " + e.getMessage();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	return "Erro ao atualizar cargo: " + e.getMessage();
		}
    }

    public String deletarCargo(String idCargo) {
        try {
            return cargoBO.deletarBO(idCargo);
        } catch (SQLException e) {
            return "Erro ao deletar cargo: " + e.getMessage();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "Erro ao deletar cargo: " + e.getMessage();
		}
    }

    public List<Cargo> obterTodosCargos() {
        try {
            return cargoBO.selecionarBO();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public Cargo obterCargo(String id) {
    	try {
            return cargoBO.buscarBO(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
}
