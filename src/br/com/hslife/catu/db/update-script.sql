/***

    Copyright (c) 2010, 2011, 2014 Hércules S. S. José


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

-- Script de atualização das tabelas

/*** Versão 1.2 ***/

-- Tabela HistoricoAtendimento
create table HistoricoAtendimento (
	id bigint not null auto_increment,
	idAtendimento bigint not null,
	descricao text not null,
	data datetime,
	idLogin bigint not null,
	primary key(id)
);

alter table HistoricoAtendimento  add constraint fk_atendimento_historicoatendimento foreign key(idAtendimento) references Atendimento(id);
alter table HistoricoAtendimento  add constraint fk_login_historicoatendimento foreign key(idLogin) references Login(id);