package br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.Cliente;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.OrdemServico;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.OrdemServicoStatus;

public class OrdemServicoDao {
	private DataSource dataSource;

	public OrdemServicoDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Boolean save(OrdemServico ordemServico) {
		String sql = "insert into ordem_servico (descricao, status, data_emissao, data_finalizacao, valor, observacao, codigo_cliente)"
				+ " values(?,?,?,?,?,?,?)";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = 
				con.prepareStatement(sql)) {
			ps.setString(1, ordemServico.getDescricao());
			ps.setString(2, ordemServico.getStatus().toString());
			ps.setDate(3, Date.valueOf(ordemServico.getDataEmissao()));
			ps.setDate(4, Date.valueOf(ordemServico.getDataFinalizacao()));
			ps.setBigDecimal(5, ordemServico.getValor());
			ps.setString(6, ordemServico.getObservacao());
			ps.setLong(7, ordemServico.getCliente().getCodigo());
			ps.executeUpdate();
			return true;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro ao inserir dados", sqlException);
		}
	}
	
	public List<OrdemServico> getOrdemServicoPorCliente(Cliente cliente) {
		String sql = "select * from ordem_servico where codigo_cliente=?";
		List<OrdemServico> ordemServicos = new ArrayList<>();
		try (Connection con = dataSource.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, cliente.getCodigo());
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					OrdemServico ordemServico = new OrdemServico();
					ordemServico.setCodigo(rs.getLong(1));
					ordemServico.setDescricao(rs.getString(2));
					ordemServico.setStatus(OrdemServicoStatus.valueOf(rs.getString(3)));
					ordemServico.setDataEmissao(LocalDate.parse(rs.getDate(4).toString()));
					ordemServico.setDataFinalizacao(LocalDate.parse(rs.getDate(5).toString()));
					ordemServico.setValor(rs.getBigDecimal(6));
					ordemServico.setObservacao(rs.getString(7));
					ordemServico.setCliente(cliente);
					ordemServicos.add(ordemServico);
				}
			}
			return ordemServicos;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro durante a consulta", sqlException);
		}
	}
	
	public OrdemServico getOrdemServicoPeloCodigo(Long codigo) {
		String sql = "select * from ordem_servico where codigo=?";
		OrdemServico ordemServico = null;
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, codigo);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					ordemServico = new OrdemServico();
					ordemServico.setCodigo(rs.getLong(1));
					ordemServico.setDescricao(rs.getString(2));
					ordemServico.setStatus(OrdemServicoStatus.valueOf(rs.getString(3)));
					ordemServico.setDataEmissao(LocalDate.parse(rs.getDate(4).toString()));
					ordemServico.setDataFinalizacao(LocalDate.parse(rs.getDate(5).toString()));
					ordemServico.setValor(rs.getBigDecimal(6));
					ordemServico.setObservacao(rs.getString(7));
					Cliente cliente = new Cliente();
					cliente.setCodigo(rs.getLong(8));
					ordemServico.setCliente(cliente);
				}
			}
			return ordemServico;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro durante a consulta", sqlException);
		}
	}

	public Boolean update(OrdemServico ordemServico) {
		String sql = "update ordem_servico set " 
				+ "descricao=?," 
				+ "status=?," 
				+ "data_emissao=?," 
				+ "data_finalizacao=?,"
				+ "valor=?"
				+ "observacao=?"
				+ "codigo_cliente"
				+ " where codigo=?";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, ordemServico.getDescricao());
			ps.setString(2, ordemServico.getStatus().toString());
			ps.setDate(3, Date.valueOf(ordemServico.getDataEmissao()));
			ps.setDate(4, Date.valueOf(ordemServico.getDataFinalizacao()));
			ps.setBigDecimal(5, ordemServico.getValor());
			ps.setString(6, ordemServico.getObservacao());
			ps.setLong(7, ordemServico.getCliente().getCodigo());
			ps.setLong(8, ordemServico.getCodigo());
			ps.executeUpdate();
			return true;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro ao atualizar dados", sqlException);
		}
	}

	public Boolean delete(OrdemServico ordemServico) {
		String sql = "delete from ordem_servico where codigo=?";
		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, ordemServico.getCodigo());
			ps.executeUpdate();
			return true;
		} catch (SQLException sqlException) {
			throw new RuntimeException("Erro ao remover dados", sqlException);
		}
	}
}
