<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>

    <f:subview id="cabecalho">
        <jsp:include page="header.jsp"/>
    </f:subview>

            <h1 class="post-title"><a href="#" rel="bookmark">Novo atendimento</a></h1>
            <h:form id="lstClientes">
                
                <br/>
                <h:messages/>
                Informe o nome do cliente:
                <h:inputText value="#{AtendimentoMB.busca}"/>
                &nbsp;
                <h:commandButton value="Pesquisar" action="#{AtendimentoMB.pesquisarCliente}"  style="padding: 3px 10px;"/>
                <br/><br/>

                <h:dataTable id="tableClientes" value="#{AtendimentoMB.listaClientes}" var="item" width="100%" border="1" style="text-align:center" styleClass="jsflist">
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
                            <h:outputText value="Setor"/>
                        </f:facet>
                        <h:outputText value="#{item.idSetor.descricao}"/>
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
                            <h:outputText value="Atendimento"/>
                        </f:facet>
                        <h:commandLink value="Novo" action="#{AtendimentoMB.novoAtendimento}">
                            <f:setPropertyActionListener target="#{AtendimentoMB.idCliente}" value="#{item.id}"/>
                        </h:commandLink>                        
                    </h:column>
                </h:dataTable>
                <br/>                
            </h:form>
            
    
    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>

</f:view>