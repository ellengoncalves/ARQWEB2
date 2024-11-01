<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao.ClienteDao" %>
<%@ page import="br.edu.ifsp.arq.tsi.arqweb2.techcare.model.Cliente" %>
<%@ page import="br.edu.ifsp.arq.tsi.arqweb2.techcare.model.dao.FormaPagamentoDao" %>
<%@ page import="br.edu.ifsp.arq.tsi.arqweb2.techcare.model.FormaPagamento" %>
<%@ page import="br.edu.ifsp.arq.tsi.arqweb2.techcare.utils.DataSourceSearcher" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link href="css/styles.css" rel="stylesheet">
<link href="css/errors.css" rel="stylesheet">
<title>Cadastro de Ordem de Serviço</title>
</head>
<body>
	<div class="container">
		<div class="col-lg-6 offset-lg-3 col-sm-12">
			<c:if test="${result == 'notRegistered'}">
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					E-mail já cadastrado. Tente novamente.
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>
			
			<!-- sempre que for fomulário o método deve ser POST -->
			<form action="serviceRegister" method="post" id="form1">
				<h1 class="text-center">Cadastrar Ordem de Serviço</h1>
				
				<div class="mb-2">
					<label for="description">Descrição*</label> <input type="text"
						name="description" id="description" class="form-control" minlength="3"
						maxlength="500" required="required"> 
					<span id="0"></span>
				</div>
				
				<div class="mb-2">
					<label for="issueDate">Data de Emissao*</label> <input
						type="date" name="issueDate" id="issueDate"
						class="form-control" max="2025-02-15" required="required">
					<span id="1"></span>
				</div>
				
				<div class="mb-2">
					<label for="endDate">Data de Finalização*</label> <input
						type="date" name="endDate" id="endDate"
						class="form-control" max="2025-02-15" required="required">
					<span id="2"></span>
				</div>
				
				<div class="mb-2">
					<label for="value">Valor*</label> <input
						type="number" name="value" id="value"
						class="form-control" max="10000" required="required">
					<span id="3"></span>
				</div>
				
				<div class="mb-2">
					<label for="name">Observação*</label> <input type="text"
						name="name" id="name" class="form-control" minlength="3"
						maxlength="500" required="required"> <span id="4"></span>
				</div>
				
				<div class="mb-2">
					<button type="submit" class="btn btn-primary">Salvar</button>
				</div>
			</form>	
		</div>
	</div>
</body>
</html>