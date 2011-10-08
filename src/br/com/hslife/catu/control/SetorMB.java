package br.com.hslife.catu.control;

import br.com.hslife.catu.dao.SetorDao;
import br.com.hslife.catu.model.Login;
import br.com.hslife.catu.model.Setor;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SetorMB {

    private Setor setor;
    private SetorDao dao;
    private Integer idSetor;
    private String busca;
    private String msg;
    private List<Setor> listaSetores;

    public SetorMB() {
        setor = new Setor();
        dao = new SetorDao();
        listaSetores = new ArrayList<Setor>();
        listaSetores = dao.listarTodos();
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
            contexto.addMessage("frmSetor", mensagem);
        } else {
            resultado = "addSetor";
            setor = new Setor();
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
            if (setor.getId() == null || setor.getId() == 0) {
                dao.salvar(setor);
                if (dao.getErrorMessage() == null) {
                    msg = "Cadastro realizado com sucesso!";
                } else {
                    msg = "Erro ao cadastrar: " + dao.getErrorMessage();
                }
                setor = new Setor();
            } else {
                dao.alterar(setor);
                if (dao.getErrorMessage() == null) {
                    msg = "Alteração realizada com sucesso!";
                } else {
                    msg = "Erro ao alterar: " + dao.getErrorMessage();
                }
            }
        }
        listaSetores = dao.listarTodos();
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("frmSetor", mensagem);
    }

    public String editar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        msg = "";
        String resultado = "";
        setor = dao.buscar(idSetor);
        if (dao.getErrorMessage() == null) {
            resultado = "editSetor";
        } else {
            msg = "Erro ao carregar: " + dao.getErrorMessage();
            FacesMessage mensagem = new FacesMessage(msg);
            contexto.addMessage("lstSetor", mensagem);
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
            setor = dao.buscar(idSetor);
            dao.excluir(setor);
            if (dao.getErrorMessage() == null) {
                msg = "Registro excluído com sucesso!";
                listaSetores = dao.listarTodos();
            } else {
                msg = "Erro ao excluir: " + dao.getErrorMessage();
            }
        }
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("lstSetor", mensagem);
    }

    public void pesquisar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        msg = "Registros carregados com sucesso!";
        setListaSetors(dao.procurar("descricao", busca));
        if (dao.getErrorMessage() != null) {
            msg = "Erro ao procurar: " + dao.getErrorMessage();
        }
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("lstSetor", mensagem);
    }

    /**
     * @return the setor
     */
    public Setor getSetor() {
        return setor;
    }

    /**
     * @param setor the setor to set
     */
    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    /**
     * @return the idSetor
     */
    public Integer getIdSetor() {
        return idSetor;
    }

    /**
     * @param idSetor the idSetor to set
     */
    public void setIdSetor(Integer idSetor) {
        this.idSetor = idSetor;
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
     * @return the listaSetores
     */
    public List<Setor> getListaSetors() {
        return listaSetores;
    }

    /**
     * @param aListaSetors the listaSetores to set
     */
    public void setListaSetors(List<Setor> aListaSetors) {
        listaSetores = aListaSetors;
    }
}