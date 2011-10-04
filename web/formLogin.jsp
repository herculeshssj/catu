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

            <h1 class="post-title"><a href="#" rel="bookmark">Usuários - Cadastrar</a></h1>

            <center>

                <h:form id="frmLogin">
                    <h:messages/>
                    <h:panelGrid columns="2" cellspacing="10" styleClass="jsfform"  style="text-align: left">

                        Nome do usuário:
                        <h:inputText value="#{LoginMB.usuario.nomeUsuario}" required="true" requiredMessage="Informe o nome do usuário." maxlength="50" size="30"/>

                        Login do usuário:
                        <h:inputText value="#{LoginMB.usuario.usuarioLogin}" required="true" requiredMessage="Informe o login do usuário." maxlength="50" size="30"/>

                        Perfil:
                        <h:selectOneMenu value="#{LoginMB.usuario.perfil}" required="true" requiredMessage="Selecione o nível de acesso!" style="width: 180px;">
                            <f:selectItem itemLabel="Selecione"/>
                            <f:selectItem itemValue="ADMIN" itemLabel="Administrador"/>
                            <f:selectItem itemValue="USER" itemLabel="Usuário"/>
                        </h:selectOneMenu>
                       
                        Senha:
                        <h:inputSecret value="#{LoginMB.usuario.usuarioSenha}"required="true" requiredMessage="Informe a senha." size="30"/>

                        Confirme a senha:
                        <h:inputSecret value="#{LoginMB.confirmaSenha}"required="true" requiredMessage="Confirme a senha." size="30"/>

                        <br/>
                        <h:commandButton value="Cadastrar" action="#{LoginMB.salvar}"style="padding: 3px 10px;"/>

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
        <jsp:include page="rodape.jsp"/>
    </f:subview>

</f:view>
