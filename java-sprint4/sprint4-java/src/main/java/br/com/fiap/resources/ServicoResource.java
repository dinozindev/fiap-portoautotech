package br.com.fiap.resources;

import java.sql.SQLException;
import java.util.ArrayList;

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

import br.com.fiap.model.bo.ServicoBO;
import br.com.fiap.model.vo.Servico;

@Path("/servico")
public class ServicoResource {

    private ServicoBO servicoBO = new ServicoBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirServico(Servico servico, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        String resultado = servicoBO.inserirBO(servico);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(servico.getIdServico());
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{idServico}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarServico(Servico servico, @PathParam("id") String id) throws ClassNotFoundException, SQLException {
        servico.setIdServico(id);
        String resultado = servicoBO.atualizarBO(servico);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{idServico}")
    public Response deletarServico(@PathParam("id") String id) throws ClassNotFoundException, SQLException  {
        String resultado = servicoBO.deletarBO(id);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Servico> selecionarServicos() throws ClassNotFoundException, SQLException {
        return (ArrayList<Servico>) servicoBO.selecionarBO();
    }
}

