package edu.upc.dsa;

import edu.upc.dsa.models.*;
import edu.upc.dsa.models.Exceptions.*;

import java.util.List;

public interface GameManager {

    // Creación de un juego
    public Juego createJuego (String id, Integer numUsuarios, Integer numEquipos);

    // Inicio de una partida de un juego por parte de un usuario. Si el usuario no existe, se crea.
    public Partida iniciarPartida(String idPartida, String idUsuario) throws JuegoDoesNotExistException, UserCurrentlyPlayingException;

    Partida iniciarPartida(int idPartida, String idUsuario) throws JuegoDoesNotExistException, UserCurrentlyPlayingException;

    //  Consulta de la vida actual
    public String getVidaActual(String idUsuario) throws UserDoesNotExistException, UserNotCurrentlyPlayingException;

    //Consultar vida del equipo
    public Integer getVidaEquipo(int numEquipo) throws EquipoNoExisteException;

    // Compra producto
    public String comprarProducto(String idUsuario, String idProducto) throws UserDoesNotExistException, ProductoDoesNotExistException, SaldoInsuficienteException;

    // Finalizar partida
    public Usuario endPartida(String idUsuario) throws UserDoesNotExistException, UserNotCurrentlyPlayingException;

    public int sizeGames();

    public Juego getJuego(String namejuego) throws JuegoDoesNotExistException;
    public Usuario getPlayer(String username) throws UserDoesNotExistException;
    public List<Partida> getPartidas (String idjuego, String iduser) throws JuegoDoesNotExistException, UserDoesNotExistException;
    public Partida getPartidaActual (String username) throws UserDoesNotExistException, UserNotCurrentlyPlayingException;


    void addUsuario(String idUsuario, String nombre, String apellido, int vida, int monedas) throws UserAlreadyExists;
}
