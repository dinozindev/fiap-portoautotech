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

import br.com.fiap.model.bo.AgendamentoBO;
import br.com.fiap.model.vo.Agendamento;

@Path("/agendamento")
public class AgendamentoResource {

    private AgendamentoBO agendamentoBO = new AgendamentoBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) 
    public Response cadastrarAgendamento(Agendamento agendamento, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        String resultado = agendamentoBO.inserirBO(agendamento);
        
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(agendamento.getIdAgendamento());
        

        return Response.created(builder.build()).entity(agendamento).build();
    }
    
    // Atualizar Agendamento (PUT)
    @PUT
    @Path("/{idAgendamento}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarAgendamento(Agendamento agendamento, @PathParam("idAgendamento") String idAgendamento) throws ClassNotFoundException, SQLException {
        agendamento.setIdAgendamento(agendamento.getIdAgendamento());
        String resultado = agendamentoBO.atualizarBO(agendamento);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    // Deletar Agendamento (DELETE)
    @DELETE
    @Path("/{idAgendamento}")
    public Response deletarAgendamento(@PathParam("idAgendamento") String idAgendamento) throws ClassNotFoundException, SQLException {
        String resultado = agendamentoBO.deletarBO(idAgendamento);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    // Selecionar todos os Agendamentos (GET)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Agendamento> listarAgendamentos() throws ClassNotFoundException, SQLException {
    	return (ArrayList<Agendamento>) agendamentoBO.selecionarBO();
    }
    
    // Endpoint para listar agendamentos por CPF
    @GET
    @Path("/usuario/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Agendamento> listarAgendamentosPorCpf(@PathParam("cpf") String cpf) throws ClassNotFoundException, SQLException {
        return (ArrayList<Agendamento>) agendamentoBO.selecionarAgendamentosUsuarioBO(cpf);
    }
}
