package aplicativo.practica.completo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import aplicativo.practica.completo.models.dtos.VentaDTO;
import aplicativo.practica.completo.models.entity.Venta;


@Service
public interface VentaService {
	public List<Venta> findAll();
	public Venta findById(Long id) throws Exception;
	public Venta  RealizarVenta(VentaDTO  ventaDTO );
	public Venta  ActualizarVenta( Long id ,VentaDTO  ventaDTO );
	public void deleteById(Long id);

}
