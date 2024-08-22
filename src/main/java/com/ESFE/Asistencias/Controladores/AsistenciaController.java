package com.ESFE.Asistencias.Controladores;

import com.ESFE.Asistencias.Entidades.Asistencia;
import com.ESFE.Asistencias.Entidades.Estudiante;
import com.ESFE.Asistencias.Entidades.Grupo;
import com.ESFE.Asistencias.Servicios.Interfaces.IAsistenciaServices;
import com.ESFE.Asistencias.Servicios.Interfaces.IEstudianteServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/Asistencias")
public class AsistenciaController {

    @Autowired
    private IAsistenciaServices asistenciaServices;

    @Autowired
    private IEstudianteServices estudianteServices;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Asistencia> asistencias = asistenciaServices.BuscarTodosPaginados(pageable);
        model.addAttribute("asistencias", asistencias);

        int totalPages = asistencias.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "asistencia/index";
    }

    @PostMapping("/login")
    public String loginAsistencia(@RequestParam("email") String email, @RequestParam("pin") String pin) {
        // Autenticar estudiante
        Estudiante estudiante = estudianteServices.BuscarPorEmailAndPin(email, pin);
        if (estudiante != null) {
            // Registrar asistencia para todos los grupos del estudiante
            for (Grupo grupo : estudiante.getGrupos()) {
                Asistencia asistencia = new Asistencia();
                asistencia.setEstudiante(estudiante);
                asistencia.setGrupo(grupo);
                asistencia.setFecha(LocalDateTime.now().toLocalDate().atStartOfDay());
                asistencia.setHoraEntrada(LocalDateTime.now());

                asistenciaServices.save(asistencia);
            }
            return "redirect:/asistencia-registrada";
        } else {
            return "redirect:/Asistencias/login?error=true";
        }
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login"; // Retorna la vista del formulario de login
    }

    @PostMapping("/perform_login")
    public String performLogin(HttpServletRequest request) {
        // Redirige a la página de inicio u otra página después del login
        return "redirect:/home";
    }
}
