package edu.upc.dsa.services;


import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.*;
import edu.upc.dsa.models.Exceptions.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static java.lang.Integer.parseInt;

@Api(value = "/game")
@Path("/")
public class GameService {
    private GameManager manager;

    public GameService() throws JuegoDoesNotExistException, UserCurrentlyPlayingException {
        this.manager = GameManagerImpl.getInstance();

        if (manager.sizeGames()==0) {
            this.manager.createJuego("12",10, 5);
            this.manager.createJuego("113",15, 3);

            this.manager.iniciarPartida("12", "alex123");
        }

    }

    @POST
    @ApiOperation(value = "create a new Game", notes = "If it does not exist a game with that name, it creates a new game")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created", response= Juego.class),
            @ApiResponse(code = 409, message = "Conflict, it already exists a game with that name")

    })
    @Path("/games/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createJuego(Juego juego) {
        Juego juegocreated = this.manager.createJuego(juego.getId(), juego.getNumUsuarios(), juego.getNumEquipos());

        if (juegocreated==null)  return Response.status(409).build();
        return Response.status(201).entity(juegocreated).build();
    }

    @PUT
    @ApiOperation(value = "start  Partida", notes = "Inicio de una partida de un juego por parte de un usuario. Si el usuario no existe, se crea.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully started", response= Partida.class),
            @ApiResponse(code = 404, message = "This game does not exists"),
            @ApiResponse(code = 400, message = "This player is already playing!")
    })
    @Path("/")
    public Response iniciarPartida(Partida partida) {
        try {
            this.manager.iniciarPartida(partida.getIdpartida(), partida.getIdUsuario());
        } catch(UserCurrentlyPlayingException  e) {
            return Response.status(409).build();
        } catch(JuegoDoesNotExistException e) {
            return Response.status(404).build();
        }
        return Response.status(201).build();
    }


    @PUT
    @ApiOperation(value = "finalizar partida", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Partida ended!"),
            @ApiResponse(code = 404, message = "This player does not exists"),
            @ApiResponse(code = 409, message = "This player is not playing right now!")
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response endPartida(@PathParam("id") String id) {
        try{
            Usuario usuario = this.manager.endPartida(id);
            return Response.status(200).build();
        } catch (UserDoesNotExistException e) {
            return Response.status(404).build();
        } catch (UserNotCurrentlyPlayingException e) {
            return Response.status(409).build();
        }
    }



}

