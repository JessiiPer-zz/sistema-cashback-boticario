package br.com.boticario.projeto.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.boticario.projeto.entities.Compra;
import br.com.boticario.projeto.entities.Revendedor;
import br.com.boticario.projeto.entities.enums.CompraStatus;
import br.com.boticario.projeto.repositories.CompraRepository;
import br.com.boticario.projeto.repositories.RevendedorRepository;
import br.com.boticario.projeto.util.Util;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private RevendedorRepository revendedorRepository;
	
	@Autowired
	private CompraRepository compraRepository;
	
	Date date = new Date(System.currentTimeMillis());
	
	@Override
	public void run(String... args) throws Exception {
		
		
		Revendedor u1 = new Revendedor(null, "Joquina", "04587997005", "joaquina@joaquina.com", Util.criptografarSenha("123489910"), 1);
		Revendedor u2 = new Revendedor(null, "Francisco", "29892548035", "Francisco@Francisco.com", Util.criptografarSenha("Francisco234"), 1);
		
		revendedorRepository.saveAll(Arrays.asList(u1,u2));
		
		Compra o1 = new Compra(null, "P0009", 900.90, date, CompraStatus.EM_VALIDAÇÃO, 0.1, 9.00,u1);
		Compra o2 = new Compra(null, "P0008", 1400.00, date, CompraStatus.EM_VALIDAÇÃO, 0.15, 210.00, u2);
		Compra o3 = new Compra(null, "M0001", 1800.00, date, CompraStatus.APROVADO, 0.2,360.00, u1);
		//Compra o4 = new Compra(null, "M0001", 30.0, Instant.parse("2019-07-22T15:21:22Z"), CompraStatus.APROVADO, new Revendedor());
	
		compraRepository.saveAll(Arrays.asList(o1,o2,o3));
	}

}
