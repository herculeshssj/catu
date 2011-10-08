<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>

    <f:subview id="cabecalho">
        <jsp:include page="header.jsp"/>
    </f:subview>

            <h1 class="post-title"><a href="#" rel="bookmark">Usuários</a></h1>
            <h:form id="lstLogin">
                <strong><h:commandLink value="Novo login" action="#{LoginMB.cadastrar}"/></strong>
                <br/><br/>
                <h:messages/>

                <h:dataTable id="tableLogins" value="#{LoginMB.listaLogin}" var="item" width="100%" border="1" style="text-align:center" styleClass="jsflist">
                    <f:facet name="header">
                        <h:outputText value="Logins cadastrados"/>
                    </f:facet>                  
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nome do usuário"/>
                        </f:facet>
                        <h:outputText value="#{item.nomeUsuario}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Login do usuário"/>
                        </f:facet>
                        <h:outputText value="#{item.usuarioLogin}"/>
                    </h:column>

                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Perfil"/>
                        </f:facet>
                        <h:outputText value="#{item.perfil}"/>
                    </h:column>
                     <h:column>
                        <f:facet name="header">
                            <h:outputText value="Info"/>
                        </f:facet>
                        <h:commandLink value="Modificar" action="#{LoginMB.editarPerfil}">
                            <f:setPropertyActionListener target="#{LoginMB.idUsuario}" value="#{item.id}"/>
                        </h:commandLink>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Senha"/>
                        </f:facet>
                        <h:commandLink value="Alterar" action="#{LoginMB.editar}">
                            <f:setPropertyActionListener target="#{LoginMB.idUsuario}" value="#{item.id}"/>
                        </h:commandLink>
                    </h:column>

                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Login"/>
                        </f:facet>
                        <h:commandLink value="Ativar" action="#{LoginMB.ativarDesativar}" rendered="#{!item.ativo}" onclick="javascript:return(confirm('Deseja ativar este login?'))">
                            <f:setPropertyActionListener target="#{LoginMB.idUsuario}" value="#{item.id}"/>
                        </h:commandLink>
                        <h:commandLink value="Desativar" action="#{LoginMB.ativarDesativar}" rendered="#{item.ativo}" onclick="javascript:return(confirm('Deseja desativar este login?'))">
                            <f:setPropertyActionListener target="#{LoginMB.idUsuario}" value="#{item.id}"/>
                        </h:commandLink>
                    </h:column>
                </h:dataTable>
                <br/>
                <strong><h:commandLink value="Novo login" action="#{LoginMB.cadastrar}"/></strong>
            </h:form>
            
 
    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>

</f:view>