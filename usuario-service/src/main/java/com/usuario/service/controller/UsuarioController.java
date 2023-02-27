package com.usuario.service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entity.Usuario;
import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;
import com.usuario.service.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping
	public ResponseEntity<List<Usuario>>listarUsuarios(){
		List<Usuario> usuarios=usuarioService.getAll();
		if (usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}
	@PostMapping
	public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario){
		Usuario nuevoUsuario=usuarioService.saveUsuario(usuario);
		return ResponseEntity.ok(nuevoUsuario);
		
	}
	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity<List<Carro>> listarCarros(@PathVariable("usuarioId") int id){
		Usuario usuario= usuarioService.getUsuarioById(id);
		if (usuario==null) {
			return ResponseEntity.notFound().build();
		}
		List<Carro> carros=usuarioService.getCarros(id);
		if(carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carros);
	}
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioId") int id){
		Usuario usuario= usuarioService.getUsuarioById(id);
		if (usuario==null) {
			return ResponseEntity.notFound().build();
		}
		List<Moto> motos=usuarioService.getMotos(id);
		if(motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motos);
	}
	
	@PostMapping("/carro/{usuarioId}")
	public ResponseEntity<Carro> guardarCarro(@PathVariable("usuarioId") int id,@RequestBody Carro carro) {
		Carro nuevoCarro=usuarioService.saveCarro(id, carro);
		return ResponseEntity.ok(nuevoCarro);
	} 
	
	@PostMapping("/moto/{usuarioId}")
	public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int id,@RequestBody Moto moto){
		Moto nuevaMoto=usuarioService.saveMoto(id, moto);
		return ResponseEntity.ok(nuevaMoto);
	}
	@GetMapping("/vehiculos/{usuarioId}")
	public ResponseEntity<Map<String,Object>> listarVehiculos(@PathVariable("usuarioId") int id){
		Map<String,Object> resultado = new HashMap<>();
		resultado=usuarioService.getUsuarioAndVehiculos(id);
		return ResponseEntity.ok(resultado);
	}
}
