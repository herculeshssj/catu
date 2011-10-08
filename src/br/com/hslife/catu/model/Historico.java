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
@Table(name="Historico")
public class Historico implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; // chave primária da tabela
    
    private Long idAtendimento; // id do atendimento incluído/alterado

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAbertura; // data de abertura do atendimento

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao; // data de encerramento do atendimento
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEncerramento; // data de encerramento do atendimento

    @Column(columnDefinition="text")
    private String problema; // descrição detalhada do problema

    @Column(columnDefinition="text")
    private String solucao; // descrição detalhada da solução

    @Column(columnDefinition="text")
    private String observacao; // observações sobre o atendimento

    @Column
    private Double custo; // custo do atendimento
    
    @Column
    private String prioridade; // define a prioridade do atendimento

    @Column
    private Long idStatus; // chave estrangeira para o status do atendimento

    @Column
    private Long idTipo; // chave estrangeira para o tipo de atendimento

    @Column
    private Long idSetor; // chave estrangeira para o setor

    @Column
    private Long idCliente; // chave estrangeira indicando o cliente que solicitou o atendimento

    @Column
    private Long idOperador; // chave estrangeira indicando o operador que abriu o atendimento
    
    @ManyToOne
    @JoinColumn(name="idLogin")
    private Login idLogin; // login do usuário que realizou a última alteração no atendimento
    
    @Column
    private String tipoAlteracao; // tipo de alteração realizada (inclusao, alteracao)

    public Historico(){        
        idLogin = new Login();       
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

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	public Long getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(Long idSetor) {
		this.idSetor = idSetor;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Long idOperador) {
		this.idOperador = idOperador;
	}

	public Login getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(Login idLogin) {
		this.idLogin = idLogin;
	}

	public String getTipoAlteracao() {
		return tipoAlteracao;
	}

	public void setTipoAlteracao(String tipoAlteracao) {
		this.tipoAlteracao = tipoAlteracao;
	}

	public Long getIdAtendimento() {
		return idAtendimento;
	}

	public void setIdAtendimento(Long idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

}
