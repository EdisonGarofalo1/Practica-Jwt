package aplicativo.practica.completo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aplicativo.practica.completo.models.dtos.DtoAuthRespuesta;
import aplicativo.practica.completo.models.dtos.DtoLogin;
import aplicativo.practica.completo.models.dtos.DtoRegistro;
import aplicativo.practica.completo.models.entity.Roles;
import aplicativo.practica.completo.models.entity.Usuarios;
import aplicativo.practica.completo.repositories.RolesRepository;
import aplicativo.practica.completo.repositories.UsuariosRepository;
import aplicativo.practica.completo.security.JwtGenerador;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

	 private AuthenticationManager authenticationManager;
	    private PasswordEncoder passwordEncoder;
	    private RolesRepository rolesRepository;
	    private UsuariosRepository usuariosRepository;
	    private JwtGenerador jwtGenerador;
	    @Autowired
	    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, RolesRepository rolesRepository, UsuariosRepository usuariosRepository, JwtGenerador jwtGenerador) {
	        this.authenticationManager = authenticationManager;
	        this.passwordEncoder = passwordEncoder;
	        this.rolesRepository = rolesRepository;
	        this.usuariosRepository = usuariosRepository;
	        this.jwtGenerador = jwtGenerador;
	    }
	    
	    //Método para poder registrar usuarios con role "user"
	    @PostMapping("register")
	    public ResponseEntity<String> registrar(@RequestBody DtoRegistro dtoRegistro) {
	        if (usuariosRepository.existsByUsername(dtoRegistro.getUsername())) {
	            return new ResponseEntity<>("el usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
	        }
	        Usuarios usuarios = new Usuarios();
	        usuarios.setUsername(dtoRegistro.getUsername());
	        usuarios.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
	        Roles roles = rolesRepository.findByName("USER").get();
	        usuarios.setRoles(Collections.singletonList(roles));
	        usuariosRepository.save(usuarios);
	        return new ResponseEntity<>("Registro de usuario exitoso", HttpStatus.OK);
	    }

	    //Método para poder guardar usuarios de tipo ADMIN
	    @PostMapping("registerAdm")
	    public ResponseEntity<String> registrarAdmin(@RequestBody DtoRegistro dtoRegistro) {
	        if (usuariosRepository.existsByUsername(dtoRegistro.getUsername())) {
	            return new ResponseEntity<>("el usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
	        }
	        Usuarios usuarios = new Usuarios();
	        usuarios.setUsername(dtoRegistro.getUsername());
	        usuarios.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
	        Roles roles = rolesRepository.findByName("ADMIN").get();
	        usuarios.setRoles(Collections.singletonList(roles));
	        usuariosRepository.save(usuarios);
	        return new ResponseEntity<>("Registro de admin exitoso", HttpStatus.OK);
	    }

	    //Método para poder logear un usuario y obtener un token
	    @PostMapping("login")
	    public ResponseEntity<DtoAuthRespuesta> login(@RequestBody DtoLogin dtoLogin) {
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	                dtoLogin.getUsername(), dtoLogin.getPassword()));
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String token = jwtGenerador.generarToken(authentication);
	        return new ResponseEntity<>(new DtoAuthRespuesta(token), HttpStatus.OK);
	    }
}
