<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" integrity="sha384-KyZXEJ7x4R3u/MKY3zF3SIA02vhFG8kTbjVRxt00LE+M/kkpU8MzYWz56mMlh5t1" crossorigin="anonymous">
    <!--<link rel="stylesheet" type="text/css" href="css/home.css">
    <LINK REL="stylesheet" type="text/css" href="css/styles.css">-->
    <title>Listar Clientes</title>
</head>
<body>

<jsp:include page="navbar.jsp" />

<header class="text-center my-4">
    <h1>Clientes Cadastrados</h1>
</header>

<div class="container">
    <table class="table table-striped table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th>Código</th>
            <th>Nome</th>
            <th>Email</th>
            <th>Telefone</th>
            <th>CPF</th>
            <th>Ativo</th>
            <th>Endereço</th>
            <th>A��es</th>
        </tr>
        </thead>
        <tbody>
        <tbody>

<%--        <c:if test="${not empty listaDeClientes}">--%>
            <c:forEach var="cliente" items="${listaDeClientes}" varStatus="index">
                <tr>

                    <td>${cliente.codigo}</td>
                    <td>${cliente.nome}</td>
                    <td>${cliente.email}</td>
                    <td>${cliente.telefone}</td>
                    <td>${cliente.cpf}</td>
                    <td>${cliente.ativo ? 'Sim' : 'Nao'}</td>
                    <td>${cliente.endereco.logradouro}, ${cliente.endereco.numero}, ${cliente.endereco.complemento}, ${cliente.endereco.bairro}, ${cliente.endereco.cep}, ${cliente.endereco.cidade} - ${cliente.endereco.estado}</td>
                    <td>
                        <a href="editarCliente?id=${cliente.codigo}" class="btn btn-primary btn-sm">Editar</a>
                        <a href="excluirCliente?id=${cliente.codigo}" onclick="return confirm('Tem certeza que deseja excluir este cliente?');" class="btn btn-danger btn-sm">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
<%--        </c:if>--%>
        <c:if test="${empty listaDeClientes}">
            <tr><td colspan="8" class="text-center">Número de clientes: ${cliente.size()}</td></tr>
        </c:if>
        </tbody>



        </tbody>
    </table>
    <a href="home.jsp" class="btn btn-secondary">Voltar para Home</a>
</div>

<!-- Link para o Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0ANJ9MwpLCa0jwY0gn4zhtcG9wRUn6sE3VoJ8m8QIQvwrxdz" crossorigin="anonymous"></script>

</body>
</html>