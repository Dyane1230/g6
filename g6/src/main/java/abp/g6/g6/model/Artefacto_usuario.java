package abp.g6.g6.model;

import jakarta.persistence.*;

@Entity
@Table(name = "artefacto_usuario")
public class Artefacto_usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "artefacto_id")
    private Artefacto artefacto;
    @Column
    private String fecha_descubrimiento;



    public Artefacto_usuario() {
    }

    public Artefacto_usuario(Usuario usuario, Artefacto artefacto) {
        this.usuario = usuario;
        this.artefacto = artefacto;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Artefacto getArtefacto() {
        return artefacto;
    }

    public void setArtefacto(Artefacto artefacto) {
        this.artefacto = artefacto;
    }

    public String getFecha_descubrimiento() {
        return fecha_descubrimiento;
    }

    public void setFecha_descubrimiento(String fecha_descubrimiento) {
        this.fecha_descubrimiento = fecha_descubrimiento;
    }

    @Override
    public String toString() {
        return "Artefacto_usuario{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", artefacto=" + artefacto +
                ", fecha_descubrimiento='" + fecha_descubrimiento + '\'' +
                '}';
    }
}
