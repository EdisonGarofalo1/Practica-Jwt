package aplicativo.practica.completo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import aplicativo.practica.completo.models.entity.DetalleVenta;


public interface DetVentaRepository   extends  JpaRepository<DetalleVenta , Long>{

}
