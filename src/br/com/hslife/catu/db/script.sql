/* 

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

*/ 

-- Exclusão das constraints

alter table Atendimento drop foreign key FK4654DB0366D5560;
alter table Atendimento drop foreign key FK4654DB0D1FA5E77;
alter table Atendimento drop foreign key FK4654DB0F47FEDBB;
alter table Atendimento drop foreign key FK4654DB09B5E1697;
alter table Atendimento drop foreign key FK4654DB0B6101FC1;
alter table Cliente drop foreign key FK96841DDA6E043565;

-- Exclusão das tabelas

drop table if exists Atendimento;
drop table if exists Cliente;
drop table if exists Endereco;
drop table if exists Login;
drop table if exists Setor;
drop table if exists Status;
drop table if exists Tipo;

-- Criação das tabelas

create table Atendimento (id bigint not null auto_increment, custo double precision, dataAbertura datetime, dataEncerramento datetime, observacao text, problema text not null, solucao text, idCliente bigint, idOperador bigint, idSetor bigint, idStatus bigint, idTipo bigint, primary key (id));
create table Cliente (id bigint not null auto_increment, celular varchar(15), email varchar(30), nomeCliente varchar(100) not null, telefone varchar(15), idEndereco bigint, primary key (id));
create table Endereco (id bigint not null auto_increment, bairro varchar(50) not null, cep varchar(8) not null, cidade varchar(50) not null, complemento varchar(50), logradouro varchar(150) not null, numero varchar(10), tipoLogradouro varchar(15) not null, uf varchar(2) not null, primary key (id));
create table Login (id bigint not null auto_increment, ativo bit not null, dataCriacao datetime, nomeUsuario varchar(100) not null, perfil varchar(15) not null, usuarioLogin varchar(50) not null unique, usuarioSenha varchar(40) not null, primary key (id));
create table Setor (id bigint not null auto_increment, descricao varchar(100) not null, responsavel varchar(50) not null, primary key (id));
create table Status (id bigint not null auto_increment, descricao varchar(100) not null, encerra bit, primary key (id));
create table Tipo (id bigint not null auto_increment, descricao varchar(100) not null, externo bit, primary key (id));

-- Criação das contraints

alter table Atendimento add index FK4654DB0366D5560 (idOperador), add constraint FK4654DB0366D5560 foreign key (idOperador) references Login (id);
alter table Atendimento add index FK4654DB0D1FA5E77 (idStatus), add constraint FK4654DB0D1FA5E77 foreign key (idStatus) references Status (id);
alter table Atendimento add index FK4654DB0F47FEDBB (idTipo), add constraint FK4654DB0F47FEDBB foreign key (idTipo) references Tipo (id);
alter table Atendimento add index FK4654DB09B5E1697 (idSetor), add constraint FK4654DB09B5E1697 foreign key (idSetor) references Setor (id);
alter table Atendimento add index FK4654DB0B6101FC1 (idCliente), add constraint FK4654DB0B6101FC1 foreign key (idCliente) references Cliente (id);
alter table Cliente add index FK96841DDA6E043565 (idEndereco), add constraint FK96841DDA6E043565 foreign key (idEndereco) references Endereco (id);

-- Inclusão do usuário Admin

INSERT INTO Login (ativo, datacriacao, nomeusuario, perfil, usuariologin, usuariosenha) VALUES (true, "2010-01-01 00:00:00.000", "Administrador do Sistema", "ADMIN", "admin", "f865b53623b121fd34ee5426c792e5c33af8c227"); -- Senha: admin123