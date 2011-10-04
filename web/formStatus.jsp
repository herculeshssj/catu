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

            <h1 class="post-title"><a href="#" rel="bookmark">Status do Atendimento</a></h1>


            <center>
                <h:form id="frmStatus">
                    <h:messages style="font-weight: bold;"/>
                    <h:panelGrid columns="2" cellspacing="10" styleClass="jsfform" style="text-align: left">

                        Descrição:
                        <h:inputText id="txtDescricao" value="#{StatusMB.status.descricao}" required="true" requiredMessage="Informe o status!" size="50" maxlength="100"/>

                        Encerra o atendimento:
                        <h:selectBooleanCheckbox id="txtEncerra" value="#{StatusMB.status.encerra}"/>

                        <h:inputHidden id="txtId" value="#{StatusMB.status.id}"/>
                        <h:commandButton id="btnSalvar" style="padding: 3px 10px;" value="Salvar" action="#{StatusMB.salvar}"/>
                    </h:panelGrid>


                </h:form>
                <br/>

                <a href="listStatus.jsf"><< Voltar</a>
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
