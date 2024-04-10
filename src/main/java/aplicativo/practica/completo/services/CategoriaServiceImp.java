package aplicativo.practica.completo.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aplicativo.practica.completo.models.entity.Categoria;
import aplicativo.practica.completo.repositories.CategoriaRepository;



@Service
public class CategoriaServiceImp  implements  CategoriaService{
	
	@Autowired
	   private 	CategoriaRepository categoriaRepository;

	@Override
	public Categoria findById(Long id) throws Exception {
	
		try {
			return categoriaRepository.findById(id).orElse(null);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Categoria save(Categoria categoria) throws Exception {
		
		try {
			return categoriaRepository.save(categoria);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Categoria> search(String filtro) {
		// TODO Auto-generated method stub
		return  null;
	}

	@Override
	public List<Categoria> findAll() {
		
		return (List<Categoria>) categoriaRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
	
		categoriaRepository.deleteById(id);
	}
	
	

}
