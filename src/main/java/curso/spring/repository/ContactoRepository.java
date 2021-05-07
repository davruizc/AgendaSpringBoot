package curso.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import curso.spring.model.Contacto;

public interface ContactoRepository extends JpaRepository<Contacto, Long> {
	
	//Derivadas del nombre del método
	Contacto findById(long id);
	Contacto findByNombre(String nombre);
	
	List<Contacto> findTop10ByOrderByNombre();
	List<Contacto> findTop10ByOrderByIdDesc();
	
	List<Contacto> findByNombreOrApellidos(String nombre, String apellidos);
	
	List<Contacto> findByNombreContainsOrApellidosContains(String nombre, String apellidos);
		
	//JPQL (inspirado en SQL y HQL)
	@Query("select c from Contacto c where c.nombre like %?1% or c.apellidos like %?1%")
	List<Contacto> buscarPorNombreOEmail(String cadena);
	
	//Nativas (SQL del gestor que usemos)
	//@Query(value="select * from Contacto where nombre like concat('%',?1,'%') or email like concat('%',?1,'%')", nativeQuery=true)
	@Query(value="select * from Contacto where nombre like '%?1%' or apellidos like '%?1%' order by nombre", nativeQuery=true)
	List<Contacto> buscarPorNombreOEmailNativa(String cadena);
	

}
