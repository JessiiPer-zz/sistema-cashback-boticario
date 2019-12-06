package br.com.boticario.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.boticario.projeto.entities.Revendedor;

@Repository
public interface RevendedorRepository extends JpaRepository<Revendedor,Long> {

	public Revendedor findByEmail(String email);

	public Revendedor findByCpf(String cpf);

}
