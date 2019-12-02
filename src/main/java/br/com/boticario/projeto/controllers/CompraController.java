package br.com.boticario.projeto.controllers;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.boticario.projeto.entities.Compra;
import br.com.boticario.projeto.services.CompraService;

@RestController
@RequestMapping(value="/compra")
public class CompraController {

	@Autowired
	private CompraService revendedorService;
	
	@GetMapping
	public ResponseEntity<List<Compra>> findAll(){
		List<Compra> list = revendedorService.findAll();
		return ResponseEntity.ok(list);
		
	}
	
	@PostMapping
	public ResponseEntity<Compra> insert(@RequestBody Compra compra) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		compra = revendedorService.insert(compra);
		return ResponseEntity.ok().body(compra);
	}
}
