package aplicativo.practica.completo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import aplicativo.practica.completo.models.entity.Roles;
import aplicativo.practica.completo.models.entity.Usuarios;
import aplicativo.practica.completo.repositories.UsuariosRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUsersDetailsService  implements UserDetailsService{
	
	
	private UsuariosRepository usuariosRepo;

    @Autowired
    public CustomUsersDetailsService(UsuariosRepository usuariosRepo) {
        this.usuariosRepo = usuariosRepo;
    }
    //Método para traernos una lista de autoridades por medio de una lista de roles
    public Collection<GrantedAuthority> mapToAuthorities(List<Roles> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
    //Método para traernos un usuario con todos sus datos por medio de sus username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuarios = usuariosRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new User(usuarios.getUsername(), usuarios.getPassword(), mapToAuthorities(usuarios.getRoles()));
    }

}
