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
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Atendimento")
public class Atendimento implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 438948233026870254L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; // chave primária da tabela

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAbertura; // data de abertura do atendimento

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao; // data de encerramento do atendimento
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEncerramento; // data de encerramento do atendimento

    @Column(columnDefinition="text", nullable=false)
    private String problema; // descrição detalhada do problema

    @Column(columnDefinition="text")
    private String solucao; // descrição detalhada da solução

    @Column(columnDefinition="text")
    private String observacao; // observações sobre o atendimento

    @Column
    private Double custo; // custo do atendimento
    
    @Column
    private String prioridade; // define a prioridade do atendimento

    @ManyToOne
    @JoinColumn(name="idStatus")
    private Status idStatus; // chave estrangeira para o status do atendimento

    @ManyToOne
    @JoinColumn(name="idTipo")
    private Tipo idTipo; // chave estrangeira para o tipo de atendimento

    @ManyToOne
    @JoinColumn(name="idSetor", nullable=true)
    private Setor idSetor; // chave estrangeira para o setor

    @ManyToOne
    @JoinColumn(name="idCliente")
    private Cliente idCliente; // chave estrangeira indicando o cliente que solicitou o atendimento

    @ManyToOne
    @JoinColumn(name="idOperador")
    private Login idOperador; // chave estrangeira indicando o operador que abriu o atendimento
    
    @OneToMany(mappedBy="atendimento", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    @OrderBy("data")
    private List<HistoricoAtendimento> historico;

    public Atendimento(){        
       idStatus = new Status();
       idTipo = new Tipo();      
       idOperador = new Login();       
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the dataAbertura
     */
    public Date getDataAbertura() {
        return dataAbertura;
    }

    /**
     * @param dataAbertura the dataAbertura to set
     */
    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    /**
     * @return the dataEncerramento
     */
    public Date getDataEncerramento() {
        return dataEncerramento;
    }

    /**
     * @param dataEncerramento the dataEncerramento to set
     */
    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    /**
     * @return the problema
     */
    public String getProblema() {
        return problema;
    }

    /**
     * @param problema the problema to set
     */
    public void setProblema(String problema) {
        this.problema = problema;
    }

    /**
     * @return the solucao
     */
    public String getSolucao() {
        return solucao;
    }

    /**
     * @param solucao the solucao to set
     */
    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the custo
     */
    public Double getCusto() {
        return custo;
    }

    /**
     * @param custo the custo to set
     */
    public void setCusto(Double custo) {
        this.custo = custo;
    }

    /**
     * @return the idStatus
     */
    public Status getIdStatus() {
        return idStatus;
    }

    /**
     * @param idStatus the idStatus to set
     */
    public void setIdStatus(Status idStatus) {
        this.idStatus = idStatus;
    }

    /**
     * @return the idTipo
     */
    public Tipo getIdTipo() {
        return idTipo;
    }

    /**
     * @param idTipo the idTipo to set
     */
    public void setIdTipo(Tipo idTipo) {
        this.idTipo = idTipo;
    }

    /**
     * @return the idSetor
     */
    public Setor getIdSetor() {
        return idSetor;
    }

    /**
     * @param idSetor the idSetor to set
     */
    public void setIdSetor(Setor idSetor) {
        this.idSetor = idSetor;
    }

    /**
     * @return the idCliente
     */
    public Cliente getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the idOperador
     */
    public Login getIdOperador() {
        return idOperador;
    }

    /**
     * @param idOperador the idOperador to set
     */
    public void setIdOperador(Login idOperador) {
        this.idOperador = idOperador;
    }

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public List<HistoricoAtendimento> getHistorico() {
		return historico;
	}

	public void setHistorico(List<HistoricoAtendimento> historico) {
		this.historico = historico;
	}
}
