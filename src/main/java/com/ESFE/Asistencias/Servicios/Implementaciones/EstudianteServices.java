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
    private IEstudianteRepositoy estudianteRepository;

    @Override
    public Page<Estudiante> BuscarTodosPaginados(Pageable pageable)
    {
        return estudianteRepository.findAll(pageable);
    }

    @Override
    public List<Estudiante> ObtenerTodos() {
        return estudianteRepository.findAll();
    }

    @Override
    public Optional<Estudiante> BuscarporId(Integer id) {
        return estudianteRepository.findById(id);
    }

    @Override
    public Estudiante CrearOEditar(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void EliminarPorId(Integer id) {
        estudianteRepository.deleteById(id);
    }

    @Override
    public Estudiante BuscarPorEmailAndPin(String email, String pin) {
        return estudianteRepository.findByEmailAndPin(email, pin);
    }
}
