package edu.upc.dsa;

import edu.upc.dsa.models.Exceptions.JuegoDoesNotExistException;
import edu.upc.dsa.models.Exceptions.UserCurrentlyPlayingException;
import edu.upc.dsa.models.Exceptions.UserDoesNotExistException;
import edu.upc.dsa.models.Exceptions.UserNotCurrentlyPlayingException;
import edu.upc.dsa.models.Juego;
import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.Usuario;

import java.util.*;

import org.apache.log4j.Logger;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    protected Map<String, Usuario> playersList; //string = username; Player = player
    protected  List<Juego> juegos; // catalogo de juegos
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    public GameManagerImpl() {
        this.playersList = new HashMap<>();
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
    public Partida iniciarPartida(String idPartida, String idUsuario) throws JuegoDoesNotExistException, UserCurrentlyPlayingException {
        Juego j = getJuego(idPartida);
        if (j==null){
            throw new JuegoDoesNotExistException();
        }
        Usuario usuario = this.playersList.get(idUsuario);
        if (usuario ==null){
            Usuario newplayer = new Usuario(idUsuario);
            logger.info("New player: "+newplayer);
            this.playersList.put(idUsuario, newplayer);
        }else{
            if(usuario.getCurrentlyPlaying()){
                logger.info("This player is currently playing");
                throw new UserCurrentlyPlayingException();
            }
        }
        Partida newpartida = new Partida(idUsuario, idPartida);
        this.playersList.get(idUsuario).addPartida(newpartida);
        logger.info("New partida: "+newpartida);

        return newpartida;
    }

    @Override
    public String getVidaActual(String iduser) throws UserDoesNotExistException, UserNotCurrentlyPlayingException {
        Partida p = getPartidaActual(iduser);
        if(p!=null){
            logger.info("You are in level "+p.getVidaJugador().toString());
            return p.getVidaJugador().toString();
        }
        return null;
    }



    @Override
    public Usuario endPartida(String iduser) throws UserDoesNotExistException, UserNotCurrentlyPlayingException {
        Partida p = getPartidaActual(iduser);
        if(p!=null){
            this.playersList.get(iduser).setCurrentlyPlaying(false);
            logger.info("You ended the partida actual");
            return this.playersList.get(iduser);
        }
        return null;
    }
    


    @Override
    public Partida getPartidaActual(String username)throws UserDoesNotExistException, UserNotCurrentlyPlayingException {
        Usuario usuario = getPlayer(username);
        if (usuario.getCurrentlyPlaying()){
            List<Partida> partidasjugadas = (List<Partida>) usuario.getPartidasJugadas().values().stream().collect(toList());
            logger.info("Partida actual: "+partidasjugadas.get(partidasjugadas.size()-1));
            return partidasjugadas.get(partidasjugadas.size()-1);
        }
        logger.info("You are not playing right now!");
        throw new UserNotCurrentlyPlayingException();
    }


    @Override
    public int sizeGames() {
        logger.info("There are "+juegos.size()+" juegos");
        return juegos.size();
    }


}