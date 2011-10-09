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


package br.com.hslife.catu.control;

import br.com.hslife.catu.dao.TipoDao;
import br.com.hslife.catu.model.Login;
import br.com.hslife.catu.model.Tipo;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class TipoMB {

    private Tipo tipo;
    private TipoDao dao;
    private Integer idTipo;
    private String busca;
    private String msg;
    private List<Tipo> listaTipos;

    public TipoMB() {
        tipo = new Tipo();
        dao = new TipoDao();
        listaTipos = new ArrayList<Tipo>();
        listaTipos = dao.listarTodos();
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
            contexto.addMessage("frmStatus", mensagem);
        } else {
            resultado = "addTipo";
            tipo = new Tipo();
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
            if (tipo.getId() == null || tipo.getId() == 0) {
                dao.salvar(tipo);
                if (dao.getErrorMessage() == null) {
                    msg = "Cadastro realizado com sucesso!";
                } else {
                    msg = "Erro ao cadastrar: " + dao.getErrorMessage();
                }
                tipo = new Tipo();
            } else {
                dao.alterar(tipo);
                if (dao.getErrorMessage() == null) {
                    msg = "Alteração realizada com sucesso!";
                } else {
                    msg = "Erro ao alterar: " + dao.getErrorMessage();
                }
            }
        }
        listaTipos = dao.listarTodos();
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("frmTipo", mensagem);
    }

    public String editar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        msg = "";
        String resultado = "";
        tipo = dao.buscar(idTipo);
        if (dao.getErrorMessage() == null) {
            resultado = "editTipo";
        } else {
            msg = "Erro ao carregar: " + dao.getErrorMessage();
            FacesMessage mensagem = new FacesMessage(msg);
            contexto.addMessage("lstTipo", mensagem);
        }
        return resultado;
    }

    public void excluir() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
        Login usuario = (Login) sessao.getAttribute("usuarioLogado");
        msg = "";
        if (usuario.getPerfil().equals("USER")) {
            msg = "Você não tem permissão para excluir!";
        } else {
        tipo = dao.buscar(idTipo);
        dao.excluir(tipo);
        if (dao.getErrorMessage() == null) {
            msg = "Registro excluído com sucesso!";
            listaTipos = dao.listarTodos();
        } else {
            msg = "Erro ao excluir: " + dao.getErrorMessage();
        }}
        tipo = new Tipo();
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("lstTipo", mensagem);
    }

    public void pesquisar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        msg = "Registros carregados com sucesso!";
        setListaTipos(dao.procurar("descricao", busca));
        if (dao.getErrorMessage() != null) {
            msg = "Erro ao procurar: " + dao.getErrorMessage();
        }
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("lstTipo", mensagem);
    }

    /**
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the idTipo
     */
    public Integer getIdTipo() {
        return idTipo;
    }

    /**
     * @param idTipo the idTipo to set
     */
    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    /**
     * @return the busca
     */
    public String getBusca() {
        return busca;
    }

    /**
     * @param busca the busca to set
     */
    public void setBusca(String busca) {
        this.busca = busca;
    }

    /**
     * @return the listaTipos
     */
    public List<Tipo> getListaTipos() {
        return listaTipos;
    }

    /**
     * @param aListaTipos the listaTipos to set
     */
    public void setListaTipos(List<Tipo> aListaTipos) {
        listaTipos = aListaTipos;
    }
}
