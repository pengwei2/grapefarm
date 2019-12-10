

window.onload=function() {
	var url = location.search;
	if (url.indexOf("amount") != -1) {
		var request = GetRequest();
		var amount = request['amount'];
		var serial = request['serial'];

		setAmount(amount);
		setSerial(serial);
	}
}

function GetRequest() {
	var url = location.search; //获取url中"?"符后的字串
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
		}
	}
	return theRequest;
}

function setAmount(amount) {
    document.getElementById("amount").innerHTML=amount;
}

function setSerial(serial) {
    document.getElementById("serial").innerHTML=serial;
}

function pay(status) {
	if (status == 'failure') {
		// 支付失败
	} else if(status == 'successfully') {
		//支付成功
	}

    window.webkit.messageHandlers.modularHander.postMessage(status);
}