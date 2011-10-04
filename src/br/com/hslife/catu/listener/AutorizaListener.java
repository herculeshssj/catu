/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.hslife.catu.listener;

import br.com.hslife.catu.model.Login;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hércules
 */
public class AutorizaListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        // Obtém o contexto atual
        FacesContext contexto = event.getFacesContext();
        // Obtém a página que atualmente está interagindo com o ciclo
        String paginaAtual = contexto.getViewRoot().getViewId();
        // Se for a página 'login.jsp' seta a variável como true
        boolean isLoginPage = paginaAtual.lastIndexOf("login.jsp") > -1;
        boolean isUserAdminPage = paginaAtual.lastIndexOf("listLogin.jsp") > -1;
        // Obtém a sessão atual
        HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
        // Restaga o nome do usuário logado
        Login usuario = (Login) sessao.getAttribute("usuarioLogado");
        // Verifica se o usuário está logado e se não está na página de login
        if (!isLoginPage && usuario == null) {
            // Redireciona o fluxo para a página de login
            NavigationHandler nh = contexto.getApplication().getNavigationHandler();
            nh.handleNavigation(contexto, null, "sair");
        } else if (isLoginPage && usuario != null) {
            // Se o usuário logado tentar acessar a página de login ele é
            // redirecionado para a página inicial
            NavigationHandler nh = contexto.getApplication().getNavigationHandler();
            nh.handleNavigation(contexto, null, "sucesso");
        } else if (isUserAdminPage && usuario.getPerfil().equals("USER")) {
            // Se o usuário logado tentar acessar a página de usuários cadastrados
            // ele é redirecionado para a página de acesso negado
            NavigationHandler nh = contexto.getApplication().getNavigationHandler();
            nh.handleNavigation(contexto, null, "acessoNegado");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}