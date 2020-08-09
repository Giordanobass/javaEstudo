<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Página de autenticação</title>
</head>
<body>
	<h3>Autenticar usuário!</h3>
	<form action="ServletAutenticacao" method="post">
	<input readonly="readonly" type="hidden" id="url" value="<%= request.getParameter("url")%>">
		<table>
			<tr>
			<td>Login: </td>
			<td><input type="text" id="login" name="login"></td>
			</tr>
			<tr>
			<td>Senha: </td>
			<td><input type="password" id="senha" name="senha"></td>
			</tr>
			<tr>
			<td></td>
			<td><input type="submit" id="logar" name="logar"></td>
			</tr>
		</table>
	</form>
</body>
</html>