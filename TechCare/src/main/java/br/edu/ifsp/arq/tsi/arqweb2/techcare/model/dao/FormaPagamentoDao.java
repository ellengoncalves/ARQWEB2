package br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.FormaPagamento;

public class FormaPagamentoDao {
	private DataSource dataSource;

	public FormaPagamentoDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public FormaPagamento buscar(String nome) {
		String sql = "select * from forma_pagamento where lower(nome) = lower(?)";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, nome);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					FormaPagamento formaPagamento = new FormaPagamento();
					formaPagamento.setCodigo(rs.getLong("id"));
					formaPagamento.setNome(rs.getString("nome"));
					return formaPagamento;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar forma de pagamento", e);
		}

		return null;
	}

	public void salvar(FormaPagamento formaPagamento) {
		String sql = "insert into forma_pagamento (nome) values (?)";

		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, formaPagamento.getNome());
			ps.executeUpdate();

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					formaPagamento.setCodigo(generatedKeys.getLong(1));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar forma de pagamento", e);
		}
	}

	public List<FormaPagamento> ListarFormasPagamento() {
		List<FormaPagamento> formasPagamento = new ArrayList<>();
		String sql = "select * from forma_pagamento";

		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				FormaPagamento formaPagamento = new FormaPagamento();
				formaPagamento.setCodigo(rs.getLong("id"));
				formaPagamento.setNome(rs.getString("nome"));
				formasPagamento.add(formaPagamento);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return formasPagamento;
	}

	public void atualizar(FormaPagamento formaPagamento) {
		String sql = "update forma_pagamento set nome = ? where id = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, formaPagamento.getNome());
			ps.setLong(2, formaPagamento.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar forma de pagamento", e);
		}
	}

	public void deletar(int id) {
		String sql = "delete from forma_pagamento where id = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao excluir forma de pagamento", e);
		}
	}

	public FormaPagamento procurarPeloId(int id) {
		String sql = "select * from forma_pagamento where id = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					FormaPagamento formaPagamento = new FormaPagamento();
					formaPagamento.setCodigo(rs.getLong("id"));
					formaPagamento.setNome(rs.getString("nome"));
					return formaPagamento;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar forma de pagamento pelo ID", e);
		}
		return null;
	}
}