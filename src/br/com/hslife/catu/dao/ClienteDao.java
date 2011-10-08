package br.com.hslife.catu.dao;


import br.com.hslife.catu.model.Cliente;
import br.com.hslife.catu.model.Setor;

import java.util.List;

public class ClienteDao extends GenericDao implements InterfaceDao<Cliente> {

    public void salvar(Cliente obj) {
        save(obj);
    }

    public void alterar(Cliente obj) {
        update(obj);
    }

    public void excluir(Cliente obj) {
        errorMessage = null;
        String sqlQuery = "select c.* from Cliente c, Atendimento a where a.idCliente = " + obj.getId();
        List lista = query(Cliente.class, sqlQuery);
        if (lista.isEmpty()) {
            delete(obj);
        } else {
            errorMessage = "Existem atendimentos para este cliente!";
        }
    }

    public Cliente buscar(long id) {
        return (Cliente) find(Cliente.class, id);
    }

    public Cliente buscar(String campo, Object valor) {
        return (Cliente) find(Cliente.class, campo, valor);
    }

    public List<Cliente> listar(String campo, Object valor) {
        return (List) findAll(Cliente.class, campo, valor);
    }

    public List<Cliente> listarTodos() {
        return (List) findAll(Cliente.class);
    }
    
    public List<Setor> listaSetores() {
    	return (List)findAll(Setor.class);
    }

    public List<Cliente> procurar(String campo, Object valor) {
        return (List) search("Cliente", campo, (String)valor);
    }

}