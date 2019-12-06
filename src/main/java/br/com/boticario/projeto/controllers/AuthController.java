package br.com.boticario.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.boticario.projeto.dto.JwtRequest;
import br.com.boticario.projeto.dto.JwtResponse;
import br.com.boticario.projeto.entities.Revendedor;
import br.com.boticario.projeto.repositories.RevendedorRepository;
import br.com.boticario.projeto.security.JwtToken;
import br.com.boticario.projeto.services.JwtUserDetailsService;
import br.com.boticario.projeto.services.exception.DatabaseException;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    
    @Autowired
    private RevendedorRepository revendedorRepository;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {


        authenticate(authenticationRequest.getEmail(), authenticationRequest.getSenha());

        final UserDetails userDetails = jwtUserDetailsService

                .loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtToken.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));

    }

    private void authenticate(String username, String password) throws Exception {

        try {

        	Revendedor revendedor = revendedorRepository.findByEmail(username);
        	if(revendedor.getStatusPerfilRevendedor().getCode() == 2){
        		throw new DatabaseException("Conta Inativa");
        	}
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {

            throw new DatabaseException("USER_DISABLED");

        } catch (BadCredentialsException e) {

            throw new DatabaseException("INVALID_CREDENTIALS");

        }

    }

}
