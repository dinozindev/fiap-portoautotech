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
import br.com.fiap.model.bo.PecaBO;
import br.com.fiap.model.vo.Peca;



@Path("/peca")
public class PecaResource {

    private PecaBO pecaBO = new PecaBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirPeca(Peca peca, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        String resultado = pecaBO.inserirBO(peca);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(peca.getIdPeca());
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{idPeca}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarPeca(Peca peca, @PathParam("idPeca") String idPeca) throws SQLException, ClassNotFoundException {
        peca.setIdPeca(peca.getIdPeca());
        String resultado = pecaBO.atualizarBO(peca);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{idPeca}")
    public Response deletarIdPeca(@PathParam("idPeca") String idPeca) throws SQLException, ClassNotFoundException {
        String resultado = pecaBO.deletarBO(idPeca);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Peca> selecionarPecas() throws SQLException, ClassNotFoundException {
        return (ArrayList<Peca>) pecaBO.selecionarBO();
    }
}

