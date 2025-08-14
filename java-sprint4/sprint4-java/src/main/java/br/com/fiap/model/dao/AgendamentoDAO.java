package br.com.fiap.model.dao;

import br.com.fiap.conexao.ConexaoFactory;
import br.com.fiap.model.vo.Agendamento;
import br.com.fiap.model.vo.CentroAutomotivo;
import br.com.fiap.model.vo.Servico;
import br.com.fiap.model.vo.Veiculo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    private Connection minhaConexao;
    private VeiculoDAO veiculoDAO;
    private CentroDAO centroDAO;
    private ServicoDAO servicoDAO;

    public AgendamentoDAO() throws ClassNotFoundException, SQLException {
        this.minhaConexao = ConexaoFactory.getConnection();
        this.veiculoDAO = new VeiculoDAO();
        this.centroDAO = new CentroDAO();
        this.servicoDAO = new ServicoDAO();
        }

    public boolean inserir(Agendamento agendamento) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO agendamento (id_agendamento, data_agendamento, horario_agendamento, descricao_agendamento, servico_id_servico, centro_automotivo_id_centro, veiculo_placa) VALUES (?, ?, ?, ?, ?, ?, ?)");

        stmt.setString(1, agendamento.getIdAgendamento());
        stmt.setDate(2, agendamento.getData());
        stmt.setString(3, agendamento.getHora());
        stmt.setString(4, agendamento.getDescricao());
        stmt.setString(5, agendamento.getServico().getIdServico());
        stmt.setString(6, agendamento.getCentro().getIdCentro());
        stmt.setString(7, agendamento.getVeiculo().getPlaca());

        int rows = stmt.executeUpdate();
        return rows > 0;
    }

    // Deletar Agendamento
    public boolean deletar(String idAgendamento) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement("DELETE FROM agendamento WHERE id_agendamento = ?");
        
        stmt.setString(1, idAgendamento);
        int rows = stmt.executeUpdate();
        return rows > 0;
    }

    // Atualizar Agendamento
    public boolean atualizar(Agendamento agendamento) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE agendamento SET data_agendamento = ?, horario_agendamento = ?, descricao_agendamento = ?, servico_id_servico = ?, centro_automotivo_id_centro = ?, veiculo_placa = ? WHERE id_agendamento = ?");

        stmt.setDate(1, agendamento.getData());
        stmt.setString(2, agendamento.getHora());
        stmt.setString(3, agendamento.getDescricao());
        stmt.setString(4, agendamento.getServico().getIdServico());
        stmt.setString(5, agendamento.getCentro().getIdCentro());
        stmt.setString(6, agendamento.getVeiculo().getPlaca());
        stmt.setString(7, agendamento.getIdAgendamento());

        int rows = stmt.executeUpdate();
        return rows > 0;
    }

    // Selecionar todos os Agendamentos
    public List<Agendamento> selecionar() throws SQLException {
        List<Agendamento> listaAgendamentos = new ArrayList<Agendamento>();
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM agendamento");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Agendamento agendamento = new Agendamento();
            agendamento.setIdAgendamento(rs.getString("id_agendamento"));
            agendamento.setData(rs.getDate("data_agendamento"));
            agendamento.setHora(rs.getString("horario_agendamento"));
            agendamento.setDescricao(rs.getString("descricao_agendamento"));
            agendamento.setServico(servicoDAO.buscarPorId(rs.getString("servico_id_servico")));
            agendamento.setCentro(centroDAO.buscarPorId(rs.getString("centro_automotivo_id_centro")));
            agendamento.setVeiculo(veiculoDAO.buscarPorPlaca(rs.getString("veiculo_placa")));
            listaAgendamentos.add(agendamento);
        }
        rs.close();
        stmt.close();
        return listaAgendamentos;
    }
    
    public Agendamento buscarPorId(String id) {
        String sql = "SELECT * FROM agendamento WHERE id_agendamento = ?";
        Agendamento agendamento = null;

        try (
        		PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
            if (resultSet.next()) {
            	String idAgendamento = resultSet.getString("id_agendamento");
                Date data = resultSet.getDate("data_agendamento");
                String hora = resultSet.getString("horario_agendamento");
                String descricao = resultSet.getString("descricao_agendamento");
                CentroAutomotivo centro = centroDAO.buscarPorId(resultSet.getString("centro_automotivo_id_centro"));
                Servico servico = servicoDAO.buscarPorId(resultSet.getString("servico_id_servico"));
                Veiculo veiculo = veiculoDAO.buscarPorPlaca(resultSet.getString("veiculo_placa"));
                
                agendamento = new Agendamento(idAgendamento, data, hora, descricao, centro, servico, veiculo);
            }
          }
        }
        catch (SQLException e) {
            System.err.println("Erro ao buscar agendamento:");
            e.printStackTrace();
        }
        return agendamento;
    }
    
    public List<Agendamento> buscarAgendamentosPorCPF(String cpf) throws SQLException {
        List<Agendamento> listaAgendamentos = new ArrayList<Agendamento>();
        String sql = """
            SELECT *
            FROM agendamento a
            JOIN veiculo v ON a.veiculo_placa = v.placa
            JOIN usuario u ON v.usuario_cpf_usuario = u.cpf_usuario
            WHERE u.cpf_usuario = ?
            """;
        try (
             PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
            
            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Agendamento agendamento = new Agendamento();
                    agendamento.setIdAgendamento(rs.getString("id_agendamento"));
                    agendamento.setData(rs.getDate("data_agendamento"));
                    agendamento.setHora(rs.getString("horario_agendamento"));
                    agendamento.setDescricao(rs.getString("descricao_agendamento"));
                    agendamento.setServico(servicoDAO.buscarPorId(rs.getString("servico_id_servico")));
                    agendamento.setCentro(centroDAO.buscarPorId(rs.getString("centro_automotivo_id_centro")));
                    agendamento.setVeiculo(veiculoDAO.buscarPorPlaca(rs.getString("veiculo_placa")));
                    listaAgendamentos.add(agendamento);
                }
            }
        }
        return listaAgendamentos;
    }
}
