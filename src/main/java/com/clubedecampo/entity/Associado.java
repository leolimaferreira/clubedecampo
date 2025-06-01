package com.clubedecampo.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "associado")
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "rg", nullable = false, length = 20)
    private String rg;

    @Column(name = "cpf", nullable = false, length = 14)
    private String cpf;

    @Column(name = "endereco", nullable = false, length = 255)
    private String endereco;

    @Column(name = "cep", nullable = false, length = 10)
    private String cep;

    @Column(name = "bairro", nullable = false, length = 50)
    private String bairro;

    @Column(name = "cidade", nullable = false, length = 50)
    private String cidade;

    @Column(name = "estado", nullable = false, length = 2)
    private String estado;

    @Column(name = "telefone_residencial", nullable = false, length = 15)
    private String telefoneResidencial;

    @Column(name = "telefone_comercial", nullable = false, length = 15)
    private String telefoneComercial;

    @Column(name = "celular", nullable = false, length = 15)
    private String celular;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_associado", length = 10)
    private TipoAssociado tipoAssociado;

    @Column(name = "status", length = 20)
    private String status = "ativo";

    @Column(name = "carteirinha_ativa")
    private boolean carteirinha_ativa = true;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDate.now();
    }

}
