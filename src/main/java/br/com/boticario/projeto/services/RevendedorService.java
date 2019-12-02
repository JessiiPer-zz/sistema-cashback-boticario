package br.com.boticario.projeto.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.boticario.projeto.entities.Revendedor;
import br.com.boticario.projeto.repositories.RevendedorRepository;
import br.com.boticario.projeto.util.Util;

@Service
public class RevendedorService {

	@Autowired
	private RevendedorRepository revendedorRepository;
	
	public List<Revendedor> findAll(){
		
		return revendedorRepository.findAll();
	}

	public Revendedor insert(Revendedor user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		user.setSenha(Util.criptografarSenha(user.getSenha()));
		return revendedorRepository.save(user);
	}
}
