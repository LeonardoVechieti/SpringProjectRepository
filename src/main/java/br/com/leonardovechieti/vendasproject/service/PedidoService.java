package br.com.leonardovechieti.vendasproject.service;
import br.com.leonardovechieti.vendasproject.model.Pedido;
import br.com.leonardovechieti.vendasproject.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);

}
