package com.desafio.backend.model;

import javax.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numeroDaConta;
    private String nomeDoResponsavel;
    private int saldo;
    //@OneToOne(cascade= CascadeType.PERSIST)
    //private Extrato extrato;


    protected Cliente() {
    }

    public void realizarSaque(int valorASerSacado){
        saldo -= valorASerSacado;
        //extrato.adicionarSaque(valorASerSacado, numeroDaConta);
    }

    public void realizarDeposito(int valorASerDepositado){
        saldo += valorASerDepositado;
        //extrato.adicionarDeposito(valorASerDepositado, numeroDaConta);
    }

    public void realizarTransferencia(int valorASerTransferido, int contaDestino){
        saldo -= valorASerTransferido;
        //extrato.adicionarTransferencia(valorASerTransferido, numeroDaConta, contaDestino);
    }

    public void receberTransferencia(int valorASerTransferido, int contaProvedora){
        saldo += valorASerTransferido;
        //extrato.adicionarTransferencia(valorASerTransferido, contaProvedora, numeroDaConta);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setNumeroDaConta(int numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public String getNomeDoResponsavel() {
        return nomeDoResponsavel;
    }

    public void setNomeDoResponsavel(String nomeDoResponsavel) {
        this.nomeDoResponsavel = nomeDoResponsavel;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
