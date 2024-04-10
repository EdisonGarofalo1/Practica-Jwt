package aplicativo.practica.completo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aplicativo.practica.completo.exception.MyException;
import aplicativo.practica.completo.models.dtos.DetalleVentaDTO;
import aplicativo.practica.completo.models.dtos.VentaDTO;
import aplicativo.practica.completo.models.entity.Cliente;
import aplicativo.practica.completo.models.entity.DetalleVenta;
import aplicativo.practica.completo.models.entity.Producto;
import aplicativo.practica.completo.models.entity.Venta;
import aplicativo.practica.completo.repositories.ClienteRepository;
import aplicativo.practica.completo.repositories.DetVentaRepository;
import aplicativo.practica.completo.repositories.ProductoRepository;
import aplicativo.practica.completo.repositories.VentaRepository;


@Service
public class VentaServiceImp  implements VentaService{

	
	@Autowired
	   private  ClienteRepository   clienteRepository;
	@Autowired
	
	private VentaRepository ventaRepository;
	
	@Autowired
	   private ProductoRepository productoRepository;
	

	@Override
	public List<Venta> findAll() {
	      return(List<Venta>) ventaRepository.findAll();
	}

	
	
	
	@Override
	public Venta  RealizarVenta(VentaDTO ventaDTO) {
		
		try {
		Venta venta = new Venta();
		
		venta.setFecha(ventaDTO.getFecha()); 
		venta.setTotal(ventaDTO.getTotal()); 	
		
		  if (ventaDTO.getTotal() == null) {
              throw new IllegalArgumentException("El tota no puede ser nula");
          }
		Cliente cliente = clienteRepository.findById(ventaDTO.getCliente_id())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + ventaDTO.getCliente_id()));
		
		venta.setCliente(cliente); 
		
		
		
		
		 for (DetalleVentaDTO detalleDTO : ventaDTO.getDetallesVenta()) {
	            Producto producto = productoRepository.findById(detalleDTO.getProducto_id())
	                                                  .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + detalleDTO.getProducto_id()));
	           
	            if (producto.getStock() < detalleDTO.getCantidad()) {
	                throw new RuntimeException("No hay suficiente stock para el producto " + producto.getNombre());
	            }
	            
	            DetalleVenta detalleVenta = new DetalleVenta();
	            detalleVenta.setProducto(producto);
	            detalleVenta.setCantidad(detalleDTO.getCantidad());
	            detalleVenta.setPrecioUnitario(detalleDTO.getPrecioUnitario());
	            detalleVenta.setSubtotal(detalleDTO.getSubtotal());
	            detalleVenta.setVenta(venta);
	            venta.agregarDetalleVenta(detalleVenta);
	            // Actualizar el stock del producto
	            producto.setStock(producto.getStock() - detalleDTO.getCantidad());
	            productoRepository.save(producto);
	        }
		 Venta ventaGuardada = ventaRepository.save(venta);
		 
		 return ventaGuardada;
		 
		} catch (Exception e) {
		       
            throw new MyException("Error al procesar la venta completa", e);
        }
		
	}




	@Override
	public Venta findById(Long id) throws Exception {
	
		try {
			return ventaRepository.findById(id).orElse(null);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}



	@Override
	public Venta ActualizarVenta(Long id, VentaDTO ventaDTO) {
		
		  try {
	
	    Venta ventaExistente = ventaRepository.findById(id).orElse(null);
        if (ventaExistente != null) {
    		
        Cliente cliente = clienteRepository.findById(ventaDTO.getCliente_id())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + ventaDTO.getCliente_id()));
    		

        ventaExistente.setCliente(cliente);
        ventaExistente.setFecha(ventaDTO.getFecha());
        ventaExistente.setTotal(ventaDTO.getTotal());
       
        for (DetalleVentaDTO detalleDTO : ventaDTO.getDetallesVenta()) {
            DetalleVenta detalleExistente = ventaExistente.getDetVenta()
                    .stream()
                    .filter(detalle -> detalle.getDetalleVenta_Id().equals(detalleDTO.getId_det_ventas()))
                    .findFirst()
                    .orElse(null);
        	
            Producto producto = productoRepository.findById(detalleDTO.getProducto_id())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + detalleDTO.getProducto_id()));

            if (detalleExistente != null) {
  
                detalleExistente.setCantidad(detalleDTO.getCantidad());
                detalleExistente.setPrecioUnitario(detalleDTO.getPrecioUnitario());
                detalleExistente.setSubtotal(detalleDTO.getSubtotal());
                
               
            } else {
        	
     
            if (producto.getStock() < detalleDTO.getCantidad()) {
                throw new RuntimeException("No hay suficiente stock para el producto " + producto.getNombre());
            }
            DetalleVenta NuevodetalleVenta = new DetalleVenta();
            NuevodetalleVenta.setProducto(producto);
            NuevodetalleVenta.setCantidad(detalleDTO.getCantidad());
            NuevodetalleVenta.setPrecioUnitario(detalleDTO.getPrecioUnitario());
            NuevodetalleVenta.setSubtotal(detalleDTO.getSubtotal());
            NuevodetalleVenta.setVenta(ventaExistente);
            ventaExistente.agregarDetalleVenta(NuevodetalleVenta);
            producto.setStock(producto.getStock() - detalleDTO.getCantidad());
            productoRepository.save(producto);
        }
            
        }

     
            return ventaRepository.save(ventaExistente);
        } else {
            return null;
        }   
        
		  } catch (Exception e) {
	       
	            throw new MyException("Error al procesar la venta completa", e);
	        }
	}




	@Override
	public void deleteById(Long id) {
		productoRepository.deleteById(id);
		
	}

}
