package br.com.leonardovechieti.vendasproject.repository;
import br.com.leonardovechieti.vendasproject.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    //Todos os Pedidos
    @GetMapping("/pedidos")
    List<Pedido> findAll();
}
