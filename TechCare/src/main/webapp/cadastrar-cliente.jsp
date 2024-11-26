<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!doctype html>
<html lang="pt-BR">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link href="css/styles.css" rel="stylesheet">
<link href="css/errors.css" rel="stylesheet">
<title>Cadastro de cliente</title>
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
			
			<form action="cadastrar-cliente" method="post" class="container mt-4">
			    <h1 class="text-center mb-4">Cadastro de Clientes</h1>
			
			    <div class="row">
			        <!-- Nome e Email -->
			        <div class="col-md-6">
			            <div class="form-item mb-3">
			                <label for="nome" class="form-label">Nome:</label>
			                <input type="text" id="nome" name="nome" class="form-control" required>
			            </div>
			        </div>
			        <div class="col-md-6">
			            <div class="form-item mb-3">
			                <label for="email" class="form-label">Email:</label>
			                <input type="email" id="email" name="email" class="form-control" required>
			            </div>
			        </div>
			    </div>
			
			    <div class="row">
			        <!-- Telefone e CPF -->
			        <div class="col-md-6">
			            <div class="form-item mb-3">
			                <label for="telefone" class="form-label">Telefone:</label>
			                <input type="text" id="telefone" name="telefone" class="form-control" required>
			            </div>
			        </div>
			        <div class="col-md-6">
			            <div class="form-item mb-3">
			                <label for="cpf" class="form-label">CPF:</label>
			                <input type="text" id="cpf" name="cpf" class="form-control" required>
			            </div>
			        </div>
			    </div>
			
			    <div class="row">
			        <!-- CEP e Logradouro -->
			        <div class="col-md-6">
			            <div class="form-item mb-3">
			                <label for="cep" class="form-label">CEP:</label>
			                <input type="text" id="cep" name="cep" class="form-control" required>
			            </div>
			        </div>
			        <div class="col-md-6">
			            <div class="form-item mb-3">
			                <label for="logradouro" class="form-label">Logradouro:</label>
			                <input type="text" id="logradouro" name="logradouro" class="form-control" required>
			            </div>
			        </div>
			    </div>
			
			    <div class="row">
			        <!-- Complemento e Bairro -->
			        <div class="col-md-6">
			            <div class="form-item mb-3">
			                <label for="complemento" class="form-label">Complemento:</label>
			                <input type="text" id="complemento" name="complemento" class="form-control">
			            </div>
			        </div>
			        <div class="col-md-6">
			            <div class="form-item mb-3">
			                <label for="bairro" class="form-label">Bairro:</label>
			                <input type="text" id="bairro" name="bairro" class="form-control" required>
			            </div>
			        </div>
			    </div>
			
			    <div class="row">
			        <!-- Número e Cidade -->
			        <div class="col-md-6">
			            <div class="form-item mb-3">
			                <label for="numero" class="form-label">Número:</label>
			                <input type="text" id="numero" name="numero" class="form-control" required>
			            </div>
			        </div>
			        <div class="col-md-6">
			            <div class="form-item mb-3">
			                <label for="cidade" class="form-label">Cidade:</label>
			                <input type="text" id="cidade" name="cidade" class="form-control" required>
			            </div>
			        </div>
			    </div>
			
			    <div class="row">
			        <!-- Estado -->
			        <div class="col-md-6">
			            <div class="form-item mb-3">
			                <label for="estado" class="form-label">Estado:</label>
			                <input type="text" id="estado" name="estado" class="form-control" required>
			            </div>
			        </div>
			    </div>
			
			    <div class="button-group">
			        <input type="submit" value="Cadastrar" class="btn btn-primary mt-3">
			
			    </div>
			</form>
		</div>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0ANJ9MwpLCa0jwY0gn4zhtcG9wRUn6sE3VoJ8m8QIQvwrxdz" crossorigin="anonymous"></script>
	
	<script type="text/javascript" src="js/buscarCep.js"></script>
</body>
</html>