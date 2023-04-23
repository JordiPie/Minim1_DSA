package edu.upc.dsa.models;

import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    String id;
    Integer numUsuarios;
    Integer numEquipos;
    List<Equipo> equipos;

    public Juego() {    }

    public Juego(String id, int numUsuarios, int numEquipos) {
        this();
        this.setId(id);
        this.setNumUsuarios(numUsuarios);
        this.setNumEquipos(numEquipos);
        this.setEquipos(new ArrayList<Equipo>());
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumUsuarios() {
        return numUsuarios;
    }
    public void setNumUsuarios(Integer numUsuarios) {
        this.numUsuarios = numUsuarios;
    }

    public Integer getNumEquipos() {
        return numEquipos;
    }

    public void setNumEquipos(Integer numEquipos) {
        this.numEquipos = numEquipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }
    public void addEquipos(Equipo equipos){
        this.equipos.add(equipos);
    }

    public List<Equipo> getEquipos(){
        return this.equipos;
    }

    public void CrearEquipos(String idEquipo, int numPersonas){
        for (int i=0; i<numEquipos;i++){
            equipos.add(new Equipo(idEquipo,numPersonas));
        }
    }

    @Override
    public String toString() {
        return "Juego [numUsuarios = " + numUsuarios +"]";
    }
}