package com.desafio.backend.model.service;

import com.desafio.backend.model.Cliente;
import com.desafio.backend.model.repository.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ClienteServico {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public void realizarDeposito(int contaDoCliente, int valorASerDepositado){
        Cliente cliente = encontrarClientePelaConta(contaDoCliente);
        cliente.realizarDeposito(valorASerDepositado);
        salvarDadosDoCliente(cliente);
    }

    public void realizarSaque(int contaDoCliente, int valorASerSacado){
        Cliente cliente = encontrarClientePelaConta(contaDoCliente);
        cliente.realizarSaque(valorASerSacado);
        salvarDadosDoCliente(cliente);
    }

    public void realizarTransferencia(int valorASerTransferido, int contaDoClienteATransferir, int contaDoClienteAReceber){
        Cliente clienteATransferir = encontrarClientePelaConta(contaDoClienteATransferir);
        Cliente clienteAReceber = encontrarClientePelaConta(contaDoClienteAReceber);

        clienteATransferir.realizarTransferencia(valorASerTransferido, contaDoClienteAReceber);
        clienteAReceber.receberTransferencia(valorASerTransferido, contaDoClienteATransferir);

        salvarDadosDoCliente(clienteATransferir);
        salvarDadosDoCliente(clienteAReceber);
    }

    public String retornarExtratoDoCliente(int numeroDaConta){
        Cliente cliente = encontrarClientePelaConta(numeroDaConta);
        Set<String> extrato = cliente.getExtrato();
        String extratoCompleto = "";

        if(extrato.isEmpty()){
            extratoCompleto = "Nenhuma operação realizada nessa conta.";
        }

        for(String operacao: extrato){
            extratoCompleto += operacao + "\n";
        }
        return extratoCompleto;
    }

    public Iterable<Cliente> findAll() {
        return clienteRepositorio.findAll();
    }

    public Cliente encontrarClientePelaConta(int numeroDaConta){
        return clienteRepositorio.getByNumeroDaConta(numeroDaConta);
    }

    public Cliente salvarDadosDoCliente(Cliente cliente){
        return clienteRepositorio.save(cliente);
    }

    public void deletarConta(int numeroDaConta) {
        Cliente cliente = encontrarClientePelaConta(numeroDaConta);
        long id = cliente.getId();
        clienteRepositorio.deleteById(id);
    }
}
