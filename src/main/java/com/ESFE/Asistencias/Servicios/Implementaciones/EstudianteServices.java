package com.ESFE.Asistencias.Servicios.Implementaciones;

import com.ESFE.Asistencias.Entidades.Estudiante;
import com.ESFE.Asistencias.Repositorio.IEstudianteRepositoy;
import com.ESFE.Asistencias.Servicios.Interfaces.IEstudianteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServices implements IEstudianteServices {
    @Autowired
    private IEstudianteRepositoy estudianteRepositoy;

    @Override
    public Page<Estudiante> BuscarTodosPaginados(Pageable pageable)
    {
        return estudianteRepositoy.findAll(pageable);
    }

    @Override
    public List<Estudiante> ObtenerTodos() {
        return estudianteRepositoy.findAll();
    }

    @Override
    public Optional<Estudiante> BuscarporId(Integer id) {
        return estudianteRepositoy.findById(id);
    }

    @Override
    public Estudiante CrearOEditar(Estudiante estudiante) {
        return estudianteRepositoy.save(estudiante);
    }

    @Override
    public void EliminarPorId(Integer id) {
        estudianteRepositoy.deleteById(id);
    }
}
