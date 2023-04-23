package edu.upc.dsa;

import edu.upc.dsa.models.Exceptions.JuegoDoesNotExistException;
import edu.upc.dsa.models.Exceptions.UserCurrentlyPlayingException;
import edu.upc.dsa.models.Exceptions.UserDoesNotExistException;
import edu.upc.dsa.models.Exceptions.UserNotCurrentlyPlayingException;
import edu.upc.dsa.models.Juego;
import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class GameServiceTest {
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);
    GameManager manager;

   /* @Before
    public void setUp() throws JuegoDoesNotExistException, UserCurrentlyPlayingException {
        manager = new GameManagerImpl();

        manager.createJuego("pichi","com el beisball", 5);
        manager.createJuego("so-pa-po","el matias sempre guanya", 2);

        manager.iniciarPartida("pichi", "alex");
    }

    @After
    public void tearDown()  {
        this.manager = null;
        logger.info("--- End of the test ---");
    }

    @Test
    public void addGame() throws JuegoDoesNotExistException {
        logger.info("--- Start of the test - Add game ---");

        Assert.assertEquals(2,this.manager.sizeGames());

        logger.info("Afegim un joc:");
        manager.createJuego("mata conills","matar", 10);

        Assert.assertEquals(3,this.manager.sizeGames());
    }
    @Test
    public void iniciarPartida() throws JuegoDoesNotExistException, UserCurrentlyPlayingException {
        logger.info("--- Start of the test - Iniciar partida ---");

        logger.info("Matias intenta de iniciar una partida en un juego que no existe:");
        Assert.assertThrows(JuegoDoesNotExistException.class, ()-> manager.iniciarPartida("randomJuego", "matias"));

        Partida p1 = manager.iniciarPartida("so-pa-po", "matias");

        Assert.assertEquals("so-pa-po", p1.getNamejuego());
        Assert.assertEquals("matias", p1.getIdUsuario());
        Assert.assertEquals("1", p1.getVidaJugador().toString());
        Assert.assertEquals("50", p1.getPuntos().toString());

        logger.info("Matias intenta de iniciar otra partida mientras ya esta jugando a una:");
        Assert.assertThrows(UserCurrentlyPlayingException.class, ()->manager.iniciarPartida("pichi", "matias"));

    }
    @Test
    public void jugarPartida() throws JuegoDoesNotExistException, UserCurrentlyPlayingException, UserDoesNotExistException, UserNotCurrentlyPlayingException {
        logger.info("--- Start of the test - Consultar nivel, puntuacion y terminar partida ---");

        Partida p1 = manager.iniciarPartida("so-pa-po", "matias");

        logger.info("Matias quiere saber en que nivel esta:");
        Assert.assertEquals("1", manager.getVidaActual("matias"));

        logger.info("Matias quiere saber cuantos puntos tiene:");
        Assert.assertEquals("50", manager.getPuntuacionActual("matias"));

        logger.info("Matias decide finalizar la partida:");
        Assert.assertEquals(false, manager.endPartida("matias").getCurrentlyPlaying());

        logger.info("Matias quiere volver a saber en que nivel esta (aunque ya no juega):");
        Assert.assertThrows(UserNotCurrentlyPlayingException.class,()->manager.getVidaActual("matias"));

        logger.info("Matias quiere volver a saber su puntuacion esta (aunque ya no juega):");
        Assert.assertThrows(UserNotCurrentlyPlayingException.class,()->manager.getPuntuacionActual("matias"));

        logger.info("Un username random quiere saber en que nivel:");
        Assert.assertThrows(UserDoesNotExistException.class,()->manager.getVidaActual("RANDOM"));

        logger.info("Un username random quiere saber su puntuacion:");
        Assert.assertThrows(UserDoesNotExistException.class,()->manager.getPuntuacionActual("RANDOM"));



    }

    @Test
    public void pasarNivel() throws JuegoDoesNotExistException, UserCurrentlyPlayingException, UserDoesNotExistException, UserNotCurrentlyPlayingException {
        logger.info("--- Start of the test - Pasar nivel ---");

        Partida p1 = manager.iniciarPartida("so-pa-po", "matias");

        Assert.assertEquals("1", manager.getVidaActual("matias"));
        Assert.assertEquals("50", manager.getPuntuacionActual("matias"));

        logger.info("Matias sube de nivel:");
        manager.nextLevel("matias", 30,"30-02-2022");
        Assert.assertEquals("2",manager.getVidaActual("matias") );
        Assert.assertEquals("80", manager.getPuntuacionActual("matias"));

        logger.info("Matias quiere seguir subiendo de nivel pero ya esta en el mas alto:");
        manager.nextLevel("matias", 200,"31-02-2022");
        Assert.assertThrows(UserNotCurrentlyPlayingException.class,()->manager.getVidaActual("matias"));

        logger.info("Como Matias ya estaba en el nivel mas alto se le han sumado 100puntos y ha terminado de jugar:");
        Partida partidajugada = manager.getPartida("so-pa-po", "matias");
        Assert.assertEquals("180", partidajugada.getPuntos().toString());
        Assert.assertEquals(false, manager.getPlayer("matias").getCurrentlyPlaying());
    }

    @Test
    public void sort() throws JuegoDoesNotExistException, UserCurrentlyPlayingException, UserDoesNotExistException, UserNotCurrentlyPlayingException {
        logger.info("--- Start of the test - Sort Players ---");

        manager.iniciarPartida("pichi", "matias");
        manager.iniciarPartida("pichi", "clara");
        manager.endPartida("alex");

        logger.info("Matias y Clara estan jugando al pichi\n Alex ha terminado de jugar");
        manager.nextLevel("clara", 23, "01-01-1992");
        manager.nextLevel("matias", 12, "02-01-1992");

        List<Usuario> sortedplayers = manager.vidaEquipo(manager.getJuego("pichi"));
        Assert.assertEquals(3, sortedplayers.size());
        Assert.assertEquals("clara", sortedplayers.get(0).getNombre());
        Assert.assertEquals("matias", sortedplayers.get(1).getNombre());
        Assert.assertEquals("alex", sortedplayers.get(2).getNombre());

        logger.info("Nadie esta jugando al so-pa-po");
        List<Usuario> tryingsortplayers = manager.vidaEquipo(manager.getJuego("so-pa-po"));
        Assert.assertEquals(null, tryingsortplayers);

        logger.info("El juego parchis no existe: ");
        Assert.assertThrows(JuegoDoesNotExistException.class, ()-> manager.vidaEquipo(new Juego("parchis", "diver", 70)));
    }
    @Test
    public void getPartidasyPerformancePlayer() throws UserDoesNotExistException, UserNotCurrentlyPlayingException, JuegoDoesNotExistException, UserCurrentlyPlayingException {
        logger.info("--- Start of the test - Get partidas Players & get actividad---");

        manager.endPartida("alex");
        manager.iniciarPartida("pichi", "alex");
        manager.nextLevel("alex", 200,"01-03-2022");
        manager.nextLevel("alex", 30,"02-03-2022");

        logger.info("Alex quiere ver las partidas a las que ha jugado:");
        Assert.assertEquals(2, manager.getPartidasPlayer("alex").size());

        logger.info("Alex quiere ver su actividad en la ultima partida a la que ha jugado pichi:");
        Assert.assertEquals(2, manager.getPerformance("pichi","alex").size());

        logger.info("Alguien random quiere ver las partidas a las que ha jugado:");
        Assert.assertThrows(UserDoesNotExistException.class,()->manager.getPartidasPlayer("random"));

        logger.info("Alex random quiere ver su actividad en la ultima partida a la que ha jugado algo random:");
        Assert.assertThrows(JuegoDoesNotExistException.class, ()-> manager.getPerformance("random","random").size());
    }

}
