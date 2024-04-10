package aplicativo.practica.completo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import aplicativo.practica.completo.models.entity.Venta;


public interface VentaRepository  extends  JpaRepository<Venta , Long> {

}
