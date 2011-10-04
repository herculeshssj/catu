package br.com.hslife.catu.db;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/*
 * Classe GerarTabelas
 *
 * Classe que gera o esquema de banco de dados e cria as tabelas no
 * servidor MySQL.
 * O esquema gerado é mostrado no console
 *
 * @author Sergio Mendes - COTI Informática
 * @version 1.0
 *
 */
public class GerarTabelas {

    public static void geraTabelas() {
        Configuration cfg = new AnnotationConfiguration();
        cfg.configure("br/com/hslife/catu/db/hibernate.cfg.xml");

        SchemaExport se = new SchemaExport(cfg);
        se.create(true, true);
    }

    public static void main(String[] args) {
        GerarTabelas.geraTabelas();
    }
}
