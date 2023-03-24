package br.com.leonardovechieti.vendasproject.repository;
import br.com.leonardovechieti.vendasproject.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    //Buscando cliente pelo nome
    List<Cliente> findByNomeLike(String nome);

    //Todos os clientes
    @GetMapping("/clientes")
    List<Cliente> findAll();
}
