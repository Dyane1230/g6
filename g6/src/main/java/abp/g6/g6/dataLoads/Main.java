package abp.g6.g6.dataLoads;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class Main {
    public static void main (String[] args) {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(GeocacherData.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            GeocacherData geocacherData = (GeocacherData) unmarshaller.unmarshal(new File("files/DataLoads.xml"));
            List<Usuario> usuarios = geocacherData.getUsuarios();
            List<Ubicacion>  ubicacions = geocacherData.getUbicacions();
            List<Artefacto>  artefactos = geocacherData.getArtefactos();
            for (Usuario u : usuarios) {
                System.out.println(u);
            }
            for (Ubicacion u : ubicacions) {
                System.out.println(u);
            }

            for (Artefacto u : artefactos) {
                System.out.println(u);
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Usuario> listaUsuarios(){
        JAXBContext context = null;
        List<Usuario> usuarios = null;
        try {
            context = JAXBContext.newInstance(GeocacherData.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            GeocacherData geocacherData = (GeocacherData) unmarshaller.unmarshal(new File("files/DataLoads.xml"));
            usuarios = geocacherData.getUsuarios();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }
    public static List<Artefacto> listaArtefacto(){
        JAXBContext context = null;
        List<Artefacto> artefactos = null;
        try {
            context = JAXBContext.newInstance(GeocacherData.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            GeocacherData geocacherData = (GeocacherData) unmarshaller.unmarshal(new File("files/DataLoads.xml"));
            artefactos = geocacherData.getArtefactos();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return artefactos;
    }

    public static List<Ubicacion> listaUbicacion(){
        JAXBContext context = null;
        List<Ubicacion> ubicacions = null;
        try {
            context = JAXBContext.newInstance(GeocacherData.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            GeocacherData geocacherData = (GeocacherData) unmarshaller.unmarshal(new File("files/DataLoads.xml"));
            ubicacions = geocacherData.getUbicacions();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return ubicacions;
    }

}
