package br.edu.ifsp.arq.tsi.arqweb2.ifitness.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceSearcher {
	
	private static DataSourceSearcher instance = 
			new DataSourceSearcher();
	private DataSource dataSource;
	
	private DataSourceSearcher() {
		try {
			Context context = new InitialContext();
			context = (Context)context.lookup("java:comp/env"); // forma de procurar o arquivo
			dataSource = (DataSource)context.lookup("jdbc/TechCareDB"); // a fim de encontrar a fonte de dados = bd
		}catch (NamingException e) {
			throw new RuntimeException("Erro durante o lookup", e);
		}
	}
	
	// pega quem é a fonte de dados
	public DataSource getDataSource() {
		return dataSource;
	}

	public static DataSourceSearcher getInstance() {
		return instance;
	}
}