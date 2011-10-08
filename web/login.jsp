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
    Document   : index
    Created on : 15/11/2010, 08:02:11
    Author     : Hércules
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
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
                <div id="wrapper">
                    <div id="content">
                        <div class="post" id="post-01">
                            <center>
                                <img src="images/support.jpg" alt="logo" width="320" height="240"/>
                                <br/><br/>
                                <h3>Para acessar informe seu login e senha</h3>

                                <h:form id="frmLogin">
                                    <h:messages/>
                                    <h:panelGrid columns="2" cellspacing="10" styleClass="jsfform">

                                        Login:
                                        <h:inputText value="#{LoginMB.usuario.usuarioLogin}"required="true" requiredMessage="Informe o seu login."/>

                                        Senha:
                                        <h:inputSecret value="#{LoginMB.usuario.usuarioSenha}"required="true" requiredMessage="Informe a sua senha."/>

                                        <br/>
                                        <h:commandButton value="Entrar no Sistema" action="#{LoginMB.efetuarLogin}" style="padding: 3px 10px;"/>

                                    </h:panelGrid>
                                </h:form>

                            </center>
                            <br/>
                        </div>
                    </div>
                </div>
                <div id="footer">
                    <p align="center">
			CATU v1.0 &copy; 2010. Todos os direitos reservado. Desenvolvido por <a href="http://hslife.com.br/">HSlife</a>.
                    </p>
                </div>
            </div>
        </body>
    </html>
</f:view>
