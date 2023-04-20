package edu.upc.dsa.models;

import io.swagger.models.auth.In;

import java.util.HashMap;

public class Usuario {
    String idUsuario;
    String nombre;
    String apellido;
    Integer monedas;
    Boolean currentlyPlaying;

    //HashMap<String, Partida> partidasJugadas; //string = idPartida; Partida = partida.


    public Usuario() {
        //this.id = RandomUtils.getId();
    }

    public Usuario(String idUsuario,String nombre,String apellido) {
        this.setIdUsuario(idUsuario);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setMonedas(25);
        this.setCurrentlyPlaying(false);
    }

    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public Integer getMonedas() {
        return monedas;
    }
    public void setMonedas(Integer monedas) {
        this.monedas = monedas;
    }

    /*public HashMap<String, Partida> getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas() {
        this.partidasJugadas = new HashMap<>();
    }
*/
    public Boolean getCurrentlyPlaying() {
        return currentlyPlaying;
    }

    public void setCurrentlyPlaying(Boolean currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }

    public void addPartida(Partida partida){
        this.setCurrentlyPlaying(true);
    }


    @Override
    public String toString() {
        return "Player [idUsuario=" + idUsuario +", nombre = " + nombre +", apellido= " + apellido +"]";
    }


}