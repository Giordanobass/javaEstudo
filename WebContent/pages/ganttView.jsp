<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gantt View</title>

<link rel="stylesheet" type="text/css"
	href="../scriptGanttView/jquery-ui-1.8.4.css" />
<link rel="stylesheet" type="text/css"
	href="../scriptGanttView/reset.css" />
<link rel="stylesheet" type="text/css"
	href="../scriptGanttView/jquery.ganttView.css" />

<script type="text/javascript" src="../scriptGanttView/jquery-1.4.2.js"></script>
<script type="text/javascript" src="../scriptGanttView/date.js"></script>
<script type="text/javascript"
	src="../scriptGanttView/jquery-ui-1.8.4.js"></script>
<script type="text/javascript"
	src="../scriptGanttView/jquery.ganttView.js"></script>

<style type="text/css">
body {
	font-family: tahoma, verdana, helvetica;
	font-size: 0.8em;
	padding: 10px;
}
</style>

</head>
<body>
	<h1>Gantt View</h1>
	
	<div id="ganttChart"></div>
	<br/><br/>
	<div id="eventMessage"></div>
</body>


<script type="text/javascript">

$(document).ready(function() {
	
	
		$.get("buscarDatasPlanejamento", function(response) {
			
		// processa dados gant view INICIO	
			var ganttDataResposta = JSON.parse(response);
		
			var ganttData = "";
			 ganttData += "["; 
			 
				$.each(ganttDataResposta, function(index, projeto) {// for dos projetos
						
					ganttData += "{ \"id\": \""+projeto.id+"\", \"name\": \""+projeto.nome+"\", \"series\": [";
					
					$.each(projeto.series, function(idx, serie) {// for das series
						
						var cores = "#3366FF,#00CC00".split(',');
					
						var cor;
						if (idx === 0) {
							cor = "#CC33CC";
						}else {
						   cor = Number.isInteger(idx / 2) ? cores[0] : cores[1];
						}
						
						var datainicial = serie.datainicial.split('-');
						var datafinal = serie.datafinal.split('-');
						
						ganttData +="{ \"name\": \""+serie.nome+"\", \"start\":\""+ new Date(datainicial[0],datainicial[1],datainicial[2])+"\", \"end\": \""+new Date(datafinal[0],datafinal[1],datafinal[2])+"\" , \"color\": \""+cor+"\", \"projeto\": \""+serie.projeto+"\" , \"serie\": \""+serie.id+"\" }";
						
						if (idx < projeto.series.length - 1){
							ganttData +=",";
						}
					});// fim for da series
					
				    ganttData +="]}";// fecha o array json de series
				 
				   if (index < ganttDataResposta.length - 1){
					   ganttData +=",";
				   }
					
				});// fim for dos projetos
			
			 ganttData += "]";
			
			 ganttData = JSON.parse(ganttData);
			 
			// processa dados gant view FIM

			 $("#ganttChart").ganttView({ 
						data: ganttData,
						slideWidth: 600,
						behavior: {
							onClick: function (data) { 
								var msg = "Evento de click: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
								$("#eventMessage").text(msg);
							},
							onResize: function (data) { 
								
								var start = data.start.toString("yyyy-M-d");
								var end = 	data.end.toString("yyyy-M-d");
								$.post("buscarDatasPlanejamento", { start: start, end : end, serie : data.serie, projeto : data.projeto });
								
								var msg = "Evento de errastar: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
								$("#eventMessage").text(msg);
								
							},
							onDrag: function (data) { 
								
								var start = data.start.toString("yyyy-M-d");
								var end = 	data.end.toString("yyyy-M-d");
								$.post("buscarDatasPlanejamento", { start: start, end : end, serie : data.serie, projeto : data.projeto });
								
								var msg = "Evento de errastar: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
								$("#eventMessage").text(msg);
								
							}
							
						}
					});
				
        });	

});
	</script>
</html>