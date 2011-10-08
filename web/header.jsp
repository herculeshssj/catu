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
                                    <li><a href="listSoftware.jsf">Software</a></li>
                                    <li><a href="listMaquina.jsf">Máquinas</a></li>
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
            

    <div id="content">

        <div class="post" id="post-01">

	<div class="post-info">

                <strong><h:outputText value="#{LoginMB.usuarioLogado.nomeUsuario}"/>, seja bem vindo!</strong>

            </div>
