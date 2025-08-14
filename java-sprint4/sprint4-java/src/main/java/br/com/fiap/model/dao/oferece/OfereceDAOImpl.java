package br.com.fiap.model.dao.oferece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OfereceDAOImpl implements OfereceDAO {
	private Connection conn;

    public OfereceDAOImpl(Connection conn) {
    	this.conn = conn;
    }

	@Override
	public boolean adicionarAssociacao(String id_servico, String id_centro) {
		String sql = "INSERT INTO oferece (servico_id_servico, centro_automotivo_id_centro) VALUES (?, ?)";
		try (
				PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id_servico);
            ps.setString(2, id_centro);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
        	System.err.println("Ocorreu um erro ao inserir associação Oferece: o centro e o serviço já estão associados.");
            return false;
        } 
	}

	@Override
	public boolean deletarAssociacao(String id_servico, String id_centro)  {
		String sql = "DELETE FROM oferece WHERE centro_automotivo_id_centro = ? AND servico_id_servico = ?";

        try (
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id_servico);
            ps.setString(2, id_centro);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao deletar associação Oferece:");
            e.printStackTrace();
            return false;
        } 
	}

	@Override
	public ArrayList<String> listarServicosPorCentro(String id_centro) {
		ArrayList<String> servicos = new ArrayList<String>();
        String sql = "SELECT servico_id_servico FROM oferece WHERE centro_automotivo_id_centro = ?";
        try (
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id_centro);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                	String idServico = rs.getString("servico_id_servico");
                    servicos.add(idServico);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao listar serviços associados a centro:");
            e.printStackTrace();
        } 
        return servicos;
	}

	@Override
	public ArrayList<String> listarCentrosPorServico(String id_servico) {
		ArrayList<String> centros = new ArrayList<String>();
        String sql = "SELECT centro_automotivo_id_centro FROM oferece WHERE servico_id_servico = ?";
        try (
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id_servico);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                	 String idCentro = rs.getString("centro_automotivo_id_centro");
                     centros.add(idCentro);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao listar centros associados a serviço:");
            e.printStackTrace();
        } 
        return centros;
	}

}
