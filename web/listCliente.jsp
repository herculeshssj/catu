<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>

    <f:subview id="cabecalho">
        <jsp:include page="header.jsp"/>
    </f:subview>

            <h1 class="post-title"><a href="#" rel="bookmark">Clientes</a></h1>


            <h:form id="lstCliente">
                <strong><h:commandLink value="Novo cliente" action="#{ClienteMB.cadastrar}"/></strong>
                <br/><br/>
                <h:messages/>
                Informe o nome do cliente:
                <h:inputText value="#{ClienteMB.busca}"/>
                &nbsp;
                <h:commandButton value="Pesquisar" action="#{ClienteMB.pesquisar}"  style="padding: 3px 10px;"/>
                <br/><br/>

                <h:dataTable id="tableClientes" value="#{ClienteMB.listaClientes}" var="item" width="100%" border="1" style="text-align:center" styleClass="jsflist">
                    <f:facet name="header">
                        <h:outputText value="Clientes cadastrados"/>
                    </f:facet>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Cliente"/>
                        </f:facet>
                        <h:outputText value="#{item.nomeCliente}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Telefone"/>
                        </f:facet>
                        <h:outputText value="#{item.telefone}"/>
                    </h:column>                    
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="E-Mail"/>
                        </f:facet>
                        <h:outputText value="#{item.email}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Setor"/>
                        </f:facet>
                        <h:outputText value="#{item.idSetor.descricao}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Editar"/>
                        </f:facet>
                        <h:commandLink value="Editar" action="#{ClienteMB.editar}">
                            <f:setPropertyActionListener target="#{ClienteMB.idCliente}" value="#{item.id}"/>
                        </h:commandLink>                        
                    </h:column>

                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Excluir"/>
                        </f:facet>
                        <h:commandLink value="Excluir" action="#{ClienteMB.excluir}" onclick="javascript:return(confirm('Deseja apagar este registro?'))">
                            <f:setPropertyActionListener target="#{ClienteMB.idCliente}" value="#{item.id}"/>
                        </h:commandLink>
                    </h:column>
                </h:dataTable>
                <br/>
                <strong><h:commandLink value="Novo cliente" action="#{ClienteMB.cadastrar}"/></strong>
            </h:form>
            
   
    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>

</f:view>