package abp.g6.g6.dao;

import abp.g6.g6.model.Artefacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtefactoRepository extends JpaRepository<Artefacto, Integer> {

    @Query("SELECT a FROM Artefacto a WHERE a.nombre LIKE %:text% OR a.descripcion LIKE %:text%")
    List<Artefacto> findByText(@Param("text") String text);

}
