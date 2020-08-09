<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Página Pai Load jQuery</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
<h3>Página pai load jQuery</h3>
<input type="button" value="Carregar página" onclick="carregar()">

<div id="mostrarPaginaFilha"></div>
</body>
<script type="text/javascript">
function carregar(){
	$("#mostrarPaginaFilha").load('paginaFilha.jsp');//Load página em jQuery
}
</script>
</html>