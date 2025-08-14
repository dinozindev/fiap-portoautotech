package br.com.fiap.model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import br.com.fiap.model.dao.VeiculoDAO;
import br.com.fiap.model.vo.Veiculo;

public class VeiculoBO {

    VeiculoDAO veiculoDAO = null;
    UsuarioBO usuarioBO = null;

    public boolean validarVeiculoExistente(String placa) throws ClassNotFoundException, SQLException {
    	veiculoDAO = new VeiculoDAO();
    	return veiculoDAO.buscarPorPlaca(placa) != null;
    }
    private boolean validarPlaca(String placa) {
    	return Pattern.matches("^[A-Z]{3}\\d[A-Z]\\d{2}$", placa);
    }
    private boolean validarMarca(String marca) {
    	return marca != null && !marca.trim().isEmpty() && marca.length() <= 50;
    }
    private boolean validarModelo(String modelo) {
        return modelo != null && !modelo.trim().isEmpty() && modelo.length() <= 50;
    }
    private boolean validarAno(int ano) {
    	String valorStr = String.valueOf(ano);
        return Pattern.matches("^(19[8-9][0-9]|20[0-1][0-9]|202[0-4])$", valorStr);
    }
    private boolean validarQuilometragem(double quilometragem) {
    	String valorStr = String.valueOf(quilometragem);
        return quilometragem > 0 && Pattern.matches("^\\d{1,8}(\\.\\d{1,2})?$", valorStr);
    }

    public String inserirBO(Veiculo veiculo) throws ClassNotFoundException, SQLException {
    	
    	veiculoDAO = new VeiculoDAO();
    	usuarioBO = new UsuarioBO();
    	
        if (veiculo == null) {
            return "Veículo não pode ser nulo.";
        }
        if (!validarPlaca(veiculo.getPlaca())) {
        	return "Placa com formato inválido."; 
        }
        if (validarVeiculoExistente(veiculo.getPlaca())) {
        	return "Placa já existente.";
        }
        if (!validarModelo(veiculo.getModelo())) {
            return "Modelo é inválido.";
        }
        if (!validarMarca(veiculo.getMarca())) {
            return "Marca é inválida.";
        }
        if (!validarAno(veiculo.getAno())) {
            return "Ano é inválido.";
        }
        if (!validarQuilometragem(veiculo.getQuilometragem())) {
            return "Quilometragem deve ser maior que zero e deve ter no máximo 8 casas inteiras e no máximo 2 casas decimais.";
        }
        if (!usuarioBO.validarCpfExistente(veiculo.getUsuario().getCpfUsuario())) {
            return "O usuário não existe.";
        }
			return veiculoDAO.inserir(veiculo) ? "OK" : "Falha ao inserir o usuário";
    }


    public String atualizarBO(Veiculo veiculo) throws ClassNotFoundException, SQLException {
    	
    	veiculoDAO = new VeiculoDAO();
    	usuarioBO = new UsuarioBO();
    	
    	if (veiculo == null) {
            return "Veículo não pode ser nulo.";
        }
        if (!validarPlaca(veiculo.getPlaca())) {
        	return "Placa com formato inválido."; 
        }
        if (!validarVeiculoExistente(veiculo.getPlaca())) {
        	return "Placa não existente.";
        }
        if (!validarModelo(veiculo.getModelo())) {
            return "Modelo é inválido.";
        }
        if (!validarMarca(veiculo.getMarca())) {
            return "Marca é inválida.";
        }
        if (!validarAno(veiculo.getAno())) {
            return "Ano é inválido.";
        }
        if (!validarQuilometragem(veiculo.getQuilometragem())) {
            return "Quilometragem deve ser maior que zero e deve ter no máximo 8 casas inteiras e no máximo 2 casas decimais.";
        }
        if (!usuarioBO.validarCpfExistente(veiculo.getUsuario().getCpfUsuario())) {
            return "O usuário não existe.";
        }
			return veiculoDAO.atualizar(veiculo) ? "OK" : "Falha ao atualizar o usuário";
		
    }


    public String deletarBO(String placa) throws ClassNotFoundException, SQLException {
    	
    	veiculoDAO = new VeiculoDAO();
    	
        if (!validarPlaca(placa)) {
            return "Placa com formato inválido.";
        }
        if (!validarVeiculoExistente(placa)) {
            return "Placa não existe.";
        }
        
			return veiculoDAO.deletar(placa) ? "OK" : "Falha ao deletar o usuário";
    }

    public ArrayList<Veiculo> selecionarBO() throws ClassNotFoundException, SQLException {
    	
    	veiculoDAO = new VeiculoDAO();
        
		return (ArrayList<Veiculo>)veiculoDAO.selecionar();
		} 
    
	public ArrayList<Veiculo> selecionarVeiculosUsuarioBO(String cpf) throws ClassNotFoundException, SQLException {
	    	
	    	veiculoDAO = new VeiculoDAO();
	        
			return (ArrayList<Veiculo>)veiculoDAO.selecionarVeiculosUsuario(cpf);
			} 
    

	public Veiculo buscarBO(String placa) throws ClassNotFoundException, SQLException {
			veiculoDAO = new VeiculoDAO();
			
			return veiculoDAO.buscarPorPlaca(placa);
	}
}