package aplicativo.practica.completo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aplicativo.practica.completo.models.entity.Producto;
import aplicativo.practica.completo.repositories.ProductoRepository;


@Service
public class ProductoServiceImp  implements ProductoService{
	
	@Autowired
	   private ProductoRepository productoRepository;

	@Override
	public List<Producto> findAll() {
		
		return (List<Producto>) productoRepository.findAll();
	}

	@Override
	public Producto findById(Long id) throws Exception {
		try {
			return productoRepository.findById(id).orElse(null);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Producto save(Producto producto) throws Exception {
		try {
			return productoRepository.save(producto);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}


	@Override
	public void deleteById(Long id) {
		productoRepository.deleteById(id);
		
	}
	
	
	@Override
	public List<Producto> search(String filtro) throws Exception {
	
		try {
			
		return productoRepository.BuscarProductoPorNombre(filtro);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	
	}

	@Override
	public List<Producto> BuscarProductoXcategoria(Long id) throws Exception {
		
		try {
			
			
			return  null;
					//(List<Producto>) productoRepository.BuscarProductoXcategoria(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	
	}
 
}
