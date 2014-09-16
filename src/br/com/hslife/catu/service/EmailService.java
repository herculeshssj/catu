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

package br.com.hslife.catu.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 * 
 * Classe EmailService
 * 
 * Classe responsável por enviar mensagem de acordo com o método escolhido
 * O código foi obtido em http://www.guj.com.br/posts/list/106280.java e 
 * adaptado para este projeto.
 *
 * @author Hércules
 * @version 1.0
 */
public class EmailService {

	/**
     * envia email simples (smente texto)
     * Nome remetente, e-mail remetente, nome destinatario, e-mail destinatario,
     * assunto, mensagem
     * @param nomeRemetente
     * @param nomeDestinatario
     * @param emailRemetente
     * @param emailDestinatario
     * @param assunto
     * @param mensagem
     * @throws EmailException
     */
    public void enviaEmailSimples(String nomeRementente, String emailRemetente,
            String nomeDestinatario, String emailDestinatario,
            String assunto, StringBuilder mensagem) throws EmailException {

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.hslife.com.br"); // o servidor SMTP para envio do e-mail
        email.addTo(emailDestinatario, nomeDestinatario); //destinatário
        email.setFrom(emailRemetente, nomeRementente); // remetente
        email.setSubject(assunto); // assunto do e-mail
        email.setMsg(mensagem.toString()); //conteudo do e-mail
        email.setAuthentication("nao-responde@hslife.com.br", "n0r3ply1@3");
        email.setCharset("UTF8");
        email.setSmtpPort(465);
        email.setSSL(true);
        email.setTLS(true);
        email.send();
    }

    /**
     * Envia email no formato HTML
     *
     * @param nomeRemetente
     * @param nomeDestinatario
     * @param emailRemetente
     * @param emailDestinatario
     * @param assunto
     * @param mensagem
     * @param anexo
     *
     * @throws EmailException
     * @throws MalformedURLException
     */
    public void enviaEmailFormatoHtml(String nomeRementente, String emailRemetente,
            String nomeDestinatario, String emailDestinatario,
            String assunto, StringBuilder mensagem,
            String anexo) throws EmailException, MalformedURLException {

        HtmlEmail email = new HtmlEmail();

        // adiciona uma imagem ao corpo da mensagem e retorna seu id
        URL url = new URL(anexo); // URL do arquivo a ser anexado
        String cid = email.embed(url, "Anexos");

        // configura a mensagem para o formato HTML
        email.setHtmlMsg("<html>Anexos</html>");

        // configure uma mensagem alternativa caso o servidor não suporte HTML
        email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");

        email.setHostName("smtp.hslife.com.br"); // o servidor SMTP para envio do e-mail
        email.addTo(emailDestinatario, nomeDestinatario); //destinatário
        email.setFrom(emailRemetente, nomeRementente); // remetente
        email.setSubject(assunto); // assunto do e-mail
        email.setMsg(mensagem.toString()); //conteudo do e-mail
        email.setAuthentication("realimoveis@hslife.com.br", "real123");
        email.setCharset("UTF8");
        //email.setSmtpPort(465);
        //email.setSSL(true);
        //email.setTLS(true);

        // envia email
        email.send();
    }

    /**
     * envia email com arquivo anexo
     * @throws EmailException
     */
    public void enviaEmailComAnexo(String nomeRementente, String emailRemetente,
            String nomeDestinatario, String emailDestinatario,
            String assunto, StringBuilder mensagem,
            List<String> anexos) throws EmailException, MalformedURLException {

        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.hslife.com.br"); // o servidor SMTP para envio do e-mail
        email.addTo(emailDestinatario, nomeDestinatario); //destinatário
        email.setFrom(emailRemetente, nomeRementente); // remetente
        email.setSubject(assunto); // assunto do e-mail
        email.setMsg(mensagem.toString()); //conteudo do e-mail
        email.setAuthentication("realimoveis@hslife.com.br", "real123");
        email.setCharset("UTF8");
        //email.setSmtpPort(465);
        //email.setSSL(true);
        //email.setTLS(true);
        // adiciona arquivo(s) anexo(s)
        EmailAttachment anexo = new EmailAttachment();
        for (String arquivo : anexos) {
            anexo.setPath("/home/hslife/" + arquivo); //caminho do arquivo (RAIZ_PROJETO/teste/teste.txt)
            anexo.setDisposition(EmailAttachment.ATTACHMENT);
            anexo.setName(arquivo);
            email.attach(anexo);
            anexo = new EmailAttachment();
        }
        // Envia o e-mail
        email.send();
    }
	
}
