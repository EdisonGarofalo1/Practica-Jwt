package aplicativo.practica.completo.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import aplicativo.practica.completo.models.entity.SmartPhone;

import java.util.List;





@Service
public class SmartPhoneServiceImp implements SmartPhoneService{
	
/*
	   @Autowired
	    private EntityManager entityManager;
	*/   
	   
	   private final JdbcTemplate jdbcTemplate;

	    @Autowired
	    public SmartPhoneServiceImp(JdbcTemplate jdbcTemplate) {
	        this.jdbcTemplate = jdbcTemplate;
	    }


	@Override
	public List<SmartPhone> findAll(){
		
	
		
		String nombreProcedimiento = "sp_smartphonefindAll";
       
        List<SmartPhone> smartPhone = jdbcTemplate.query("CALL " + nombreProcedimiento + "()", new BeanPropertyRowMapper<>(SmartPhone.class));
		   return smartPhone;
			
	
	}

	@Override
	public SmartPhone findById(Long id) throws Exception {
		
	      try {
		  String sql = "CALL sp_smartphonefindById(?)"; // Nombre del procedimiento almacenado y sus parámetros
	        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
	            SmartPhone smartPhone = new SmartPhone();
	            smartPhone.setId_smartphone(rs.getLong("id_smartphone"));
	            smartPhone.setMarca(rs.getString("marca"));
	            smartPhone.setPrecio(rs.getLong("precio"));
	            return smartPhone;
	        });
	        
	        
	      } catch (Exception e) {
	    	  throw new Exception(e.getMessage());
	        }
	
		/*
		 StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_smartphonefindById", SmartPhone.class);
	        query.registerStoredProcedureParameter("p_id_smartphone", Long.class, ParameterMode.IN);
	        query.setParameter("p_id_smartphone", id);
            query.execute();
	        return (SmartPhone) query.getSingleResult();
	        */
		
	}

	@Override
	public String save(SmartPhone smartPhone) throws Exception {
	
		  String mensaje;
	        try {
	            jdbcTemplate.update("CALL sp_save(?, ?, ?, @mensaje)", smartPhone.getId_smartphone(),smartPhone.getMarca(), smartPhone.getPrecio());
	            mensaje = jdbcTemplate.queryForObject("SELECT @mensaje", String.class);
	        } catch (Exception e) {
	            mensaje = "Error al guardar el producto";
	        }
	        return mensaje;

	}
	
	/*
	StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("sp_save");
	        storedProcedure.setParameter("p_id_smartphone", smartPhone.getIdSmartPhone());
	        storedProcedure.setParameter("p_marca", smartPhone.getMarca());
	        storedProcedure.setParameter("p_precio", smartPhone.getPrecio());
	        storedProcedure.registerStoredProcedureParameter("mensaje", String.class, ParameterMode.OUT);
	        storedProcedure.execute();
	        

	        return (String) storedProcedure.getOutputParameterValue("mensaje");

		*/

	@Override
	public List<SmartPhone> search(String filtro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String  deleteById(Long id) {
		
		
		 String mensaje = "";
	        try {
	            jdbcTemplate.update("{CALL sp_delete(?,  @mensaje)}", id);
	            mensaje = jdbcTemplate.queryForObject("SELECT @mensaje", String.class);
	        } catch (Exception e) {
	            mensaje = e.getMessage();
	        }
	        return mensaje;

			
	
	}

}
