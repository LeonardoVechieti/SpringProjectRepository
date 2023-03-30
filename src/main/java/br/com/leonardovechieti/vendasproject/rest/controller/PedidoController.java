package br.com.leonardovechieti.vendasproject.rest.controller;

import br.com.leonardovechieti.vendasproject.model.Cliente;
import br.com.leonardovechieti.vendasproject.model.Pedido;
import br.com.leonardovechieti.vendasproject.repository.PedidoRepository;
import br.com.leonardovechieti.vendasproject.rest.dto.PedidoDTO;
import br.com.leonardovechieti.vendasproject.service.PedidoService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    //Injeção de dependência

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping ("/")
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto) {
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }
}
