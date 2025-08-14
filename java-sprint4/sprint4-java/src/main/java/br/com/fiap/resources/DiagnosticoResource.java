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
import br.com.fiap.model.bo.DiagnosticoBO;
import br.com.fiap.model.vo.Diagnostico;

@Path("/diagnostico")
public class DiagnosticoResource {

    private DiagnosticoBO diagnosticoBO = new DiagnosticoBO();

    // Inserir Diagnostico (POST)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarDiagnostico(Diagnostico diagnostico, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
    	String resultado = diagnosticoBO.inserirBO(diagnostico);
    	if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(diagnostico.getIdDiagnostico());
        return Response.created(builder.build()).build();
    }
    
    // Atualizar Diagnóstico (PUT)
    @PUT
    @Path("/{idDiagnostico}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarDiagnostico(Diagnostico diagnostico, @PathParam("idDiagnostico") String idDiagnostico) throws ClassNotFoundException, SQLException {
        diagnostico.setIdDiagnostico(diagnostico.getIdDiagnostico());
        
        String status = diagnostico.getStatus();
        
        String resultado = diagnosticoBO.atualizarBO(idDiagnostico, status);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }
    

    // Deletar Diagnóstico (DELETE)
    @DELETE
    @Path("/{idDiagnostico}")
    public Response deletarDiagnostico(@PathParam("idDiagnostico") String idDiagnostico) throws ClassNotFoundException, SQLException {
        String resultado = diagnosticoBO.deletarBO(idDiagnostico);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    // Selecionar todos os Diagnósticos (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Diagnostico> listarDiagnosticos() throws ClassNotFoundException, SQLException {
    	return (ArrayList<Diagnostico>) diagnosticoBO.selecionarBO();
    }
    
    // Endpoint para listar diagnósticos por CPF
    @GET
    @Path("/usuario/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Diagnostico> listarDiagnosticosPorCpf(@PathParam("cpf") String cpf) throws ClassNotFoundException, SQLException {
        return (ArrayList<Diagnostico>) diagnosticoBO.selecionarDiagnosticosUsuarioBO(cpf);
    }
}