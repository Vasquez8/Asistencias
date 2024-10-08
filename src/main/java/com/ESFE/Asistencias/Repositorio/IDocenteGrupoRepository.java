package com.ESFE.Asistencias.Repositorio;

import com.ESFE.Asistencias.Entidades.DocenteGrupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IDocenteGrupoRepository extends JpaRepository<DocenteGrupo, Integer> {
    Page<DocenteGrupo> findByOrderByAnioDesc(Pageable pageable);
}
