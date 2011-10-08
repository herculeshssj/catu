package br.com.hslife.catu.dao;

import java.util.List;

import br.com.hslife.catu.model.Maquina;

public class MaquinaDao extends GenericDao implements InterfaceDao<Maquina> {

	@Override
    public void salvar(Maquina obj) {
        save(obj);
    }

    @Override
    public void alterar(Maquina obj) {
        update(obj);
    }

    @Override
    public void excluir(Maquina obj) {
        errorMessage = null;
        String sqlQuery = "select s.* from Maquina s, Cliente c where c.idMaquina = " + obj.getId();
        List lista = query(Maquina.class, sqlQuery);
        if (lista.isEmpty()) {
            delete(obj);
        } else {
            errorMessage = "Existem clientes associados a este maquina!";
        }
    }

    @Override
    public Maquina buscar(long id) {
        return (Maquina) find(Maquina.class, id);
    }

    @Override
    public Maquina buscar(String campo, Object valor) {
        return (Maquina) find(Maquina.class, campo, valor);
    }

    @Override
    public List<Maquina> listar(String campo, Object valor) {
        return (List) findAll(Maquina.class, campo, valor);
    }

    @Override
    public List<Maquina> listarTodos() {
        return (List) findAll(Maquina.class);
    }

    @Override
    public List<Maquina> procurar(String campo, Object valor) {
        return (List) search("Maquina", campo, (String)valor);
    }
}