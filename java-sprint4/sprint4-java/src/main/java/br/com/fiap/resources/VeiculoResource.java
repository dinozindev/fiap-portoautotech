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

import br.com.fiap.model.bo.VeiculoBO;
import br.com.fiap.model.vo.Veiculo;

@Path("/veiculo")
public class VeiculoResource {

    private VeiculoBO veiculoBO = new VeiculoBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirVeiculo(Veiculo veiculo, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        String resultado = veiculoBO.inserirBO(veiculo);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(veiculo.getPlaca());
        return Response.created(builder.build()).build();
    }
    
    @GET
    @Path("/{placa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Veiculo selecionarVeiculo(@PathParam("placa") String placa) throws SQLException, ClassNotFoundException {
        return veiculoBO.buscarBO(placa);
    }


    @PUT
    @Path("/{placa}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarVeiculo(Veiculo veiculo, @PathParam("placa") String placa) throws SQLException, ClassNotFoundException {
        veiculo.setPlaca(veiculo.getPlaca());
        String resultado = veiculoBO.atualizarBO(veiculo);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{placa}")
    public Response deletarPlaca(@PathParam("placa") String placa) throws SQLException, ClassNotFoundException {
        String resultado = veiculoBO.deletarBO(placa);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veiculo> selecionarVeiculos() throws SQLException, ClassNotFoundException {
        return (ArrayList<Veiculo>) veiculoBO.selecionarBO();
    }
    
    // Endpoint para listar veículos por CPF
    @GET
    @Path("/usuario/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veiculo> listarVeiculosPorCpf(@PathParam("cpf") String cpf) throws ClassNotFoundException, SQLException {
        return (ArrayList<Veiculo>) veiculoBO.selecionarVeiculosUsuarioBO(cpf);
    }
}
