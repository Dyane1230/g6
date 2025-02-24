package abp.g6.g6.dataLoads;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

public class Artefacto {
    private int id;
    private String descripcion;
    private String nombre;
    private Ubicacion id_ubicacion;
    private ArrayList<Usuario> usuarios;
    private String enlace;
    private double latitude;
    private double longitude;

    public Artefacto() {
    }

    public Artefacto(int id, String descripcion, String nombre, String enlace, Ubicacion id_ubicacion, double tatitude, double longitude, 
                     ArrayList<Usuario> usuarios) {
        this.id = id;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.id_ubicacion = id_ubicacion;
        this.usuarios = new ArrayList<>();
        this.enlace = enlace;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @XmlElement(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name="desc")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlElement(name="nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name="enlace")
    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    @XmlElement(name="ubicacion")
    public Ubicacion getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(Ubicacion id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    @XmlElement(name="latitude")
    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @XmlElement(name="longitude")
    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @XmlElementWrapper(name="Usuarios")
    @XmlElement(name="Usuario")
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "Artefacto{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", id_ubicacion=" + id_ubicacion +
                ", usuarios=" + usuarios +
                ", enlace='" + enlace + '\'' +
                '}';
    }

}
