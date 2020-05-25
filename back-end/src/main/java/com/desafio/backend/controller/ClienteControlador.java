package com.desafio.backend.controller;

import com.desafio.backend.model.Cliente;
import com.desafio.backend.model.service.ClienteServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
@CrossOrigin
public class ClienteControlador {

    @Autowired
    private ClienteServico clienteServico;

    @PostMapping("")
    public ResponseEntity<?> adicionaContaAoRegistro(@RequestBody Cliente cliente, BindingResult resultado) {

        if(resultado.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : resultado.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }

        Cliente novoCliente = clienteServico.salvaDadosDoCliente(cliente);
        return new ResponseEntity<Cliente>(novoCliente, HttpStatus.CREATED);
    }

    @PostMapping("/{numeroDaConta}+{valor}")
    public ResponseEntity<?> realizarDepositoParaUmCliente(@PathVariable int numeroDaConta, @PathVariable int valor){
        Cliente cliente = clienteServico.encontrarClientePelaConta(numeroDaConta);
        clienteServico.realizaDeposito(numeroDaConta, valor);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.ACCEPTED);
    }

    @PostMapping("/{numeroDaConta}-{valor}")
    public ResponseEntity<?> realizarSaqueParaUmCliente(@PathVariable int numeroDaConta, @PathVariable int valor){
        Cliente cliente = clienteServico.encontrarClientePelaConta(numeroDaConta);
        clienteServico.realizaSaque(numeroDaConta, valor);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.ACCEPTED);
    }

    @PostMapping("/{numeroDaContaATransferir}/{numeroDaContaAReceber}/{valor}")
    public ResponseEntity<?> realizarUmaTransferenciaDeUmClienteParaOutro(@PathVariable int numeroDaContaATransferir,
                                                                          @PathVariable int numeroDaContaAReceber,
                                                                          @PathVariable int valor){
        clienteServico.realizaTransferencia(numeroDaContaATransferir, numeroDaContaAReceber, valor);
        return new ResponseEntity<String>("Valor transferido", HttpStatus.ACCEPTED);
    }

    @GetMapping("/todosClientes")
    public Iterable<Cliente> receberTodosClientes() {
        return clienteServico.findAll();
    }

    @GetMapping("/{numeroConta}")
    public ResponseEntity<?> encontrarClientePorNumeroDaConta(@PathVariable int numeroDaConta) {
        Cliente cliente = clienteServico.encontrarClientePelaConta(numeroDaConta);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @DeleteMapping("/{numeroConta}")
    public ResponseEntity<?> deletarClientePorNumeroDaConta(@PathVariable int numeroDaConta) {
        clienteServico.delete(numeroDaConta);
        return new ResponseEntity<String>("Conta Deletada", HttpStatus.OK);
    }
}
