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

package br.com.hslife.catu.control;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.hslife.catu.dao.SoftwareDao;
import br.com.hslife.catu.model.Login;
import br.com.hslife.catu.model.Software;

public class SoftwareMB {

    private Software software;
    private SoftwareDao dao;
    private Long idSoftware;
    private String busca;
    private String msg;
    private List<Software> listaSoftwares;

    public SoftwareMB() {
        software = new Software();
        dao = new SoftwareDao();
        listaSoftwares = new ArrayList<Software>();       
    }
    
    public String cadastrar() {
    	FacesContext contexto = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
        Login usuario = (Login) sessao.getAttribute("usuarioLogado");
        msg = "";
        String resultado = "";
        if (usuario.getPerfil().equals("USER")) {
            msg = "Você não tem permissão para cadastrar!";
            FacesMessage mensagem = new FacesMessage(msg);
            contexto.addMessage("frmSoftware", mensagem);
        } else {
            resultado = "addSoftware";
            software = new Software();
        }
        return resultado;
    }
    
    public void salvar() {
    	FacesContext contexto = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
        Login usuario = (Login) sessao.getAttribute("usuarioLogado");
        msg = "";
        if (usuario.getPerfil().equals("USER")) {
            msg = "Você não tem permissão para alterar!";
        } else {
            if (software.getId() == null || software.getId() == 0) {
            	try {
            		dao.salvar(software);
            		msg = "Cadastro realizado com sucesso!";
            		software = new Software();
            	} catch (Exception e) {
            		msg = "Erro ao cadastrar: " + e.getMessage();
            	}                
            } else {
            	try {
            		dao.alterar(software);
            		msg = "Alteração realizada com sucesso!";
            	} catch (Exception e) {
            		msg = "Erro ao alterar: " + e.getMessage();
            	}                
            }
        }     
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("frmSoftware", mensagem);
    }
    
    public String editar() {
    	FacesContext contexto = FacesContext.getCurrentInstance();
    	String resultado = "";
    	try {
    		software = dao.buscar(idSoftware);
    		resultado = "editSoftware";
    	} catch (Exception e) {
    		msg = "Erro ao carregar: " + e.getMessage();
            FacesMessage mensagem = new FacesMessage(msg);
            contexto.addMessage("lstSoftware", mensagem);
    	}        
        return resultado;
    }
    
    public void excluir() {
    	FacesContext contexto = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
        Login usuario = (Login) sessao.getAttribute("usuarioLogado");        
        if (usuario.getPerfil().equals("USER")) {
            msg = "Você não tem permissão para excluir!";
        } else {
        	try {
        		dao.excluir(dao.buscar(idSoftware));
        		msg = "Exclusão realizada com sucesso!";
        	} catch (Exception e) {
        		msg = "Erro ao excluir: " + e.getMessage();
        	}
        }
        listaSoftwares = new ArrayList<Software>();
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("lstSoftware", mensagem);
    }
    
    public void pesquisar() {
    	FacesContext contexto = FacesContext.getCurrentInstance();
    	try {
    		listaSoftwares = dao.procurar("nome", busca);
    		msg = "Registros carregados com sucesso!";
    	} catch (Exception e) {
    		msg = "Erro ao procurar: " + e.getMessage();
    	}        
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("lstSoftware", mensagem);
    }

	public Software getSoftware() {
		return software;
	}

	public void setSoftware(Software software) {
		this.software = software;
	}

	public SoftwareDao getDao() {
		return dao;
	}

	public void setDao(SoftwareDao dao) {
		this.dao = dao;
	}

	public Long getIdSoftware() {
		return idSoftware;
	}

	public void setIdSoftware(Long idSoftware) {
		this.idSoftware = idSoftware;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Software> getListaSoftwares() {
		return listaSoftwares;
	}

	public void setListaSoftwares(List<Software> listaSoftwares) {
		this.listaSoftwares = listaSoftwares;
	}
    
    

    
}