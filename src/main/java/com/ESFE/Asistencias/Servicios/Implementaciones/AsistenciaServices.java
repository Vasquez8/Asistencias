package com.ESFE.Asistencias.Servicios.Implementaciones;

import com.ESFE.Asistencias.Entidades.Asistencia;
import com.ESFE.Asistencias.Repositorio.IAsistenciaRepository;
import com.ESFE.Asistencias.Servicios.Interfaces.IAsistenciaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaServices implements IAsistenciaServices {
    @Autowired
    private IAsistenciaRepository iAsistenciaRepository;

    @Override
    public Page<Asistencia> BuscarTodosPaginados(Pageable pageable)
    {
        return iAsistenciaRepository.findAll(pageable);
    }

    @Override
    public List<Asistencia> ObtenerTodos() {
        return iAsistenciaRepository.findAll();
    }

    @Override
    public Optional<Asistencia> BuscarporId(Integer id) {
        return iAsistenciaRepository.findById(id);
    }

    @Override
    public Asistencia CrearOEditar(Asistencia estudianteGrupo) {
        return iAsistenciaRepository.save(estudianteGrupo);
    }

    @Override
    public void EliminarPorId(Integer id) {
        iAsistenciaRepository.deleteById(id);
    }

    @Override
    public Asistencia save(Asistencia asistencia) {
        return iAsistenciaRepository.save(asistencia);
    }
}
