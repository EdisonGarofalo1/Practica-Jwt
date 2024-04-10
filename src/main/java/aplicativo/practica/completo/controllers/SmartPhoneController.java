package aplicativo.practica.completo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import aplicativo.practica.completo.models.entity.SmartPhone;
import aplicativo.practica.completo.services.SmartPhoneService;

import java.util.List;


@RestController
@RequestMapping("/api/celular/")
public class SmartPhoneController {
	
	private SmartPhoneService phoneService;

    @Autowired
    public SmartPhoneController(SmartPhoneService phoneService) {
        this.phoneService = phoneService;
    }

  
    @PostMapping("crear")
    public ResponseEntity<String> crearCelular(@RequestBody SmartPhone smartPhone) throws Exception{
    	try {
    	  String mensaje = phoneService.save(smartPhone);
        return ResponseEntity.ok(mensaje);
    	} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    }
    @PutMapping( "actualizar/{id}")
    public ResponseEntity<String> actualizarCelular(@RequestBody SmartPhone smartPhone ,@PathVariable Long id) throws Exception {
    	try {
    	SmartPhone smartPhoneDB = phoneService.findById(id);
    	smartPhoneDB.setMarca(smartPhone.getMarca());
    	smartPhoneDB.setPrecio(smartPhone.getPrecio());
    
    	String mensaje = phoneService.save(smartPhoneDB);
          return ResponseEntity.ok(mensaje);
          
    	} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    }

    @GetMapping("listar")
    public List<SmartPhone> listarCelulares() {
        return phoneService.findAll();
    }


    @GetMapping("listarId/{id}")
    public SmartPhone obtenerCelularPorId(@PathVariable Long id) throws Exception {
        return phoneService.findById(id);
    }

  
  

 
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminarCelular(@PathVariable Long id)  throws Exception{
    	 String mensaje = phoneService.deleteById(id);
         return ResponseEntity.ok(mensaje);
    }


}
