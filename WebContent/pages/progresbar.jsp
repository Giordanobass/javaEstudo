<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Barra de Progresso</title>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">
/*fundo da barra de progresso */
#myProgress {
	width: 100%;
	background-color: #ddd;
}

#myBar {
	width: 1%;
	height: 30px;
	background-color: #4caf50;
}

.ui-progressbar {
	position: relative;
}

.progress-label {
	position: relative;
	left: 50%;
	top: 4px;
	font-wight: bold;
	text-shadow: 1px 1px 0 #fff;
}
</style>

</head>
<body>
	<h3>Exemplo com javascript</h3>

	<div id="myProgress">
		<div id="myBar"></div>
	</div>
	<br />
	<button onclick="exibirBarra()">Inicar barra de progresso</button>

	<br />
	<h2>Barra de progresso com jQuery</h2>
	<div id="progressbar">
		<div class="progress-label">Carregando...</div>
	</div>


	<script type="text/javascript">
		//barra de progresso por jQuery
		$(function() {//sem nome para ser executada diretamente
			var progressbar = $("#progressbar"), progresslabel = $(".progress-label");

			//cria a barra na div
			progressbar.progressbar({
				value : false,
				change : function() {
					progresslabel.text(progressbar.progressbar('value') + "%");
				},
				complete : function() {
					progresslabel.text('Completo!');
				}
			});

			function progress() {
				var val = progressbar.progressbar("value") || 0;
				progressbar.progressbar("value", val + 2);
				if (val < 99) {
					setTimeout(progress, 80);
				}
			}
			setTimeout(progress, 2000)
		})

		//barra de progresso por javascript
		function exibirBarra() {
			var elem = document.getElementById("myBar");
			var width = 1;
			var id = setInterval(frame, 10);

			function frame() {
				if (width >= 100) {
					clearInterval(id);
				} else {
					width++;
					elem.style.width = width + "%";
				}
			}
		}
	</script>
</body>
</html>