package br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.Cliente;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.Endereco;

public class ClienteDao {
	private DataSource dataSource;

	public ClienteDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void salvar(String nome, String email, String telefone, String cpf, Endereco endereco) {
	    String sqlCliente = "insert into cliente (nome, email, telefone, cpf, ativo) values (?, ?, ?, ?, ?)";
	    String sqlEndereco = "insert into endereco (logradouro, numero, complemento, bairro, cep, cidade, estado, codigo_cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = dataSource.getConnection()) {
	        try (PreparedStatement psCliente = conn.prepareStatement(sqlCliente, PreparedStatement.RETURN_GENERATED_KEYS)) {
	            psCliente.setString(1, nome);
	            psCliente.setString(2, email);
	            psCliente.setString(3, telefone);
	            psCliente.setString(4, cpf);
	            psCliente.setBoolean(5, true);
	            psCliente.executeUpdate();

	            ResultSet rs = psCliente.getGeneratedKeys();
	            if (rs.next()) {
	                long clienteId = rs.getLong(1);

	                try (PreparedStatement psEndereco = conn.prepareStatement(sqlEndereco)) {
	                    psEndereco.setString(1, endereco.getLogradouro());
	                    psEndereco.setString(2, endereco.getNumero());
	                    psEndereco.setString(3, endereco.getComplemento());
	                    psEndereco.setString(4, endereco.getBairro());
	                    psEndereco.setString(5, endereco.getCep());
	                    psEndereco.setString(6, endereco.getCidade());
	                    psEndereco.setString(7, endereco.getEstado());
	                    psEndereco.setLong(8, clienteId);
	                    psEndereco.executeUpdate();
	                }
	            } else {
	                throw new RuntimeException("Erro ao obter o ID do cliente");
	            }
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("Erro não foi possível salvar cliente", e);
	    }
	}

	public void deletar(int id) {
		String sqlCliente = "delete from cliente where id = ?";
		String sqlEndereco = "delete from endereco where codigo_cliente = ?";

		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement psEndereco = conn.prepareStatement(sqlEndereco)) {
				psEndereco.setInt(1, id);
				psEndereco.executeUpdate();
			}

			try (PreparedStatement psCliente = conn.prepareStatement(sqlCliente)) {
				psCliente.setInt(1, id);
				psCliente.executeUpdate();
			}

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao excluir cliente", e);
		}
	}

	public void atualizar(int id, String nome, String email, String telefone, String cpf, Endereco endereco) {
		String sqlCliente = "update cliente set nome = ?, email = ?, telefone = ?, cpf = ? where id = ?";
		String sqlEndereco = "update endereco set logradouro = ?, numero = ?, complemento = ?, bairro = ?, cep = ?, cidade = ?, estado = ? where codigo_cliente = ?";

		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement psCliente = conn.prepareStatement(sqlCliente)) {
				psCliente.setString(1, nome);
				psCliente.setString(2, email);
				psCliente.setString(3, telefone);
				psCliente.setString(4, cpf);
				psCliente.setInt(5, id);
				psCliente.executeUpdate();
			}

			try (PreparedStatement psEndereco = conn.prepareStatement(sqlEndereco)) {
				psEndereco.setString(1, endereco.getLogradouro());
				psEndereco.setString(2, endereco.getNumero());
				psEndereco.setString(3, endereco.getComplemento());
				psEndereco.setString(4, endereco.getBairro());
				psEndereco.setString(5, endereco.getCep());
				psEndereco.setString(6, endereco.getCidade());
				psEndereco.setString(7, endereco.getEstado());
				psEndereco.setInt(8, id);
				psEndereco.executeUpdate();
			}

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar cliente", e);
		}
	}

	public List<Cliente> listarClientes() {
		List<Cliente> clientes = new ArrayList<>();
		String sql = "select c.id, c.nome, c.email, c.telefone, c.cpf, c.ativo, "
				+ "e.logradouro, e.numero, e.complemento, e.bairro, e.cep, e.cidade, e.estado " + "from cliente c "
				+ "join endereco e on c.id = e.codigo_cliente";

		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCodigo(rs.getLong("id"));
				;
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setAtivo(rs.getBoolean("ativo"));

				Endereco endereco = new Endereco();
				endereco.setLogradouro(rs.getString("logradouro"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setCep(rs.getString("cep"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setEstado(rs.getString("estado"));

				cliente.setEndereco(endereco);

				clientes.add(cliente);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar clientes", e);
		}

		return clientes;
	}

	public Cliente procurarPeloId(int id) {
		Cliente cliente = null;
		String sql = "select c.id, c.nome, c.email, c.telefone, c.cpf, c.ativo, "
				+ "e.logradouro, e.numero, e.complemento, e.bairro, e.cep, e.cidade, e.estado "
				+ "from cliente c join endereco e on c.id = e.codigo_cliente where c.id = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					cliente = new Cliente();
					cliente.setCodigo(rs.getLong("id"));
					cliente.setNome(rs.getString("nome"));
					cliente.setEmail(rs.getString("email"));
					cliente.setTelefone(rs.getString("telefone"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setAtivo(rs.getBoolean("ativo"));

					Endereco endereco = new Endereco();
					endereco.setLogradouro(rs.getString("logradouro"));
					endereco.setNumero(rs.getString("numero"));
					endereco.setComplemento(rs.getString("complemento"));
					endereco.setBairro(rs.getString("bairro"));
					endereco.setCep(rs.getString("cep"));
					endereco.setCidade(rs.getString("cidade"));
					endereco.setEstado(rs.getString("estado"));

					cliente.setEndereco(endereco);
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar cliente por ID", e);
		}

		return cliente;
	}
}