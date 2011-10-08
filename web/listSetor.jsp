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

            <h:form id="lstSetor">
                <strong><h:commandLink value="Novo setor" action="#{SetorMB.cadastrar}"/></strong>
                <br/><br/>
                <h:messages/>
                Informe o setor:
                <h:inputText value="#{SetorMB.busca}"/>
                &nbsp;
                <h:commandButton value="Pesquisar" action="#{SetorMB.pesquisar}"  style="padding: 3px 10px;"/>
                <br/><br/>
                
                <h:dataTable id="tableSetors" value="#{SetorMB.listaSetors}" var="item" width="100%" border="1" style="text-align:center" styleClass="jsflist">
                    <f:facet name="header">
                        <h:outputText value="Setores cadastrados"/>
                    </f:facet>                    
                     <h:column>
                        <f:facet name="header">
                            <h:outputText value="Setor"/>
                        </f:facet>
                         <h:outputText value="#{item.descricao}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Responsável"/>
                        </f:facet>
                         <h:outputText value="#{item.responsavel}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Editar"/>
                        </f:facet>
                        <h:commandLink value="Editar" action="#{SetorMB.editar}">
                            <f:setPropertyActionListener target="#{SetorMB.idSetor}" value="#{item.id}"/>
                        </h:commandLink>                        
                    </h:column>

                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Excluir"/>
                        </f:facet>
                        <h:commandLink value="Excluir" action="#{SetorMB.excluir}" onclick="javascript:return(confirm('Deseja apagar este registro?'))">
                            <f:setPropertyActionListener target="#{SetorMB.idSetor}" value="#{item.id}"/>
                        </h:commandLink>
                    </h:column>
                </h:dataTable>
                   <br/>
                <strong><h:commandLink value="Novo setor" action="#{SetorMB.cadastrar}"/></strong>
            </h:form>
            

    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>

</f:view>