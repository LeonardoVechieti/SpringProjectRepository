package br.com.leonardovechieti.vendasproject.exception;

public class PedidoNaoEncontradoException extends RuntimeException{
    public PedidoNaoEncontradoException() {
        super("Pedido não encontrado");
    }
}
