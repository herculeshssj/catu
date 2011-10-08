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


			<center>
				<h:form id="frmMaquina">
					<h:messages style="font-weight: bold;" />
					<h:panelGrid columns="4" cellspacing="10" styleClass="jsfform"
						style="text-align: left">
						
						Número patrimonial:
                        <h:inputText
							value="#{MaquinaMB.maquina.numPatrimonial}" size="20"
							maxlength="20"></h:inputText>
						
						Número do lacre:
                        <h:inputText
							value="#{MaquinaMB.maquina.numLacre}" size="20"
							maxlength="20"></h:inputText>
							
						Cliente:
                        <h:selectOneMenu value="#{MaquinaMB.idCliente}" required="true" requiredMessage="Selecione um cliente!">
							<f:selectItem itemLabel="Selecione o cliente" />
							<f:selectItems value="#{MaquinaMB.listaClientes}" />
						</h:selectOneMenu>	
							
						Setor:
                        <h:selectOneMenu value="#{MaquinaMB.idSetor}" required="true" requiredMessage="Selecione o setor!">
							<f:selectItem itemLabel="Selecione o setor" />
							<f:selectItems value="#{MaquinaMB.listaSetores}" />
						</h:selectOneMenu>
						
						Nível para utilização:
                        <h:selectOneMenu
							value="#{MaquinaMB.maquina.nivel}">
							<f:selectItem itemLabel="Aceitável" itemValue="1" />
							<f:selectItem itemLabel="Médio" itemValue="2" />
							<f:selectItem itemLabel="Inaceitável" itemValue="3" />
						</h:selectOneMenu>
						
						Em uso:
						<h:selectBooleanCheckbox value="#{MaquinaMB.maquina.emUso}"></h:selectBooleanCheckbox>
						
						Processador:
                        <h:inputText
							value="#{MaquinaMB.maquina.processador}"></h:inputText>
							
						Memória:                        
                        <h:inputText
							value="#{MaquinaMB.maquina.memoria}"></h:inputText>
						
						Disco rígido:
                        <h:inputText value="#{MaquinaMB.maquina.hd}"></h:inputText>                        
                        
                        CD / DVD:
                        <h:inputText value="#{MaquinaMB.maquina.cdDvd}"></h:inputText>
						
						Monitor:
                        <h:inputText value="#{MaquinaMB.maquina.monitor}"></h:inputText>
                        
                        Num. Patrimonial do Monitor:
                        <h:inputText value="#{MaquinaMB.maquina.numPatrMonitor}"></h:inputText>
                    
                        Tipo de monitor:
                        <h:selectOneMenu
							value="#{MaquinaMB.maquina.tipoMonitor}">
							<f:selectItem itemLabel="CRT" itemValue="CRT" />
							<f:selectItem itemLabel="LCD" itemValue="LCD" />
							<f:selectItem itemLabel="LED" itemValue="LED" />
						</h:selectOneMenu>
                       
                        Tamanho do monitor:
                        <h:inputText
							value="#{MaquinaMB.maquina.tamanhoMonitor}"></h:inputText>
                        
                        Placa de vídeo:
                        <h:inputText
							value="#{MaquinaMB.maquina.placaVideo}"></h:inputText>
							
						Placa de som:
                        <h:inputText
							value="#{MaquinaMB.maquina.placaSom}"></h:inputText>
                        
                        Placa de rede:
                        <h:inputText
							value="#{MaquinaMB.maquina.placaRede}"></h:inputText>
                        
                        IP da máquina:
                        <h:inputText value="#{MaquinaMB.maquina.ip}"></h:inputText>
                        
                        Endereço MAC:
                        <h:inputText
							value="#{MaquinaMB.maquina.macAddress}"></h:inputText>
                        
                      	<h:inputHidden></h:inputHidden>
						<h:inputHidden></h:inputHidden>
						
						Teclado:
                        <h:inputText value="#{MaquinaMB.maquina.teclado}"></h:inputText>
                        
                        Mouse:
                        <h:inputText value="#{MaquinaMB.maquina.mouse}"></h:inputText>
                        
                        Impressora:
                        <h:inputText value="#{MaquinaMB.maquina.impressora}"></h:inputText>
                        
                        Num. Patrimonial Impressora:
                        <h:inputText value="#{MaquinaMB.maquina.numPatrImpressora}"></h:inputText>
                        
                        Scanner:
                        <h:inputText value="#{MaquinaMB.maquina.scanner}"></h:inputText>
                        
                        Num. Patrimonial Scanner:
                        <h:inputText value="#{MaquinaMB.maquina.numPatrScanner}"></h:inputText>
                        
                        Estabilizador / No-Break:
                        <h:inputText value="#{MaquinaMB.maquina.estabilizador}"></h:inputText>
                        
                        Num. Patrimonial Estab./No-Break:
                        <h:inputText value="#{MaquinaMB.maquina.numPatrEstabilizador}"></h:inputText>
                        
						Parecer do CPD:
                        <h:inputTextarea
							value="#{MaquinaMB.maquina.parecer}" rows="3"></h:inputTextarea>
							
						<h:inputHidden></h:inputHidden>
						<h:inputHidden></h:inputHidden>
						
						<h:inputHidden></h:inputHidden>
                        Softwares instalados:
                        <h:inputHidden value="MaquinaMB.maquina.id"></h:inputHidden>
                        Softwares disponíveis:
                       
                                                
                        <h:inputHidden></h:inputHidden>
						<h:selectOneListbox value="#{MaquinaMB.instaladosSelecionados}" size="10">
							<f:selectItems value="#{MaquinaMB.instalados}" />
						</h:selectOneListbox>

						<h:inputHidden></h:inputHidden>

						<h:selectManyListbox  value="#{MaquinaMB.disponiveisSelecionados}" size="10">
							<f:selectItems value="#{MaquinaMB.disponiveis}" />
						</h:selectManyListbox>

						

						<h:commandButton id="btnSalvar" style="padding: 3px 10px;"
							value="Salvar" action="#{MaquinaMB.salvar}" />
					</h:panelGrid>

				</h:form>
				<br /> <a href="listMaquina.jsf"><< Voltar</a>
			</center>
			
	<f:subview id="rodape">
		<jsp:include page="footer.jsp" />
	</f:subview>



</f:view>
