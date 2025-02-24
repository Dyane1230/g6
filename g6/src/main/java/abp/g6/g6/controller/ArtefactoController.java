package abp.g6.g6.controller;

import abp.g6.g6.dao.ArtefactoRepository;
import abp.g6.g6.model.Artefacto;
import abp.g6.g6.service.ArtefactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artefacto")
public class ArtefactoController {

    @Autowired
    private ArtefactoService artefactoService;

    @GetMapping
    public ResponseEntity getAllArtefactos() {
        return artefactoService.getArtefactos();
    }

    @GetMapping("/{id}")
    public ResponseEntity getArtefactoById(@PathVariable int id) {
        return artefactoService.getArtefactoById(id);
    }

    @GetMapping("/search")
    public ResponseEntity getArtefactoByText(@RequestParam String text) {
        return artefactoService.getArtefactoByText(text);
    }

    @PostMapping
    public ResponseEntity postArtefacto(@RequestBody Artefacto artefacto) {
        return artefactoService.postArtefacto(artefacto);
    }

    @PutMapping("/{id}")
    public ResponseEntity putArtefacto(@RequestBody Artefacto artefacto, @PathVariable int id) {
        return artefactoService.putArtefacto(artefacto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteArtefactoById(@PathVariable int id) {
        return artefactoService.deleteArtefacto(id);
    }

    @PostMapping("/cargarDatos")
    public ResponseEntity cargarArtefactosDesdeXML() {
        return artefactoService.cargarArtefactosDesdeXML();
    }
}
