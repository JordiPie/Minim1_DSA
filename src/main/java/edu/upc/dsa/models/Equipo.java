package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    List<Usuario> usuarios;
    String idEquipo;

    public Equipo() {    }

    public Equipo(String idEquipo) {
        this();
        this.setUsuarios(new ArrayList<Usuario>());
        this.setIdEquipo(idEquipo);
    }


    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    public void addUsuarios(Usuario usuarios){
        this.usuarios.add(usuarios);
    }

    public String  getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    @Override
    public String toString() {
        return "Juego [idEquipo = " + idEquipo +"]";
    }
}
