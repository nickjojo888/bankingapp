var tableBody = document.querySelector("#stockBody");

function loadStocks(stockList) {
//	var stockNames = new Array();
//	for(let stock of stockList) {
//		stockNames.push(stock.getName());
//	}
	
	$.get("stockBody").done(function(fragment){
		$("#stockPrice").replaceWith(fragment);
	});
	
}

//setInterval(loadStocks, 2000);