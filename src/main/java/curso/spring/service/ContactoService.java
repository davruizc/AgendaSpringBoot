package curso.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.spring.model.Contacto;
import curso.spring.repository.ContactoRepository;

@Service
public class ContactoService {

    @Autowired
    private ContactoRepository repositorio;

    public List<Contacto> obtenerListaContactos() {
        //return repositorio.findAll();
        return repositorio.findTop10ByOrderByNombre();
    }

    public void guardarContacto(Contacto contacto) {
        repositorio.save(contacto);
    }
    
    public void borrarContacto(Contacto contacto) {
        repositorio.delete(contacto);
    }
    
    public void eliminarContacto(long id) {
    	repositorio.deleteById(id);
    }
    
    public void editarContacto(Contacto contacto) {
    	guardarContacto(contacto);
    }
    
    public boolean comprobarContacto(long id) {
    	return repositorio.existsById(id);
    }
    
    public long obtenerNumeroContactos() {
    	return repositorio.count();
    }
    
    public Contacto obtenerContacto(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    public Contacto obtenerContacto(long id) {
        return repositorio.findById(id);
    }
    
	public List<Contacto> buscador(String cadena) {
		return repositorio.findByNombreContainsOrApellidosContains(cadena, cadena);
		//return repositorio.buscarPorNombreOEmail(cadena);
		//return repositorio.buscarPorNombreOEmailNativa(cadena);
		
	}

}
