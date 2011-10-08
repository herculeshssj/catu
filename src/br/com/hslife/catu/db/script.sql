-- Exclusão das constraints

alter table Atendimento drop constraint FK4654DB0366D5560;
alter table Atendimento drop constraint FK4654DB0D1FA5E77;
alter table Atendimento drop constraint FK4654DB0F47FEDBB;
alter table Atendimento drop constraint FK4654DB09B5E1697;
alter table Atendimento drop constraint FK4654DB0B6101FC1;
alter table Cliente drop constraint FK96841DDA9B5E1697;
alter table Cliente drop constraint FK96841DDA6E043565;
alter table Historico drop constraint FKDE4AFFD09AA181DF;
alter table Maquina drop constraint FK95350E049B5E1697;
alter table Maquina drop constraint FK95350E04B6101FC1;
alter table SoftwareInstalado drop constraint FKC73F814B3720015;
alter table SoftwareInstalado drop constraint FKC73F8143CF02921;

-- Exclusão das tabelas

drop table Atendimento;
drop table Cliente;
drop table Endereco;
drop table Historico;
drop table Login;
drop table Maquina;
drop table Setor;
drop table Software;
drop table SoftwareInstalado;
drop table Status;
drop table Tipo;

-- Criação das tabelas

create table Atendimento (id  bigserial not null, custo float8, dataAbertura timestamp, dataAlteracao timestamp, dataEncerramento timestamp, observacao text, prioridade varchar(255), problema text not null, solucao text, idCliente int8, idOperador int8, idSetor int8, idStatus int8, idTipo int8, primary key (id));
create table Cliente (id  bigserial not null, celular varchar(15), email varchar(30), nomeCliente varchar(100) not null, telefone varchar(15), idEndereco int8, idSetor int8, primary key (id));
create table Endereco (id  bigserial not null, bairro varchar(50) not null, cep varchar(8) not null, cidade varchar(50) not null, complemento varchar(50), logradouro varchar(150) not null, numero varchar(10), tipoLogradouro varchar(15) not null, uf varchar(2) not null, primary key (id));
create table Historico (id  bigserial not null, custo float8, dataAbertura timestamp, dataAlteracao timestamp, dataEncerramento timestamp, idAtendimento int8, idCliente int8, idOperador int8, idSetor int8, idStatus int8, idTipo int8, observacao text, prioridade varchar(255), problema text, solucao text, tipoAlteracao varchar(255), idLogin int8, primary key (id));
create table Login (id  bigserial not null, ativo bool not null, dataCriacao timestamp, nomeUsuario varchar(100) not null, perfil varchar(15) not null, usuarioLogin varchar(50) not null unique, usuarioSenha varchar(40) not null, primary key (id));
create table Maquina (id  bigserial not null, cdDvd varchar(255), emUso bool, estabilizador varchar(255), hd varchar(255), impressora varchar(255), ip varchar(255), macAddress varchar(255), memoria varchar(255), monitor varchar(255), mouse varchar(255), nivel int4, numLacre varchar(20) unique, numPatrEstabilizador varchar(255), numPatrImpressora varchar(255), numPatrMonitor varchar(255), numPatrScanner varchar(255), numPatrimonial varchar(20) unique, parecer text, placaRede varchar(255), placaSom varchar(255), placaVideo varchar(255), processador varchar(255), scanner varchar(255), tamanhoMonitor varchar(255), teclado varchar(255), tipoMonitor varchar(255), idCliente int8, idSetor int8, primary key (id));
create table Setor (id  bigserial not null, descricao varchar(100) not null, responsavel varchar(50) not null, primary key (id));
create table Software (id  bigserial not null, descricao varchar(255), emUso bool, licenca varchar(255), nome varchar(50) not null, primary key (id));
create table SoftwareInstalado (idSoftware int8 not null, idMaquina int8 not null);
create table Status (id  bigserial not null, descricao varchar(100) not null, encerra bool, primary key (id));
create table Tipo (id  bigserial not null, descricao varchar(100) not null, externo bool, primary key (id));


-- Criação das contraints

alter table Atendimento add constraint FK4654DB0366D5560 foreign key (idOperador) references Login;
alter table Atendimento add constraint FK4654DB0D1FA5E77 foreign key (idStatus) references Status;
alter table Atendimento add constraint FK4654DB0F47FEDBB foreign key (idTipo) references Tipo;
alter table Atendimento add constraint FK4654DB09B5E1697 foreign key (idSetor) references Setor;
alter table Atendimento add constraint FK4654DB0B6101FC1 foreign key (idCliente) references Cliente;
alter table Cliente add constraint FK96841DDA9B5E1697 foreign key (idSetor) references Setor;
alter table Cliente add constraint FK96841DDA6E043565 foreign key (idEndereco) references Endereco;
alter table Historico add constraint FKDE4AFFD09AA181DF foreign key (idLogin) references Login;
alter table Maquina add constraint FK95350E049B5E1697 foreign key (idSetor) references Setor;
alter table Maquina add constraint FK95350E04B6101FC1 foreign key (idCliente) references Cliente;
alter table SoftwareInstalado add constraint FKC73F814B3720015 foreign key (idMaquina) references Maquina;
alter table SoftwareInstalado add constraint FKC73F8143CF02921 foreign key (idSoftware) references Software;

-- Inclusão do usuário Admin

INSERT INTO login (ativo, datacriacao, nomeusuario, perfil, usuariologin, usuariosenha) VALUES (true, '2011-01-01 00:00:00', 'Administrador do Sistema', 'ADMIN', 'admin', 'f865b53623b121fd34ee5426c792e5c33af8c227');