<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<f:view>

    <f:subview id="cabecalho">
        <jsp:include page="header.jsp"/>
    </f:subview>

            <h1 class="post-title"><a href="#" rel="bookmark">Softwares</a></h1>


            <center>
                <h:form id="frmSoftware">
                    <h:messages style="font-weight: bold;"/>
                    <h:panelGrid columns="2" cellspacing="10" styleClass="jsfform" style="text-align: left">

                        Nome do software:
                        <h:inputText id="txtNomeSoftware" value="#{SoftwareMB.software.nome}" required="true" requiredMessage="Informe o nome do software!" size="50" maxlength="50"/>

                        Descrição:
                        <h:inputText id="txtDescricao" value="#{SoftwareMB.software.descricao}" size="50" maxlength="255"/>
                        
                        Licença:
                        <h:inputText id="txtLicenca" value="#{SoftwareMB.software.licenca}" size="50" maxlength="255"/>
                        
                        Em uso:
                        <h:selectBooleanCheckbox id="txtEmUso" value="#{SoftwareMB.software.emUso}" />

                        <h:inputHidden id="txtId" value="#{SoftwareMB.software.id}"/>
                        <h:commandButton id="btnSalvar" style="padding: 3px 10px;" value="Salvar" action="#{SoftwareMB.salvar}"/>
                    </h:panelGrid>


                </h:form>
                 <br/>

                <a href="listSoftware.jsf"><< Voltar</a>
            </center>
    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>



</f:view>
