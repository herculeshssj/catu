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