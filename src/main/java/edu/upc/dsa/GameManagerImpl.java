package edu.upc.dsa;

import edu.upc.dsa.models.Equipo;
import edu.upc.dsa.models.Exceptions.*;
import edu.upc.dsa.models.Juego;
import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.Usuario;

import java.util.*;

import org.apache.log4j.Logger;

import static java.lang.Integer.parseInt;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    private static Juego juego;
    protected Map<String, Usuario> usuario; //string = username; Player = player
    protected  List<Juego> juegos; // catalogo de juegos
    LinkedList<String> listaUsuarios = new LinkedList<String>();
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    public GameManagerImpl() {
        this.usuario = new HashMap<>();
        this.juegos= new ArrayList<>();
    }

    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }

    @Override
    public Juego createJuego(String id, Integer numUsuarios, Integer numEquipos)  {
        try {
            Juego j = getJuego(id);
        } catch (JuegoDoesNotExistException e){
            Juego g = new Juego(id, numUsuarios, numEquipos);
            juegos.add(g);
            logger.info("New juego: "+g);
            return g;
        }
        logger.info("There is already a game called: "+id);
        return null;

    }

    @Override
    public Partida iniciarPartida(int idPartida, String idUsuario) throws JuegoDoesNotExistException, UserCurrentlyPlayingException {
        Juego j = getJuego(String.valueOf(idPartida));
        if (j==null){
            throw new JuegoDoesNotExistException();
        }
        Usuario usuario = this.usuario.get(idUsuario);
        if (usuario ==null){
            Usuario newplayer = new Usuario(idUsuario);
            logger.info("New player: "+newplayer);
            this.usuario.put(idUsuario, newplayer);
        }else{
            if(usuario.getCurrentlyPlaying()){
                logger.info("This player is currently playing");
                throw new UserCurrentlyPlayingException();
            }
        }
        Partida newpartida = new Partida(idUsuario, idPartida);
        this.usuario.get(idUsuario).addPartida(newpartida);
        logger.info("New partida: "+newpartida);

        return newpartida;
    }

    @Override
    public String getVidaActual(String iduser) throws UserDoesNotExistException, UserNotCurrentlyPlayingException {
        String p = getVidaActual(iduser);
        if(p!=null){
            logger.info("Tienes esta vida: "+p.getVida().toString());
            return p.getVida().toString();
        }
        return null;
    }



    @Override
    public Usuario endPartida(String iduser) throws UserDoesNotExistException, UserNotCurrentlyPlayingException {
        Partida p = getPartidaActual(iduser);
        if(p!=null){
            this.usuario.get(iduser).setCurrentlyPlaying(false);
            logger.info("You ended the partida actual");
            return this.usuario.get(iduser);
        }
        return null;
    }

    @Override
    public Integer getVidaEquipo(int numEquipo) throws EquipoNoExisteException {
        int resultado = 0;
        if (numEquipo < this.juego.getEquipos().size() - 1) {
            Equipo equipo = this.juego.getEquipos().get(numEquipo);
            resultado = equipo.getVida();
            logger.info(resultado);
        }
        return resultado;
    }

    @Override
    public void addUsuario(String idUsuario, String nombre, String apellido, int vida, int monedas) throws UserAlreadyExists {
        if(listaUsuarios.contains(idUsuario)) {
            logger.error("Este usuario ya existe");
            throw new UserAlreadyExists();

        }
        else {
            Usuario usuario = new Usuario(idUsuario,nombre,apellido,vida,monedas);
            listaUsuarios.addLast(idUsuario);
        }
    }






}