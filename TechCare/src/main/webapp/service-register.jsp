<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
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
		
		<c:if test="${result == 'registered'}">
				<div class="alert alert-success alert-dismissible fade show"
					role="alert">
					Ordem de Serviço salva com sucesso!
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>
			
			<!-- sempre que for fomulário o método deve ser POST -->
			<form action="serviceRegister" method="post" id="form1">
				<c:choose>
					<c:when test="${ordemServico == null}">
						<input type="hidden" name="codigo" value="0">
					</c:when>
					<c:when test="${ordemServico != null}">
						<input type="hidden" name="codigo" value="${ordemServico.codigo}">
					</c:when>
				</c:choose>
				
				<h1 class="text-center">Cadastrar Ordem de Serviço</h1>
				
				<div class="mb-2">
					<label for="descricao">Descrição*</label> <input type="text"
						name="descricao" id="descricao" class="form-control" minlength="3"
						maxlength="500" required="required"> 
					<span id="0"></span>
				</div>
				
				<div class="mb-2">
					<label for="dataEmissao">Data de Emissao*</label> <input
						type="date" name="dataEmissao" id="dataEmissao"
						class="form-control" max="2025-02-15" required="required">
					<span id="1"></span>
				</div>
				
				<div class="mb-2">
					<label for="dataFinalizacao">Data de Finalização*</label> <input
						type="date" name="dataFinalizacao" id="dataFinalizacao"
						class="form-control" max="2025-02-15">
					<span id="2"></span>
				</div>
				
				<div class="mb-2">
					<label for="valor">Valor*</label> <input
						type="number" name="valor" id="valor"
						class="form-control" max="10000" required="required">
					<span id="3"></span>
				</div>
				
				<div class="mb-2">
					<label for="observacao">Observação*</label> <input type="text"
						name="observacao" id="observacao" class="form-control" minlength="1"
						maxlength="500" required="required"> <span id="4"></span>
				</div>
				
				<div class="mb-2">
					<label for="observacao">Observação*</label> <input type="text"
						name="observacao" id="observacao" class="form-control" minlength="1"
						maxlength="500" required="required"> <span id="4"></span>
				</div>
				
				<div class="mb-2">
					<label for="status">Status da Ordem de Serviço*</label> 
					<select class="form-select"
						name="status" id="status" required="required">
						<c:if test="${ordemServico == null}">
							<option value="" selected>Selecione</option>
						</c:if>
						<c:choose>
							<c:when test="${ordemServico.status != 'EM_APROVACAO'}">
								<option value="EM_APROVACAO">Em aprovação</option>
							</c:when>
							<c:when test="${ordemServico.type == 'EM_APROVACAO'}">
								<option value="EM_APROVACAO" selected>Em aprovação</option>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${ordemServico.status != 'APROVADA'}">
								<option value="APROVADA">Aprovada</option>
							</c:when>
							<c:when test="${ordemServico.status == 'APROVADA'}">
								<option value="APROVADA" selected>Aprovada</option>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${ordemServico.status != 'EM_ANDAMENTO'}">
								<option value="EM_ANDAMENTO">Em andamento</option>
							</c:when>
							<c:when test="${ordemServico.status == 'EM_ANDAMENTO'}">
								<option value="EM_ANDAMENTO" selected>Em andamento</option>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${ordemServico.status != 'FINALIZADA'}">
								<option value="FINALIZADA">Finalizada</option>
							</c:when>
							<c:when test="${ordemServico.status == 'FINALIZADA'}">
								<option value="FINALIZADA" selected>Finalizada</option>
							</c:when>
						</c:choose>
					</select>
				</div>
				
				<div class="mb-2">
					<button type="submit" class="btn btn-primary">Salvar</button>
				</div>
			</form>	
		</div>
	</div>
</body>
</html>