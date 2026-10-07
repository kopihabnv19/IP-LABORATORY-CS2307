function loadOrders() {
    var output = document.getElementById("orderData");
    output.textContent = "Loading orders...";
    var xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        var xml = this.responseXML;
        if (this.status !== 200 || !xml || xml.getElementsByTagName("parsererror").length) {
            output.textContent = "Unable to load orders.xml.";
            return;
        }
        var orders = xml.getElementsByTagName("order");
        var table = document.createElement("table");
        table.border = "1";
        table.cellPadding = "10";
        var heading = table.insertRow();
        ["ID", "Customer", "Product", "Quantity", "Price"].forEach(function (label) {
            var th = document.createElement("th");
            th.textContent = label;
            heading.appendChild(th);
        });
        for (var i = 0; i < orders.length; i++) {
            var row = table.insertRow();
            ["id", "customer", "product", "quantity", "price"].forEach(function (field) {
                var value = orders[i].getElementsByTagName(field)[0];
                row.insertCell().textContent = value ? value.textContent : "";
            });
        }
        output.textContent = "";
        var title = document.createElement("h2");
        title.textContent = "Order Details";
        output.appendChild(title);
        output.appendChild(table);
    };
    xhttp.onerror = function () { output.textContent = "Request failed. Open this page through localhost."; };
    xhttp.open("GET", "orders.xml", true);
    xhttp.send();
}
