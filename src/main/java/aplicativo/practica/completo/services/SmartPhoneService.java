package aplicativo.practica.completo.services;

import java.util.List;

import aplicativo.practica.completo.models.entity.SmartPhone;



public interface SmartPhoneService {
	public List<SmartPhone> findAll()  ;
	public SmartPhone findById(Long id) throws Exception;
	public String save(SmartPhone smartPhone) throws Exception;
	public List<SmartPhone> search(String filtro);
	public String  deleteById(Long id);
}
