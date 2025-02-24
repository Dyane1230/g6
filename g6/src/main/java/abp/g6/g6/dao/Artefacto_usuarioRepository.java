package abp.g6.g6.dao;



import abp.g6.g6.model.Artefacto_usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Artefacto_usuarioRepository extends JpaRepository<Artefacto_usuario, Integer> {
    @Query(value = "SELECT * FROM artefacto_usuario WHERE usuario_id= :usuario_id AND artefacto_id= :artefacto_id", nativeQuery = true)
    List<Artefacto_usuario> FindUsuarioIdArtefactoId(@Param("usuario_id") int usuario_id, @Param("artefacto_id") int artefacto_id);

    @Query(value = "SELECT * FROM artefacto_usuario WHERE usuario_id= :usuario_id", nativeQuery = true)
    List<Artefacto_usuario> encontrarArtefactosDeUsuario(@Param("usuario_id") int usuario_id);

    //PARTE 2
    @Query(value = "SELECT au.*  FROM usuario u INNER JOIN artefacto_usuario au ON u.id = au.usuario_id INNER JOIN  artefacto a ON a.id=au.artefacto_id WHERE au.usuario_id = :id AND a.descripcion LIKE %:text%", nativeQuery = true)
    List<Artefacto_usuario> searchUsuarioArtefacto(@Param("id") int id, @Param("text") String text);


}
