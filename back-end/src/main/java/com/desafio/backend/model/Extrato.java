package com.desafio.backend.model;

import com.desafio.backend.model.service.Transacao;

import java.util.ArrayList;
import java.util.Date;

public class Extrato {

    private ArrayList<Transacao> transacoes;

    public Extrato (){}

    public void adicionarSaque(int valorDaTransacao, int contaEmQueFoiFeitaTransacao){
        transacoes.add(new Transacao("Saque", valorDaTransacao, contaEmQueFoiFeitaTransacao));
    }

    public void adicionarDeposito(int valorDaTransacao, int contaEmQueFoiFeitaTransacao){
        transacoes.add(new Transacao("Deposito", valorDaTransacao, contaEmQueFoiFeitaTransacao));
    }

    public void adicionarTransferencia(int valorDaTransacao, int contaEmQueFoiFeitaTransacao, int contaEmQueFoiRecebidaATransacao){
        transacoes.add(new Transacao("Transferencia", valorDaTransacao, contaEmQueFoiFeitaTransacao, contaEmQueFoiRecebidaATransacao));
    }

    public String imprimirTransacoesEmDeterminadoDia(Date dataDeExtrato){
        Date dataAuxiliar = new Date(dataDeExtrato.getYear(), dataDeExtrato.getMonth(), dataDeExtrato.getDay()+1);
        return imprimirTransacoesEmIntervalo(dataDeExtrato, dataAuxiliar);
    }

    public String imprimirTransacoesEmDeterminadoMes(Date dataDeExtrato){
        Date dataAuxiliar = new Date(dataDeExtrato.getYear(), dataDeExtrato.getMonth(), 1);
        return imprimirTransacoesEmIntervalo(dataDeExtrato, dataAuxiliar);
    }

    public String imprimirTransacoesEmIntervalo(Date dataDeInicio, Date dataDeFim){
        String saidaDoExtrato = "";

        for(Transacao dataEntreIntervalo: transacoes){
            if(dataEntreIntervalo.getData().after(dataDeInicio) && dataEntreIntervalo.getData().before(dataDeFim)){
                saidaDoExtrato += dataEntreIntervalo.imprimirTransacao() + "\n";
            }
        }

        return saidaDoExtrato;
    }
}
