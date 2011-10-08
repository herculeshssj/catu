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
				<a href="#" rel="bookmark">Softwares</a>
			</h1>

			<h:form id="lstSoftware">
				<strong><h:commandLink value="Novo software"
						action="#{SoftwareMB.cadastrar}" />
				</strong>
				<br />
				<br />
				<h:messages />
                Informe o software:
                <h:inputText value="#{SoftwareMB.busca}" />
                &nbsp;
                <h:commandButton value="Pesquisar"
					action="#{SoftwareMB.pesquisar}" style="padding: 3px 10px;" />
				<br />
				<br />

				<h:dataTable id="tableSoftwares"
					value="#{SoftwareMB.listaSoftwares}" var="item" width="100%"
					border="1" style="text-align:center" styleClass="jsflist">
					<f:facet name="header">
						<h:outputText value="Softwares cadastrados" />
					</f:facet>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Software" />
						</f:facet>
						<h:outputText value="#{item.nome}" />
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Descrição" />
						</f:facet>
						<h:outputText value="#{item.descricao}" />
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
						<h:commandLink value="Editar" action="#{SoftwareMB.editar}">
							<f:setPropertyActionListener target="#{SoftwareMB.idSoftware}"
								value="#{item.id}" />
						</h:commandLink>
					</h:column>
				</h:dataTable>
				<br />
				<strong><h:commandLink value="Novo software"
						action="#{SoftwareMB.cadastrar}" />
				</strong>
			</h:form>
			
	<f:subview id="rodape">
		<jsp:include page="footer.jsp" />
	</f:subview>

</f:view>