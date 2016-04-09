<%--

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

 --%>


<%--
    Document   : index
    Created on : 15/11/2010, 08:02:11
    Author     : Hércules
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>

    <f:subview id="cabecalho">
        <jsp:include page="header.jsp"/>
    </f:subview>



            <h1 class="post-title"><a href="#" rel="bookmark">Usuários - Cadastrar</a></h1>

            <center>

                <h:form id="frmLogin">
                    <h:messages/>
                    <h:panelGrid columns="2" cellspacing="10" styleClass="jsfform"  style="text-align: left">

                        Nome do usuário:
                        <h:inputText value="#{LoginMB.usuario.nomeUsuario}" required="true" requiredMessage="Informe o nome do usuário." maxlength="50" size="30"/>

                        Login do usuário:
                        <h:outputText value="#{LoginMB.usuario.usuarioLogin}" />

                        Perfil:
                        <h:selectOneMenu value="#{LoginMB.usuario.perfil}" required="true" requiredMessage="Selecione o nível de acesso!" style="width: 180px;">
                            <f:selectItem itemLabel="Selecione"/>
                            <f:selectItem itemValue="ADMIN" itemLabel="Administrador"/>
                            <f:selectItem itemValue="USER" itemLabel="Usuário"/>
                        </h:selectOneMenu>

                        <br/>
                        <h:commandButton value="Alterar" action="#{LoginMB.salvarPerfil}" style="padding: 3px 10px;"/>

                    </h:panelGrid>
                </h:form>
                <br/>

                <a href="listLogin.jsf"><< Voltar</a>
            </center>
            
            
    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>

</f:view>
