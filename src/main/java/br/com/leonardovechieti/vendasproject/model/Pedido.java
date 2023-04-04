package br.com.leonardovechieti.vendasproject.model;
import br.com.leonardovechieti.vendasproject.model.enums.StatusPedido;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    //Mapeamento de relacionamento de muitos para um.
    @ManyToOne
    @JoinColumn(name = "cliente_id") // @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(name = "total")
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;

    //Mapeamento de relacionamento de um para muitos.
    @ManyToMany (mappedBy = "pedido")
    private List<ItemPedido> itens;

}
