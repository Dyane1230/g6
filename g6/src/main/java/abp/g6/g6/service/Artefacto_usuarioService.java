package abp.g6.g6.service;


import abp.g6.g6.dao.ArtefactoRepository;
import abp.g6.g6.dao.Artefacto_usuarioRepository;
import abp.g6.g6.dao.UsuarioRepository;
import abp.g6.g6.dataLoads.Main;
import abp.g6.g6.model.Artefacto;
import abp.g6.g6.model.Artefacto_usuario;
import abp.g6.g6.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Artefacto_usuarioService {
    @Autowired
    private Artefacto_usuarioRepository artefacto_usuarioRepository;

    @Autowired
    private ArtefactoRepository artefactoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity NuevoArtefacto_usuarioById(int id_artefacto, int id_usuario) {
        try {
            Usuario usuario = usuarioRepository.getReferenceById(id_usuario);
            Artefacto artefacto = artefactoRepository.getReferenceById(id_artefacto);
            if (usuario == null && artefacto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe el Usuario con id " + id_usuario + "o artefacto con id " + id_artefacto);
            } else {
                Artefacto_usuario artefacto_Usuario = new Artefacto_usuario(usuario, artefacto);
                artefacto_usuarioRepository.save(artefacto_Usuario);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }


    public ResponseEntity ActualizarArtefacto_usuarioById(int idArtefacto, int idUsuario) {
        try {

            Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
            Artefacto artefacto = artefactoRepository.getReferenceById(idArtefacto);

            Artefacto_usuario artefacto_Usuario = new Artefacto_usuario(usuario, artefacto);
            artefacto_usuarioRepository.save(artefacto_Usuario);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }


    public ResponseEntity eliminarArtefacto_usuario(int id_usuario, int id_artefacto) {
        try {
            List<Artefacto_usuario> artefactoUsuarios = artefacto_usuarioRepository.FindUsuarioIdArtefactoId(id_usuario, id_artefacto);
            if (artefactoUsuarios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe el Usuario con id " + id_usuario + "o artefacto con id " + id_artefacto);
            }
            for (Artefacto_usuario artefacto_usuario : artefactoUsuarios) {
                artefacto_usuarioRepository.delete(artefacto_usuario);
            }

            return ResponseEntity.ok("Artefacto_usuario eliminado ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }


    // PARTE 2

    public ResponseEntity searchUsuarioArtefacto(int id, String text) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            if (usuario.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe el Usuario con id " + id);
            }
            List<Artefacto_usuario> artefactos = artefacto_usuarioRepository.searchUsuarioArtefacto(id, text);
            List<Artefacto> artefactoList = new ArrayList<>();
            for (Artefacto_usuario artefacto : artefactos) {
                artefactoList.add(artefacto.getArtefacto());
            }
            return ResponseEntity.ok(artefactoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    // data Loads
    public ResponseEntity cargarDatosArtefactoUsuario() {
        Main datosUsuario = new Main();
        List<abp.g6.g6.dataLoads.Artefacto> artefactosNuevos = datosUsuario.listaArtefacto();

        List<Artefacto_usuario> artefactoUsuarios = new ArrayList<>();

        for (abp.g6.g6.dataLoads.Artefacto ar : artefactosNuevos) {
            Artefacto artefactoAux = artefactoRepository.getReferenceById(ar.getId());

            for (abp.g6.g6.dataLoads.Usuario u : ar.getUsuarios()) {
                Usuario usuarioAux = usuarioRepository.getReferenceById(u.getId());

                Artefacto_usuario artefactoUsuarioAUX = new Artefacto_usuario();
                artefactoUsuarioAUX.setArtefacto(artefactoAux);
                artefactoUsuarioAUX.setUsuario(usuarioAux);
                artefactoUsuarioAUX.setFecha_descubrimiento(u.getFecha_registro());

                artefactoUsuarios.add(artefactoUsuarioAUX);
            }
        }

        try {
            List<Artefacto_usuario> savedRecords = artefacto_usuarioRepository.saveAll(artefactoUsuarios);
            return ResponseEntity.status(HttpStatus.CREATED).body("La carga de datos ha sido satisfactoria.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error guardando Artefacto_usuarios: " + e.getMessage());
        }
    }

}
