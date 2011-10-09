<%--

    Copyright (c) 2010, 2011 Hércules S. S. José



    Este arquivo é parte do programa CATU.

    CATU é um software livre; você pode redistribui-lo e/ou 

    modificá-lo dentro dos termos da Licença Pública Geral Menor GNU como 

    publicada pela Fundação do Software Livre (FSF); na versão 2.1 da 

    Licença.



    Este programa é distribuído na esperança que possa ser útil, 

    mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUAÇÂO a qualquer

    MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral Menor GNU 
    
    em português para maiores detalhes.



    Você deve ter recebido uma cópia da Licença Pública Geral Menor GNU sob o 
    
    nome de "LICENSE.TXT" junto com este programa, se não, acesse o site HSlife no 

    endereco www.hslife.com.br ou escreva para a Fundação do Software Livre(FSF) Inc., 

    51 Franklin St, Fifth Floor, Boston, MA  02110-1301, USA.



    Para mais informações sobre o programa CATU e seus autores acesse o 

    endereço www.hslife.com.br, pelo e-mail contato@hslife.com.br ou escreva para 

    Hércules S. S. José, Av. Ministro Lafaeyte de Andrade, 1683 - Bl. 3 Apt 404, 

    Marco II - Nova Iguaçu, RJ, Brasil.

 --%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>

    <f:subview id="header">
        <jsp:include page="header.jsp"/>
    </f:subview>

            <h1 class="post-title"><a href="#" rel="bookmark">Atendimento</a></h1>

            <p>
            <center>
                <h:form id="frmAtendimento">
                    <h:messages style="font-weight: bold;"/>
                    <h:panelGrid columns="2" cellspacing="10" styleClass="jsfform" style="text-align: left">

                        Código do Atendimento:
                        <h:outputLabel value="#{AtendimentoMB.atendimento.id}"/>

                        Operador:
                        <h:outputLabel value="#{AtendimentoMB.atendimento.idOperador.nomeUsuario}"/>

                        Data de abertura:
                        <h:outputLabel value="#{AtendimentoMB.atendimento.dataAbertura}">
                            <f:convertDateTime pattern="dd/MM/yyyy HH:mm"/>
                        </h:outputLabel>

                        Data de encerramento:
                        <h:outputLabel value="#{AtendimentoMB.atendimento.dataEncerramento}">
                            <f:convertDateTime pattern="dd/MM/yyyy HH:mm"/>
                        </h:outputLabel>

                        Cliente:
                        <h:outputText value="#{AtendimentoMB.atendimento.idCliente.nomeCliente}"/>
                        
                        Setor:
                        <h:outputText value="#{AtendimentoMB.atendimento.idCliente.idSetor.descricao}"/>
                        
                        Situação do atendimento:
                        <h:selectOneMenu value="#{AtendimentoMB.idStatus}"required="true" requiredMessage="Selecione a situação do atendimento!" disabled="#{AtendimentoMB.atendimento.idStatus.encerra}">
                            <f:selectItem itemLabel="Selecione"/>
                            <f:selectItems value="#{AtendimentoMB.listaStatus}"/>
                        </h:selectOneMenu>

                        Tipo de suporte:
                        <h:selectOneMenu value="#{AtendimentoMB.idTipo}"required="true" requiredMessage="Selecione o tipo de atendimento!" disabled="#{AtendimentoMB.atendimento.idStatus.encerra}">
                            <f:selectItem itemLabel="Selecione"/>
                            <f:selectItems value="#{AtendimentoMB.listaTipo}"/>
                        </h:selectOneMenu>
                                                
                        Custo do atendimento:
                        <h:inputText value="#{AtendimentoMB.atendimento.custo}" converterMessage="Entre com valores numéricos para o campo 'Custo'!"
                                     disabled="#{AtendimentoMB.atendimento.idStatus.encerra}"/>
                                     
                        Prioridade:
                        <h:selectOneMenu value="#{AtendimentoMB.atendimento.prioridade}" disabled="#{AtendimentoMB.atendimento.idStatus.encerra}">
                            <f:selectItem itemLabel="Normal" itemValue="NORMAL"/>
                            <f:selectItem itemLabel="Baixa" itemValue="BAIXA"/>
                            <f:selectItem itemLabel="Alta" itemValue="ALTA"/>
                            <f:selectItem itemLabel="Urgente" itemValue="URGENTE"/>
                            <f:selectItem itemLabel="Imediato" itemValue="IMEDIATO"/>                            
                        </h:selectOneMenu>

                        Descrição do problema:
                        <h:inputTextarea value="#{AtendimentoMB.atendimento.problema}" cols="70" rows="5" required="true" requiredMessage="Informe o problema!"
                                         readonly="#{AtendimentoMB.atendimento.idStatus.encerra}"/>

                        Descrição da solução:
                        <h:inputTextarea value="#{AtendimentoMB.atendimento.solucao}" cols="70" rows="5"
                                         readonly="#{AtendimentoMB.atendimento.idStatus.encerra}"/>

                        Observações sobre o atendimento:
                        <h:inputTextarea value="#{AtendimentoMB.atendimento.observacao}" cols="70" rows="5"
                                         readonly="#{AtendimentoMB.atendimento.idStatus.encerra}"/>

                        <h:inputHidden value="#{AtendimentoMB.idCliente}"/>
                        <h:inputHidden />
                        <h:commandButton value="Registrar chamado" action="#{AtendimentoMB.registrarChamado}" style="padding: 3px 10px;" rendered="#{AtendimentoMB.isNovo}"
                                         disabled="#{AtendimentoMB.atendimento.idStatus.encerra}"/>
                        <h:commandButton value="Salvar alterações" action="#{AtendimentoMB.salvarAlteracoes}" style="padding: 3px 10px;" rendered="#{!AtendimentoMB.isNovo}"
                                         disabled="#{AtendimentoMB.atendimento.idStatus.encerra}"/>

                        <h:commandButton rendered="#{!AtendimentoMB.isNovo}" value="Imprimir OS" action="#{AtendimentoMB.imprimirOS}" style="padding: 3px 10px;"/>&nbsp;

                    </h:panelGrid>

                </h:form>
                <br/>

                <a href="listAtendimento.jsf"><< Voltar</a>
            </center>
            
   
    <f:subview id="footer">
        <jsp:include page="footer.jsp"/>
    </f:subview>
</f:view>