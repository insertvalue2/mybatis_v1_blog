<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body>
	<h1>test</h1>
	
    <div id="chart_div"></div>
	
	<script type="text/javascript">
		// Load the Visualization API and the corechart package.
		google.charts.load('current', {
			'packages' : [ 'corechart' ]
		});

		// Set a callback to run when the Google Visualization API is loaded.
		google.charts.setOnLoadCallback(drawChart);

		// Callback that creates and populates a data table,
		// instantiates the pie chart, passes in the data and
		// draws it.
		function drawChart() {
			let myData =  JSON.parse(`${list}`);
			console.log(typeof myData);
			console.log(myData);
			
			// Create the data table.
			var data = new google.visualization.DataTable();
			data.addColumn('string', 'Topping');
			data.addColumn('number', 'Slices');
			
			data.addRows([
				[ myData[0].username, 3 ],
				[  myData[1].username, 1 ],
					[ myData[2].username, 1 ], [ myData[3].username, 1 ], [myData[4], 2 ] ]);

			// Set chart options
			var options = {
				'title' : 'How Much Pizza I Ate Last Night',
				'width' : 400,
				'height' : 300
			};

			// Instantiate and draw our chart, passing in some options.
			var chart = new google.visualization.PieChart(document
					.getElementById('chart_div'));
			
			chart.draw(data, options);
		}
	</script>
</body>
</html>