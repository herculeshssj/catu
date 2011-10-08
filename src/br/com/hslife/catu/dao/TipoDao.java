package br.com.hslife.catu.dao;


import br.com.hslife.catu.model.Tipo;
import java.util.List;

public class TipoDao extends GenericDao implements InterfaceDao<Tipo> {

    @Override
    public void salvar(Tipo obj) {
        save(obj);
    }

    @Override
    public void alterar(Tipo obj) {
        update(obj);
    }

    @Override
    public void excluir(Tipo obj) {
        errorMessage = null;
        String sqlQuery = "select t.* from Tipo t, Atendimento a where a.idTipo = " + obj.getId();
        List lista = query(Tipo.class, sqlQuery);
        if (lista.isEmpty()) {
            delete(obj);
        } else {
            errorMessage = "Existem atendimentos cadastrados para este tipo de atendimento!";
        }
    }

    @Override
    public Tipo buscar(String campo, Object valor) {
        return (Tipo) find(Tipo.class, campo, valor);
    }

    @Override
    public List<Tipo> listar(String campo, Object valor) {
        return (List) findAll(Tipo.class, campo, valor);
    }

    @Override
    public List<Tipo> listarTodos() {
        return (List) findAll(Tipo.class);
    }

    @Override
    public List<Tipo> procurar(String campo, Object valor) {
        return (List) search("Tipo", campo, (String)valor);
    }

    @Override
    public Tipo buscar(long id) {
        return (Tipo)find(Tipo.class, id);
    }
}
