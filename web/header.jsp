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

<%-- 
    Document   : header
    Created on : 28/10/2010, 15:06:49
    Author     : Hércules
--%>
 

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<html>

    <head>
        <title>C A T U - Controle de Atendimento Técnico ao Usuário</title>

        <link rel="stylesheet" href="style.css" type="text/css" media="screen" />
        <!--[if IE]><link rel="stylesheet" type="text/css" href="ie.css" media="screen" /><![endif]-->
    </head>

    <body>

        <div id="container">

            <div id="header">
                <h1><a href="/">C A T U</a><span>Controle de Atendimento Técnico ao Usuário</span></h1>
                
            </div>

            <!-- Código do menu flutuante -->
            <f:subview id="header">
                <h:form>
                    <div class="menu">

                        <ul>
                            <li><a href="#" >Cadastro</a>
                                <ul>
                                    <li><a href="listLogin.jsf">Usuários</a></li>                                    
                                    <li><a href="listCliente.jsf">Clientes</a></li>
                                    <li><a href="listSetor.jsf">Setores</a></li>
                                    <li><a href="listTipo.jsf">Tipos de atendimento</a></li>                                    
                                    <li><a href="listStatus.jsf">Status dos atendimentos</a></li>
                                    
                                </ul>
                            </li>
                            <li><a href="#">Atendimentos</a>
                                <ul>
                                    <li><a href="listSeleciona.jsf">Novo</a></li>
                                    <li><a href="listAtendimento.jsf">Pesquisar</a></li>
                                </ul>
                            </li>                            
                            <li><a href="#">Configurações</a>
                                <ul>
                                    <li><h:commandLink value="Alterar Senha" action="#{LoginMB.editarSenha}"/></li>
                                </ul>
                            </li>
                            <li><h:commandLink action="#{LoginMB.efetuarLogoff}" value="Sair"/></li>
                        </ul>
                    </div>
                </h:form>
            </f:subview>
            <!-- Fim do Código do menu flutuante -->

            <div id="wrapper">
