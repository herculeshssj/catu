<%@page contentType="text/html" pageEncoding="UTF-8"%>
 

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<f:view>

    <f:subview id="cabecalho">
        <jsp:include page="header.jsp"/>
    </f:subview>


            <h1 class="post-title"><a href="#" rel="bookmark">Tipos de Atendimento</a></h1>


            <center>
                <h:form id="frmTipo">
                    <h:messages style="font-weight: bold;"/>
                    <h:panelGrid columns="2" cellspacing="10" styleClass="jsfform" style="text-align: left">

                        Descrição:
                        <h:inputText id="txtDescricao" value="#{TipoMB.tipo.descricao}" required="true" requiredMessage="Informe o tipo de atendimento!" size="50" maxlength="100"/>

                        Atendimento externo:
                        <h:selectBooleanCheckbox id="txtPresencial" value="#{TipoMB.tipo.externo}"/>

                        <h:inputHidden id="txtId" value="#{TipoMB.tipo.id}"/>
                        <h:commandButton id="btnSalvar" style="padding: 3px 10px;" value="Salvar" action="#{TipoMB.salvar}"/>
                    </h:panelGrid>


                </h:form>
                        <br/>

                <a href="listTipo.jsf"><< Voltar</a>
            </center>
            
            
    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>



</f:view>
