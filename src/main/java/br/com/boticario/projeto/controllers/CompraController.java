package br.com.boticario.projeto.controllers;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.boticario.projeto.dto.CompraDTO;
import br.com.boticario.projeto.entities.Compra;
import br.com.boticario.projeto.security.JwtToken;
import br.com.boticario.projeto.services.CompraService;

@RestController
@RequestMapping(value = "/compra")
public class CompraController {

	@Autowired
	private CompraService compraService;

	private final JwtToken jwtTokenUtil = new JwtToken();

	@GetMapping
	public Page<CompraDTO> listarCompras(@RequestHeader(name = "Authorization", required = true) String token,
			Pageable page) throws ParseException {

		 String tokenaTUAL = token.substring(7);
		 String email = jwtTokenUtil.getUsernameFromToken(tokenaTUAL);
		 
		return compraService.listaCompras(page, email);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Compra> findById(@PathVariable Long id) {
		Compra order = compraService.findById(id);
		return ResponseEntity.ok(order);
	}

	@PostMapping
	public List<CompraDTO> insert(@Valid @RequestBody List<Compra> compra)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
		return compraService.insert(compra);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Compra> update(@PathVariable Long id, @Valid @RequestBody Compra compra) {
		compra = compraService.update(id, compra);
		return ResponseEntity.ok(compra);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		compraService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
