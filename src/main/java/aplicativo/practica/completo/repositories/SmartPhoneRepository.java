package aplicativo.practica.completo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aplicativo.practica.completo.models.entity.SmartPhone;



@Repository
public interface SmartPhoneRepository  extends JpaRepository<SmartPhone, Long>{

}
