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


import br.com.hslife.catu.model.Cliente;
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

    public List<Cliente> procurar(String campo, Object valor) {
        return (List) search("Cliente", campo, (String)valor);
    }

}