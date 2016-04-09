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


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<f:view>

    <f:subview id="cabecalho">
        <jsp:include page="header.jsp"/>
    </f:subview>

            <h1 class="post-title"><a href="#" rel="bookmark">Clientes</a></h1>


            <center>
                <h:form id="frmCliente">
                    <h:messages style="font-weight: bold;"/>
                    <h:panelGrid columns="2" cellspacing="10" styleClass="jsfform"  style="text-align: left">

                        Nome do cliente:
                        <h:inputText value="#{ClienteMB.cliente.nomeCliente}" required="true" requiredMessage="Informe o nome da cliente!" maxlength="100" size="50"/>
                        
                        Setor:
                        <h:selectOneMenu value="#{ClienteMB.idSetor}" required="true" requiredMessage="Selecione o setor!">
                            <f:selectItem itemLabel="Selecione"/>
                            <f:selectItems value="#{ClienteMB.listaSetor}"/>
                        </h:selectOneMenu>

                        Tipo de logradouro:
                        <h:selectOneMenu value="#{ClienteMB.endereco.tipoLogradouro}">                            
                            <f:selectItems value="#{ClienteMB.tipoLogradouro}"/>
                        </h:selectOneMenu>

                        Logradouro:
                        <h:inputText value="#{ClienteMB.endereco.logradouro}" size="50" maxlength="100"/>

                        Número:
                        <h:inputText value="#{ClienteMB.endereco.numero}" size="5" maxlength="5" />

                        Complemento:
                        <h:inputText value="#{ClienteMB.endereco.complemento}" maxlength="50"/>

                        Bairro:
                        <h:inputText value="#{ClienteMB.endereco.bairro}" maxlength="50"/>

                        Cidade:
                        <h:inputText value="#{ClienteMB.endereco.cidade}" maxlength="50"/>

                        Estado:
                        <h:selectOneMenu value="#{ClienteMB.endereco.uf}">
                            <f:selectItems value="#{ClienteMB.estados}"/>
                        </h:selectOneMenu>

                        CEP (sem hífen):
                        <h:inputText value="#{ClienteMB.endereco.cep}" size="8" maxlength="8">
                            <f:validateLength minimum="8"/>
                        </h:inputText>

                        Telefone:
                        <h:inputText value="#{ClienteMB.cliente.telefone}" maxlength="15"/>

                        Celular:
                        <h:inputText value="#{ClienteMB.cliente.celular}" maxlength="15"/>

                        E-Mail:
                        <h:inputText value="#{ClienteMB.cliente.email}" maxlength="30">
                            <f:validator validatorId="emailvalidate"/>
                        </h:inputText>

                        <h:inputHidden value="#{ClienteMB.idEndereco}"/>
                        <h:inputHidden value="#{ClienteMB.cliente.id}"/>

                        <h:inputHidden />
                        <h:commandButton id="btnSalvar" style="padding: 3px 10px;" value="Salvar" action="#{ClienteMB.salvar}"/>
                    </h:panelGrid>


                </h:form>
                <br/>

                <a href="listCliente.jsf"><< Voltar</a>
            </center>
            

    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>


</f:view>
