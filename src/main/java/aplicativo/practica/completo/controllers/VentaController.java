package aplicativo.practica.completo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aplicativo.practica.completo.models.dtos.VentaDTO;
import aplicativo.practica.completo.models.entity.Venta;
import aplicativo.practica.completo.services.VentaService;



@RestController
@RequestMapping("/api/venta")

public class VentaController {
	
	   @Autowired
	    private  VentaService ventaService;
	   
	   

		@GetMapping("/listar")
		// @ApiOperation(value = "Crear un nuevo producto", notes = "Crea un nuevo producto en el sistema.")

		public List<Venta> listar() {
			return ventaService.findAll();
		}
		
		@GetMapping("/listarId/{id}")
	
		public Venta detalle(@PathVariable Long id) throws Exception {
			return ventaService.findById(id);
		}
	   @PostMapping("/realiarventa")
	
	    public ResponseEntity<Venta> procesarVenta(@RequestBody VentaDTO ventaDTO) {
		   
	
		    Venta ventaGuardada =   ventaService.RealizarVenta(ventaDTO);
	        return new ResponseEntity<>( ventaGuardada,HttpStatus.CREATED);
	        
		  
	        
	        
	    }
	   
	   @PutMapping("/actualizar/{id}")
	
	    public ResponseEntity<Venta> actualizarVenta(@PathVariable Long id, @RequestBody VentaDTO ventaDTO) {
	        Venta ventaActualizada = ventaService.ActualizarVenta(id, ventaDTO);
	        if (ventaActualizada != null) {
	            return new ResponseEntity<>(ventaActualizada, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	   

	 
	   @DeleteMapping("/eliminar/{id}")
	
	   public ResponseEntity<?> delete (@PathVariable(value ="id") long id) throws Exception{
		   
		 
		   Venta VentaDB = ventaService.findById(id);
		   
		   if(VentaDB == null) {
			   
			   return ResponseEntity.notFound().build();
			   
		   }
		   
		   ventaService.deleteById(id);
		   
		   return  ResponseEntity.ok().build();
	   }
	   

}
