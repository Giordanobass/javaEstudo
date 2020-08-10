<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Barra de Progresso</title>

<style type="text/css">
/*fundo da barra de progresso */
#myProgress{
	width: 100%;
	background-color: #ddd;
}
#myBar{
	width: 1%;
	height: 30px;
	background-color: #4caf50;
}
</style>

</head>
<body>
<h3>Exemplo com javascript</h3>

<div id="myProgress">
	<div id="myBar">
	
	</div>
</div>
<br/>
<button onclick="exibirBarra()">Inicar barra de progresso</button>
<script type="text/javascript">
function exibirBarra(){
	var elem = document.getElementById("myBar");
	var width = 1;
	var id = setInterval(frame, 10);

	function frame(){
		if(width >=100){
			clearInterval(id);
			}else{
				width++;
				elem.style.width = width + "%";
			}
		}
}
</script>
</body>
</html>