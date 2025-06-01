package com.clubedecampo.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "dependente")
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Dependente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "rg", nullable = false, length = 20)
    private String rg;


    @ManyToOne
    @JoinColumn(name = "associado_id")
    private Associado associado;
}
