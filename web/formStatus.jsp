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

    <f:subview id="cabecalho">
        <jsp:include page="header.jsp"/>
    </f:subview>


            <h1 class="post-title"><a href="#" rel="bookmark">Status do Atendimento</a></h1>


            <center>
                <h:form id="frmStatus">
                    <h:messages style="font-weight: bold;"/>
                    <h:panelGrid columns="2" cellspacing="10" styleClass="jsfform" style="text-align: left">

                        Descrição:
                        <h:inputText id="txtDescricao" value="#{StatusMB.status.descricao}" required="true" requiredMessage="Informe o status!" size="50" maxlength="100"/>

                        Encerra o atendimento:
                        <h:selectBooleanCheckbox id="txtEncerra" value="#{StatusMB.status.encerra}"/>

                        <h:inputHidden id="txtId" value="#{StatusMB.status.id}"/>
                        <h:commandButton id="btnSalvar" style="padding: 3px 10px;" value="Salvar" action="#{StatusMB.salvar}"/>
                    </h:panelGrid>


                </h:form>
                <br/>

                <a href="listStatus.jsf"><< Voltar</a>
            </center>
            

    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>



</f:view>
