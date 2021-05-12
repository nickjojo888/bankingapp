function connect() {

	var socket = new SockJS("/sws/stock");
	stompClient = Stomp.over(socket);

	stompClient.connect({}, function(frame) {
		setConnected(true);
		stompClient.subscribe("/topic/stocks", function(retrieveData) {
			display(retrieveData.body);
		});
	})
}

function disconnect() {
	stompClient.disconnect();
	setConnected(false);
	console.log("Disconnected from stomp client!")
}

function setConnected(connected) {
	document.getElementById("connect").disabled = connected;
	document.getElementById("disconnect").disabled = !connect;
}

function display(message) {
	if (message) {
		var stockList = JSON.parse(message).getStocks;
		var table = document.createElement('table');
		var tableBody = document.getElementById('stockBody');

		for (var i = 0; i < stockList.length; i++) {
			var tr = document.createElement('tr');
			for (var j = 0; j < 4; j++) {
				var td = document.createElement("td");
				td.innerHTML = stockList.get(i).getPrice();
			}
		}
	}
}
