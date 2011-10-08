/***

    Copyright (c) 2010, 2011 Hércules S. S. José



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

package br.com.hslife.catu.dao;

import br.com.hslife.catu.db.HibernateUtil;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * Classe GenericDao
 *
 * Classe responsável por prover atributos, métodos e objetos comuns às
 * classes herdeiras (classes tipo GenericDao) usando a InterfaceDao para prover
 * os métodos genéricos que serão reconhecidos pela camada acima.
 *
 * Esta classe possui os seguintes atributos comuns a todas as classes GenericDao:
 * - Session session
 * - Transaction transaction
 * - Criteria criteria;
 * - Query query;
 * - String errorMessage - armazena a mensagem de erro;
 * - String stackTrace - armazena resultado resultado do método printStackTrace;
 *
 * Os seguintes métodos são comuns a todos
 *
 * - save(Object obj)
 * - update (Object obj)
 * - delete (Object obj)
 * - find (Integer id) retorna Object
 * - find (String campo, Object valor) retorna Object
 * - find (String campo, Object valor) retorna List<Object>
 * - findAll() retorna List<Object>
 *
 * Os métodos de busca, no momento, precisam de valores exatos para não
 * causarem exceção. Atentar ao enviar valores e tratar as exceções geradas.
 *
 *
 * @author HSlife
 */
public class GenericDao {

    // Atributos protegidos. Utilizados para as classes que herdam de GenericDao
    protected Session session;
    protected Transaction transaction;
    protected Criteria criteria;
    protected Query query;
    protected String errorMessage;
    protected String stackTrace;

    protected GenericDao() {
        // construtor protegido para impedir que classes não herdeiras e fora
        // do pacote de persistência instancie a classe
        errorMessage = null;
        stackTrace = null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    protected void save(Object obj) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            session.save(obj);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            errorMessage = e.getMessage();
            stackTrace = e.getStackTrace().toString();
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }

    protected void update(Object obj) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            session.update(obj);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            errorMessage = e.getMessage();
            stackTrace = e.getStackTrace().toString();
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }

    protected void delete(Object obj) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            try {
                session.delete(obj);
                transaction.commit();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        } catch (Exception e) {
            transaction.rollback();
            errorMessage = e.getMessage();
            stackTrace = e.getStackTrace().toString();
            e.printStackTrace();
        }
    }

    /*
     *  Busca específica usando resultado id informado
     */
    protected Object find(Class classe, long id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Object resultado = null;
        try {
            try {
                resultado = session.get(classe, id);
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        } catch (Exception e) {
            errorMessage = e.getMessage();
            stackTrace = e.getStackTrace().toString();
            e.printStackTrace();
        }
        return resultado;
    }

    /*
     * Busca resultado valor no campo informado
     * Use em situações que você sabe que irá retornar somente um valor.
     * Mais útil em campos únicos(unique) e que não contém repetição de valores
     */
    protected Object find(Class classe, String campo, Object valor) {
        Object resultado = null;
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            try {
                resultado = session.createCriteria(classe).add(Restrictions.eq(campo, valor)).uniqueResult();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        } catch (Exception e) {
            errorMessage = e.getMessage();
            stackTrace = e.getStackTrace().toString();
            e.printStackTrace();
        }
        return resultado;
    }

    /*
     * Retorna uma lista de objetos obtidos na busca pelo valor no campo
     * informado. Caso nenhum objeto seja encontrado resultado método retorna uma lista
     * vazia (size = 0)
     */
    protected List<Object> findAll(Class classe, String campo, Object valor) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Object> lista = null;
        try {
            try {
                lista = session.createCriteria(classe).add(Expression.eq(campo, valor)).addOrder(Order.asc("id")).list();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        } catch (Exception e) {
            errorMessage = e.getMessage();
            stackTrace = e.getStackTrace().toString();
            e.printStackTrace();
        }
        return lista;
    }

    protected List<Object> findAll(Class classe) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Object> lista = null;
        try {
            try {
                lista = session.createCriteria(classe).addOrder(Order.asc("id")).list();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        } catch (Exception e) {
            errorMessage = e.getMessage();
            stackTrace = e.getStackTrace().toString();
            e.printStackTrace();
        }
        return lista;
    }

    /*
     * Retorna uma lista de objetos obtidos na busca pelo valor no campo
     * informado. Caso nenhum objeto seja encontrado resultado método retorna uma lista
     * vazia (size = 0)
     */
    protected List<Object> search(String classe, String campo, String valor) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Object> lista = null;
        try {
            try {
                String hql = "from " + classe + " where " + campo + " like '%" + valor + "%' order by " + campo;
                lista = session.createQuery(hql).list();
            } finally {
                if (session.isOpen()) {
                    session.close();
                }
            }
        } catch (Exception e) {
            errorMessage = e.getMessage();
            stackTrace = e.getStackTrace().toString();
            e.printStackTrace();
        }
        return lista;
    }

    protected List<Object> query(Class classe, String sqlQuery) {
        session = HibernateUtil.getSessionFactory().openSession();
        List lista = null;
        try {
            Query queryResult = session.createSQLQuery(sqlQuery).addEntity(classe);
            lista = queryResult.list();
        } catch (Exception e) {
            errorMessage = e.getMessage();
            stackTrace = e.getStackTrace().toString();
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return lista;
    }

    protected Object queryUnique(Class classe, String sqlQuery) {
        session = HibernateUtil.getSessionFactory().openSession();
        Object resultado = null;
        try {
            Query queryResult = session.createSQLQuery(sqlQuery).addEntity(classe);
            resultado = queryResult.uniqueResult();
        } catch (Exception e) {
            errorMessage = e.getMessage();
            stackTrace = e.getStackTrace().toString();
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return resultado;
    }

     protected void queryNoResult(String sqlQuery) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            session.createSQLQuery(sqlQuery).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            errorMessage = e.getMessage();
            stackTrace = e.getStackTrace().toString();
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }
}