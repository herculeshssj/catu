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


    <div id="content">

        <div class="post" id="post-01">

            <h1 class="post-title"><a href="#" rel="bookmark">Usuários - Alterar senha</a></h1>
            <center>
                <h:form id="formSenha">

                    Usuário: <strong><h:outputText value="#{LoginMB.usuario.nomeUsuario}"/></strong>
                    <br/>
                    <h:messages/>
                    <h:panelGrid columns="2" cellspacing="10" styleClass="jsfform" style="text-align: left">
                        <h:outputText value="Informe a senha atual:" rendered="#{!LoginMB.isAdminAlteraSenha}"/>
                        <h:inputSecret value="#{LoginMB.senhaAtual}" rendered="#{!LoginMB.isAdminAlteraSenha}" size="30" maxlength="50" required="true" requiredMessage="Informe a senha atual!"/>
                        Informe a nova senha:
                        <h:inputSecret value="#{LoginMB.novaSenha}" size="30" maxlength="50" required="true" requiredMessage="Informe a nova senha!"/>
                        Confirma a nova senha:
                        <h:inputSecret value="#{LoginMB.confirmaSenha}" size="30" maxlength="50" required="true" requiredMessage="Confirme a nova senha!"/>

                        <h:inputHidden value="#{LoginMB.usuario.id}"/>
                        <h:commandButton value="Alterar senha" action="#{LoginMB.alterar}" rendered="#{LoginMB.isAdminAlteraSenha}" style="padding: 3px 10px;"/>
                        <h:commandButton value="Alterar senha" action="#{LoginMB.alterarSenha}" rendered="#{!LoginMB.isAdminAlteraSenha}"  style="padding: 3px 10px;"/>
                    </h:panelGrid>

                </h:form>
                        <br/>

                    <a href="listLogin.jsf"><< Voltar</a>
            </center>
            <br/>
            <div class="post-info">

                <strong><h:outputText value="#{LoginMB.usuarioLogado.nomeUsuario}"/>, seja bem vindo!</strong>

            </div>

        </div>


    </div>
    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>


</f:view>
