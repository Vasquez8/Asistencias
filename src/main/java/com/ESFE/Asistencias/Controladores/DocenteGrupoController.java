package com.ESFE.Asistencias.Controladores;

import com.ESFE.Asistencias.Entidades.Docente;
import com.ESFE.Asistencias.Entidades.DocenteGrupo;
import com.ESFE.Asistencias.Entidades.Grupo;
import com.ESFE.Asistencias.Servicios.Interfaces.IDocenteGrupoServices;
import com.ESFE.Asistencias.Servicios.Interfaces.IDocenteServices;
import com.ESFE.Asistencias.Servicios.Interfaces.IGrupoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/DocenteGrupos")
public class DocenteGrupoController {

    @Autowired
    private IDocenteGrupoServices docenteGrupoServices;
    @Autowired
    private IDocenteServices docenteServices; // Servicio para Docente
    @Autowired
    private IGrupoServices grupoServices; // Servicio para Grupo

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<DocenteGrupo> docenteGrupos = docenteGrupoServices.BuscarTodosPaginados(pageable);
        model.addAttribute("docenteGrupos", docenteGrupos);

        int totalPage = docenteGrupos.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumber", pageNumber);
        }
        return "docenteGrupo/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("docenteGrupo", new DocenteGrupo());
        // Cargar lista de docentes y grupos
        // Método para obtener todos los docentes
        List<Docente> docentes = docenteServices.ObtenerTodos();
        // Método para obtener todos los grupos
        List<Grupo> grupos = grupoServices.ObtenerTodos();
        model.addAttribute("docentes", docentes);
        model.addAttribute("grupos", grupos);
        return "docenteGrupo/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute DocenteGrupo docenteGrupo, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("docenteGrupo", docenteGrupo);
            return "docenteGrupo/create";
        }

        boolean isEdit = (docenteGrupo.getId() != null && docenteGrupoServices.BuscarporId(docenteGrupo.getId()).isPresent());
        docenteGrupoServices.CrearOEditar(docenteGrupo);

        if (isEdit) {
            attributes.addFlashAttribute("msg", "Editado correctamente");
        } else {
            attributes.addFlashAttribute("msg", "Creado correctamente");
        }

        return "redirect:/DocenteGrupos";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        Optional<DocenteGrupo> docenteGrupoOpt = docenteGrupoServices.BuscarporId(id);
        if (docenteGrupoOpt.isPresent()) {
            model.addAttribute("docenteGrupo", docenteGrupoOpt.get());
            return "docenteGrupo/details";
        } else {
            return "docenteGrupo/not_found";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        // Obtener el DocenteGrupo para editar
        Optional<DocenteGrupo> docenteGrupoOpt = docenteGrupoServices.BuscarporId(id);
        if (docenteGrupoOpt.isEmpty()) {
            throw new IllegalArgumentException("DocenteGrupo no encontrado: " + id);
        }
        DocenteGrupo docenteGrupo = docenteGrupoOpt.get();
        // Obtener la lista de docentes y grupos
        List<Docente> docentes = docenteServices.ObtenerTodos();
        List<Grupo> grupos = grupoServices.ObtenerTodos();
        // Añadir atributos al modelo
        model.addAttribute("docenteGrupo", docenteGrupo);
        model.addAttribute("docentes", docentes);
        model.addAttribute("grupos", grupos);
        // Retornar la vista para editar
        return "docenteGrupo/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        DocenteGrupo docenteGrupo = docenteGrupoServices.BuscarporId(id)
                .orElseThrow(() -> new IllegalArgumentException("DocenteGrupo no encontrado: " + id));
        model.addAttribute("docenteGrupo", docenteGrupo);
        return "docenteGrupo/delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute DocenteGrupo docenteGrupo, RedirectAttributes attributes) {
        docenteGrupoServices.EliminarPorId(docenteGrupo.getId());
        attributes.addFlashAttribute("msg", "Eliminado correctamente");
        return "redirect:/DocenteGrupos";
    }
}
