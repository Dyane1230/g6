package abp.g6.g6.service;


import abp.g6.g6.dao.Artefacto_usuarioRepository;
import abp.g6.g6.dao.UsuarioRepository;
import abp.g6.g6.dataLoads.Main;
import abp.g6.g6.model.Artefacto_usuario;
import abp.g6.g6.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private Artefacto_usuarioRepository artefacto_usuarioRepository;
    private final RestTemplate restTemplate;

    public UsuarioService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public ResponseEntity getUsuarios() {
        try{
            List<Usuario> usuarios = usuarioRepository.findAll();
            if(usuarios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay datos");
            }
            return ResponseEntity.ok(usuarios);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    /*
    public ResponseEntity login (Usuario usuario) {
        String authUrl = "http://localhost:8081/auth/generate";
        try{
            Usuario usuarios = usuarioRepository.login(usuario.getEmail(), usuario.getPass());
            System.out.println(usuarios);
            if ( usuarios == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email o contraseña no son correctos.");
            }
            //ResponseEntity<String> response = restTemplate.postForEntity(authUrl, usuario, Usuario.class);
            //String Token = response.getBody();


            return ResponseEntity.ok(usuarios);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

     */

    public ResponseEntity usuarioById (int id){
        try{
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            if (usuario.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe el usuario con id "+ id);
            }
            return ResponseEntity.ok(usuario);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    public ResponseEntity nuevoUsuario (Usuario usuario){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    public ResponseEntity actualizarUsuarioById (Usuario usuario, int id){
        try{
            Optional<Usuario> usuarioE = usuarioRepository.findById(id);
            if(usuarioE.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe el usuario con id " + id);
            }
            usuarioE.get().setNombre(usuario.getNombre());
            usuarioE.get().setEmail(usuario.getEmail());
            usuarioE.get().setBiografia(usuario.getBiografia());
            usuarioE.get().setEstado(usuario.getEstado());
            usuarioE.get().setFecha_registro(usuario.getFecha_registro());
            usuarioE.get().setFoto_perfil(usuario.getFoto_perfil());
            usuarioE.get().setUsername(usuario.getUsername());
            usuarioE.get().setToken(usuario.getToken());
            ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuarioE.get()));
            return  ResponseEntity.ok(usuarioE.get());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    public ResponseEntity eliminarUsuario(int id_usuario) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id_usuario);
            if(usuario.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe el usuario con id " + id_usuario);
            }
            List <Artefacto_usuario> artefactoUsuarios = artefacto_usuarioRepository.encontrarArtefactosDeUsuario(id_usuario);
            for (Artefacto_usuario a : artefactoUsuarios){
                artefacto_usuarioRepository.deleteById(a.getId());
            }

            usuarioRepository.deleteById(id_usuario);
            return ResponseEntity.ok("Usuario "+ id_usuario +" eliminado ");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    // PARTE 2
    public ResponseEntity searchText(String text){
        try{
            List<Usuario> usuario = usuarioRepository.searchText(text);
            if(usuario.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay datos");
            }
            return ResponseEntity.ok(usuario);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //dataloads
    public ResponseEntity cargarDatosUsuario(){
        Main datosUsuario = new Main();
        List<abp.g6.g6.dataLoads.Usuario> usuariosNuevos = datosUsuario.listaUsuarios();

        List<Usuario> usuarios = new ArrayList<Usuario>();
        for (abp.g6.g6.dataLoads.Usuario usu : usuariosNuevos){
            Usuario usuarioAux = new Usuario();
            usuarioAux.setBiografia(usu.getBiografia());
            usuarioAux.setEmail(usu.getEmail());
            usuarioAux.setFecha_registro(usu.getFecha_registro());
            usuarioAux.setNombre(usu.getNombre());
            usuarioAux.setPass(usu.getPass());
            usuarioAux.setRol(usu.getRol());
            usuarioAux.setUsername(usu.getUsername());
            usuarios.add(usuarioAux);

        }

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.saveAll(usuarios));
    }



}
