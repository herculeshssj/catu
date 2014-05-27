/***

    Copyright (c) 2010, 2011 Hércules S. S. José



    Este arquivo é parte do programa CATU.

    CATU é um software livre; você pode redistribui-lo e/ou 

    modificá-lo dentro dos termos da Licença Pública Geral Menor GNU como 

    publicada pela Fundação do Software Livre (FSF); na versão 2.1 da 

    Licença.



    Este programa é distribuído na esperança que possa ser útil, 

    mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUAÇÂO a qualquer

    MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral Menor GNU 
    
    em português para maiores detalhes.



    Você deve ter recebido uma cópia da Licença Pública Geral Menor GNU sob o 
    
    nome de "LICENSE.TXT" junto com este programa, se não, acesse o site HSlife no 

    endereco www.hslife.com.br ou escreva para a Fundação do Software Livre(FSF) Inc., 

    51 Franklin St, Fifth Floor, Boston, MA  02110-1301, USA.



    Para mais informações sobre o programa CATU e seus autores acesse o 

    endereço www.hslife.com.br, pelo e-mail contato@hslife.com.br ou escreva para 

    Hércules S. S. José, Av. Ministro Lafaeyte de Andrade, 1683 - Bl. 3 Apt 404, 

    Marco II - Nova Iguaçu, RJ, Brasil.

 ***/

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
    
    @SuppressWarnings("rawtypes")
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