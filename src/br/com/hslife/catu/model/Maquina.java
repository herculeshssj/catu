/***

    Copyright (c) 2010-2014 Hércules S. S. José



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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 *
 * @author Hércules
 */
@Entity
@Table(name="Maquina")
public class Maquina {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(length=20, unique=true)
    private String numLacre;

    @Column(length=20, unique=true)
    private String numPatrimonial;

    @OneToOne
    @JoinColumn(name="idCliente", nullable = true)    
    private Cliente cliente;

    @Column
    private String processador;

    @Column
    private String memoria;

    @Column
    private String hd;
    
    @Column
    private String cdDvd;
    
    @Column
    private String monitor;

    @Column
    private String tipoMonitor;

    @Column
    private String tamanhoMonitor;
    
    @Column
    private String numPatrMonitor;
    
    @Column
    private String placaVideo;
    
    @Column
    private String placaSom;
    
    @Column
    private String placaRede;
    
    @Column
    private String teclado;
    
    @Column
    private String mouse;
    
    @Column
    private String impressora;
    
    @Column 
    private String numPatrImpressora;
    
    @Column
    private String scanner;
    
    @Column
    private String numPatrScanner;
    
    @Column
    private String estabilizador;
    
    @Column
    private String numPatrEstabilizador;

    @Column
    private Integer nivel;
    
    @Column
    private String ip;
    
    @Column
    private String macAddress;
    
    @Column
    private Boolean emUso;

    @ManyToOne
    @JoinColumn(name="idSetor", nullable=true)
    private Setor setor;

    @Column(columnDefinition="text")
    private String parecer;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SoftwareInstalado", joinColumns = @JoinColumn(name = "idMaquina"),
        inverseJoinColumns = @JoinColumn(name = "idSoftware"))
    @Fetch(FetchMode.SELECT)
    private List<Software> softwares;

    public Maquina() {
    	softwares = new ArrayList<Software>();
    	cliente = new Cliente();
    	setor = new Setor();
    	emUso = false;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumLacre() {
		return numLacre;
	}

	public void setNumLacre(String numLacre) {
		this.numLacre = numLacre;
	}

	public String getNumPatrimonial() {
		return numPatrimonial;
	}

	public void setNumPatrimonial(String numPatrimonial) {
		this.numPatrimonial = numPatrimonial;
	}

	public String getProcessador() {
		return processador;
	}

	public void setProcessador(String processador) {
		this.processador = processador;
	}

	public String getMemoria() {
		return memoria;
	}

	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}

	public String getHd() {
		return hd;
	}

	public void setHd(String hd) {
		this.hd = hd;
	}

	public String getCdDvd() {
		return cdDvd;
	}

	public void setCdDvd(String cdDvd) {
		this.cdDvd = cdDvd;
	}

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public String getTipoMonitor() {
		return tipoMonitor;
	}

	public void setTipoMonitor(String tipoMonitor) {
		this.tipoMonitor = tipoMonitor;
	}

	public String getTamanhoMonitor() {
		return tamanhoMonitor;
	}

	public void setTamanhoMonitor(String tamanhoMonitor) {
		this.tamanhoMonitor = tamanhoMonitor;
	}

	public String getNumPatrMonitor() {
		return numPatrMonitor;
	}

	public void setNumPatrMonitor(String numPatrMonitor) {
		this.numPatrMonitor = numPatrMonitor;
	}

	public String getPlacaVideo() {
		return placaVideo;
	}

	public void setPlacaVideo(String placaVideo) {
		this.placaVideo = placaVideo;
	}

	public String getPlacaSom() {
		return placaSom;
	}

	public void setPlacaSom(String placaSom) {
		this.placaSom = placaSom;
	}

	public String getPlacaRede() {
		return placaRede;
	}

	public void setPlacaRede(String placaRede) {
		this.placaRede = placaRede;
	}

	public String getTeclado() {
		return teclado;
	}

	public void setTeclado(String teclado) {
		this.teclado = teclado;
	}

	public String getMouse() {
		return mouse;
	}

	public void setMouse(String mouse) {
		this.mouse = mouse;
	}

	public String getImpressora() {
		return impressora;
	}

	public void setImpressora(String impressora) {
		this.impressora = impressora;
	}

	public String getNumPatrImpressora() {
		return numPatrImpressora;
	}

	public void setNumPatrImpressora(String numPatrImpressora) {
		this.numPatrImpressora = numPatrImpressora;
	}

	public String getScanner() {
		return scanner;
	}

	public void setScanner(String scanner) {
		this.scanner = scanner;
	}

	public String getNumPatrScanner() {
		return numPatrScanner;
	}

	public void setNumPatrScanner(String numPatrScanner) {
		this.numPatrScanner = numPatrScanner;
	}

	public String getEstabilizador() {
		return estabilizador;
	}

	public void setEstabilizador(String estabilizador) {
		this.estabilizador = estabilizador;
	}

	public String getNumPatrEstabilizador() {
		return numPatrEstabilizador;
	}

	public void setNumPatrEstabilizador(String numPatrEstabilizador) {
		this.numPatrEstabilizador = numPatrEstabilizador;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public Boolean getEmUso() {
		return emUso;
	}

	public void setEmUso(Boolean emUso) {
		this.emUso = emUso;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public List<Software> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(List<Software> softwares) {
		this.softwares = softwares;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}