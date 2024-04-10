package aplicativo.practica.completo.services;

import java.util.List;

import aplicativo.practica.completo.models.entity.Producto;



public interface ProductoService {

	public List<Producto> findAll();
	public Producto findById(Long id) throws Exception;
	public Producto save(Producto producto) throws Exception;

	public void deleteById(Long id);
	
	public List<Producto> BuscarProductoXcategoria(Long id)throws Exception;
	public List<Producto> search(String filtro) throws Exception;
	
}
