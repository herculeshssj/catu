package br.com.hslife.catu.dao;


import br.com.hslife.catu.model.Status;
import java.util.List;

public class StatusDao extends GenericDao implements InterfaceDao<Status> {

    @Override
    public void salvar(Status obj) {
        save(obj);
    }

    @Override
    public void alterar(Status obj) {
        update(obj);
    }

    @Override
    public void excluir(Status obj) {
        errorMessage = null;
        String sqlQuery = "select s.* from Status s, Atendimento a where a.idStatus = " + obj.getId();
        List lista = query(Status.class, sqlQuery);
        if (lista.isEmpty()) {
            delete(obj);
        } else {
            errorMessage = "Existem atendimentos cadastrados com este status!";
        }
    }

    @Override
    public Status buscar(long id) {
        return (Status) find(Status.class, id);
    }

    @Override
    public Status buscar(String campo, Object valor) {
        return (Status) find(Status.class, campo, valor);
    }

    @Override
    public List<Status> listar(String campo, Object valor) {
        return (List) findAll(Status.class, campo, valor);
    }

    @Override
    public List<Status> listarTodos() {
        return (List) findAll(Status.class);
    }

    @Override
    public List<Status> procurar(String campo, Object valor) {
        return (List) search("Status", campo, (String)valor);
    }

}
