package abp.g6.g6.dao;


import abp.g6.g6.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(value = "SELECT id, username, pass, rol, estado, nombre, email, foto_perfil, fecha_registro, biografia,token  FROM usuario WHERE email = :email AND pass = :pass", nativeQuery = true)
    Usuario login(@Param("email") String emailUsuario,@Param("pass") String passUsuario);

    //PARTE 2
    @Query(value = "SELECT id, username, pass, rol, estado, nombre, email, foto_perfil, fecha_registro, biografia  FROM usuario WHERE biografia LIKE :text", nativeQuery = true)
    List<Usuario> searchText(@Param("text") String text);

}
