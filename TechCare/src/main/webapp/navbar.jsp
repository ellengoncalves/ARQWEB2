<!DOCTYPE html>
<html lang="pt-BR">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/estilos.css">
	<title>Página Inicial</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<div class="container-fluid">
			<a class="navbar-brand" href="home.jsp">Hightech</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<!-- Ordens de Serviço Dropdown -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="ordensDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Ordens de Serviço
						</a>
						<ul class="dropdown-menu" aria-labelledby="ordensDropdown">
							<li><a class="dropdown-item" href="cadastrarOrdemServico.jsp">Cadastrar Ordem Serviço</a></li>
							<li><a class="dropdown-item" href="listarOrdensServico.jsp">Listar Ordem de Serviço</a></li>
						</ul>
					</li>
					<!-- Forma de Pagamento Dropdown -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="pagamentoDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Forma de Pagamento
						</a>
						<ul class="dropdown-menu" aria-labelledby="pagamentoDropdown">
							<li><a class="dropdown-item" href="cadastrarFormaPagamento.jsp">Cadastrar Forma de Pagamento</a></li>
							<li><a class="dropdown-item" href="listarFormasPagamento.jsp">Listar Forma de Pagamento</a></li>
						</ul>
					</li>
					<!-- Clientes Dropdown -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="clientesDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Clientes
						</a>
						<ul class="dropdown-menu" aria-labelledby="clientesDropdown">
							<li><a class="dropdown-item" href="cadastrarCliente.jsp">Cadastrar Cliente</a></li>
							<li><a class="dropdown-item" href="listarClientes.jsp">Listar Cliente</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>