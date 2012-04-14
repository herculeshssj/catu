/***

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

 ***/



 /*** INSTRUÇÕES DE INSTALAÇÃO ***/

Requisitos:

- (1) Java JRE 6 ou superior;
- (2) Tomcat 6 ou superior;
- (3) MySQL 5 ou superior;

Instalação:

	Efetue a instalação dos requisitos acima na ordem mostrada. Após concluir a instalação do MySQL, crie uma base de dados chamada "catu". Descompacte o arquivo ZIP e rode o arquivo "script.sql" no MySQL (pode ser via linha de comando, via utilitário gráfico ou via phpMyAdmin).

	Copie o arquivo CATU.war para a pasta "webapps" do Tomcat (TOMCAT_DIR\webapps). Inicie ou reinicie o Tomcat para efetuar o deploy da aplicação.

	Vá no diretório CATU/WEB-INF/classes/br/com/hslife/catu/db e edite as seguintes linhas do arquivo "hibernate.cfg.xml":

	- Linha 
		<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/catu</property>
	  substitua "localhost" pelo endereço do seu servidor MySQL. Se o MySQL for local, não precise efetuar a alteração;

	- Linha
		<property name="hibernate.connection.username">root</property>
	  substitua "root" pelo login do usuário MySQL configurado para acessar a base "catu";

	- Linha
		<property name="hibernate.connection.password">root</property>
	  substitua "root" pela senha do usuário MySQL configurado para acessar a base "catu".

	Reinicie o Tomcat e acesse o sistema pelo endereço http://localhost:8080/CATU/. Caso o servidor Tomcat esteja em outro local e respondendo por outra porta, substitua o "localhost:8080" pelo endereço do servidor com sua respectiva porta.

	A senha inicial do login "admin" é "admin123".

Configuração do sistema:

	Após iniciado siga estes passso para começar o uso normal do sistema:

	1 - Cadastrar os status dos atendimentos (exemplo: aberto, em andamento, fechado, cancelado, etc.);
	2 - Cadastrar os tipos de atendimento (exemplo: suporte, reparo, visita técnica, substituição, etc.);
	3 - Cadastrar os setores (exemplo: comercial, RH, financeiro, etc.).

Contato:

	Informações para suporte, correções de bugs, novas funcionalidades e participação no projeto entre em contato pelo e-mail contato@hslife.com.br.
