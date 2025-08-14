package br.com.fiap.model.dao;

import br.com.fiap.conexao.ConexaoFactory;
import br.com.fiap.model.vo.Diagnostico;
import br.com.fiap.model.vo.Orcamento;
import br.com.fiap.model.vo.Servico;
import br.com.fiap.model.vo.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticoDAO {

    private Connection minhaConexao;
    private VeiculoDAO veiculoDAO;
    private ServicoDAO servicoDAO;
    private OrcamentoDAO orcamentoDAO;

    public DiagnosticoDAO() throws ClassNotFoundException, SQLException {
        this.minhaConexao = ConexaoFactory.getConnection();
        this.veiculoDAO = new VeiculoDAO();
        this.servicoDAO = new ServicoDAO();
        this.orcamentoDAO = new OrcamentoDAO();
        }

    public boolean inserir(Diagnostico diagnostico) throws SQLException {
    	try (PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO diagnostico (id_diagnostico, descricao_sintomas, categoria_problema, solucao, status_diagnostico, veiculo_placa, servico_id_servico) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
    		stmt.setString(1, diagnostico.getIdDiagnostico());
            stmt.setString(2, diagnostico.getDescricaoSintomas());
            stmt.setString(3, diagnostico.getCategoria());
            stmt.setString(4, diagnostico.getSolucao());
            stmt.setString(5, diagnostico.getStatus());
            stmt.setString(6, diagnostico.getVeiculo().getPlaca());
            stmt.setString(7, diagnostico.getServico().getIdServico());

            int rows = stmt.executeUpdate();
            return rows > 0;
    	}
    }

    // Deletar Diagnostico
    public boolean deletar(String idDiagnostico) throws SQLException {
    	try (PreparedStatement stmt = minhaConexao.prepareStatement("DELETE FROM diagnostico WHERE id_diagnostico = ?")) {
    		stmt.setString(1, idDiagnostico);
            int rows = stmt.executeUpdate();
            return rows > 0;
    	}
    }
    
    // Atualiza o status do diagnóstico
    public boolean atualizarStatusDiagnostico(String idDiagnostico, String status) throws SQLException {
    		String sql = "UPDATE diagnostico SET status_diagnostico = ? where id_diagnostico = ?";
        	try(PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
        		ps.setString(1, status);
        		ps.setString(2, idDiagnostico);
        		int rows = ps.executeUpdate();
        		return rows > 0;
        	} 
    	} 

    // Selecionar todos os Diagnosticos
    public List<Diagnostico> selecionar() throws SQLException {
        List<Diagnostico> listaDiagnosticos = new ArrayList<Diagnostico>();
        try (PreparedStatement stmt = minhaConexao.prepareStatement("SELECT * FROM diagnostico");
                ResultSet rs = stmt.executeQuery()) {
        	while (rs.next()) {
                Diagnostico diagnostico = new Diagnostico(
                rs.getString("id_diagnostico"),
                veiculoDAO.buscarPorPlaca(rs.getString("veiculo_placa")),
                rs.getString("descricao_sintomas"),
                servicoDAO.buscarPorId(rs.getString("servico_id_servico")),
                rs.getString("categoria_problema"),
                rs.getString("status_diagnostico"),
                orcamentoDAO.buscarPorId(rs.getString("orcamento_id_orcamento")),
                rs.getString("solucao")
                );
                listaDiagnosticos.add(diagnostico);
            }
            return listaDiagnosticos;
        }
    }
    
    public Diagnostico buscarPorId(String id) {
        String sql = "SELECT * FROM diagnostico WHERE id_diagnostico = ?";
        Diagnostico diagnostico = null;

        try (
             PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String idDiagnostico = resultSet.getString("id_diagnostico");
                    String descricao = resultSet.getString("descricao_sintomas");
                    String categoria = resultSet.getString("categoria_problema");
                    String status = resultSet.getString("status_diagnostico");
                    Orcamento orcamento = orcamentoDAO.buscarPorId(resultSet.getString("orcamento_id_orcamento"));
                    Servico servico = servicoDAO.buscarPorId(resultSet.getString("servico_id_servico"));
                    Veiculo veiculo = veiculoDAO.buscarPorPlaca(resultSet.getString("veiculo_placa"));
                    String solucao = resultSet.getString("solucao");

                    diagnostico = new Diagnostico(idDiagnostico, veiculo, descricao, servico, categoria, status, orcamento, solucao);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao buscar diagnóstico:");
            e.printStackTrace();
        }

        return diagnostico;
    }
    
    public List<Diagnostico> buscarDiagnosticosPorCPF(String cpf) throws SQLException {
        List<Diagnostico> listaDiagnosticos = new ArrayList<Diagnostico>();
        String sql = """
            SELECT *
            FROM diagnostico d
            JOIN veiculo v ON d.veiculo_placa = v.placa
            JOIN usuario u ON v.usuario_cpf_usuario = u.cpf_usuario
            WHERE u.cpf_usuario = ?
            """;
        try (
             PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
            
            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                	Diagnostico diagnostico = new Diagnostico(
                            rs.getString("id_diagnostico"),
                            veiculoDAO.buscarPorPlaca(rs.getString("veiculo_placa")),
                            rs.getString("descricao_sintomas"),
                            servicoDAO.buscarPorId(rs.getString("servico_id_servico")),
                            rs.getString("categoria_problema"),
                            rs.getString("status_diagnostico"),
                            orcamentoDAO.buscarPorId(rs.getString("orcamento_id_orcamento")),
                            rs.getString("solucao")
                            );
                    listaDiagnosticos.add(diagnostico);
                }
            }
        }
        
        return listaDiagnosticos;
    }
    
    
    public boolean inserirOrcamentoNoDiagnostico(String idDiagnostico, String idOrcamento) throws SQLException {
		String sql = "UPDATE diagnostico SET orcamento_id_orcamento = ? WHERE id_diagnostico = ?";
    	try(PreparedStatement ps = minhaConexao.prepareStatement(sql)) {
    		ps.setString(1, idOrcamento);
    		ps.setString(2, idDiagnostico);
    		int rows = ps.executeUpdate();
    		return rows > 0;
    	} 
	}
}