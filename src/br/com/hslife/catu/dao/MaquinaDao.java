/***

    Copyright (c) 2010-2014 Hércules S. S. José



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