package br.com.boticario.projeto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.boticario.projeto.entities.Revendedor;
import br.com.boticario.projeto.repositories.RevendedorRepository;

@Service
public class RevendedorService {

	@Autowired
	private RevendedorRepository revendedorRepository;
	
	public List<Revendedor> findAll(){
		return revendedorRepository.findAll();
	}

	public Revendedor insert(Revendedor user) {
		return revendedorRepository.save(user);
	}
}
