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

import br.com.fiap.model.bo.CargoBO;
import br.com.fiap.model.vo.Cargo;

@Path("/cargo")
public class CargoResource {

    private CargoBO cargoBO = new CargoBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirCargo(Cargo cargo, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        String resultado = cargoBO.inserirBO(cargo);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(cargo.getIdCargo());
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{idCargo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarCargo(Cargo cargo, @PathParam("idCargo") String idCargo) throws SQLException, ClassNotFoundException {
        cargo.setIdCargo(idCargo);
        String resultado = cargoBO.atualizarBO(cargo);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{idCargo}")
    public Response deletarCargo(@PathParam("idCargo") String idCargo) throws SQLException, ClassNotFoundException {
        String resultado = cargoBO.deletarBO(idCargo);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cargo> selecionarCargos() throws SQLException, ClassNotFoundException {
        return cargoBO.selecionarBO();
    }
}
