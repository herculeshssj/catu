package br.com.hslife.catu.dao;

import java.util.Date;
import java.util.List;

import br.com.hslife.catu.model.Atendimento;
import br.com.hslife.catu.model.Cliente;
import br.com.hslife.catu.model.Status;
import br.com.hslife.catu.model.Tipo;

public class AtendimentoDao extends GenericDao implements InterfaceDao<Atendimento> {

    public static final int STATUS = 1;
    public static final int TIPO = 2; 
    public static final int CLIENTE = 3;

    @Override
    public void salvar(Atendimento obj) {
        save(obj);
    }

    @Override
    public void alterar(Atendimento obj) {
        update(obj);
    }

    @Override
    public void excluir(Atendimento obj) {
        delete(obj);
    }

    @Override
    public Atendimento buscar(long id) {
        return (Atendimento) find(Atendimento.class, id);
    }

    @Override
    public Atendimento buscar(String campo, Object valor) {
        return (Atendimento) find(Atendimento.class, campo, valor);
    }

    @Override
    public List<Atendimento> listar(String campo, Object valor) {
        return (List) findAll(Atendimento.class, campo, valor);
    }

    @Override
    public List<Atendimento> listarTodos() {
        return (List) findAll(Atendimento.class);
    }

    @Override
    public List<Atendimento> procurar(String campo, Object valor) {
        return (List) search("Atendimento", campo, (String)valor);
    }
    
    public List listarTodos(int item) {
        List lista = null;
        switch (item) {            
            case STATUS: lista =  findAll(Status.class);break;
            case TIPO: lista =  findAll(Tipo.class);break;            
            case CLIENTE: lista =  findAll(Cliente.class);break;
        }
        return lista;
    }

    public List listarTodos(String valor) {
        List lista = null;
        String sqlQuery = "select * from Atendimento where id = " + valor;
        lista = query(Atendimento.class, sqlQuery);
        return lista;
    }

    public List listarTodos(Date valor) {
        List lista = null;
        String sqlQuery = "select * from Atendimento where dataAbertura >= '"+(valor.getYear()+1900)+"-"+(valor.getMonth()+1)+"-"+valor.getDate()+"'";
        lista = query(Atendimento.class, sqlQuery);
        return lista;
    }

    public List listarTodos(Date valor, long status) {
        List lista = null;
        String sqlQuery = "select * from Atendimento where dataAbertura >= '"+(valor.getYear()+1900)+"-"+(valor.getMonth()+1)+"-"+valor.getDate()+"' and idStatus = " + status;
        lista = query(Atendimento.class, sqlQuery);
        return lista;
    }

    public List listarTodos(long valor) {
        List lista = null;
        String sqlQuery = "select * from Atendimento where idStatus = " + valor;
        lista = query(Atendimento.class, sqlQuery);
        return lista;
    }

}