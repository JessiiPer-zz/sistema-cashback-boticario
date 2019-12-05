package br.com.boticario.projeto.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.boticario.projeto.entities.Revendedor;
import br.com.boticario.projeto.repositories.RevendedorRepository;

@Component
public class JwtUserDetailsService implements UserDetailsService {

	    @Autowired
	    private RevendedorRepository revendedorRepository;


	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	        Revendedor user = revendedorRepository.findByEmail(email);
	        if (user == null) {
	            throw new UsernameNotFoundException("Revendedor não encontrado com o nome de : " + email);
	        }
	        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getSenha(),
	                new ArrayList<>());
	    }

	
}

