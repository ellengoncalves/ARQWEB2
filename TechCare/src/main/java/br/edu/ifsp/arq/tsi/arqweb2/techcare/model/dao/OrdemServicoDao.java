package br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.FormaPagamento;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.OrdemServico;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.Status;

public class OrdemServicoDao {
	private DataSource dataSource;

	public OrdemServicoDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void salvar(OrdemServico ordemServico) {
		FormaPagamentoDao formaPagamentoDAO = new FormaPagamentoDao(dataSource);
		FormaPagamento formaPagamentoExistente = formaPagamentoDAO.buscar(ordemServico.getFormaPagamento().getNome());

		if (formaPagamentoExistente != null) {
			ordemServico.setFormaPagamento(formaPagamentoExistente);
		} else {
			formaPagamentoDAO.salvar(ordemServico.getFormaPagamento());
		}

		String sql = "insert into ordem_servico (descricao, data_emissao, data_finalizacao, valor, observacao, status, forma_pagamento_id, cliente_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, ordemServico.getDescricao());
			ps.setDate(2, java.sql.Date.valueOf(ordemServico.getDataEmissao()));
			ps.setDate(3,
					ordemServico.getDataFinalizacao() != null ? java.sql.Date.valueOf(ordemServico.getDataFinalizacao())
							: null);
			ps.setBigDecimal(4, ordemServico.getValor());
			ps.setString(5, ordemServico.getObservacao());
			ps.setString(6, ordemServico.getStatus().toString());
			ps.setLong(7, ordemServico.getFormaPagamento().getCodigo());
			ps.setLong(8, ordemServico.getCliente().getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar ordem de serviço", e);
		}
	}

	public void atualizar(int id, String descricao, String dataEmissao, String dataFinalizacao, double valor,
			String observacao, String status) {
		String sql = "update ordem_servico set descricao = ?, data_emissao = ?, data_finalizacao = ?, valor = ?, observacao = ?, status = ? WHERE id = ?";

		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, descricao);
			ps.setString(2, dataEmissao);
			ps.setString(3, dataFinalizacao);
			ps.setDouble(4, valor);
			ps.setString(5, observacao);
			ps.setString(6, status);
			ps.setInt(7, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar ordem de serviço", e);
		}
	}

	public void deletar(int id) {
		String sql = "DELETE FROM ordem_servico WHERE id = ?";

		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao excluir ordem de serviço", e);
		}
	}

	public List<OrdemServico> listarOrdensServico() {
		List<OrdemServico> ordensServico = new ArrayList<>();
		String sql = "select os.id, os.descricao, os.data_emissao, os.data_finalizacao, os.valor, os.observacao, os.status, fp.nome AS forma_pagamento "
				+ "from ordem_servico os " + "join forma_pagamento fp on os.forma_pagamento_id = fp.id";

		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				OrdemServico ordemServico = new OrdemServico();
				ordemServico.setCodigo(rs.getLong("id"));
				ordemServico.setDescricao(rs.getString("descricao"));
				ordemServico.setDataEmissao(rs.getDate("data_emissao").toLocalDate());
				ordemServico.setDataFinalizacao(
						rs.getDate("data_finalizacao") != null ? rs.getDate("data_finalizacao").toLocalDate() : null);
				ordemServico.setValor(rs.getBigDecimal("valor"));
				ordemServico.setObservacao(rs.getString("observacao"));
				ordemServico.setStatus(Status.valueOf(rs.getString("status")));

				FormaPagamento formaPagamento = new FormaPagamento();
				formaPagamento.setNome(rs.getString("forma_pagamento"));
				ordemServico.setFormaPagamento(formaPagamento);

				ordensServico.add(ordemServico);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar ordens de serviço", e);
		}

		return ordensServico;

	}

	public OrdemServico procurarPeloId(int id) {
		String sql = "select os.id, os.descricao, os.data_emissao, os.data_finalizacao, os.valor, os.observacao, os.status, fp.nome AS forma_pagamento "
				+ "from ordem_servico os " + "join forma_pagamento fp on os.forma_pagamento_id = fp.id "
				+ "where os.id = ?";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					OrdemServico ordemServico = new OrdemServico();
					ordemServico.setCodigo(rs.getLong("id"));
					ordemServico.setDescricao(rs.getString("descricao"));
					ordemServico.setDataEmissao(rs.getDate("data_emissao").toLocalDate());
					ordemServico.setDataFinalizacao(
							rs.getDate("data_finalizacao") != null ? rs.getDate("data_finalizacao").toLocalDate()
									: null);
					ordemServico.setValor(rs.getBigDecimal("valor"));
					ordemServico.setObservacao(rs.getString("observacao"));
					ordemServico.setStatus(Status.valueOf(rs.getString("status")));

					FormaPagamento formaPagamento = new FormaPagamento();
					formaPagamento.setNome(rs.getString("forma_pagamento"));
					ordemServico.setFormaPagamento(formaPagamento);

					return ordemServico;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar ordem de serviço por ID", e);
		}

		return null;
	}
}