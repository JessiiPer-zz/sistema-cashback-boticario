package br.com.boticario.projeto.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.boticario.projeto.entities.Revendedor;
import br.com.boticario.projeto.services.RevendedorService;

@RestController
@RequestMapping(value="/revendedor")
public class RevendedorController {

	@Autowired
	private RevendedorService revendedorService;
	
	@GetMapping
	public ResponseEntity<List<Revendedor>> findAll(){//@RequestHeader(value="Authorization", required = true) String token){
		
		List<Revendedor> list = revendedorService.findAll();
		return ResponseEntity.ok(list);
		
		
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Revendedor> findById(@PathVariable Long id){
		Revendedor revendedor = revendedorService.findById(id);
		return ResponseEntity.ok(revendedor);
	}
	
	@PostMapping("/inserir")
	public ResponseEntity<Revendedor> insert(@RequestBody @Valid Revendedor user) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		user = revendedorService.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@PatchMapping(value="/{id}")
	public ResponseEntity<Void> desativarPerfilRevendedor(@PathVariable Long id){
		revendedorService.inativarRevendedor(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
