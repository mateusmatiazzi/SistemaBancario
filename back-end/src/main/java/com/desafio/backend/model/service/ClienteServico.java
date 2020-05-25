package com.desafio.backend.model.service;

import com.desafio.backend.model.Cliente;
import com.desafio.backend.model.repository.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServico {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public void realizaDeposito(int contaDoCliente, int valorASerDepositado){
        Cliente cliente = encontrarClientePelaConta(contaDoCliente);
        cliente.realizaDeposito(valorASerDepositado);
        salvaDadosDoCliente(cliente);
    }

    public void realizaSaque(int contaDoCliente, int valorASerSacado){
        Cliente cliente = encontrarClientePelaConta(contaDoCliente);
        cliente.realizaSaque(valorASerSacado);
        salvaDadosDoCliente(cliente);
    }

    public void realizaTransferencia(int contaDoClienteATransferir, int contaDoClienteAReceber, int valorASerTransferido){
        Cliente clienteATransferir = encontrarClientePelaConta(contaDoClienteATransferir);
        Cliente clienteAReceber = encontrarClientePelaConta(contaDoClienteAReceber);

        clienteATransferir.realizaSaque(valorASerTransferido);
        clienteAReceber.realizaDeposito(valorASerTransferido);
        //registrar transferencia

        salvaDadosDoCliente(clienteATransferir);
        salvaDadosDoCliente(clienteAReceber);
    }

    public Iterable<Cliente> findAll() {
        return clienteRepositorio.findAll();
    }

    public Cliente encontrarClientePelaConta(int numeroDaConta){
        return clienteRepositorio.getByNumeroDaConta(numeroDaConta);
    }

    public Cliente salvaDadosDoCliente (Cliente cliente){
        return clienteRepositorio.save(cliente);
    }

    public void delete(Long id) {
        clienteRepositorio.deleteById(id);
    }
}
