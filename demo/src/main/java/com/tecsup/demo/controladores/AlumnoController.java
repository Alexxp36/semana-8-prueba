package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Alumno;
import com.tecsup.demo.servicios.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("alumno")
public class AlumnoController {
    @Autowired
    private AlumnoService servicio;

    @RequestMapping(value = "/alumnos", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Alumnos   CJAVA");
        model.addAttribute("alumnos", servicio.listar());
        return "listarAlumnos";
    }

    @RequestMapping(value = "/alumno/form")
    public String crear(Map<String, Object> model) {
        Alumno alumno = new Alumno();
        model.put("alumno", alumno);
        model.put("titulo", "Formulario de Alumno");
        return "formAlumno";
    }

    @RequestMapping(value = "/alumno/form/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Alumno alumno = null;
        if(id > 0) {
            alumno = servicio.buscar(id);
        } else {
            return "redirect:/alumnos";
        }
        model.put("alumno", alumno);
        model.put("titulo", "Editar Alumno");
        return "formAlumno";
    }

    @RequestMapping(value = "/alumno/form", method = RequestMethod.POST)
    public String guardar(@Valid Alumno alumno, BindingResult result, Model model, SessionStatus status) {
        if(result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Alumno");
            return "formAlumno";
        }
        servicio.grabar(alumno);
        status.setComplete();
        return "redirect:/alumnos";
    }

    @RequestMapping(value = "/alumno/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if(id > 0) {
            servicio.eliminar(id);
        }
        return "redirect:/alumnos";
    }
}
