<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="rich" uri="http://richfaces.org/rich" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>

    <f:subview id="cabecalho">
        <jsp:include page="header.jsp"/>
    </f:subview>

    <div id="content">

        <div class="post" id="post-01">

            <h1 class="post-title"><a href="#" rel="bookmark">Atendimentos</a></h1>

            <h:form id="lstAtendimento">

                <h:messages />
                Cód. do Atendimento:
                <h:inputText id="txtCodAtend" value="#{AtendimentoMB.codAtend}" size="10"/>
                &nbsp;                
                Status:
                <h:selectOneMenu value="#{AtendimentoMB.idStatus}">
                    <f:selectItem itemLabel="Selecione" itemValue="0"/>
                    <f:selectItems value="#{AtendimentoMB.listaStatus}"/>
                </h:selectOneMenu>
                &nbsp;
                Data de Abertura:
                <rich:calendar id="txtDataAbertura" datePattern="dd/MM/yyyy" value="#{AtendimentoMB.dataAbertura}"/>
                &nbsp;
                <h:commandButton value="Pesquisar" action="#{AtendimentoMB.pesquisarAtendimento}" style="padding: 3px 10px;"/>
                <br/><br/>

                <h:dataTable id="listAtendimentos" width="100%" value="#{AtendimentoMB.listaAtendimentos}" var="item" style="text-align:center" styleClass="jsflist">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Cód. Atend." />
                        </f:facet>
                        <h:outputText value="#{item.id}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Cliente" />
                        </f:facet>
                        <h:outputText value="#{item.idCliente.nomeCliente}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Data de abertura" />
                        </f:facet>
                        <h:outputText value="#{item.dataAbertura}">
                            <f:convertDateTime pattern="dd/MM/yyyy HH:mm"/>
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Status do atendimento" />
                        </f:facet>
                        <h:outputText value="#{item.idStatus.descricao}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Setor" />
                        </f:facet>
                        <h:outputText value="#{item.idSetor.descricao}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Operador" />
                        </f:facet>
                        <h:outputText value="#{item.idOperador.nomeUsuario}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Detalhes" />
                        </f:facet>
                        <h:commandLink value="Ver" action="#{AtendimentoMB.editar}">
                            <f:setPropertyActionListener target="#{AtendimentoMB.idAtendimento}" value="#{item.id}"/>
                        </h:commandLink>
                    </h:column>
                </h:dataTable>

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