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


            <h1 class="post-title"><a href="#" rel="bookmark">Setores</a></h1>

            <h:form id="lstSetor">
                <strong><h:commandLink value="Novo setor" action="#{SetorMB.cadastrar}"/></strong>
                <br/><br/>
                <h:messages/>
                Informe o setor:
                <h:inputText value="#{SetorMB.busca}"/>
                &nbsp;
                <h:commandButton value="Pesquisar" action="#{SetorMB.pesquisar}"  style="padding: 3px 10px;"/>
                <br/><br/>
                
                <h:dataTable id="tableSetors" value="#{SetorMB.listaSetors}" var="item" width="100%" border="1" style="text-align:center" styleClass="jsflist">
                    <f:facet name="header">
                        <h:outputText value="Setores cadastrados"/>
                    </f:facet>                    
                     <h:column>
                        <f:facet name="header">
                            <h:outputText value="Setor"/>
                        </f:facet>
                         <h:outputText value="#{item.descricao}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Responsável"/>
                        </f:facet>
                         <h:outputText value="#{item.responsavel}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Editar"/>
                        </f:facet>
                        <h:commandLink value="Editar" action="#{SetorMB.editar}">
                            <f:setPropertyActionListener target="#{SetorMB.idSetor}" value="#{item.id}"/>
                        </h:commandLink>                        
                    </h:column>

                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Excluir"/>
                        </f:facet>
                        <h:commandLink value="Excluir" action="#{SetorMB.excluir}" onclick="javascript:return(confirm('Deseja apagar este registro?'))">
                            <f:setPropertyActionListener target="#{SetorMB.idSetor}" value="#{item.id}"/>
                        </h:commandLink>
                    </h:column>
                </h:dataTable>
                   <br/>
                <strong><h:commandLink value="Novo setor" action="#{SetorMB.cadastrar}"/></strong>
            </h:form>
            

    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>

</f:view>