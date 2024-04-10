package aplicativo.practica.completo.models.entity;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.*;

@Entity
@Table(name = "producto",uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
public class Producto  implements Serializable{
	
	


	private static final long serialVersionUID = 3791493197760367854L;

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long producto_id;

	    @Column(name = "nombre")
	    private String nombre;
	    
	  
	    @ManyToOne (fetch= FetchType.LAZY, optional=false)
        @JoinColumn(name = "categoria_id")
        @JsonProperty(access = Access.WRITE_ONLY)
		private   Categoria categoria;
	    
	
		@Column(name = "precio")
		    private Double precio;
		  
		  @Column(name = "stock")
		    private Integer stock;

		public Producto() {
			super();
		}

		public Long getProducto_id() {
			return producto_id;
		}

		public void setProducto_id(Long producto_id) {
			this.producto_id = producto_id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public Categoria getCategoria() {
			return categoria;
		}

		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}

		public Double getPrecio() {
			return precio;
		}

		public void setPrecio(Double precio) {
			this.precio = precio;
		}

		public Integer getStock() {
			return stock;
		}

		public void setStock(Integer stock) {
			this.stock = stock;
		}

}
