package br.com.leonardovechieti.vendasproject.model;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Entity
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

//    @NotNull(message = "{campo.descricao.obrigatorio}")
    @Column(name = "descricao")
    private String descricao;

//    @NotNull(message = "{campo.preco.obrigatorio}")
    @Column(name = "preco_unitario")
    private BigDecimal preco;

}
