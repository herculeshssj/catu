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
        email.setAuthentication("realimoveis@hslife.com.br", "real123");
        email.setCharset("UTF8");
        //email.setSmtpPort(465);
        //email.setSSL(true);
        //email.setTLS(true);
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
