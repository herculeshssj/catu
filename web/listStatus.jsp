<%--

    Copyright (c) 2010-2014 Hércules S. S. José



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
<%@taglib prefix="rich" uri="http://richfaces.org/rich" %>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>

    <f:subview id="cabecalho">
        <jsp:include page="header.jsp"/>
    </f:subview>

            <h1 class="post-title"><a href="#" rel="bookmark">Status do Atendimento</a></h1>

            <h:form id="lstStatus">
                <strong><h:commandLink value="Novo status" action="#{StatusMB.cadastrar}"/></strong>
                <br/><br/>
                <h:messages/>
                Informe o status:
                <h:inputText value="#{StatusMB.busca}"/>
                &nbsp;
                <h:commandButton value="Pesquisar" action="#{StatusMB.pesquisar}"  style="padding: 3px 10px;"/>
                <br/><br/>                
                <rich:dataTable id="tableStatus" value="#{StatusMB.listaStatus}" var="item" rows="15" width="100%" border="1" style="text-align:center" styleClass="jsflist">
                    <f:facet name="header">
                        <h:outputText value="Status cadastrados"/>
                    </f:facet>                    
                     <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Status"/>
                        </f:facet>
                         <h:outputText value="#{item.descricao}"/>
                    </rich:column>
                    <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Encerra atendimento"/>
                        </f:facet>
                        <h:outputText value="#{item.encerra}">
                            <f:converter converterId="simnaoconverter"/>
                        </h:outputText>
                    </rich:column>
                    <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Editar"/>
                        </f:facet>
                        <h:commandLink value="Editar" action="#{StatusMB.editar}">
                            <f:setPropertyActionListener target="#{StatusMB.idStatus}" value="#{item.id}"/>
                        </h:commandLink>                        
                    </rich:column>
                    <rich:column>
                        <f:facet name="header">
                            <h:outputText value="Excluir"/>
                        </f:facet>
                        <h:commandLink value="Excluir" action="#{StatusMB.excluir}" onclick="javascript:return(confirm('Deseja apagar este registro?'))">
                            <f:setPropertyActionListener target="#{StatusMB.idStatus}" value="#{item.id}"/>
                        </h:commandLink>
                    </rich:column>
                </rich:dataTable>
                <rich:datascroller id="dsStatus" for="tableStatus"></rich:datascroller>
                    <br/>
                    <strong><h:commandLink value="Novo status" action="#{StatusMB.cadastrar}"/></strong>
            </h:form>
            
  
    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>

</f:view>