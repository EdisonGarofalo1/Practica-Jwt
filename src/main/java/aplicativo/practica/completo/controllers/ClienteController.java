package aplicativo.practica.completo.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import aplicativo.practica.completo.models.entity.Cliente;
import aplicativo.practica.completo.services.ClienteService;



@RestController
@RequestMapping("/api/cliente")

public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/listar")

	public List<Cliente> listar() {
		return clienteService.findAll();
	}
	
	@GetMapping("/listarId/{id}")

	public Cliente detalle(@PathVariable Long id) throws Exception {
		return clienteService.findById(id);
	}
	
	
	@PostMapping("/crear")

	@ResponseStatus(HttpStatus.CREATED)
	public Cliente crear(@RequestBody Cliente cliente) throws Exception {
		return clienteService.save(cliente);
	}
	
	@PutMapping("/actualizar/{id}")

	@ResponseStatus(HttpStatus.CREATED)
	public Cliente editar(@RequestBody Cliente cliente, @PathVariable Long id) throws Exception {

		try {
			Cliente clienteDB = clienteService.findById(id);
			clienteDB.setNombre(cliente.getNombre());
			clienteDB.setTelefono(cliente.getTelefono());
			clienteDB.setDireccion(cliente.getDireccion());
			
		
			return clienteService.save(clienteDB);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	

    @DeleteMapping("/eliminar/{id}")
		
	   public ResponseEntity<?> delete (@PathVariable(value ="id") long id) throws Exception{
		   
		 
    	Cliente ClienteDB = clienteService.findById(id);
		   
		   if(ClienteDB == null) {
			   
			   return ResponseEntity.notFound().build();
			   
		   }
		   
		   clienteService.deleteById(id);
		   
		   return  ResponseEntity.ok().build();
	   }

    
	@GetMapping("/buscar/{nombre}")
	public List<Cliente> BuscarPorNombre(@PathVariable String nombre) throws Exception {
		return clienteService.search(nombre);
	}
}
