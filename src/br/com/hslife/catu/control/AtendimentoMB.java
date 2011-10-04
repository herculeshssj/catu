package br.com.hslife.catu.control;

import br.com.hslife.catu.dao.AtendimentoDao;
import br.com.hslife.catu.dao.ClienteDao;
import br.com.hslife.catu.dao.SetorDao;
import br.com.hslife.catu.dao.StatusDao;
import br.com.hslife.catu.dao.TipoDao;
import br.com.hslife.catu.model.Atendimento;
import br.com.hslife.catu.model.Cliente;
import br.com.hslife.catu.model.Login;
import br.com.hslife.catu.model.Setor;
import br.com.hslife.catu.model.Status;
import br.com.hslife.catu.model.Tipo;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

public class AtendimentoMB {

    private Atendimento atendimento;
    private Cliente cliente;
    private AtendimentoDao dao;
    private String busca;
    private String codAtend;
    private Long idAtendimento;
    private String msg;
    private List<Atendimento> listaAtendimentos;
    // Ids das chaves estrangeiras
    private Long idTipo;
    private Long idStatus;
    private Long idSetor;
    private Long idCliente;
    // Listagem dos registros das tabelas relacionadas
    private List<SelectItem> listaStatus;
    private List<SelectItem> listaTipo;
    private List<SelectItem> listaSetor;
    private List<Cliente> listaClientes;
    // Flag que indica quais componentes que serão mostrados ou ativados
    private Boolean isNovo;

    private Date dataAbertura;

    public AtendimentoMB() {
        atendimento = new Atendimento();
        dao = new AtendimentoDao();
        listaAtendimentos = new ArrayList<Atendimento>();
        listaClientes = new ArrayList<Cliente>();
        ClienteDao daoC = new ClienteDao();
        listaClientes = daoC.listarTodos();
        isNovo = true;
        carregaCombos();
    }

    private void carregaCombos() {

        setListaStatus(new ArrayList<SelectItem>());
        List<Status> listaI = new ArrayList<Status>();
        listaI = getDao().listarTodos(AtendimentoDao.STATUS);
        for (Status i : listaI) {
            getListaStatus().add(new SelectItem(i.getId(), i.getDescricao()));
        }

        setListaTipo(new ArrayList<SelectItem>());
        List<Tipo> listaT = new ArrayList<Tipo>();
        listaT = getDao().listarTodos(AtendimentoDao.TIPO);
        for (Tipo t : listaT) {
            getListaTipo().add(new SelectItem(t.getId(), t.getDescricao()));
        }

        setListaSetor(new ArrayList<SelectItem>());
        List<Setor> listaS = new ArrayList<Setor>();
        listaS = getDao().listarTodos(AtendimentoDao.SETOR);
        for (Setor s : listaS) {
            getListaSetor().add(new SelectItem(s.getId(), s.getDescricao()));
        }
    }

    public String novoAtendimento() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
        Login u = (Login) sessao.getAttribute("usuarioLogado");
        setAtendimento(new Atendimento());
        getAtendimento().setIdOperador(u);
        getAtendimento().setDataAbertura(new Date());
        ClienteDao daoC = new ClienteDao();
        setCliente(daoC.buscar(getIdCliente()));
        getAtendimento().setIdCliente(getCliente());
        setIsNovo((Boolean) true);
        setIdTipo((Long) 0l);
        setIdSetor((Long) 0l);
        setIdStatus((Long) 0l);
        carregaCombos();
        return "addAtendimento";
    }

    public void registrarChamado() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(false);
        Login u = (Login) sessao.getAttribute("usuarioLogado");
        //atendimento.setIdOperador(u);
        //atendimento.setDataAbertura(new Date());
        //atendimento.getIdEntidade().setId(idCliente);

        getAtendimento().setIdTipo(new TipoDao().buscar(getIdTipo()));

        getAtendimento().setIdSetor(new SetorDao().buscar(getIdSetor()));

        getAtendimento().setIdStatus(new StatusDao().buscar(getIdStatus()));

        StatusDao daoSit = new StatusDao();
        if (daoSit.buscar(getIdStatus()).getEncerra()) {
            getAtendimento().setDataEncerramento(new Date());
            getAtendimento().getIdStatus().setEncerra(true);
        }
        getDao().salvar(getAtendimento());
        if (getDao().getErrorMessage() == null) {
            setMsg("Atendimento registrado com sucesso!");
        } else {
            setMsg("Erro ao registrar atendimento: " + getDao().getErrorMessage());
        }
        setIsNovo((Boolean) false);
        FacesMessage mensagem = new FacesMessage(getMsg());
        contexto.addMessage("frmAtendimento", mensagem);
    }

    public String editar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        setMsg("");
        String resultado = "";
        setAtendimento(getDao().buscar(getIdAtendimento()));
        if (getDao().getErrorMessage() == null) {
            setIsNovo((Boolean) false);
            ClienteDao daoC = new ClienteDao();
            setCliente(daoC.buscar(getAtendimento().getIdCliente().getId()));
            getAtendimento().setIdCliente(getCliente());
            setIdCliente(getAtendimento().getIdCliente().getId());
            setIdTipo(getAtendimento().getIdTipo().getId());
            setIdSetor(getAtendimento().getIdSetor().getId());
            setIdStatus(getAtendimento().getIdStatus().getId());
            resultado = "editAtendimento";
        } else {
            setMsg("Erro ao carregar: " + getDao().getErrorMessage());
            FacesMessage mensagem = new FacesMessage(getMsg());
            contexto.addMessage("lstAtendimento", mensagem);
        }
        return resultado;
    }

    public void salvarAlteracoes() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        getAtendimento().getIdCliente().setId(getIdCliente());
        getAtendimento().getIdTipo().setId(getIdTipo());
        getAtendimento().getIdSetor().setId(getIdSetor());
        getAtendimento().getIdStatus().setId(getIdStatus());
        StatusDao daoSit = new StatusDao();
        if (daoSit.buscar(idStatus).getEncerra()) {
            getAtendimento().setDataEncerramento(new Date());
            getAtendimento().getIdStatus().setEncerra(true);
        }
        getDao().alterar(getAtendimento());
        if (getDao().getErrorMessage() == null) {
            setMsg("Atendimento alterado com sucesso!");
        } else {
            setMsg("Erro ao alterar atendimento: " + getDao().getErrorMessage());
        }
        FacesMessage mensagem = new FacesMessage(getMsg());
        contexto.addMessage("frmAtendimento", mensagem);
    }

    /*
     * Método que realiza a impressão da OS, salvando em PDF
     */
    public void imprimirOS() {
        // Obtem o contexto atual da aplicação
        FacesContext contexto = FacesContext.getCurrentInstance();
        // Obtém o contexto do servlet
        ServletContext servletContexto = (ServletContext) contexto.getExternalContext().getContext();
        // Obtém o caminho do relatório a ser gerado
        String caminhoRelOS = servletContexto.getRealPath("/relatorio/os.jasper");
        
        // Obtem a respossa da requisição
        HttpServletResponse resposta = (HttpServletResponse) contexto.getExternalContext().getResponse();

        // A partir deste ponto, carregamos a lista de parâmetros que será enviada para o relatório
        Map<String, Object> parametros = new HashMap<String, Object>();
        String dataFim = "";
        String complemento = "";        
        if (getAtendimento().getIdCliente().getEndereco().getComplemento() != null
                && !atendimento.getIdCliente().getEndereco().getComplemento().isEmpty()) {
            complemento = " - " + getAtendimento().getIdCliente().getEndereco().getComplemento();
        }
        parametros.put("numOS", getAtendimento().getId());
        parametros.put("nomeCliente", getAtendimento().getIdCliente().getNomeCliente());
        parametros.put("celSolicitante", getAtendimento().getIdCliente().getCelular());
        parametros.put("telSolicitante", getAtendimento().getIdCliente().getTelefone());
        parametros.put("logradouro", getAtendimento().getIdCliente().getEndereco().getTipoLogradouro() + " "
                + getAtendimento().getIdCliente().getEndereco().getLogradouro() + ", "
                + getAtendimento().getIdCliente().getEndereco().getNumero()
                + complemento);
        parametros.put("bairro", getAtendimento().getIdCliente().getEndereco().getBairro());
        parametros.put("cidade", getAtendimento().getIdCliente().getEndereco().getCidade());
        parametros.put("uf", getAtendimento().getIdCliente().getEndereco().getUf());
        parametros.put("cep", getAtendimento().getIdCliente().getEndereco().getCep());
        parametros.put("setor", getAtendimento().getIdSetor().getDescricao());
        parametros.put("email", getAtendimento().getIdCliente().getEmail());
        parametros.put("operador", getAtendimento().getIdOperador().getNomeUsuario());
        parametros.put("dataInicio", getAtendimento().getDataAbertura());
        parametros.put("dataTermino", getAtendimento().getDataEncerramento());
        parametros.put("tipoAtend", getAtendimento().getIdTipo().getDescricao());
        parametros.put("problema", getAtendimento().getProblema());
        parametros.put("solucao", getAtendimento().getSolucao());
        parametros.put("observacao", getAtendimento().getObservacao());
        // Fim do carregamento


        // Chama o método do JasperReport para preencher o relatório
        try {
            JasperPrint impressao = JasperFillManager.fillReport(caminhoRelOS, parametros);
            byte[] dados = JasperExportManager.exportReportToPdf(impressao);

            // Complementa a resposta para exibir o relatório gerado
            resposta.setHeader("Content-Disposition", "attachment; filename=\"os.pdf\";");
            resposta.setContentLength(dados.length);
            ServletOutputStream saida = resposta.getOutputStream();
            saida.write(dados, 0, dados.length);
            contexto.responseComplete();
            // Mensagem de sucesso
            setMsg("Ordem de serviço gerado com sucesso!");
        } catch (Exception e) {
            // Mensagem de erro
            setMsg("Não foi possível gerar a OS: " + e.getMessage());
        }
        // Exibição da mensagem no formulário
        FacesMessage mensagem = new FacesMessage(getMsg());
        contexto.addMessage("lstAtendimento", mensagem);
    }

    public void pesquisarAtendimento() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        setMsg("Registros carregados com sucesso!");
        if (getCodAtend().length()==0 && getIdStatus() == 0 && getDataAbertura() == null) {
            setMsg("Escolha uma das três opções de pesquisa!");
        } else if (codAtend.length()!=0) {
            setListaAtendimentos((List<Atendimento>) getDao().listarTodos(getBusca()));
        } else if (getIdStatus() != 0) {
            setListaAtendimentos((List<Atendimento>) getDao().listarTodos(getIdStatus()));
        } else if (getDataAbertura() != null) {
            setListaAtendimentos((List<Atendimento>) getDao().listarTodos(getDataAbertura()));
        } else if (getIdStatus() != 0 && getDataAbertura() != null) {
            setListaAtendimentos((List<Atendimento>) getDao().listarTodos(getDataAbertura(), getIdStatus()));
        }
        if (getDao().getErrorMessage() != null) {
            setMsg("Erro ao procurar: " + getDao().getErrorMessage());
        }
        FacesMessage mensagem = new FacesMessage(getMsg());
        contexto.addMessage("lstAtendimento", mensagem);
    }

    public void pesquisarCliente() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        setMsg("Registros carregados com sucesso!");
        ClienteDao daoE = new ClienteDao();
        setListaClientes(daoE.procurar("nomeCliente", getBusca()));
        if (getListaClientes().isEmpty()) {
            setMsg("Nenhum registro foi encontrado!");
        }
        if (getDao().getErrorMessage() != null) {
            setMsg("Erro ao procurar: " + getDao().getErrorMessage());
        }
        FacesMessage mensagem = new FacesMessage(getMsg());
        contexto.addMessage("lstEntidade", mensagem);
    }

    /**
     * @return the atendimento
     */
    public Atendimento getAtendimento() {
        return atendimento;
    }

    /**
     * @param atendimento the atendimento to set
     */
    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
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
     * @return the dao
     */
    public AtendimentoDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(AtendimentoDao dao) {
        this.dao = dao;
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
     * @return the codAtend
     */
    public String getCodAtend() {
        return codAtend;
    }

    /**
     * @param codAtend the codAtend to set
     */
    public void setCodAtend(String codAtend) {
        this.codAtend = codAtend;
    }

    /**
     * @return the idAtendimento
     */
    public Long getIdAtendimento() {
        return idAtendimento;
    }

    /**
     * @param idAtendimento the idAtendimento to set
     */
    public void setIdAtendimento(Long idAtendimento) {
        this.idAtendimento = idAtendimento;
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
     * @return the listaAtendimentos
     */
    public List<Atendimento> getListaAtendimentos() {
        return listaAtendimentos;
    }

    /**
     * @param listaAtendimentos the listaAtendimentos to set
     */
    public void setListaAtendimentos(List<Atendimento> listaAtendimentos) {
        this.listaAtendimentos = listaAtendimentos;
    }

    /**
     * @return the idTipo
     */
    public Long getIdTipo() {
        return idTipo;
    }

    /**
     * @param idTipo the idTipo to set
     */
    public void setIdTipo(Long idTipo) {
        this.idTipo = idTipo;
    }

    /**
     * @return the idStatus
     */
    public Long getIdStatus() {
        return idStatus;
    }

    /**
     * @param idStatus the idStatus to set
     */
    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    /**
     * @return the idSetor
     */
    public Long getIdSetor() {
        return idSetor;
    }

    /**
     * @param idSetor the idSetor to set
     */
    public void setIdSetor(Long idSetor) {
        this.idSetor = idSetor;
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
     * @return the listaStatus
     */
    public List<SelectItem> getListaStatus() {
        return listaStatus;
    }

    /**
     * @param listaStatus the listaStatus to set
     */
    public void setListaStatus(List<SelectItem> listaStatus) {
        this.listaStatus = listaStatus;
    }

    /**
     * @return the listaTipo
     */
    public List<SelectItem> getListaTipo() {
        return listaTipo;
    }

    /**
     * @param listaTipo the listaTipo to set
     */
    public void setListaTipo(List<SelectItem> listaTipo) {
        this.listaTipo = listaTipo;
    }

    /**
     * @return the listaSetor
     */
    public List<SelectItem> getListaSetor() {
        return listaSetor;
    }

    /**
     * @param listaSetor the listaSetor to set
     */
    public void setListaSetor(List<SelectItem> listaSetor) {
        this.listaSetor = listaSetor;
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
     * @return the isNovo
     */
    public Boolean getIsNovo() {
        return isNovo;
    }

    /**
     * @param isNovo the isNovo to set
     */
    public void setIsNovo(Boolean isNovo) {
        this.isNovo = isNovo;
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
    

}
