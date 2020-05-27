package com.desafio.backend.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numeroDaConta;
    private String nomeDoResponsavel;
    private int saldo;

    @ElementCollection
    @CollectionTable(name = "extrato_clientes", joinColumns = @JoinColumn(name = "cliente_id"))
    @Column(name = "extrato")
    private Set<String> extrato = new HashSet<>();

    protected Cliente() {
    }

    public void realizarSaque(int valorASerSacado){
        saldo -= valorASerSacado;
        extrato.add(retornarDataAtual() + "/ Saque realizado no valor de: R$ " + valorASerSacado);
    }

    public void realizarDeposito(int valorASerDepositado){
        saldo += valorASerDepositado;
        extrato.add(retornarDataAtual() + "/ Deposito realizado no valor de: R$ " + valorASerDepositado);
    }

    public void realizarTransferencia(int valorASerTransferido, int contaDestino){
        saldo -= valorASerTransferido;
        extrato.add(retornarDataAtual() + "/ Transferência realizada no valor de: R$ " + valorASerTransferido + " para: " + contaDestino);
    }

    public void receberTransferencia(int valorASerTransferido, int contaProvedora){
        saldo += valorASerTransferido;
        extrato.add(retornarDataAtual() + "/ Transferência recebida no valor de: R$ " + valorASerTransferido + " de: " + contaProvedora);
    }

    public String retornarDataAtual(){
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
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

    public Set<String> getExtrato() {
        return extrato;
    }

    public void setExtrato(Set<String> extrato) {
        this.extrato = extrato;
    }
}
