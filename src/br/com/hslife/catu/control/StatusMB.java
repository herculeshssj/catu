package br.com.hslife.catu.control;

import br.com.hslife.catu.dao.StatusDao;
import br.com.hslife.catu.model.Login;
import br.com.hslife.catu.model.Status;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class StatusMB {

    private Status status;
    private StatusDao dao;
    private Integer idStatus;
    private String busca;
    private String msg;
    private List<Status> listaStatus;

    public StatusMB() {
        status = new Status();
        dao = new StatusDao();
        listaStatus = new ArrayList<Status>();
        listaStatus = dao.listarTodos();
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
            resultado = "addStatus";
            setStatus(new Status());
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
            if (getStatus().getId() == null || getStatus().getId() == 0) {
                getDao().salvar(getStatus());
                if (getDao().getErrorMessage() == null) {
                    msg = "Cadastro realizado com sucesso!";
                } else {
                    msg = "Erro ao cadastrar: " + getDao().getErrorMessage();
                }
                setStatus(new Status());
            } else {
                getDao().alterar(getStatus());
                if (getDao().getErrorMessage() == null) {
                    msg = "Alteração realizada com sucesso!";
                } else {
                    msg = "Erro ao alterar: " + getDao().getErrorMessage();
                }
            }
        }
        setStatus(new Status());
        setListaStatus(getDao().listarTodos());
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("frmStatus", mensagem);
    }

    public String editar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        msg = "";
        String resultado = "";
        status = dao.buscar(idStatus);
        setStatus(getDao().buscar(getIdStatus()));
        if (getDao().getErrorMessage() == null) {
            resultado = "editStatus";
        } else {
            msg = "Erro ao carregar: " + getDao().getErrorMessage();
            FacesMessage mensagem = new FacesMessage(msg);
            contexto.addMessage("lstStatus", mensagem);
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
            setStatus(getDao().buscar(getIdStatus()));
            getDao().excluir(getStatus());
            if (getDao().getErrorMessage() == null) {
                msg = "Registro excluído com sucesso!";
                setListaStatus(getDao().listarTodos());
            } else {
                msg = "Erro ao excluir: " + getDao().getErrorMessage();
            }
        }
        setListaStatus(getDao().listarTodos());
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("lstStatus", mensagem);
    }

    public void pesquisar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        msg = "Registros carregados com sucesso!";
        listaStatus = getDao().procurar("descricao", getBusca());
        if (getDao().getErrorMessage() != null) {
            msg = "Erro ao procurar: " + getDao().getErrorMessage();
        }
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("lstStatus", mensagem);
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the dao
     */
    public StatusDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(StatusDao dao) {
        this.dao = dao;
    }

    /**
     * @return the idStatus
     */
    public Integer getIdStatus() {
        return idStatus;
    }

    /**
     * @param idStatus the idStatus to set
     */
    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
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
     * @return the listaStatus
     */
    public List<Status> getListaStatus() {
        return listaStatus;
    }

    /**
     * @param listaStatus the listaStatus to set
     */
    public void setListaStatus(List<Status> listaStatus) {
        this.listaStatus = listaStatus;
    }
}
