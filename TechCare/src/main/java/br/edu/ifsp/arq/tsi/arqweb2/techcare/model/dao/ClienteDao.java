package br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.Cliente;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.utils.PasswordEncoder;

public class ClienteDao {
private DataSource dataSource;
	
	public ClienteDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Optional<Cliente> getClienteByEmailAndPassword(String email, String password) {
		String passwordEncripted = PasswordEncoder.encode(password);
		
		String sql = "select codigo,nome,email from client where email=? and password=?";
		Optional<Cliente> optional = Optional.empty();
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, email);
			ps.setString(2, passwordEncripted);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodigo(rs.getLong(1));
					cliente.setNome(rs.getString(2));
					cliente.setEmail(rs.getString(3));
					optional = Optional.of(cliente);
				}
			}
			return optional;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro durante a consulta no BD", sqlException);
		}
	}
	
	public Optional<Cliente> getClientByEmail(String email){
		
		String sql = "select codigo,nome,email from client where email=?";
	
		Optional<Cliente> optional = Optional.empty();
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){ 
			ps.setString(1, email);
			try(ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodigo(rs.getLong(1));
					cliente.setNome(rs.getString(2));
					cliente.setEmail(rs.getString(3));
					optional = Optional.of(cliente);
				}
			}
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a consulta no BD", e);
		}
		return optional;
	}
	
	public Boolean save(Cliente cliente){
		Optional<Cliente> optional = getClientByEmail(cliente.getEmail());
		if(optional.isPresent()) {
			return false;
		}
		String sql = "insert into client (nome, email, password, " + "telefone, cpf, ativo, logradouro, numero, complemento, bairro, cidade, estado, cep) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getEmail());
			ps.setString(3, cliente.getPassword());
			ps.setString(4, cliente.getTelefone());
			ps.setString(5, cliente.getCpf().toString());
			ps.setBoolean(6, true);
			ps.setString(7, cliente.getEndereco().getLogradouro());
			ps.setString(8, cliente.getEndereco().getNumero());
			ps.setString(9, cliente.getEndereco().getComplemento());
			ps.setString(10, cliente.getEndereco().getBairro());
			ps.setString(12, cliente.getEndereco().getCidade());
			ps.setString(13, cliente.getEndereco().getEstado());
			ps.setString(11, cliente.getEndereco().getCep());
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException("Erro durante a escrita no BD", e);
		}
		return true;
	}
}