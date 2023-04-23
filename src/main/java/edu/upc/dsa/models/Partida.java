package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    String idpartida;
    String idUsuario;
    Integer vidaJugador;
    String estado;
    List<Juego> partidas;


    public Partida() {
        //this.id = RandomUtils.getId();
    }

    public Partida(String idUsuario, int vidaJugador) {
        this.idpartida = RandomUtils.getId();
        this.setIdUsuario(idUsuario);
        this.setVidaJugador(vidaJugador);
    }


    public String getIdpartida() {
        return idpartida;
    }
    public void setIdpartida(String idpartida) {
        this.idpartida = idpartida;
    }

    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getVidaJugador() {
        return vidaJugador;
    }
    public void setVidaJugador(Integer vidaJugador) {
        this.vidaJugador = vidaJugador;
    }

    public void setPartidas(List<Juego> partidas) {
        this.partidas = partidas;
    }
    public void addPartidas(Juego partidas){
        this.partidas.add(partidas);
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Partida [ idpartida= "+ idpartida +", iduser = "+ idUsuario +",  vida actual = " + vidaJugador +"]";
    }

}