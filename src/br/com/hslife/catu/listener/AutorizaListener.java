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

package br.com.hslife.catu.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.hslife.catu.model.Login;

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
        boolean isSetorPage = paginaAtual.lastIndexOf("listSetor.jsp") > -1;
        boolean isStatusPage = paginaAtual.lastIndexOf("listStatus.jsp") > -1;
        boolean isTipoPage = paginaAtual.lastIndexOf("listTipo.jsp") > -1;
        boolean isClientePage = paginaAtual.lastIndexOf("listCliente.jsp") > -1;
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
        } else if ((isUserAdminPage || isSetorPage || isStatusPage || isTipoPage || isClientePage) && usuario.getPerfil().equals("USER")) {
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