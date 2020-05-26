 
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
            <td>{{ cliente.saldo }}</td>
            <td>
              <button @click="depositar(cliente.numeroDaConta, valorASerMovimentado)" class="waves-effect btn-small green darken-1">Depositar</button>
              <button @click="sacar(cliente.numeroDaConta, valorASerMovimentado)" class="waves-effect btn-small blue darken-1">Sacar</button>
              <button @click="transferir(cliente.numeroDaConta, numeroDaContaBeneficiada, valorASerMovimentado)" class="waves-effect btn-small coral darken-1">Transferir</button>
              <button @click="deletar(cliente.numeroDaConta)" class="waves-effect btn-small red darken-1">Deletar</button>
              <input type="text" v-if="mostraInputDeMovimentacao" v-model="valorASerMovimentado" placeholder="Valor a ser Depositado">
              <input type="text" v-if="mostrarInputDeTransferencia" v-model="numeroDaContaBeneficiada" placeholder="Número da conta beneficiada">
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
      mostrarInputDeTransferencia: false
    }
  },

  mounted(){
    this.listar()
  },

  methods:{

    listar(){
      Cliente.listar().then(resposta => {this.clientes = resposta.data})
      .catch(e => console.log(e))
    }, 

    deletar(numeroDaConta){
      if(confirm('Deseja excluir a conta ?')){
        Cliente.deletar(numeroDaConta).then(() => {this.listar()}).catch( e => console.log(e))
      }
    },

    depositar(numeroDaConta, valorASerDepositado){
      this.mostraInputDeMovimentacao = !this.mostraInputDeMovimentacao
      Cliente.depositar(numeroDaConta, valorASerDepositado).then(() => {this.listar()}).catch(e => console.log(e))
      this.valorASerMovimentado = null
    },

    sacar(numeroDaConta, valorASerSacado){
      this.mostraInputDeMovimentacao = !this.mostraInputDeMovimentacao
      Cliente.sacar(numeroDaConta, valorASerSacado).then(() => {this.listar()}).catch(e => console.log(e))
      this.valorASerMovimentado = null
    },

    transferir(numeroDaContaATransferir, numeroDaContaAReceber, valorASerMandado){
      this.mostraInputDeMovimentacao = !this.mostraInputDeMovimentacao
      this.mostrarInputDeTransferencia = !this.mostrarInputDeTransferencia
      Cliente.transferir(numeroDaContaATransferir, numeroDaContaAReceber, valorASerMandado).then(() => {this.listar()}).catch(e=>console.log(e))
      this.valorASerMovimentado = null
      this.numeroDaContaBeneficiada = null
    },

    adicionarCliente(cliente){
          Cliente.adicionarCliente(cliente).then(() => {
            this.cliente = {}
            alert('Cadastrado com sucesso!')
            this.listar()
            }).catch( e => console.log(e))
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
