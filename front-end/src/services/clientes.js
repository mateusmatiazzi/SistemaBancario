import { http } from './config'

export default	{
    listarContas:()=>{
		return http.get('todosClientes')
    },

    deletarConta:(numeroDaConta)=>{
        return http.delete('/' + numeroDaConta)
    },

    realizarDeposito:(numeroDaConta, valorASerDepositado)=>{
        return http.post('/' + numeroDaConta + '+' + valorASerDepositado)
    },

    realizarSaque:(numeroDaConta, valorASerSacado) => {
        return http.post('/' + numeroDaConta + '-' + valorASerSacado)
    },

    realizarTransferencia:(numeroDaContaATransferir, numeroDaContaAReceber, valorASerMandado)=>{
        return http.post('/' + numeroDaContaATransferir + '/' + numeroDaContaAReceber + '/' + valorASerMandado)
    },

    adicionarCliente:(cliente)=>{
        return http.post('/', cliente)
    },

    mostrarExtrato:(numeroDaConta)=>{
        return http.get('/extrato/'+numeroDaConta);
    }
}