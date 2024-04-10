package aplicativo.practica.completo.services;

import java.util.List;

import aplicativo.practica.completo.models.entity.Cliente;



public interface ClienteService {
	
	public List<Cliente> findAll();
	public Cliente findById(Long id) throws Exception;
	public Cliente save(Cliente cliente) throws Exception;
	public List<Cliente> search(String filtro) throws Exception;
	public void deleteById(Long id);

}
