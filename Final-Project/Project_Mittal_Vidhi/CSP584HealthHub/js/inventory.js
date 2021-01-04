google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback();

function chart() {
	console.log("here");
    $.ajax({
        url: "DataAnalysis",
        type: "GET",
        data: {
        	method:"inventory"
        },
        success: function (msg) {
        	console.log(msg);
            createChartTable(msg)  
        },
        error: function(){
            console.log("error occurred while making ajax call;")
        }
    });    
}
$("#viewProducts").click(function () {
    $.ajax({
        url: "Inventory?button=viewProducts",
        type: "POST",
        data: "{}",
        success: function (msg) {
          createAllProductsTable(msg);
        },
        error: function(){
            console.log("error occurred while making ajax call;")
        }
    });    
});
$("#viewProductsOnSale").click(function () {
    $.ajax({
        url: "Inventory?button=viewProductsOnSale",
        type: "POST",
        data: "{}",
        success: function (msg) {
            createDiscountTable(msg);
        },
        error: function(){
            console.log("error occurred while making ajax call;")
        }
    });    
});
$("#viewRebatedProducts").click(function () {
    $.ajax({
        url: "Inventory?button=viewRebatedProduct",
        type: "POST",
        data: "{}",
        success: function (msg) {
            createRebateTable(msg);
        },
        error: function(){
            console.log("error occurred while making ajax call;")
        }
    });    
});
function createAllProductsTable(jsonData){

  var parsedData = $.parseJSON(jsonData);
  var tableData = "<table  class='gridtable'>";
  tableData += "<tr><td>Product Name</td><td>Product Price</td><td>Product Inventory</td></tr>";
  for(var i=0; i<parsedData.length; i++){
    var productName = parsedData[i]["name"];
    var productPrice = parsedData[i]['price'];
    var productInventory = parsedData[i]["inventory"];
    tableData += "<tr><td>" + productName + "</td>";
    tableData += "<td>" + productPrice+ "</td>";
    tableData += "<td>" + productInventory + "</td></tr>";
  }
  tableData += "</table>";
  $("#chart_div").html(tableData);
}
function createRebateTable(jsonData){

  var parsedData = $.parseJSON(jsonData);
  var tableData = "<table  class='gridtable'>";
  tableData += "<tr><td>Product Name</td><td>Product Price</td><td>Product Inventory</td><td>Product Rebate</td></tr>";
  for(var i=0; i<parsedData.length; i++){
    var productName = parsedData[i]["name"];
    var productPrice = parsedData[i]['price'];
    var productInventory = parsedData[i]["inventory"];
    var productRebate = parsedData[i]['rebate'];
    tableData += "<tr><td>" + productName + "</td>";
    tableData += "<td>" + productPrice+ "</td>";
    tableData += "<td>" + productInventory + "</td>";
    tableData += "<td>" + productRebate + "</td></tr>";
  }
  tableData += "</table>";
  $("#chart_div").html(tableData);
}
var buttons = "<h3><button id='chartData'>View Chart</h3>" + "<h3><button id='viewProducts'>All Products</h3>" + "<h3><button id='viewRebatedProducts'>Rebated Products</h3>" + "<h3><button id='viewProductsOnSale'>Products on sale</h3>";
function createDiscountTable(jsonData){

  var parsedData = $.parseJSON(jsonData);
  var tableData = "<table  class='gridtable'>";
  tableData += "<tr><td>Product Name</td><td>Product Price</td><td>Product Inventory</td><td>Product Discount</td></tr>";
  for(var i=0; i<parsedData.length; i++){
    var productName = parsedData[i]["name"];
    var productPrice = parsedData[i]['price'];
    var productInventory = parsedData[i]["inventory"];
    var productDiscount = parsedData[i]['discount'];
    tableData += "<tr><td>" + productName + "</td>";
    tableData += "<td>" + productPrice+ "</td>";
    tableData += "<td>" + productInventory + "</td>";
    tableData += "<td>" + productDiscount + "</td></tr>";
  }
  tableData += "</table>";
  $("#chart_div").html(tableData);
}
function createChartTable(jsonData){
  var parsedData = $.parseJSON(jsonData);
  
  var data = new Array();
  data[0] = ['Product', 'Inventory'];

  //Create an array of product name and an array of stock
    for(var i=0; i<parsedData.length; i++) {
        var productName = parsedData[i]["name"];
        var productInventory = parsedData[i]["inventory"];
        var tmp = new Array();
        tmp.push(productName);
        tmp.push(parseInt(productInventory,10));
        data.push(tmp);
     }
     console.log(data)
     drawChart(data);
}
function drawChart(data) {

      var chartData = google.visualization.arrayToDataTable(data);

      var options = {
        'width':600,
        'height':650,
        title: 'Available Products',
        chartArea: {width: '50%'},
        hAxis: {
          title: 'Inventory',
          minValue: 0,
          textStyle: {
            bold: true,
            fontSize: 20,
            color: '#4d4d4d'
          },
          titleTextStyle: {
            bold: true,
            fontSize: 18,
            color: '#4d4d4d'
          }
        },
        vAxis: {
          title: 'Product Name',
          textStyle: {
            fontSize: 20,
            bold: true,
            color: '#848484'
          },
          titleTextStyle: {
            fontSize: 14,
            bold: true,
            color: '#848484'
          }
        }
      };
      $("#chart_div").removeAttr("style");
      document.getElementById("table").setAttribute("style", "display:none");
      var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
      chart.draw(chartData, options);
    }