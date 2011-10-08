<%@page contentType="text/html" pageEncoding="UTF-8"%>
 

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<f:view>

    <f:subview id="cabecalho">
        <jsp:include page="header.jsp"/>
    </f:subview>


            <h1 class="post-title"><a href="#" rel="bookmark">Setores</a></h1>


            <center>
                <h:form id="frmSetor">
                    <h:messages style="font-weight: bold;"/>
                    <h:panelGrid columns="2" cellspacing="10" styleClass="jsfform" style="text-align: left">

                        Nome do setor:
                        <h:inputText id="txtDescricao" value="#{SetorMB.setor.descricao}" required="true" requiredMessage="Informe o setor!" size="50" maxlength="100"/>

                        Responsável:
                        <h:inputText id="txtResponsavel" value="#{SetorMB.setor.responsavel}" required="true" requiredMessage="Informe o responsável pelo setor!" size="50" maxlength="50"/>

                        <h:inputHidden id="txtId" value="#{SetorMB.setor.id}"/>
                        <h:commandButton id="btnSalvar" style="padding: 3px 10px;" value="Salvar" action="#{SetorMB.salvar}"/>
                    </h:panelGrid>


                </h:form>
                <br/>

                <a href="listSetor.jsf"><< Voltar</a>
            </center>
            
   
    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>



</f:view>
