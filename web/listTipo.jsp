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

            <h1 class="post-title"><a href="#" rel="bookmark">Tipos de Atendimento</a></h1>


            <h:form id="lstTipo">
                <strong><h:commandLink value="Novo tipo" action="#{TipoMB.cadastrar}"/></strong>
                <br/><br/>
                <h:messages/>
                Informe o tipo:
                <h:inputText value="#{TipoMB.busca}"/>
                &nbsp;
                <h:commandButton value="Pesquisar" action="#{TipoMB.pesquisar}"  style="padding: 3px 10px;"/>
                <br/><br/>
                
                <h:dataTable id="tableTipos" value="#{TipoMB.listaTipos}" var="item" width="100%" border="1" style="text-align:center" styleClass="jsflist">
                    <f:facet name="header">
                        <h:outputText value="Tipos cadastrados"/>
                    </f:facet>                    
                     <h:column>
                        <f:facet name="header">
                            <h:outputText value="Tipo"/>
                        </f:facet>
                         <h:outputText value="#{item.descricao}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Atendimento externo"/>
                        </f:facet>
                        <h:outputText value="#{item.externo}">
                            <f:converter converterId="simnaoconverter"/>
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Editar"/>
                        </f:facet>
                        <h:commandLink value="Editar" action="#{TipoMB.editar}">
                            <f:setPropertyActionListener target="#{TipoMB.idTipo}" value="#{item.id}"/>
                        </h:commandLink>                        
                    </h:column>

                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Excluir"/>
                        </f:facet>
                        <h:commandLink value="Excluir" action="#{TipoMB.excluir}" onclick="javascript:return(confirm('Deseja apagar este registro?'))">
                            <f:setPropertyActionListener target="#{TipoMB.idTipo}" value="#{item.id}"/>
                        </h:commandLink>
                    </h:column>
                </h:dataTable>
                   <br/>
                <strong><h:commandLink value="Novo tipo" action="#{TipoMB.cadastrar}"/></strong>
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