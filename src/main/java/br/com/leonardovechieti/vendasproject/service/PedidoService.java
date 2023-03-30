package br.com.leonardovechieti.vendasproject.service;
import br.com.leonardovechieti.vendasproject.model.Pedido;
import br.com.leonardovechieti.vendasproject.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

}
