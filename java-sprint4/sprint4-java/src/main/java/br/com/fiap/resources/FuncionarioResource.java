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

import br.com.fiap.model.bo.FuncionarioBO;
import br.com.fiap.model.vo.Funcionario;

@Path("/funcionario")
public class FuncionarioResource {

    private FuncionarioBO funcionarioBO = new FuncionarioBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirFuncionario(Funcionario funcionario, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        String resultado = funcionarioBO.inserirBO(funcionario);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(funcionario.getMatriculaFuncionario());
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{matricula}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarFuncionario(Funcionario funcionario, @PathParam("matricula") String matricula) throws SQLException, ClassNotFoundException {
        funcionario.setMatriculaFuncionario(funcionario.getMatriculaFuncionario());
        String resultado = funcionarioBO.atualizarBO(funcionario);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{matricula}")
    public Response deletarFuncionario(@PathParam("matricula") String matricula) throws SQLException, ClassNotFoundException {
        String resultado = funcionarioBO.deletarBO(matricula);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Funcionario> selecionarFuncionarios() throws SQLException, ClassNotFoundException {
        return (ArrayList<Funcionario>) funcionarioBO.selecionarBO();
    }

}

