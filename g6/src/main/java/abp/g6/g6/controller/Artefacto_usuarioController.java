package abp.g6.g6.controller;


import abp.g6.g6.service.Artefacto_usuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Artefacto_usuarioController {

    @Autowired
    private Artefacto_usuarioService artefactoUsuarioService;

    @PostMapping("/usuario/{id}/artefacto/{id_artefacto}")
    public ResponseEntity cerarUsuarioByIdArtefactoById(@PathVariable int id, @PathVariable int id_artefacto ){
        return artefactoUsuarioService.NuevoArtefacto_usuarioById(id_artefacto, id);
    }

    @PutMapping("/usuario/{id}/artefacto/{id_artefacto}")
    public ResponseEntity ActualizarUsuarioByIdArtefactoById(@PathVariable int id, @PathVariable int id_artefacto){
        return artefactoUsuarioService.ActualizarArtefacto_usuarioById(id_artefacto, id);
    }

    @DeleteMapping("/usuario/{id}/artefacto/{id_artefacto}")
    public ResponseEntity eliminarArtefacto_usaurio(@PathVariable int id, @PathVariable int id_artefacto){
        return artefactoUsuarioService.eliminarArtefacto_usuario(id, id_artefacto);
    }

    //PARTE 2
    @GetMapping("/usuario/{id}/artefacto/search")
    public ResponseEntity searchUsuarioArtefacto(@PathVariable int id, @RequestParam String text){
        return artefactoUsuarioService.searchUsuarioArtefacto(id, text);
    }

    //DATA LOADS
    @PostMapping("usuario/artefacto/cargarDatos")
    public ResponseEntity dataLoads(){
        return artefactoUsuarioService.cargarDatosArtefactoUsuario();
    }

}
