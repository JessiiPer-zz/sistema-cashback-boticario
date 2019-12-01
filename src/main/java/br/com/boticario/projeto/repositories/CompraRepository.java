package br.com.boticario.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.boticario.projeto.entities.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {

}
