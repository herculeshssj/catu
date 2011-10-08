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
			CATU v2.0 &copy; 2010. Todos os direitos reservado. Desenvolvido por <a href="http://hslife.com.br/">HSlife</a>.
                    </p>
                </div>
            </div>
        </body>
    </html>
</f:view>
