package com.ESFE.Asistencias.Servicios.Interfaces;

import com.ESFE.Asistencias.Entidades.Asistencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IAsistenciaServices {
    Page<Asistencia> BuscarTodosPaginados(Pageable pageable);
    List<Asistencia> ObtenerTodos();
    Optional<Asistencia> BuscarporId(Integer id);
    Asistencia CrearOEditar(Asistencia asistencia);
    void EliminarPorId(Integer id);
    Asistencia save(Asistencia asistencia);
}
