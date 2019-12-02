package br.com.boticario.projeto.controllers;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.boticario.projeto.entities.Compra;
import br.com.boticario.projeto.services.CompraService;

@RestController
@RequestMapping(value="/compra")
public class CompraController {

	@Autowired
	private CompraService compraService;
	
	@GetMapping
	public ResponseEntity<List<Compra>> findAll(){
		List<Compra> list = compraService.findAll();
		return ResponseEntity.ok(list);
		
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Compra> findById(@PathVariable Long id){
		Compra order = compraService.findById(id);
		return ResponseEntity.ok(order);
	}

	
	@PostMapping
	public ResponseEntity<Compra> insert(@RequestBody Compra compra) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		compra = compraService.insert(compra);
		return ResponseEntity.ok().body(compra);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Compra> update(@PathVariable Long id, @RequestBody Compra compra){
		compra = compraService.update(id, compra);
		return ResponseEntity.ok(compra);
		
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		compraService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
