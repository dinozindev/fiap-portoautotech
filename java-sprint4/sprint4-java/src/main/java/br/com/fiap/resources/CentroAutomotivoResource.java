package br.com.fiap.resources;

import java.sql.SQLException;
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

import br.com.fiap.model.bo.CentroBO;
import br.com.fiap.model.vo.CentroAutomotivo;

@Path("/centro")
public class CentroAutomotivoResource {

    private CentroBO centroBO = new CentroBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirCentro(CentroAutomotivo centro, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        String resultado = centroBO.inserirBO(centro);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(centro.getIdCentro());
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{idCentro}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarCentro(CentroAutomotivo centro, @PathParam("idCentro") String idCentro) throws SQLException, ClassNotFoundException {
        centro.setIdCentro(idCentro);
        String resultado = centroBO.atualizarBO(centro);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{idCentro}")
    public Response deletarCentro(@PathParam("idCentro") String idCentro) throws SQLException, ClassNotFoundException {
        String resultado = centroBO.deletarBO(idCentro);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CentroAutomotivo> selecionarCentros() throws SQLException, ClassNotFoundException {
        return centroBO.selecionarBO();
    }
}
