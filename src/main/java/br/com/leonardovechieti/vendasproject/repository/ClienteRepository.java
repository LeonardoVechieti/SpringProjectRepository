package br.com.leonardovechieti.vendasproject.repository;
import br.com.leonardovechieti.vendasproject.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


}
