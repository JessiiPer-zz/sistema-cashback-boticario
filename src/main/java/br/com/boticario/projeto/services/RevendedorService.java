package br.com.boticario.projeto.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.boticario.projeto.entities.Revendedor;
import br.com.boticario.projeto.entities.enums.StatusRevendedor;
import br.com.boticario.projeto.repositories.RevendedorRepository;
import br.com.boticario.projeto.services.exception.ResourceNotFoundException;
import br.com.boticario.projeto.util.Util;

@Service
public class RevendedorService {

	@Autowired
	private RevendedorRepository revendedorRepository;
	
	public List<Revendedor> findAll(){
		return revendedorRepository.findAll();
	}
	
	public Revendedor findById(Long id) {

		Optional<Revendedor> revendedor = revendedorRepository.findById(id);
		return revendedor.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Revendedor insert(Revendedor revendedor) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		revendedor.setStatusPerfilRevendedor(StatusRevendedor.ATIVO);
		revendedor.setSenha(Util.criptografarSenha(revendedor.getSenha()));
		return revendedorRepository.save(revendedor);
	}
	
	public Revendedor update(Long id, Revendedor revendedor) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		try {
			Revendedor entity = revendedorRepository.getOne(id);
			updateData(entity, revendedor);
			return revendedorRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	private void updateData(Revendedor entity, Revendedor revendedor) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		entity.setEmail((revendedor.getEmail()));
		entity.setNome(revendedor.getNome());
		entity.setSenha(Util.criptografarSenha(revendedor.getSenha()));
	}

	public void inativarRevendedor(Long id) {
		Revendedor entity = revendedorRepository.getOne(id);
		entity.setStatusPerfilRevendedor(StatusRevendedor.INATIVO);
		revendedorRepository.saveAndFlush(entity);
		
	}
	
}
