package curso.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.spring.model.Contacto;
import curso.spring.service.ContactoService;

@Controller
@RequestMapping("")
public class ContactoController {

	@Autowired
	private ContactoService servicio;
	
	@GetMapping("")
	public String index(Model model, @RequestParam(name="buscar", required=false, defaultValue="") String cadena) {
    	List<Contacto> listaContactos = null;
    	if(cadena.equals("")) {
    		listaContactos = servicio.obtenerListaContactos();
    	}
    	else {
    		listaContactos = servicio.buscador(cadena);
    	}
    	model.addAttribute("lista", listaContactos);
		return "index";
	}
	
	@GetMapping("/new")
	public String nuevo(Model model) {
		model.addAttribute("contacto", new Contacto());
		model.addAttribute("disabled", "disabled");
		//return "/contacto/new";
		return "/contacto/form";
	}
	
	@PostMapping("/new/submit")
	public String nuevoSubmit(@Valid @ModelAttribute Contacto contacto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "/contacto/form";
		}
		else {
			servicio.guardarContacto(contacto);
			return "redirect:/";
		}
	}
	
	@GetMapping("/edit/{id}")
	public String editContact(@PathVariable("id") int id, Model model) {
		Contacto contacto = servicio.obtenerContacto(id);
		model.addAttribute("contacto", contacto);
		//return "/contacto/edit";
		return "/contacto/form";
	}
	
	@PostMapping("/edit/submit")
	public String submitEditContact(@Valid @ModelAttribute Contacto contacto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "/contacto/form";
		}
		else {
			servicio.editarContacto(contacto);
			return "redirect:/";
		}
	}
	
	@GetMapping("/del/{id}")
	public String deleteContact(@PathVariable("id") int id, Model model) {
		servicio.eliminarContacto(id);
		return "redirect:/";
	}
}
