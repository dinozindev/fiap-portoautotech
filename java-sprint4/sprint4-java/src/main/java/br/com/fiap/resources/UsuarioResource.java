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

import br.com.fiap.model.bo.UsuarioBO;
import br.com.fiap.model.vo.Usuario;

@Path("/usuario")
public class UsuarioResource {

    private UsuarioBO usuarioBO = new UsuarioBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirUsuario(Usuario usuario, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        String resultado = usuarioBO.inserirBO(usuario);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(usuario.getCpfUsuario());
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{cpf}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarUsuario(Usuario usuario, @PathParam("cpf") String cpf) throws ClassNotFoundException, SQLException {
        usuario.setCpfUsuario(cpf);
        String resultado = usuarioBO.atualizarBO(usuario);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{cpf}")
    public Response deletarUsuario(@PathParam("cpf") String cpf) throws ClassNotFoundException, SQLException  {
        String resultado = usuarioBO.deletarBO(cpf);
        if (!resultado.equals("OK")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Usuario> selecionarUsuarios() throws ClassNotFoundException, SQLException {
        return (ArrayList<Usuario>) usuarioBO.selecionarBO();
    }
}
