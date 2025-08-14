package br.com.fiap.model.bo;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import br.com.fiap.model.dao.CargoDAO;
import br.com.fiap.model.vo.Cargo;

public class CargoBO {

    private CargoDAO cargoDAO = null;

    private boolean validarIdCargo(String idCargo) {
    	return Pattern.matches("CG\\d{2}", idCargo);
    }
    public boolean validarIdExistente(String idCargo) throws ClassNotFoundException, SQLException {
    	cargoDAO = new CargoDAO();
    	return cargoDAO.buscarPorId(idCargo) != null;
    }
    private boolean validarNomeCargo(String nomeCargo) {
        return nomeCargo != null && !nomeCargo.trim().isEmpty() && nomeCargo.length() <= 50;
    }

    private boolean validarDescricaoCargo(String descricaoCargo) {
        return descricaoCargo != null && descricaoCargo.length() <= 255;
    }

    private boolean validarAreaCargo(String areaCargo) {
        return areaCargo != null && Pattern.matches("^[a-zA-Z ]+$", areaCargo) && areaCargo.length() <= 50;
    }

    private boolean validarSalario(double salarioCargo) {
    	String valorStr = String.valueOf(salarioCargo);
        return salarioCargo > 0 && Pattern.matches("^\\d{1,7}(\\.\\d{1,2})?$", valorStr);
    }

    public String inserirBO(Cargo cargo) throws SQLException, ClassNotFoundException {
    	
    	cargoDAO = new CargoDAO();
    	
        if (cargo == null) {
            return "Cargo não pode ser nulo.";
        }
        if (!validarIdCargo(cargo.getIdCargo())) {
        	return "Id com formato inválido."; 
        }
        if (validarIdExistente(cargo.getIdCargo())) {
        	return "ID já existente.";
        }
        if (!validarNomeCargo(cargo.getNomeCargo())) {
            return "Nome do cargo é inválido.";
        }
        if (!validarDescricaoCargo(cargo.getDescricaoCargo())) {
            return "Descrição do cargo é inválida.";
        }
        if (!validarAreaCargo(cargo.getAreaCargo())) {
            return "Área do cargo é inválida.";
        }
        if (!validarSalario(cargo.getSalarioCargo())) {
            return "Salário deve ser positivo e ter no máximo 7 casas inteiras e 2 casas decimais.";
        }
        	return cargoDAO.inserir(cargo) ? "OK" : "Falha ao inserir o cargo";
    }


    public String atualizarBO(Cargo cargo) throws SQLException, ClassNotFoundException {
    	
    	cargoDAO = new CargoDAO();
    	
        if (cargo == null) {
            return "Cargo não pode ser nulo.";
        }
        if (!validarIdCargo(cargo.getIdCargo())) {
        	return "Id com formato inválido."; 
        }
        if (!validarIdExistente(cargo.getIdCargo())) {
        	return "ID não existente.";
        }
        if (!validarNomeCargo(cargo.getNomeCargo())) {
            return "Nome do cargo é inválido.";
        }
        if (!validarDescricaoCargo(cargo.getDescricaoCargo())) {
            return "Descrição do cargo é inválida.";
        }
        if (!validarAreaCargo(cargo.getAreaCargo())) {
            return "Área do cargo é inválida.";
        }
        if (!validarSalario(cargo.getSalarioCargo())) {
            return "Salário deve ser positivo e ter no máximo 7 casas inteiras e 2 casas decimais.";
        }
        return cargoDAO.atualizar(cargo) ? "OK" : "Falha ao atualizar o cargo";
    }


    public String deletarBO(String idCargo) throws SQLException, ClassNotFoundException {
    	
    	cargoDAO = new CargoDAO();
    	
        if (!validarIdCargo(idCargo)) {
            return "ID do cargo com formato inválido.";
        }
        if (!validarIdExistente(idCargo)) {
            return "ID do cargo não existe.";
        }
        return cargoDAO.deletar(idCargo) ? "OK" : "Falha ao deletar o cargo";
    }

    public List<Cargo> selecionarBO() throws SQLException, ClassNotFoundException {
    	
    	cargoDAO = new CargoDAO();
    	
        return cargoDAO.selecionar();
    }
    
    public Cargo buscarBO(String id) throws ClassNotFoundException, SQLException {
		cargoDAO = new CargoDAO();
		
		return cargoDAO.buscarPorId(id);
}
}
