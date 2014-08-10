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

package br.com.hslife.catu.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="HistoricoAtendimento")
public class HistoricoAtendimento implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -602546129962860546L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; // chave primária da tabela

    @Temporal(TemporalType.TIMESTAMP)
    private Date data; // data que o registro foi feito

    @Column(columnDefinition="text", nullable=false)
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name="idAtendimento")
    private Atendimento atendimento; // chave estrangeira indicando o atendimento
    
    @ManyToOne
    @JoinColumn(name="idLogin")
    private Login login; // chave estrangeira indicando o operador que registrou o histórico
    

    public HistoricoAtendimento(){        
    	data = new Date();
    }

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Atendimento getAtendimento() {
		return atendimento;
	}


	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}


	public Login getLogin() {
		return login;
	}


	public void setLogin(Login login) {
		this.login = login;
	}
}
