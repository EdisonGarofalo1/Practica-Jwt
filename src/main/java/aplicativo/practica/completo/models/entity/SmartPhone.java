package aplicativo.practica.completo.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "smartphone")
public class SmartPhone {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_smartphone")
	    private Long id_smartphone;
	    private String marca;
	    private Long precio;
	
	    
	    
		public Long getId_smartphone() {
			return id_smartphone;
		}
		public void setId_smartphone(Long id_smartphone) {
			this.id_smartphone = id_smartphone;
		}
		public String getMarca() {
			return marca;
		}
		public void setMarca(String marca) {
			this.marca = marca;
		}
		public Long getPrecio() {
			return precio;
		}
		public void setPrecio(Long precio) {
			this.precio = precio;
		}
	    
	    
}
