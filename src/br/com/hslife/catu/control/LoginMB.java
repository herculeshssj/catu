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

import br.com.hslife.catu.dao.LoginDao;
import br.com.hslife.catu.model.Login;
import br.com.hslife.catu.util.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class LoginMB {

    private Login usuario;
    private Login usuarioLogado;
    private LoginDao dao;
    private String msg;
    private String senhaAtual;
    private String novaSenha;
    private String confirmaSenha;
    private List<Login> listaLogin;
    private Long idUsuario;
    private Boolean isAdminAlteraSenha;
    
    // Retorna a data e hora atual
    private Date horaAtual;

    /** Creates a new instance of LoginMB */
    public LoginMB() {
        usuario = new Login();
        usuarioLogado = new Login();
        dao = new LoginDao();
        listaLogin = new ArrayList<Login>();
        isAdminAlteraSenha = false;        
        
    }

    public String cadastrar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        msg = "";
        String resultado = "addLogin";
        usuario = new Login();
        return resultado;
    }

    public String efetuarLogin() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String resultado = null;
        usuarioLogado = dao.buscar("usuarioLogin", usuario.getUsuarioLogin());
        if (dao.getErrorMessage() == null) {
            if (usuarioLogado != null) {
                if (usuarioLogado.getUsuarioSenha().equals(Util.SHA1(usuario.getUsuarioSenha()))) {
                    if (usuarioLogado.isAtivo()) {
                        HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(true);
                        sessao.setAttribute("usuarioLogado", getUsuarioLogado());
                        setUsuario(new Login());
                        resultado = "sucesso";
                    } else {
                        msg = "Login desativado! Contate o administrador.";
                    }
                } else {
                    msg = "As senhas não conferem!";
                }
            } else {
                msg = "Usuário não encontrado!";
            }
            FacesMessage mensagem = new FacesMessage(getMsg());
            contexto.addMessage("frmLogin", mensagem);
        } else {
            msg = "Erro ao logar: " + dao.getErrorMessage();
            usuario = new Login();
            FacesMessage mensagem = new FacesMessage(getMsg());
            contexto.addMessage("frmLogin", mensagem);
        }
        return resultado;
    }

    public String efetuarLogoff() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
        sessao.invalidate();
        setUsuarioLogado(new Login());
        return "sair";
    }

    public void salvar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        setMsg("Cadastro realizado com sucesso!");

        // Verifica se as senhas coincidem
        if (getUsuario().getUsuarioSenha().equals(getConfirmaSenha())) {
            // Verifica se o login informado já existe
            if (getDao().buscar("usuarioLogin", getUsuario().getUsuarioLogin()) == null) {
                // Cadastra o novo login
                getUsuario().setAtivo(true);
                getUsuario().setDataCriacao(new Date());
                getUsuario().setUsuarioSenha(Util.SHA1(getUsuario().getUsuarioSenha()));                
                getDao().salvar(getUsuario());
                if (getDao().getErrorMessage() != null) {
                    setMsg("Erro ao cadastrar: " + getDao().getErrorMessage());
                }
                setUsuario(new Login());
            } else {
                setMsg("Login informado já existe. Informe outro login.");
            }
        } else {
            setMsg("As senhas não coincidem!");
        }

        // Retorna com as mensagens para o formulário
        FacesMessage mensagem = new FacesMessage(getMsg());
        contexto.addMessage("frmLogin", mensagem);
    }

    public String editar() {
        String resultado = "";
        setUsuario(getDao().buscar(getIdUsuario()));
        if (getDao().getErrorMessage() == null) {
            isAdminAlteraSenha = true;
            resultado = "editLogin";
        } else {
            msg = "Erro ao carregar: " + getDao().getErrorMessage();
            FacesMessage mensagem = new FacesMessage(msg);
            FacesContext.getCurrentInstance().addMessage("lstLogin", mensagem);
        }
        return resultado;
    }

    public String editarPerfil() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        msg = "";
        String resultado = "";
        usuario = dao.buscar(idUsuario);        
        if (dao.getErrorMessage() == null) {
            resultado = "editPerfil";
        } else {
            msg = "Erro ao carregar: " + dao.getErrorMessage();
            FacesMessage mensagem = new FacesMessage(msg);
            contexto.addMessage("lstLogin", mensagem);
        }
        return resultado;
    }

    public String editarSenha() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
        usuario = (Login) sessao.getAttribute("usuarioLogado");
        isAdminAlteraSenha = false;
        return "editSenha";
    }

    public void alterar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        setUsuario(getDao().buscar(getIdUsuario()));
        setMsg("Senha alterada com sucesso!");

        // Verifica se a senha atual informada é igual a senha cadastrada
        if (getNovaSenha().equals(getConfirmaSenha())) {
            // Altera a senha
            usuario.setUsuarioSenha(Util.SHA1(getNovaSenha()));
            getDao().alterar(usuario);
            if (getDao().getErrorMessage() != null) {
                setMsg("Erro ao alterar: " + getDao().getErrorMessage());
            }
        } else {
            setMsg("As senhas não coincidem!");
        }

        // Apaga as variáveis
        setSenhaAtual("");
        setNovaSenha("");
        setConfirmaSenha("");

        // Retorna com as mensagens para o formulário
        FacesMessage mensagem = new FacesMessage(getMsg());
        contexto.addMessage("frmSenha", mensagem);
    }

    public void alterarSenha() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
        Login u = (Login) sessao.getAttribute("usuarioLogado");
        setMsg("Senha alterada com sucesso!");

        // Verifica se a senha atual informada é igual a senha cadastrada
        if (u.getUsuarioSenha().equals(Util.SHA1(getSenhaAtual()))) {
            if (getNovaSenha().equals(getConfirmaSenha())) {
                if (!Util.SHA1(novaSenha).equals(u.getUsuarioSenha())) {
                    // Altera a senha
                    u.setUsuarioSenha(Util.SHA1(getNovaSenha()));
                    getDao().alterar(u);
                    if (getDao().getErrorMessage() != null) {
                        setMsg("Erro ao alterar: " + getDao().getErrorMessage());
                    }
                } else {
                    setMsg("Senha nova não pode ser igual a senha antiga!");
                }
            } else {
                setMsg("As senhas não coincidem!");
            }
        } else {
            setMsg("Senha atual informada não coincide com a senha cadastrada.");
        }

        // Apaga as variáveis
        setSenhaAtual("");
        setNovaSenha("");
        setConfirmaSenha("");

        // Retorna com as mensagens para o formulário
        FacesMessage mensagem = new FacesMessage(getMsg());
        contexto.addMessage("frmCadastro", mensagem);
    }

    public void salvarPerfil() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        msg = "";        
        dao.alterar(usuario);
        if (dao.getErrorMessage() == null) {
            msg = "Alteração realizada com sucesso!";
        } else {
            msg = "Erro ao alterar: " + dao.getErrorMessage();
        }
        listaLogin = dao.listarTodos();
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("frmLogin", mensagem);
    }

    public void ativarDesativar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        Login u = getDao().buscar(idUsuario);
        if (u.getUsuarioLogin().equals("admin")) {
            setMsg("Usuário 'Administrador' não pode ser ativado / desativado!");
        } else {
            u.setAtivo(!u.isAtivo());
            getDao().alterar(u);
            if (getDao().getErrorMessage() == null) {
                if (u.isAtivo()) {
                    setMsg("Login ativado com sucesso!");
                } else {
                    setMsg("Login desativado com sucesso!");
                }
            } else {
                setMsg("Erro ao alterar a situação: " + getDao().getErrorMessage());
            }
        }
        FacesMessage mensagem = new FacesMessage(getMsg());
        contexto.addMessage("lstLogin", mensagem);
    }

    public Login getUsuarioSessao() {
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return (Login) sessao.getAttribute("usuarioLogado");
    }

    /**
     * @return the usuario
     */
    public Login getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Login usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the usuarioLogado
     */
    public Login getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * @param usuarioLogado the usuarioLogado to set
     */
    public void setUsuarioLogado(Login usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    /**
     * @return the dao
     */
    public LoginDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(LoginDao dao) {
        this.dao = dao;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the senhaAtual
     */
    public String getSenhaAtual() {
        return senhaAtual;
    }

    /**
     * @param senhaAtual the senhaAtual to set
     */
    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    /**
     * @return the novaSenha
     */
    public String getNovaSenha() {
        return novaSenha;
    }

    /**
     * @param novaSenha the novaSenha to set
     */
    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    /**
     * @return the confirmaSenha
     */
    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    /**
     * @param confirmaSenha the confirmaSenha to set
     */
    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    /**
     * @return the listaLogin
     */
    public List<Login> getListaLogin() {
        listaLogin = dao.listarTodos();
        return listaLogin;
    }

    /**
     * @param listaLogin the listaLogin to set
     */
    public void setListaLogin(List<Login> listaLogin) {
        this.listaLogin = listaLogin;
    }

    /**
     * @return the idUsuario
     */
    public long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the isUsuarioAlteraSenha
     */
    public Boolean getIsAdminAlteraSenha() {
        return isAdminAlteraSenha;
    }

    /**
     * @param isUsuarioAlteraSenha the isUsuarioAlteraSenha to set
     */
    public void setIsAdminAlteraSenha(Boolean isAdminAlteraSenha) {
        this.isAdminAlteraSenha = isAdminAlteraSenha;
    }

	public Date getHoraAtual() {
		return new Date();
	}

	public void setHoraAtual(Date horaAtual) {
		this.horaAtual = horaAtual;
	}

}
