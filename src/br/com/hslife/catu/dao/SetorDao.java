package br.com.hslife.catu.dao;


import br.com.hslife.catu.model.Setor;
import java.util.List;

public class SetorDao extends GenericDao implements InterfaceDao<Setor> {

    @Override
    public void salvar(Setor obj) {
        save(obj);
    }

    @Override
    public void alterar(Setor obj) {
        update(obj);
    }

    @Override
    public void excluir(Setor obj) {
        errorMessage = null;
        String sqlQuery = "select s.* from Setor s, Atendimento a where a.idSetor = " + obj.getId();
        List lista = query(Setor.class, sqlQuery);
        if (lista.isEmpty()) {
            delete(obj);
        } else {
            errorMessage = "Existem atendimentos associados a este setor!";
        }
    }

    @Override
    public Setor buscar(long id) {
        return (Setor) find(Setor.class, id);
    }

    @Override
    public Setor buscar(String campo, Object valor) {
        return (Setor) find(Setor.class, campo, valor);
    }

    @Override
    public List<Setor> listar(String campo, Object valor) {
        return (List) findAll(Setor.class, campo, valor);
    }

    @Override
    public List<Setor> listarTodos() {
        return (List) findAll(Setor.class);
    }

    @Override
    public List<Setor> procurar(String campo, Object valor) {
        return (List) search("Setor", campo, (String)valor);
    }

}