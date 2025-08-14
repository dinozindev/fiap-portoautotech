package br.com.fiap.resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.model.bo.OrcamentoBO;
import br.com.fiap.model.vo.Orcamento;

@Path("/orcamento")
public class OrcamentoResource {

    private OrcamentoBO orcamentoBO = new OrcamentoBO();

    // Inserir Orçamento a partir do ID do diagnóstico e atrela o orçamento ao diagnóstico (POST)
  
    @POST
    @Path("/{idDiagnostico}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarOrcamento(@PathParam("idDiagnostico") String idDiagnostico, Orcamento orcamento, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
    	
        String resultado = orcamentoBO.inserirBO(idDiagnostico, orcamento); 
    	if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(orcamento.getIdOrcamento());
        
        return Response.created(builder.build()).build();
    }
  
    
    // Atualizar Status Orçamento (PUT)
    @PUT
    @Path("/{idOrcamento}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarDiagnostico(Orcamento orcamento, @PathParam("idOrcamento") String idOrcamento) throws ClassNotFoundException, SQLException {
        orcamento.setIdOrcamento(orcamento.getIdOrcamento());
        
        String status = orcamento.getStatusOrcamento();
        
        String resultado = orcamentoBO.atualizarBO(idOrcamento, status);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }
    

    // Deletar Orçamento (DELETE)
    @DELETE
    @Path("/{idOrcamento}")
    public Response deletarOrcamento(@PathParam("idOrcamento") String idOrcamento) throws ClassNotFoundException, SQLException {
        String resultado = orcamentoBO.deletarBO(idOrcamento);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    // Selecionar todos os Orçamentos (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Orcamento> listarOrcamentos() throws ClassNotFoundException, SQLException {
    	return (ArrayList<Orcamento>) orcamentoBO.selecionarBO();
    }
}