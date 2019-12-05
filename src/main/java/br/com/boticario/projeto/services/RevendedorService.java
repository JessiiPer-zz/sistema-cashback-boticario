package br.com.boticario.projeto.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.boticario.projeto.entities.Revendedor;
import br.com.boticario.projeto.entities.enums.StatusRevendedor;
import br.com.boticario.projeto.repositories.RevendedorRepository;
import br.com.boticario.projeto.services.exception.DatabaseException;
import br.com.boticario.projeto.services.exception.ResourceNotFoundException;
import br.com.boticario.projeto.util.Util;

@Service
public class RevendedorService implements UserDetailsService {

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
		if (revendedorRepository.findByEmail(revendedor.getEmail()) != null){

            throw new DatabaseException("Email já cadastrado.");

        }
		 String encodedPassword = new BCryptPasswordEncoder().encode(revendedor.getSenha());
		 revendedor.setSenha(encodedPassword);
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
	
//	public String login(Login login) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//		String senha = Util.criptografarSenha(login.getSenha());
//		Revendedor user =  revendedorRepository.findByEmailAndSenha(login.getEmail(),senha);
//		if(user != null) {
//			return "Autenticação OK";
//		} else throw new DatabaseException("Falha de autenticação");
//	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Revendedor usuario = this.revendedorRepository.findByEmail(email);

        UserBuilder builder = null;
        if (usuario != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(usuario.getEmail());
            builder.password(usuario.getSenha());

        } else {
            throw new UsernameNotFoundException("Usuário não encontrado!!");
        }

        return builder.build();
    }
	
	
}
