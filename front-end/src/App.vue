 
<template>
  <div id="app">

    <nav>
      <div class="nav-wrapper blue darken-1">
        <a href="#" class="brand-logo center">Contas Bancárias</a>
      </div>
    </nav>

    <div class="container">

      <form @submit.prevent="adicionarCliente(clienteASerCadastrado)">

          <label>Nome do responsavel</label>
          <input type="text" placeholder="Nome do responsavel" v-model="clienteASerCadastrado.nomeDoResponsavel" >
          <label>Saldo da conta</label>
          <input type="number" placeholder="Saldo da conta" v-model="clienteASerCadastrado.saldo" >
          <label>Número da conta</label>
          <input type="text" placeholder="Número da conta" v-model="clienteASerCadastrado.numeroDaConta" >

          <button class="waves-effect waves-light btn-small">Salvar<i class="material-icons left">save</i></button>

      </form>

      <table>
        <thead>

          <tr>
            <th>NOME DO CLIENTE</th>
            <th>NÚMERO DA CONTA</th>
            <th>SALDO</th>
            <th>OPERAÇÕES</th>
          </tr>

        </thead>

        <tbody>

          <tr v-for="cliente of clientes" :key="cliente.id">

            <td>{{ cliente.nomeDoResponsavel }}</td>
            <td>{{ cliente.numeroDaConta }}</td>
            <td>R$ {{ cliente.saldo }}</td>
            <td>
              <button @click="realizarDeposito(cliente.numeroDaConta, valorASerMovimentado)" class="waves-effect btn-small green darken-1">Depositar</button>
              <button @click="realizarSaque(cliente.numeroDaConta, valorASerMovimentado)" class="waves-effect btn-small blue darken-1">Sacar</button>
              <button @click="realizarTransferencia(cliente.numeroDaConta, numeroDaContaBeneficiada, valorASerMovimentado)" class="waves-effect btn-small coral darken-1">Transferir</button>
              <button @click="mostrarExtrato(cliente.numeroDaConta)" class="waves-effect btn-small brown darken-1">Extrato</button>
              <button @click="deletarConta(cliente.numeroDaConta)" class="waves-effect btn-small red darken-1">Deletar</button>
              <input type="text" v-if="mostraInputDeMovimentacao" v-model="valorASerMovimentado" placeholder="Valor a ser Processado">
              <input type="text" v-if="mostrarInputDeTransferencia" v-model="numeroDaContaBeneficiada" placeholder="Número da conta beneficiada">
              <ul v-if="clienteQuerVerExtrato">
                <li v-for="operacao in cliente.extrato" :key="operacao">{{operacao}}</li>
              </ul>
            </td>
          </tr>

        </tbody>
      
      </table>

    </div>

  </div>
</template>

<script>
import Cliente from './services/clientes'

export default {
  name: 'App',
  data () {
    return {
      clienteASerCadastrado:{
        nomeDoResponsavel: '',
        saldo: '',
        numeroDaConta: ''
      },
      clientes: [],
      valorASerMovimentado: null,
      numeroDaContaBeneficiada: null,
      mostraInputDeMovimentacao: false,
      mostrarInputDeTransferencia: false,
      clienteQuerVerExtrato: false
    }
  },

  mounted(){
    this.listarContas()
  },

  methods:{

    listarContas(){
      Cliente.listarContas().then(resposta => {this.clientes = resposta.data})
      .catch(e => console.log(e))
    }, 

    deletarConta(numeroDaConta){
      if(confirm('Deseja excluir a conta ?')){
        Cliente.deletarConta(numeroDaConta).then(() => {this.listarContas()}).catch( e => console.log(e))
      }
    },

    realizarDeposito(numeroDaConta, valorASerDepositado){
      this.mostraInputDeMovimentacao = !this.mostraInputDeMovimentacao
      this.mostrarSomenteUmCliente(numeroDaConta)
      Cliente.realizarDeposito(numeroDaConta, valorASerDepositado).then(() => {this.listarContas()}).catch(e => console.log(e))
      this.valorASerMovimentado = null
    },

    realizarSaque(numeroDaConta, valorASerSacado){
      this.mostraInputDeMovimentacao = !this.mostraInputDeMovimentacao
      this.mostrarSomenteUmCliente(numeroDaConta)
      Cliente.realizarSaque(numeroDaConta, valorASerSacado).then(() => {this.listarContas()}).catch(e => console.log(e))
      this.valorASerMovimentado = null
    },

    realizarTransferencia(numeroDaContaATransferir, numeroDaContaAReceber, valorASerMandado){
      this.mostraInputDeMovimentacao = !this.mostraInputDeMovimentacao
      this.mostrarInputDeTransferencia = !this.mostrarInputDeTransferencia
      this.mostrarSomenteUmCliente(numeroDaContaATransferir)
      Cliente.realizarTransferencia(numeroDaContaATransferir, numeroDaContaAReceber, valorASerMandado).then(() => {this.listarContas()}).catch(e=>console.log(e))
      this.numeroDaContaBeneficiada = null
      this.valorASerMovimentado = null
    },

    adicionarCliente(cliente){
          Cliente.adicionarCliente(cliente).then(() => {
            this.clienteASerCadastrado = {}
            alert('Cadastrado com sucesso!')
            this.listarContas()
            }).catch( e => console.log(e))
    },

    mostrarExtrato(numeroDaConta){
      this.botaoDeOperacaoPressionado = true
      this.clienteQuerVerExtrato = !this.clienteQuerVerExtrato
      if(this.clienteQuerVerExtrato === false){
        this.listarContas()
      }
      Cliente.mostrarExtrato(numeroDaConta).then(this.mostrarSomenteUmCliente(numeroDaConta)).catch(e => console.log(e))
    },

    mostrarSomenteUmCliente(numeroDaConta){
      this.clientes = [this.clientes.find((cliente) => cliente.numeroDaConta === numeroDaConta)]
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
