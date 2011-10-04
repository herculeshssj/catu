package br.com.hslife.catu.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;  // chave primária da tabela

    @Column(length=100, nullable=false)
    private String descricao; // descrição do status do atendimento

    @Column
    private Boolean encerra; // diz se o status escolhido encerra o atendimento

    public Status() {
        encerra = false;
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the encerra
     */
    public Boolean getEncerra() {
        return encerra;
    }

    /**
     * @param encerra the encerra to set
     */
    public void setEncerra(Boolean encerra) {
        this.encerra = encerra;
    }

}
