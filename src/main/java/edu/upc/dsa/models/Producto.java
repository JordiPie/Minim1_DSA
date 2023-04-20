package edu.upc.dsa.models;

public class Producto {
    String idProducto;
    String descripcion;
    Integer precio;

    public Producto() {    }

    public Producto(String id, String descripcion, int precio) {
        this();
        this.setIdProducto(id);
        this.setDescripcion(descripcion);
        this.setPrecio(precio);
    }


    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String  descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto [id = " + idProducto +", descripcion = " + descripcion +", precio = "+ precio + "]";
    }
}
