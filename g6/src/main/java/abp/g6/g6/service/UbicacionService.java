package abp.g6.g6.service;

import abp.g6.g6.dao.ArtefactoRepository;
import abp.g6.g6.dao.UbicacionRepository;
import abp.g6.g6.dataLoads.Main;
import abp.g6.g6.model.Artefacto;
import abp.g6.g6.model.Ubicacion;
import abp.g6.g6.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UbicacionService {
    @Autowired
    private UbicacionRepository ubicacionRepository;
    @Autowired
    private ArtefactoRepository artefactoRepository;

    public ResponseEntity getUbi() {
        try {
            List<Ubicacion> ubicaciones = ubicacionRepository.findAll();
            if (ubicaciones.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron ubicaciones.");
            }
            return ResponseEntity.ok(ubicaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }

    public ResponseEntity postUbi(Ubicacion u) {
        try {
            ubicacionRepository.save(u);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la ubicación.");
        }
    }

    public ResponseEntity getUbiById(int id) {
        try {
            Optional<Ubicacion> ubicacion = ubicacionRepository.findById(id);
            if (ubicacion.isPresent()) {
                return ResponseEntity.ok(ubicacion.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ubicación no encontrada.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }

    public ResponseEntity putUbiById(Ubicacion newUbi, int id) {
        try {
            Optional<Ubicacion> ubicacionOpt = ubicacionRepository.findById(id);
            if (ubicacionOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ubicación no encontrada.");
            }
            Ubicacion u = ubicacionOpt.get();
            u.setDescripcion(newUbi.getDescripcion());
            u.setEstado(newUbi.getEstado());
            u.setNombre(newUbi.getNombre());
            ubicacionRepository.save(u);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la ubicación.");
        }
    }

    public ResponseEntity deleteById(int id) {
        try {
            Optional<Ubicacion> ubicacionOpt = ubicacionRepository.findById(id);
            if (ubicacionOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ubicación no encontrada.");
            }
            ubicacionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la ubicación.");
        }
    }

    public ResponseEntity createArtOnUbi(Ubicacion idU, int idA) {
        try {
            Optional<Artefacto> artefactoOpt = artefactoRepository.findById(idA);
            if (artefactoOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artefacto no encontrado.");
            }
            Artefacto a = artefactoOpt.get();
            a.setUbicacion(idU);
            artefactoRepository.save(a);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al asignar artefacto a ubicación.");
        }
    }

    public ResponseEntity putArtOnUbi(Ubicacion idU, int idA) {
        try {
            Optional<Artefacto> artefactoOpt = artefactoRepository.findById(idA);
            if (artefactoOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artefacto no encontrado.");
            }
            Artefacto artefacto = artefactoOpt.get();
            artefacto.setUbicacion(idU);
            artefactoRepository.save(artefacto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar artefacto en ubicación.");
        }
    }

    public ResponseEntity deleteArtOnUbi(Ubicacion idU, int idA) {
        try {
            Optional<Artefacto> artefactoOpt = artefactoRepository.findById(idA);
            if (artefactoOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artefacto no encontrado.");
            }
            Artefacto artefacto = artefactoOpt.get();
            artefacto.setUbicacion(null);
            artefactoRepository.save(artefacto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar artefacto de la ubicación.");
        }
    }

    public ResponseEntity<List<Ubicacion>> searchUbicacions(String text) {
        try {
            List<Ubicacion> ubicaciones = ubicacionRepository.findAll().stream()
                    .filter(ubicacio -> ubicacio.getNombre().toLowerCase().contains(text.toLowerCase()))
                    .collect(Collectors.toList());
            if (ubicaciones.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(ubicaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    public ResponseEntity<List<Artefacto>> searchArtefactosByUbicacion(int idUbicacion, String text) {
        try {
            Optional<Ubicacion> ubiOpt = ubicacionRepository.findById(idUbicacion);
            if (ubiOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            Ubicacion ubi = ubiOpt.get();
            List<Artefacto> artefactos = artefactoRepository.findAll().stream()
                    .filter(artefacto -> artefacto.getUbicacion().equals(ubi) && artefacto.getNombre().toLowerCase().contains(text.toLowerCase()))
                    .collect(Collectors.toList());
            if (artefactos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(artefactos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //dataloads
    public ResponseEntity cargarDatosUbicacion(){
        Main datos = new Main();
        List<abp.g6.g6.dataLoads.Ubicacion> ubicacionNuevos = datos.listaUbicacion();

        List<Ubicacion> ubicacions = new ArrayList<Ubicacion>();
        for (abp.g6.g6.dataLoads.Ubicacion ubi : ubicacionNuevos){
            Ubicacion ubiAUX = new Ubicacion();
            ubiAUX.setDescripcion(ubi.getDescripcion());
            ubiAUX.setEstado(1);
            ubiAUX.setNombre(ubi.getNombre());

            ubicacions.add(ubiAUX);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(ubicacionRepository.saveAll(ubicacions));
    }

}
