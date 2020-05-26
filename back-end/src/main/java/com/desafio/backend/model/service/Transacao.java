package com.desafio.backend.model.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transacao {
    private String tipoDeTransacao;
    private int valorDaTransacao;
    private int contaEmQueFoiFeitaTransacao;
    private int contaEmQueFoiRecebidaATransacao;
    private String dataDaTransacao;
    private Date data;
    private SimpleDateFormat formatoDeHora = new SimpleDateFormat ("'no dia' dd.MM.yyyy");

    public Transacao(String tipoDeTransacao, int valorDaTransacao, int contaEmQueFoiFeitaTransacao) {
        this.tipoDeTransacao = tipoDeTransacao;
        this.valorDaTransacao = valorDaTransacao;
        this.contaEmQueFoiFeitaTransacao = contaEmQueFoiFeitaTransacao;
        this.data = new Date();
        dataDaTransacao = formatoDeHora.format(data);
    }

    public Transacao(String tipoDeTransacao, int valorDaTransacao, int contaEmQueFoiFeitaTransacao, int contaEmQueFoiRecebidaATransacao) {
        this.tipoDeTransacao = tipoDeTransacao;
        this.valorDaTransacao = valorDaTransacao;
        this.contaEmQueFoiFeitaTransacao = contaEmQueFoiFeitaTransacao;
        this.contaEmQueFoiRecebidaATransacao = contaEmQueFoiRecebidaATransacao;
        this.data = new Date();
        dataDaTransacao = formatoDeHora.format(data);
    }

    public String imprimirTransacao(){
        if(tipoDeTransacao.equals("Transferência"))
            return tipoDeTransacao + " de: " + Integer.toString(contaEmQueFoiFeitaTransacao) + " para:" +
                    Integer.toString(contaEmQueFoiRecebidaATransacao) + " no valor de: " +
                    Integer.toString(valorDaTransacao) + " no data de: " + dataDaTransacao;
        return tipoDeTransacao + " de: " + Integer.toString(contaEmQueFoiFeitaTransacao) + " no valor de: " +
                Integer.toString(valorDaTransacao) + " no data de: " + dataDaTransacao;
    }

    public Date getData() {
        return data;
    }

}
