package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    List<Usuario> usuarios;
    Integer numEquipo;
    Integer numPersonas;

    public Equipo() {    }

    public Equipo(int numEquipo, int numPersonas) {
        this();
        this.setUsuarios(new ArrayList<Usuario>());
        this.setNumEquipo(numEquipo);
        this.setNumPersonas(numPersonas);
    }




    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    public void addUsuarios(Usuario usuarios){
        this.usuarios.add(usuarios);
    }

    public int getNumEquipo() {
        return numEquipo;
    }

    public void setNumEquipo(int numEquipo) {
        this.numEquipo = numEquipo;
    }

    public Integer  getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(Integer numPersonas) {
        this.numPersonas = numPersonas;
    }



    @Override
    public String toString() {
        return "Juego [numPersonas = " + numPersonas +"]";
    }
}
