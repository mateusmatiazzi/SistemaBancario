 
<template>
  <div id="app">

    <nav>
      <div class="nav-wrapper blue darken-1">
        <a href="#" class="brand-logo center">Lista de Contas</a>
      </div>
    </nav>

    <div class="container">
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
              <button class="waves-effect btn-small coral darken-1">Transferir</button>
              <button @click="deletar(cliente.numeroDaConta)" class="waves-effect btn-small red darken-1">Deletar</button>
              <input v-if="mostraInputDeMovimentacao" v-model="valorASerMovimentado" placeholder="Valor a ser Depositado">
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
      cliente:{
        id: '',
        nomeDoResponsavel: '',
        saldo: '',
        numeroDaConta: ''
      },
      clientes: [],
      valorASerMovimentado: 0,
      mostraInputDeMovimentacao: false
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
      this.valorASerMovimentado = 0
    },

    sacar(numeroDaConta, valorASerSacado){
      this.mostraInputDeMovimentacao = !this.mostraInputDeMovimentacao
      Cliente.sacar(numeroDaConta, valorASerSacado).then(() => {this.listar()}).catch(e => console.log(e))
      this.valorASerMovimentado = 0
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
