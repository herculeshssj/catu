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

package br.com.hslife.catu.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import br.com.hslife.catu.dao.ClienteDao;
import br.com.hslife.catu.dao.MaquinaDao;
import br.com.hslife.catu.dao.SetorDao;
import br.com.hslife.catu.dao.SoftwareDao;
import br.com.hslife.catu.model.Cliente;
import br.com.hslife.catu.model.Login;
import br.com.hslife.catu.model.Maquina;
import br.com.hslife.catu.model.Setor;
import br.com.hslife.catu.model.Software;

public class MaquinaMB {

	private Maquina maquina;
	private MaquinaDao dao;
	private Long idMaquina;
	private Long idSetor;
	private Long idCliente;
	private String busca;
	private String msg;
	private List<Maquina> listaMaquinas;
	private List<SelectItem> listaSetores;
	private List<SelectItem> listaClientes;
	private List<SelectItem> instalados;
	private List<String> instaladosSelecionados;
	private List<SelectItem> disponiveis;
	private List<String> disponiveisSelecionados;

	public MaquinaMB() {
		maquina = new Maquina();
		dao = new MaquinaDao();
		listaMaquinas = new ArrayList<Maquina>();
		instaladosSelecionados = new ArrayList<String>();
		disponiveisSelecionados = new ArrayList<String>();
	}

	private void carregaCombos() throws Exception {
		listaSetores = new ArrayList<SelectItem>();
		List<Setor> listaS = new ArrayList<Setor>();
		listaS = new SetorDao().listarTodos();
		for (Setor s : listaS) {
			listaSetores.add(new SelectItem(s.getId(), s.getDescricao()));
		}

		listaClientes = new ArrayList<SelectItem>();
		List<Cliente> listaM = new ArrayList<Cliente>();
		listaM = new ClienteDao().listarTodos();
		for (Cliente m : listaM) {
			listaClientes.add(new SelectItem(m.getId(), m.getNomeCliente()));
		}

		disponiveis = new ArrayList<SelectItem>();
		List<Software> listaO = new ArrayList<Software>();
		listaO = new SoftwareDao().listarSoftwareDisponivel();
		for (Software s : listaO) {
			disponiveis.add(new SelectItem(s.getId(), s.getNome()));
		}

		instalados = new ArrayList<SelectItem>();
		listaO = maquina.getSoftwares();
		for (Software s : listaO) {
			instalados.add(new SelectItem(s.getId(), s.getNome()));
		}

	}

	public String cadastrar() throws Exception {
		FacesContext contexto = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) contexto.getExternalContext()
				.getSession(false);
		Login usuario = (Login) sessao.getAttribute("usuarioLogado");
		msg = "";
		String resultado = "";
		if (usuario.getPerfil().equals("USER")) {
			msg = "Você não tem permissão para cadastrar!";
			FacesMessage mensagem = new FacesMessage(msg);
			contexto.addMessage("frmMaquina", mensagem);
		} else {
			carregaCombos();
			resultado = "addMaquina";
			maquina = new Maquina();
		}
		return resultado;
	}

	public void salvar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) contexto.getExternalContext()
				.getSession(false);
		Login usuario = (Login) sessao.getAttribute("usuarioLogado");
		msg = "";
		if (usuario.getPerfil().equals("USER")) {
			msg = "Você não tem permissão para alterar!";
		} else {
			if (maquina.getId() == null || maquina.getId() == 0) {
				try {
					maquina.setCliente(new ClienteDao().buscar(idCliente));
					maquina.setSetor(new SetorDao().buscar(idSetor));
					associaSotware();
					dao.salvar(maquina);
					msg = "Cadastro realizado com sucesso!";
					maquina = new Maquina();
				} catch (Exception e) {
					msg = "Erro ao cadastrar: " + e.getMessage();
				}
			} else {
				try {
					associaSotware();
					dao.alterar(maquina);
					msg = "Alteração realizada com sucesso!";
				} catch (Exception e) {
					msg = "Erro ao alterar: " + e.getMessage();
				}
			}
		}
		FacesMessage mensagem = new FacesMessage(msg);
		contexto.addMessage("frmMaquina", mensagem);
	}

	private void associaSotware() {
		// TODO Auto-generated method stub

		// Adiciona os softwares disponíveis
		if (disponiveisSelecionados.size() > 0) {
			Iterator<String> iteratorDisp = disponiveisSelecionados.iterator();
			Software obj = new Software();
			maquina.setSoftwares(new ArrayList<Software>());
			while (iteratorDisp.hasNext()) {
				obj.setId(Long.parseLong(iteratorDisp.next()));
				maquina.getSoftwares().add(obj);
				obj = new Software();
			}
		}
		instaladosSelecionados = new ArrayList<String>();
		disponiveisSelecionados = new ArrayList<String>();

	}

	public String editar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String resultado = "";
		try {
			maquina = dao.buscar(idMaquina);
			idSetor = maquina.getSetor().getId();
			idCliente = maquina.getCliente().getId();
			carregaCombos();
			resultado = "editMaquina";
		} catch (Exception e) {
			msg = "Erro ao carregar: " + e.getMessage();
			FacesMessage mensagem = new FacesMessage(msg);
			contexto.addMessage("lstMaquina", mensagem);
		}
		return resultado;
	}

	public void pesquisar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		try {
			listaMaquinas = dao.procurar("numPatrimonial", busca);
			msg = "Registros carregados com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao procurar: " + e.getMessage();
		}
		FacesMessage mensagem = new FacesMessage(msg);
		contexto.addMessage("lstMaquina", mensagem);
	}
	
	public void gerarInventario() {
    	// Obtem o contexto atual da aplicação
        FacesContext contexto = FacesContext.getCurrentInstance();
        // Obtém o contexto do servlet
        ServletContext servletContexto = (ServletContext) contexto.getExternalContext().getContext();
        // Obtém o caminho do relatório a ser gerado
        String caminhoRelOS = servletContexto.getRealPath("/relatorio/maquina.jasper");
        
        // Obtem a respossa da requisição
        HttpServletResponse resposta = (HttpServletResponse) contexto.getExternalContext().getResponse();

        // Map para passar para o relatório
        Map<String, Object> parametros = new HashMap<String, Object>();       


        // Chama o método do JasperReport para preencher o relatório
        try {
            JasperPrint impressao = JasperFillManager.fillReport(caminhoRelOS, parametros, dao.getConnection());
            byte[] dados = JasperExportManager.exportReportToPdf(impressao);

            // Complementa a resposta para exibir o relatório gerado
            resposta.setHeader("Content-Disposition", "attachment; filename=\"maquina.pdf\";");
            resposta.setContentLength(dados.length);
            ServletOutputStream saida = resposta.getOutputStream();
            saida.write(dados, 0, dados.length);
            contexto.responseComplete();
            // Mensagem de sucesso
            setMsg("Inventário gerado com sucesso!");
        } catch (Exception e) {
            // Mensagem de erro
            setMsg("Não foi possível gerar o inventário: " + e.getMessage());
            e.printStackTrace();
        }
        // Exibição da mensagem no formulário
        FacesMessage mensagem = new FacesMessage(getMsg());
        contexto.addMessage("lstMaquina", mensagem);
    }

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public MaquinaDao getDao() {
		return dao;
	}

	public void setDao(MaquinaDao dao) {
		this.dao = dao;
	}

	public Long getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
	}

	public Long getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(Long idSetor) {
		this.idSetor = idSetor;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Maquina> getListaMaquinas() {
		return listaMaquinas;
	}

	public void setListaMaquinas(List<Maquina> listaMaquinas) {
		this.listaMaquinas = listaMaquinas;
	}

	public List<SelectItem> getListaSetores() {
		return listaSetores;
	}

	public void setListaSetores(List<SelectItem> listaSetores) {
		this.listaSetores = listaSetores;
	}

	public List<SelectItem> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<SelectItem> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<SelectItem> getInstalados() {
		return instalados;
	}

	public void setInstalados(List<SelectItem> instalados) {
		this.instalados = instalados;
	}

	public List<String> getInstaladosSelecionados() {
		return instaladosSelecionados;
	}

	public void setInstaladosSelecionados(List<String> instaladosSelecionados) {
		this.instaladosSelecionados = instaladosSelecionados;
	}

	public List<SelectItem> getDisponiveis() {
		return disponiveis;
	}

	public void setDisponiveis(List<SelectItem> disponiveis) {
		this.disponiveis = disponiveis;
	}

	public List<String> getDisponiveisSelecionados() {
		return disponiveisSelecionados;
	}

	public void setDisponiveisSelecionados(List<String> disponiveisSelecionados) {
		this.disponiveisSelecionados = disponiveisSelecionados;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

}