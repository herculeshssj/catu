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
public class Tipo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // chave primária da tabela

    @Column(length = 100, nullable = false)
    private String descricao; // descrição do tipo de atendimento

    @Column
    private Boolean externo; // diz se o atendimento é externo à empresa

    public Tipo() {
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
     * @return the externo
     */
    public Boolean getExterno() {
        return externo;
    }

    /**
     * @param externo the externo to set
     */
    public void setExterno(Boolean externo) {
        this.externo = externo;
    }
    
}
