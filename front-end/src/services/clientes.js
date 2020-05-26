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
    }
}