//package br.com.leonardovechieti.vendasproject.model;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "item_pedido")
//public class ItemPedido {
//    @Id
//    @GeneratedValue (strategy = GenerationType.AUTO)
//    private Integer id;
//    @Column(name = "pedido")
//    private Pedido pedido;
//    @Column(name = "produto")
//    private Produto produto;
//    @Column(name = "quantidade")
//    private Integer quantidade;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Pedido getPedido() {
//        return pedido;
//    }
//
//    public void setPedido(Pedido pedido) {
//        this.pedido = pedido;
//    }
//
//    public Produto getProduto() {
//        return produto;
//    }
//
//    public void setProduto(Produto produto) {
//        this.produto = produto;
//    }
//
//    public Integer getQuantidade() {
//        return quantidade;
//    }
//
//    public void setQuantidade(Integer quantidade) {
//        this.quantidade = quantidade;
//    }
//}