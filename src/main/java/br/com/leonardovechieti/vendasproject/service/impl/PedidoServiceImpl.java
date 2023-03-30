package br.com.leonardovechieti.vendasproject.service.impl;

import br.com.leonardovechieti.vendasproject.exception.RegraNegocioException;
import br.com.leonardovechieti.vendasproject.model.Cliente;
import br.com.leonardovechieti.vendasproject.model.ItemPedido;
import br.com.leonardovechieti.vendasproject.model.Pedido;
import br.com.leonardovechieti.vendasproject.model.Produto;
import br.com.leonardovechieti.vendasproject.repository.ClienteRepository;
import br.com.leonardovechieti.vendasproject.repository.ItemPedidoRepository;
import br.com.leonardovechieti.vendasproject.repository.PedidoRepository;
import br.com.leonardovechieti.vendasproject.repository.ProdutoRepository;
import br.com.leonardovechieti.vendasproject.rest.dto.ItemPedidoDTO;
import br.com.leonardovechieti.vendasproject.rest.dto.PedidoDTO;
import br.com.leonardovechieti.vendasproject.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar( PedidoDTO dto ) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    @Override
    @Transactional
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidoRepository.findByIdFetchItens(id);
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }
}
