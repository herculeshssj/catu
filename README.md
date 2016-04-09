CATU
====

Controle de Atendimento Técnico ao Usuário
------------------------------------------

Sistema para registro de atendimentos a usuários de uma empresa, loja ou prestadora de serviço. 
Esta versão inclui os recursos básicos para registrar os atendimentos, emitir a ordem de serviço e cadastrar os clientes. 

Tecnologias usadas: JavaServer Faces e Hibernate, rodando sobre o MySQL.

### Requisitos:

* Java JDK SE 6 ou superior;
* Eclipse Helios 3.6 ou superior;
* Tomcat 6.0.25 ou superior;
* MySQL 5.0 ou superior;
* Windows ou Linux, qualquer versão capaz de rodar os softwares acima.

### Links de download:

* *Java JDK SE 6*: http://www.oracle.com/technetwork/java/javase/downloads/index.html
* *Eclipse Helios*: http://www.eclipse.org/downloads
* *Tomcat 7*: http://tomcat.apache.org/download-70.cgi
* *MySQL 5.5*: http://dev.mysql.com/downloads/mysql/5.6.html

### Configuração do MySQL:

Após instalar o MySQL, acesse via console ou usando uma ferramenta gráfica de administração e execute os seguintes comandos:

```sql
-- Criação da base de dados
create database catu;

-- Criação do usuário para acessar a base
create user 'catu'@'localhost' identified by 'c4tvc4cik3';
grant all privileges on catu.* to 'catu'@'localhost' with grant option;
```

Dentro do projeto, no pacote **br.com.hslife.catu.db**, execute os arquivos **script.sql** e **update-script.sql** para realizar a criação das tabelas da base.