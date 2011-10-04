
package br.com.hslife.catu.dao;

import java.util.List;


public interface InterfaceDao<T> {

    public void salvar(T obj);

    public void alterar(T obj);

    public void excluir(T obj);

    public T buscar(long id);

    public T buscar(String campo, Object valor);

    public List<T> listar(String campo, Object valor);

    public List<T> listarTodos();

    public List<T> procurar(String campo, Object valor);

}
