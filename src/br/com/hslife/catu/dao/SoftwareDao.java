package br.com.hslife.catu.dao;

import java.util.List;

import br.com.hslife.catu.model.Software;

public class SoftwareDao extends GenericDao implements InterfaceDao<Software> {

	@Override
    public void salvar(Software obj) {
        save(obj);
    }

    @Override
    public void alterar(Software obj) {
        update(obj);
    }

    @Override
    public void excluir(Software obj) {
        errorMessage = null;
        String sqlQuery = "select s.* from Software s, Atendimento a where a.idSoftware = " + obj.getId();
        List lista = query(Software.class, sqlQuery);
        if (lista.isEmpty()) {
            delete(obj);
        } else {
            errorMessage = "Existem atendimentos cadastrados com este software!";
        }
    }

    @Override
    public Software buscar(long id) {
        return (Software) find(Software.class, id);
    }

    @Override
    public Software buscar(String campo, Object valor) {
        return (Software) find(Software.class, campo, valor);
    }

    @Override
    public List<Software> listar(String campo, Object valor) {
        return (List) findAll(Software.class, campo, valor);
    }

    @Override
    public List<Software> listarTodos() {
        return (List) findAll(Software.class);
    }

    @Override
    public List<Software> procurar(String campo, Object valor) {
        return (List) search("Software", campo, (String)valor);
    }
    
    public List<Software> listarSoftwareDisponivel() throws Exception {
		String sqlQuery = "select s.* from Software s where emuso = true";
		return (List)query(Software.class, sqlQuery);
		
	}
}