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

            <h:form id="lstStatus">
                <strong><h:commandLink value="Novo status" action="#{StatusMB.cadastrar}"/></strong>
                <br/><br/>
                <h:messages/>
                Informe o status:
                <h:inputText value="#{StatusMB.busca}"/>
                &nbsp;
                <h:commandButton value="Pesquisar" action="#{StatusMB.pesquisar}"  style="padding: 3px 10px;"/>
                <br/><br/>                
                <h:dataTable id="tableStatus" value="#{StatusMB.listaStatus}" var="item" width="100%" border="1" style="text-align:center" styleClass="jsflist">
                    <f:facet name="header">
                        <h:outputText value="Status cadastrados"/>
                    </f:facet>                    
                     <h:column>
                        <f:facet name="header">
                            <h:outputText value="Status"/>
                        </f:facet>
                         <h:outputText value="#{item.descricao}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Encerra atendimento"/>
                        </f:facet>
                        <h:outputText value="#{item.encerra}">
                            <f:converter converterId="simnaoconverter"/>
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Editar"/>
                        </f:facet>
                        <h:commandLink value="Editar" action="#{StatusMB.editar}">
                            <f:setPropertyActionListener target="#{StatusMB.idStatus}" value="#{item.id}"/>
                        </h:commandLink>                        
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Excluir"/>
                        </f:facet>
                        <h:commandLink value="Excluir" action="#{StatusMB.excluir}" onclick="javascript:return(confirm('Deseja apagar este registro?'))">
                            <f:setPropertyActionListener target="#{StatusMB.idStatus}" value="#{item.id}"/>
                        </h:commandLink>
                    </h:column>
                </h:dataTable>
                    <br/>
                    <strong><h:commandLink value="Novo status" action="#{StatusMB.cadastrar}"/></strong>
            </h:form>
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