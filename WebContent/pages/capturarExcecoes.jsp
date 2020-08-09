<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Capturando exceções com jquery</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
	<h3>Capturando exceções com jquery</h3>
	<input type="text" value="Valor informado" id="txtvalor">
	<input type="button" onclick="testarExcecao();" value="Testar Exceção">
</body>
<script type="text/javascript">
	function testarExcecao(){
		var valorInformado = $('#txtvalor').val();
		$.ajax({
			method: "POST",
			url: "capturarExcecao",
			data: {valorParam: valorInformado}
			})
			.done(function(response){//resposta ok - nenhum erro
				alert("Sucesso!" + response);
			})
			.fail(function(xhr, status, errorThrown){//resposta ok - nenhum erro
				alert("ERROR! " + xhr.responseText);
			});
		}
</script>
</html>