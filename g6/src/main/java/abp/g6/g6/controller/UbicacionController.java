package abp.g6.g6.controller;

import abp.g6.g6.model.Artefacto;
import abp.g6.g6.model.Ubicacion;
import abp.g6.g6.service.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UbicacionController {
    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping("/ubicacion")
    public ResponseEntity getUbi() {
        return ubicacionService.getUbi();
    }

    @PostMapping("/ubicacion")
    public ResponseEntity postPics(@RequestBody Ubicacion u) {
        return ubicacionService.postUbi(u);
    }

    @GetMapping("/ubicacion/{id}")
    public ResponseEntity getUbiById(@PathVariable("id") int id) {
        return ubicacionService.getUbiById(id);
    }

    @PutMapping("/ubicacion/{id}")
    public ResponseEntity putPicsId(@RequestBody Ubicacion u, @PathVariable("id") int id) {
        return ubicacionService.putUbiById(u, id);
    }
    @DeleteMapping("/ubicacion/{id}")
    public ResponseEntity deletePicsId(@PathVariable("id") int id) {
        return ubicacionService.deleteById(id);
    }

    @PostMapping("/ubicacion/{ubicacionId}/artefacte/{artefacteId}")
    public ResponseEntity postUbiArtById(@PathVariable("ubicacionId") Ubicacion idU, @PathVariable("artefacteId") int idA) {
        return ubicacionService.createArtOnUbi(idU, idA);
    }

    @PutMapping("/ubicacion/{ubicacionId}/artefacte/{artefacteId}")
    public ResponseEntity putUbiArtById(@PathVariable("ubicacionId") Ubicacion idU, @PathVariable("artefacteId") int idA) {
        return ubicacionService.putArtOnUbi(idU, idA);
    }
    @DeleteMapping("/ubicacion/{ubicacionId}/artefacte/{artefacteId}")
    public ResponseEntity deleteUbiArtById(@PathVariable("ubicacionId") Ubicacion idU, @PathVariable("artefacteId") int idA) {
        return ubicacionService.deleteArtOnUbi(idU, idA);
    }
    @GetMapping("/ubicacion/search")
    public ResponseEntity<List<Ubicacion>> searchUbicacions(@RequestParam("text") String text) {
        return ubicacionService.searchUbicacions(text);
    }

    @GetMapping("/ubicacion/{id}/artefacte/search")
    public ResponseEntity<List<Artefacto>> searchArtefactos(
            @PathVariable("id") int idUbicacion,
            @RequestParam("text") String text) {
        return ubicacionService.searchArtefactosByUbicacion(idUbicacion, text);
    }

    //data loads

    @PostMapping("/ubicacion/cargarDatos")
    public ResponseEntity cargarDatos() {
        return ubicacionService.cargarDatosUbicacion();
    }
}
