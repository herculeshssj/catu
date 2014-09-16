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

package br.com.hslife.catu.db;


import java.sql.Connection;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author Hércules
 */
public class HibernateUtil {
	/*
    private static SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml)
            // config file.
            sessionFactory = new AnnotationConfiguration()
                    .configure("br/com/hslife/catu/db/hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception.
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
        	try {
                // Create the SessionFactory from standard (hibernate.cfg.xml)
                // config file.
                sessionFactory = new AnnotationConfiguration()
                        .configure("br/com/hslife/catu/db/hibernate.cfg.xml")
                        .buildSessionFactory();
            } catch (Throwable ex) {
                // Log the exception.
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }
    */
	
	private static final SessionFactory sessionFactory;
	private static final ThreadLocal<Session> sessionThread = new ThreadLocal<Session>();
	private static final ThreadLocal<Transaction> transactionThread = new ThreadLocal<Transaction>();

	static {
		try {
			sessionFactory = new AnnotationConfiguration().configure(
					"br/com/hslife/catu/db/hibernate.cfg.xml")
					.buildSessionFactory();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static Session getSession() {
		if (sessionThread.get() == null) {
			Session session = sessionFactory.openSession();
			session.setCacheMode(CacheMode.IGNORE);
			sessionThread.set(session);
			System.out.println("Nova sessão criada");
		}
		return (Session) sessionThread.get();
	}
	
	public static Connection getConnection() {
		return getSession().connection();
	}

	public static void closeSession() {
		Session session = (Session) sessionThread.get();
		if (session != null && session.isOpen()) {
			sessionThread.set(null);
			session.close();
			System.out.println("Sessão atual fechada");
		}
	}

	public static void beginTransaction() {
		Transaction transaction = getSession().beginTransaction();
		transactionThread.set(transaction);
		System.out.println("Transacao iniciada");
	}

	public static void commitTransaction() {
		Transaction transaction = (Transaction) transactionThread.get();
		if (transaction != null && !transaction.wasCommitted()
				&& !transaction.wasRolledBack()) {
			transaction.commit();
			transactionThread.set(null);
			System.out.println("Transação finalizada com sucesso");
		}
	}

	public static void rollbackTransaction() {
		Transaction transaction = (Transaction) transactionThread.get();
		if (transaction != null && !transaction.wasCommitted()
				&& !transaction.wasRolledBack()) {
			transaction.rollback();
			transactionThread.set(null);
			System.out.println("Transacao finalizada com falha. Alteraçoes desfeitas");
		}
	}
}