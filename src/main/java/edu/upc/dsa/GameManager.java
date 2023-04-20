package edu.upc.dsa;

import edu.upc.dsa.models.*;
import edu.upc.dsa.models.Exceptions.*;
import io.swagger.models.auth.In;

import java.util.List;

public interface GameManager {

    // Creación de un juego
    public Juego createJuego (String id, Integer numUsuarios, Integer numEquipos);

    // Inicio de una partida de un juego por parte de un usuario. Si el usuario no existe, se crea.
    public Partida iniciarPartida(String idPartida, String idUsuario) throws JuegoDoesNotExistException, UserCurrentlyPlayingException;

    //  Consulta de la vida actual
    public String getVidaActual(String idUsuario) throws UserDoesNotExistException, UserNotCurrentlyPlayingException;

    // Compra producto
    public String comprarProducto(String idUsuario, String idProducto, Integer precio, Integer monedas) throws UserDoesNotExistException, ProductoDoesNotExistException, SaldoInsuficienteException;

    // Finalizar partida
    public Usuario endPartida(String iduser) throws UserDoesNotExistException, UserNotCurrentlyPlayingException;

    public int sizeGames();
}
