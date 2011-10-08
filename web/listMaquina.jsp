<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>

	<f:subview id="cabecalho">
		<jsp:include page="header.jsp" />
	</f:subview>

			<h1 class="post-title">
				<a href="#" rel="bookmark">Máquinas</a>
			</h1>

			<h:form id="lstMaquina">
				<strong><h:commandLink value="Nova máquina"
						action="#{MaquinaMB.cadastrar}" />
				</strong>
				<br />
				<br />
				<h:messages />
                Informe o número patrimonial:
                <h:inputText value="#{MaquinaMB.busca}" />
                &nbsp;
                <h:commandButton value="Pesquisar"
					action="#{MaquinaMB.pesquisar}" style="padding: 3px 10px;" />
				<br />
				<br />

				<h:dataTable id="tableMaquinas"
					value="#{MaquinaMB.listaMaquinas}" var="item" width="100%"
					border="1" style="text-align:center" styleClass="jsflist">
					<f:facet name="header">
						<h:outputText value="Máquinas cadastrados" />
					</f:facet>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Patrimônio" />
						</f:facet>
						<h:outputText value="#{item.numPatrimonial}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Cliente" />
						</f:facet>
						<h:outputText value="#{item.cliente.nomeCliente}" />						
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Setor" />
						</f:facet>
						<h:outputText value="#{item.setor.descricao}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Em uso" />
						</f:facet>
						<h:outputText value="#{item.emUso}">
							<f:converter converterId="simnaoconverter" />
						</h:outputText>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Editar" />
						</f:facet>
						<h:commandLink value="Editar" action="#{MaquinaMB.editar}">
							<f:setPropertyActionListener target="#{MaquinaMB.idMaquina}"
								value="#{item.id}" />
						</h:commandLink>
					</h:column>					
				</h:dataTable>
				<br />
				<strong><h:commandLink value="Nova máquina"
						action="#{MaquinaMB.cadastrar}" />
				</strong>
			</h:form>
			
	<f:subview id="rodape">
		<jsp:include page="footer.jsp" />
	</f:subview>

</f:view>