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
@Table
public class Atendimento implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; // chave primária da tabela

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAbertura; // data de abertura do atendimento

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

    @ManyToOne
    @JoinColumn(name="idStatus")
    private Status idStatus; // chave estrangeira para o status do atendimento

    @ManyToOne
    @JoinColumn(name="idTipo")
    private Tipo idTipo; // chave estrangeira para o tipo de atendimento

    @ManyToOne
    @JoinColumn(name="idSetor")
    private Setor idSetor; // chave estrangeira para o setor

    @ManyToOne
    @JoinColumn(name="idCliente")
    private Cliente idCliente; // chave estrangeira indicando o cliente que solicitou o atendimento

    @ManyToOne
    @JoinColumn(name="idOperador")
    private Login idOperador; // chave estrangeira indicando o operador que abriu o atendimento

    public Atendimento(){        
       idStatus = new Status();
       idTipo = new Tipo();
       idSetor = new Setor();       
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

}
