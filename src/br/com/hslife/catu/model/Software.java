package br.com.hslife.catu.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Software")
public class Software {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // chave primária da tabela

	@Column(length = 50, nullable = false)
	private String nome; // nome do software

	@Column
	private String descricao; // descrição/detalhes do software

	@Column
	private String licenca; // licença do software

	@Column
	private Boolean emUso; // informa se o software ainda é utilizado na OM

	@ManyToMany
	@JoinTable(name = "SoftwareInstalado", joinColumns = @JoinColumn(name = "idSoftware"), inverseJoinColumns = @JoinColumn(name = "idMaquina"))
	private List<Maquina> maquinas;

	public Software() {
		maquinas = new ArrayList<Maquina>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLicenca() {
		return licenca;
	}

	public void setLicenca(String licenca) {
		this.licenca = licenca;
	}

	public List<Maquina> getMaquinas() {
		return maquinas;
	}

	public void setMaquinas(List<Maquina> maquinas) {
		this.maquinas = maquinas;
	}

	public Boolean getEmUso() {
		return emUso;
	}

	public void setEmUso(Boolean emUso) {
		this.emUso = emUso;
	}

}
