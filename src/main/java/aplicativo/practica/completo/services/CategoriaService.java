package aplicativo.practica.completo.services;

import java.util.List;

import aplicativo.practica.completo.models.entity.Categoria;



public interface CategoriaService {
	
	public List<Categoria> findAll();
	public Categoria findById(Long id) throws Exception;
	public Categoria save(Categoria categoria) throws Exception;
	public List<Categoria> search(String filtro);
	public void deleteById(Long id);

	


}
