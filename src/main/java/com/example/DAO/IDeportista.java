package com.example.DAO;

import com.example.model.DTOResponse.DeportistaResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.entity.Deportista;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface IDeportista extends JpaRepository<Deportista, Long>{
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE deportistas SET nombre = :nombre, ap=:ap,am=:am,especialidad=:especialidad  where id = :id",nativeQuery = true)
    int findByUpdate(@Param("id") Long id,
            @Param("nombre") String nombre,
            @Param("ap")String Ap,
            @Param("am")String Am,
            @Param("especialidad")String especialidad);
    
}
