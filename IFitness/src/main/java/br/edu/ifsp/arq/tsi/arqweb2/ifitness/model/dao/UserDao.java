package br.edu.ifsp.arq.tsi.arqweb2.ifitness.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import br.edu.ifsp.arq.tsi.arqweb2.ifitness.model.User;
import br.edu.ifsp.arq.tsi.arqweb2.ifitness.utils.PasswordEncoder;

public class UserDao {
	
	private DataSource dataSource;
	
	// recebe a fonte dos dados como parâmetro, para sempre que precisar de um objeto UserDao
	public UserDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	// método de criptografia de senha
	public Optional<User> getUserByEmailAndPassword(String email, String password) {
		// a senha chega limpa e em seguida é encriptada
		String passwordEncripted = PasswordEncoder.encode(password);
		
		String sql = "select id,name,email from user where email=? and password=?";
		Optional<User> optional = Optional.empty();
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, email);
			ps.setString(2, passwordEncripted);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getLong(1));
					user.setName(rs.getString(2));
					user.setEmail(rs.getString(3));
					optional = Optional.of(user);
				}
			}
			return optional;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro durante a consulta no BD", sqlException);
		}
	}
	
	// método usado para verificar se o já existe um usuário com esse email
	public Optional<User> getUserByEmail(String email){
		// instrução sql a ser executada,no caso, só com o necessário (id, name, email) buscados na tabela 'user'
		String sql = "select id,name,email from user where email=?"; //  o uso do '?' o dado é tratado como dado, ao contrário da concatenação, onde o que for escrito vai ter tratado como instrução
		// classe de utilitários. Interessante usar!
		Optional<User> optional = Optional.empty();
		try(Connection conn = dataSource.getConnection(); //  obtém a conexão/fonte de dados (pois utilizando o pool de conexões ele somente pega uma já existente)
				PreparedStatement ps = conn.prepareStatement(sql)){ // com ela, prepara-se a instrução
			ps.setString(1, email); // passada de parâmetros para a instrução (tratado como String e nao como sql = facilidade de manutenção, evitando o sql injection)
			try(ResultSet rs = ps.executeQuery()) { // execução da consulta que é atribuído ao 'ResultSet'
				if(rs.next()) { // formato de tabela e não objeto
					User user = new User(); // instancia um usuário
					user.setId(rs.getLong(1)); // 1 de primeira coluna e assim sucessivamente 
					user.setName(rs.getString(2));
					user.setEmail(rs.getString(3));
					optional = Optional.of(user);
				}
			}
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta no BD", e);
		}
		return optional;
	}
	
	public Boolean save(User user){
		Optional<User> optional = getUserByEmail(user.getEmail());
		if(optional.isPresent()) { // se encontrou um usuário com esse email (isPresent)
			return false;
		}
		String sql = "insert into user (name, email, password, "
				+ "telefone, cpf, active) values (?,?,?,?,?,?)";  // o id é auto incremental
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getTelefone()); // tranformação do localdate (data de nasc original do usuário) em um date (esperado)
			ps.setString(5, user.getCpf().toString());
			ps.setBoolean(6, true); // salvando o  usuário como ativo
			ps.executeUpdate(); // execução da instrução
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a escrita no BD", e);
		}
		return true;
	}
}
