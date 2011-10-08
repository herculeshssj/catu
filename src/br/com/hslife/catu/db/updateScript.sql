-- Este script realiza a atualização da base para a versão 2.0

-- Define um setor padrão para atribuir aos clientes
insert into Setor(descricao,responsavel) values ('DEFAULT','ADMIN');

-- Insere a coluna idSetor na tabela Cliente
alter table Cliente add column idSetor int8 set null;

-- Define todos os clientes com o setor DEFAULT
update Cliente set idSetor = (select id from Setor where descricao = "DEFAULT");

-- Define a constraint para idSetor na tabela Cliente
alter table Cliente add constraint FK96841DDA9B5E1697 foreign key (idSetor) references Setor (id);

-- Adiciona as colunas dataAlteracao e prioridade na tabela Atendimento
alter table Atendimento add column dataAlteracao timestamp set '2011-09-01 00:00:00';
alter table Atendimento add column prioridade varchar(255) set 'NORMAL';

-- Cria a tabela Histórico
create table Historico (id  bigserial not null, custo float8, dataAbertura timestamp, dataAlteracao timestamp, dataEncerramento timestamp, idAtendimento int8, idCliente int8, idOperador int8, idSetor int8, idStatus int8, idTipo int8, observacao text, prioridade varchar(255), problema text, solucao text, tipoAlteracao varchar(255), idLogin int8, primary key (id));

-- Define as constraints da tabela Historico
alter table Historico add constraint FKDE4AFFD09AA181DF foreign key (idLogin) references Login;

-- Cria as tabelas Maquina e Software
create table Maquina (id  bigserial not null, cdDvd varchar(255), emUso bool, estabilizador varchar(255), hd varchar(255), impressora varchar(255), ip varchar(255), macAddress varchar(255), memoria varchar(255), monitor varchar(255), mouse varchar(255), nivel int4, numLacre varchar(20) unique, numPatrEstabilizador varchar(255), numPatrImpressora varchar(255), numPatrMonitor varchar(255), numPatrScanner varchar(255), numPatrimonial varchar(20) unique, parecer text, placaRede varchar(255), placaSom varchar(255), placaVideo varchar(255), processador varchar(255), scanner varchar(255), tamanhoMonitor varchar(255), teclado varchar(255), tipoMonitor varchar(255), idCliente int8, idSetor int8, primary key (id));
create table Software (id  bigserial not null, descricao varchar(255), emUso bool, licenca varchar(255), nome varchar(50) not null, primary key (id));
create table SoftwareInstalado (idSoftware int8 not null, idMaquina int8 not null);

-- Define as constraints das tabelas Maquina e Software
alter table Maquina add constraint FK95350E049B5E1697 foreign key (idSetor) references Setor;
alter table Maquina add constraint FK95350E04B6101FC1 foreign key (idCliente) references Cliente;
alter table SoftwareInstalado add constraint FKC73F814B3720015 foreign key (idMaquina) references Maquina;
alter table SoftwareInstalado add constraint FKC73F8143CF02921 foreign key (idSoftware) references Software;