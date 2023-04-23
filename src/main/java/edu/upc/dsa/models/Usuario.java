package edu.upc.dsa.models;

import io.swagger.models.auth.In;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Usuario {
    Map<String,Usuario>MapaUsuarios=new HashMap<>();
    String idUsuario;
    String nombre;
    String apellido;
    Integer monedas;
    Integer vida;
    Boolean currentlyPlaying;
    List<Producto> listaProductos;

    //HashMap<String, Partida> partidasJugadas; //string = idPartida; Partida = partida.


    public Usuario() {
        //this.id = RandomUtils.getId();
    }



    public Usuario(String idUsuario,String nombre,String apellido,int  vida) {
        this.setIdUsuario(idUsuario);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setMonedas(25);
        this.setVida(vida);
        this.setCurrentlyPlaying(false);
    }


    public Usuario(String idUsuario) {
    }

    public Usuario(String idUsuario, String nombre, String apellido, int vida, double dsaCoins) {
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

    public Integer getVida() {
        return vida;
    }
    public void setVida(Integer vida) {
        this.vida = vida;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
    public void addListaProductos(Producto listaProductos){
        this.listaProductos.add(listaProductos);
    }
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