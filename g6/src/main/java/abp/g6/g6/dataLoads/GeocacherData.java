package abp.g6.g6.dataLoads;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="GeocacherData")
public class GeocacherData {
    private List<Usuario> usuarios;
    private List<Ubicacion> ubicacions;
    private List<Artefacto> artefactos;


    public GeocacherData() {
    }
    @XmlElementWrapper(name="Artefactos")
    @XmlElement(name="Artefacto")
    public List<Artefacto> getArtefactos() {
        return artefactos;
    }

    public void setArtefactos(List<Artefacto> artefactos) {
        this.artefactos = artefactos;
    }

    @XmlElementWrapper(name="Ubicaciones")
    @XmlElement(name="Ubicacion")
    public List<Ubicacion> getUbicacions() {
        return ubicacions;
    }

    public void setUbicacions(List<Ubicacion> ubicacions) {
        this.ubicacions = ubicacions;
    }

    @XmlElementWrapper(name="Usuarios")
    @XmlElement(name="Usuario")
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
