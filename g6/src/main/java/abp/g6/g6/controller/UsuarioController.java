package abp.g6.g6.controller;


import abp.g6.g6.model.Usuario;
import abp.g6.g6.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

/*
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Usuario usuario){
        return usuarioService.login(usuario);
    }

 */
    @GetMapping("/usuario")
    public ResponseEntity getUsuarios() {
        return usuarioService.getUsuarios();
    }
    @PostMapping("/usuario")
    public ResponseEntity nuevoUsuario(@RequestBody Usuario usuario){
        return usuarioService.nuevoUsuario(usuario);
    }

    @GetMapping("/usuario/{id}")
    public  ResponseEntity encontrarUsuarioById(@PathVariable int id){
        return usuarioService.usuarioById(id);
    }
    @PutMapping("/usuario/{id}")
    public ResponseEntity actualizarUsuarioById(@PathVariable int id, @RequestBody Usuario usuario){
        return usuarioService.actualizarUsuarioById(usuario, id);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity eliminarUsuarioById(@PathVariable int id){
        return usuarioService.eliminarUsuario(id);
    }

    //2
    @GetMapping("usuario/search")
    public ResponseEntity searchUsername (@RequestParam String text){
        return usuarioService.searchText(text);
    }


    //dataLoads
    @PostMapping("usuario/cargarDatos")
    public ResponseEntity dataLoads(){
        return usuarioService.cargarDatosUsuario();
    }
}
