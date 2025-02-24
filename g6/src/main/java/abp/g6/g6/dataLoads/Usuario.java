package abp.g6.g6.dataLoads;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Usuario")
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario {
    @XmlElement(name="id")
    private int id;
    @XmlElement(name="username")
    private String username;
    @XmlElement(name="pass")
    private String pass;
    @XmlElement(name="rol")
    private int rol;
    private int estado;
    @XmlElement(name="nombre")
    private String nombre;
    @XmlElement(name="email")
    private String email;
    private String foto_perfil;
    @XmlElement(name="fecha")
    private String fecha_registro;
    private String biografia;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto_perfil() {
        return foto_perfil;
    }

    public void setFoto_perfil(String foto_perfil) {
        this.foto_perfil = foto_perfil;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", rol=" + rol +
                ", estado=" + estado +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", foto_perfil='" + foto_perfil + '\'' +
                ", fecha_registro='" + fecha_registro + '\'' +
                ", biografia='" + biografia + '\'' +
                '}';
    }

}
