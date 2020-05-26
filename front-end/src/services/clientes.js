import { http } from './config'

export default	{
    listar:()=>{
		return http.get('todosClientes')
    },

    deletar:(numeroDaConta)=>{
        return http.delete('/' + numeroDaConta)
    },

    depositar:(numeroDaConta, valorASerDepositado)=>{
        return http.post('/' + numeroDaConta + '+' + valorASerDepositado)
    },

    sacar:(numeroDaConta, valorASerSacado) => {
        return http.post('/' + numeroDaConta + '-' + valorASerSacado)
    },

    transferir:(numeroDaContaATransferir, numeroDaContaAReceber, valorASerMandado)=>{
        return http.post('/' + numeroDaContaATransferir + '/' + numeroDaContaAReceber + '/' + valorASerMandado)
    },

    adicionarCliente:(cliente)=>{
        return http.post('/', cliente)
    }
}