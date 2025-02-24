package abp.g6.g6.service;

import abp.g6.g6.controller.ArtefactoController;
import abp.g6.g6.dao.ArtefactoRepository;
import abp.g6.g6.dao.UbicacionRepository;
import abp.g6.g6.dataLoads.GeocacherData;
import abp.g6.g6.dataLoads.Main;
import abp.g6.g6.model.Artefacto;
import abp.g6.g6.model.Ubicacion;
import abp.g6.g6.model.Usuario;
import org.apache.coyote.Response;
import org.hibernate.annotations.processing.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtefactoService {

    @Autowired
    private ArtefactoRepository artefactoRepository;

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public ResponseEntity getArtefactos() {

        if (artefactoRepository.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron artefactos.");
        }

        try {
            return ResponseEntity.ok(artefactoRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error interno al procesar la solicitud.");
        }
    }

    public ResponseEntity getArtefactoById(int id) {

        try {
            Optional<Artefacto> artefacto = artefactoRepository.findById(id);
            if (artefacto.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron artefactos con id " + id + ".");
            }
            return ResponseEntity.ok(artefacto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error interno al procesar la solicitud.");
        }
    }

    public ResponseEntity postArtefacto(Artefacto artefacto) {

        if (artefacto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El artefacto no puede ser nulo.");
        }

        if (artefacto.getNombre() == null || artefacto.getNombre().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre del artefacto es obligatorio.");
        }

        try {
            artefactoRepository.save(artefacto);
            return ResponseEntity.status(HttpStatus.CREATED).body("El artefacto se ha creado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error interno al procesar la solicitud.");
        }
    }

    public ResponseEntity putArtefacto(Artefacto artefacto, int id) {

        if (artefacto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron artefactos con id " + id + ".");
        }

        try {
            Artefacto a = artefactoRepository.getReferenceById(id);
            a.setNombre(artefacto.getNombre());
            a.setDescripcion(artefacto.getDescripcion());
            a.setEstado(artefacto.getEstado());
            artefactoRepository.save(a);
            return ResponseEntity.ok("El artefacto con id " + id + " se ha actualizado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error interno al procesar la solicitud.");
        }
    }

    public ResponseEntity deleteArtefacto(int id) {

        Optional<Artefacto> artefactoOptional = artefactoRepository.findById(id);
        if (artefactoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron artefactos con id " + id + ".");
        }

        try {
            artefactoRepository.deleteById(id);
            return ResponseEntity.ok().body("El artefacto con id " + id + " se ha eliminado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error interno al procesar la solicitud.");
        }
    }

    public ResponseEntity getArtefactoByText (String text) {

        if (text == null || text.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El texto no puede estar vacío.");
        }

        try {
            List<Artefacto> artefactos = artefactoRepository.findByText(text);

            if (artefactos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron artefactos.");
            }
            return ResponseEntity.ok(artefactos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hubo un error interno al procesar la solicitud.");
        }
    }

    public ResponseEntity cargarArtefactosDesdeXML() {
        try {
            Main main = new Main();
            List<abp.g6.g6.dataLoads.Artefacto> artefactos = main.listaArtefacto();
            List<Artefacto> artefactoList = new ArrayList<>();

            for (abp.g6.g6.dataLoads.Artefacto a : artefactos) {
                Artefacto artefactoAUX = new Artefacto();
                artefactoAUX.setNombre(a.getNombre());
                artefactoAUX.setDescripcion(a.getDescripcion());
                artefactoAUX.setEstado(0);
                artefactoAUX.setEnlace(a.getEnlace());
                artefactoAUX.setLatitude(a.getLatitude());
                artefactoAUX.setLongitude(a.getLongitude());

                if (a.getId_ubicacion() != null) {
                    Ubicacion ubicacionBD = ubicacionRepository.getReferenceById(a.getId());
                    artefactoAUX.setUbicacion(ubicacionBD);
                }
                artefactoList.add(artefactoAUX);
                artefactoRepository.saveAll(artefactoList);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("La carga de datos ha sido satisfactoria.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cargar los datos desde el XML.");
        }
    }
}
