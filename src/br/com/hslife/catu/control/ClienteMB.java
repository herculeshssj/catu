package br.com.hslife.catu.control;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import br.com.hslife.catu.dao.ClienteDao;
import br.com.hslife.catu.dao.SetorDao;
import br.com.hslife.catu.model.Cliente;
import br.com.hslife.catu.model.Endereco;
import br.com.hslife.catu.model.Login;
import br.com.hslife.catu.model.Setor;
import br.com.hslife.catu.util.Estados;
import br.com.hslife.catu.util.TipoLogradouro;

public class ClienteMB {

    private Cliente cliente;
    private Endereco endereco;
    private ClienteDao dao;
    private Long idCliente;
    private Long idSetor;
    private Long idEndereco;
    private String busca;
    private String msg;
    private List<Cliente> listaClientes;
    private List<SelectItem> listaSetor;
    private List<SelectItem> estados;
    private List<SelectItem> tipoLogradouro;

    public ClienteMB() {
        cliente = new Cliente();
        endereco = new Endereco();
        dao = new ClienteDao();
        listaClientes = new ArrayList<Cliente>();
        listaClientes = dao.listarTodos();
    }
    
    private void carregaCombos() {

    	estados = new ArrayList<SelectItem>();
        tipoLogradouro = new ArrayList<SelectItem>();
        listaSetor = new ArrayList<SelectItem>();
        
        // Carrega a combobox com os estados
        for (int i = 0; i < Estados.getTamanho(); i++) {
            estados.add(i, new SelectItem(Estados.getEstados(i), Estados.getEstados(i)));
        }
        // Carrega a combobox com os tipos de logradouro
        for (int i = 0; i < TipoLogradouro.getTamanho(); i++) {
            tipoLogradouro.add(i, new SelectItem(TipoLogradouro.getTipoLogradouro(i), TipoLogradouro.getTipoLogradouro(i)));
        }
    	        
        for (Setor s : dao.listaSetores()) {
            listaSetor.add(new SelectItem(s.getId(), s.getDescricao()));
        }
    }

    public String cadastrar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
        Login usuario = (Login) sessao.getAttribute("usuarioLogado");
        String resultado = "";
        if (usuario.getPerfil().equals("USER")) {
            setMsg("Você não tem permissão para cadastrar!");
            FacesMessage mensagem = new FacesMessage(getMsg());
            contexto.addMessage("frmCliente", mensagem);
        } else {
            setCliente(new Cliente());
            endereco = new Endereco();
            carregaCombos();
            resultado = "addCliente";
        }
        return resultado;
    }

    public void salvar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
        Login usuario = (Login) sessao.getAttribute("usuarioLogado");
        if (usuario.getPerfil().equals("USER")) {
            setMsg("Você não tem permissão para alterar!");
        } else {
            if (cliente.getId() == null || cliente.getId() == 0) {
                getCliente().setEndereco(getEndereco());
                cliente.setIdSetor(new SetorDao().buscar(idSetor));
                dao.salvar(cliente);
                if (getDao().getErrorMessage() == null) {
                    setMsg("Cadastro realizado com sucesso!");
                } else {
                    setMsg("Erro ao cadastrar: " + getDao().getErrorMessage());
                }
                setCliente(new Cliente());
                setEndereco(new Endereco());
            } else {
                getEndereco().setId(getIdEndereco());
                getCliente().setEndereco(getEndereco());
                cliente.setIdSetor(new SetorDao().buscar(idSetor));
                dao.alterar(cliente);
                if (getDao().getErrorMessage() == null) {
                    setMsg("Alteração realizada com sucesso!");
                } else {
                    setMsg("Erro ao alterar: " + getDao().getErrorMessage());
                }
            }
        }
        listaClientes = dao.listarTodos();
        FacesMessage mensagem = new FacesMessage(getMsg());
        contexto.addMessage("frmCliente", mensagem);
    }

    public String editar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        String resultado = "";
        cliente = dao.buscar(idCliente);
        setEndereco(getCliente().getEndereco());
        setIdEndereco(getCliente().getEndereco().getId());
        carregaCombos();
        if (getDao().getErrorMessage() == null) {
            resultado = "editCliente";
        } else {
            msg = "Erro ao carregar: " + getDao().getErrorMessage();
            FacesMessage mensagem = new FacesMessage(msg);
            contexto.addMessage("lstCliente", mensagem);
        }
        return resultado;
    }

    public void excluir() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
        Login usuario = (Login) sessao.getAttribute("usuarioLogado");
        if (usuario.getPerfil().equals("USER")) {
            setMsg("Você não tem permissão para excluir!");
        } else {
            cliente = dao.buscar(idCliente);
            dao.excluir(cliente);
            if (getDao().getErrorMessage() == null) {
                setMsg("Registro excluído com sucesso!");
                listaClientes = dao.listarTodos();
            } else {
                setMsg("Erro ao excluir: " + getDao().getErrorMessage());
            }
        }
        cliente = new Cliente();
        setEndereco(new Endereco());
        listaClientes = dao.listarTodos();
        FacesMessage mensagem = new FacesMessage(getMsg());
        contexto.addMessage("lstCliente", mensagem);
    }

    public void pesquisar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        setMsg("Registros carregados com sucesso!");
        setListaClientes(getDao().procurar("nomeCliente", getBusca()));       
        if (getDao().getErrorMessage() != null) {
            setMsg("Erro ao procurar: " + getDao().getErrorMessage());
        }
        FacesMessage mensagem = new FacesMessage(getMsg());
        contexto.addMessage("lstCliente", mensagem);
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the dao
     */
    public ClienteDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ClienteDao dao) {
        this.dao = dao;
    }

    /**
     * @return the idCliente
     */
    public Long getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the idEndereco
     */
    public Long getIdEndereco() {
        return idEndereco;
    }

    /**
     * @param idEndereco the idEndereco to set
     */
    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    /**
     * @return the busca
     */
    public String getBusca() {
        return busca;
    }

    /**
     * @param busca the busca to set
     */
    public void setBusca(String busca) {
        this.busca = busca;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the listaClientes
     */
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    /**
     * @param listaClientes the listaClientes to set
     */
    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    /**
     * @return the estados
     */
    public List<SelectItem> getEstados() {
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(List<SelectItem> estados) {
        this.estados = estados;
    }

    /**
     * @return the tipoLogradouro
     */
    public List<SelectItem> getTipoLogradouro() {
        return tipoLogradouro;
    }

    /**
     * @param tipoLogradouro the tipoLogradouro to set
     */
    public void setTipoLogradouro(List<SelectItem> tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

	public Long getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(Long idSetor) {
		this.idSetor = idSetor;
	}

	public List<SelectItem> getListaSetor() {
		return listaSetor;
	}

	public void setListaSetor(List<SelectItem> listaSetor) {
		this.listaSetor = listaSetor;
	}
    
}