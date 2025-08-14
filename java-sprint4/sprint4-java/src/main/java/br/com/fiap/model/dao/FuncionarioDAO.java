package br.com.fiap.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.conexao.ConexaoFactory;
import br.com.fiap.model.vo.Cargo;
import br.com.fiap.model.vo.CentroAutomotivo;
import br.com.fiap.model.vo.Funcionario;

public class FuncionarioDAO {
	private Connection minhaConexao;
	private CentroDAO centroDAO;
	private CargoDAO cargoDAO;
	
	// construtor 
		public FuncionarioDAO() throws ClassNotFoundException, SQLException {
			this.minhaConexao = ConexaoFactory.getConnection();
			this.centroDAO = new CentroDAO();
			this.cargoDAO = new CargoDAO();
		}

    public boolean inserir(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionario (matricula_func, nome_func, horario_trabalho, disponibilidade_func, centro_automotivo_id_centro, cargo_id_cargo) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getMatriculaFuncionario());
            stmt.setString(2, funcionario.getNomeFuncionario());
            stmt.setString(3, funcionario.getHorarioTrabalho());
            stmt.setString(4, funcionario.getDisponibilidade());
            stmt.setString(5, funcionario.getCentroAutomotivo().getIdCentro());
            stmt.setString(6, funcionario.getCargo().getIdCargo());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public boolean atualizar(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE funcionario SET nome_func = ?, horario_trabalho = ?, disponibilidade_func = ?, centro_automotivo_id_centro = ?, cargo_id_cargo = ? WHERE matricula_func = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNomeFuncionario());
            stmt.setString(2, funcionario.getHorarioTrabalho());
            stmt.setString(3, funcionario.getDisponibilidade());
            stmt.setString(4, funcionario.getCentroAutomotivo().getIdCentro());
            stmt.setString(5, funcionario.getCargo().getIdCargo());
            stmt.setString(6, funcionario.getMatriculaFuncionario());
            
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public boolean deletar(String matriculaFuncionario) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE matricula_func = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {

            stmt.setString(1, matriculaFuncionario);

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public List<Funcionario> selecionar() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        String sql = "SELECT * FROM funcionario ORDER BY 1";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                        rs.getString("matricula_func"),
                        rs.getString("nome_func"),
                        cargoDAO.buscarPorId(rs.getString("cargo_id_cargo")),
                        centroDAO.buscarPorId(rs.getString("centro_automotivo_id_centro")),
                        rs.getString("disponibilidade_func"),
                        rs.getString("horario_trabalho")
                );
                funcionarios.add(funcionario);
            }
        }
        return funcionarios;
    }
    
    public Funcionario buscarPorId(String id)  {
        String sql = "SELECT * FROM funcionario WHERE matricula_func = ?";
        Funcionario funcionario = null;
        try (
        		PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String matricula = resultSet.getString("matricula_func");
                    String nome = resultSet.getString("nome_func");
                    String horario = resultSet.getString("horario_trabalho");
                    String disponibilidade = resultSet.getString("disponibilidade_func");
                    String idCargo = resultSet.getString("cargo_id_cargo");
                    String idCentro = resultSet.getString("centro_automotivo_id_centro");
                    Cargo cargo = cargoDAO.buscarPorId(idCargo);
                    CentroAutomotivo centro = centroDAO.buscarPorId(idCentro);
                    funcionario = new Funcionario(matricula, nome, cargo, centro, disponibilidade, horario);
                } 
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao buscar funcionário:");
            e.printStackTrace();
        } 
        return funcionario;
    }
 }
