package abp.g6.g6.model;

import jakarta.persistence.*;

@Entity
@Table(name = "artefacto")
public class Artefacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "enlace")
    private String enlace;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "id_ubicacion")
    private Ubicacion id_ubicacion;

    @Column(name = "estado")
    private int estado;

    public Artefacto(int id, String nombre, String descripcion, String enlace, Ubicacion id_ubicacion, double latitude, double longitude, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id_ubicacion = id_ubicacion;
        this.estado = estado;
        this.enlace = enlace;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Artefacto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Ubicacion getUbicacion() {
        return id_ubicacion;
    }

    public void setUbicacion (Ubicacion id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public Ubicacion getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(Ubicacion id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }
}
