

package br.com.hslife.catu.dao;


import br.com.hslife.catu.model.Login;
import java.util.List;


public class LoginDao extends GenericDao implements InterfaceDao<Login>{

    @Override
    public void salvar(Login obj) {
        save(obj);
    }

    @Override
    public void alterar(Login obj) {
        update(obj);
    }

    @Override
    public void excluir(Login obj) {
        delete(obj);
    }

    @Override
    public Login buscar(long id) {
        return (Login)find(Login.class, id);
    }

    @Override
    public Login buscar(String campo, Object valor) {
        return (Login)find(Login.class, campo, valor);
    }

    @Override
    public List<Login> listar(String campo, Object valor) {
       return (List)findAll(Login.class, campo, valor);
    }

    @Override
    public List<Login> listarTodos() {
        return (List)findAll(Login.class);
    }

    @Override
    public List<Login> procurar(String campo, Object valor) {
        return (List) search("Usuario", campo, (String)valor);
    }

}
