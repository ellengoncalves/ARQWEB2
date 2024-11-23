package br.edu.ifsp.arq.tsi.arqweb2.techcare.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.Endereco;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao.ClienteDao;
import br.edu.ifsp.arq.tsi.arqweb2.techcare.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cadastrarCliente")
public class CadastrarClienteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		String telefone = req.getParameter("telefone");
		String cpf = req.getParameter("cpf");
		String logradouro = req.getParameter("logradouro");
		String numero = req.getParameter("numero");
		String complemento = req.getParameter("complemento");
		String bairro = req.getParameter("bairro");
		String cep = req.getParameter("cep");
		String cidade = req.getParameter("cidade");
		String estado = req.getParameter("estado");

		Endereco endereco = new Endereco();
		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		endereco.setComplemento(complemento);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setCidade(cidade);
		endereco.setEstado(estado);

		cookies(resp, "nome", nome, "email", email, "telefone", telefone, "cpf", cpf, "numero", numero, "complemento",
				complemento, "cep", cep);

		ClienteDao clienteDao = new ClienteDao(DataSourceSearcher.getInstance().getDataSource());
		clienteDao.salvar(nome, email, telefone, cpf, endereco);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/listarClientes.jsp");
		dispatcher.forward(req, resp);
	}

	public void cookies(HttpServletResponse resp, String... dados) {

		if (dados.length % 2 != 0) {
			throw new IllegalArgumentException("Os dados devem ser passados em pares nome-valor.");
		}

		int expiraEmSegundos = 60 * 60 * 24 * 7; // 7 dias

		try {
			for (int i = 0; i < dados.length; i += 2) {
				String nomeCookie = dados[i];
				String valorCookie = dados[i + 1];

				String valorCookieCodificado = URLEncoder.encode(valorCookie, "UTF-8");

				Cookie cookie = new Cookie(nomeCookie, valorCookieCodificado);
				cookie.setMaxAge(expiraEmSegundos);
				resp.addCookie(cookie);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();

		}
	}
}