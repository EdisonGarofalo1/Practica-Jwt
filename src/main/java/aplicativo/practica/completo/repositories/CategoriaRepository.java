package aplicativo.practica.completo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import aplicativo.practica.completo.models.entity.Categoria;



public interface CategoriaRepository  extends  JpaRepository< Categoria, Long>{

}
