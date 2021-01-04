/**
 * 
 */
$(function(){
	$("#cart").click(function(){
		var flag = true;
		$.ajax({
			async:false,
	        url: "Cart",
	        type: "POST",
	        data: {
	        	"method" : "checkCart",
	        },
	        success: function (msg) {
	            if(msg == "your cart is empty"){
	            	flag = false;
	            	alert("your cart is empty");
	            }
	            
	        },
	        error: function(){
	            console.log("error occurred while making ajax call;")
	        }
	    });    
		
		return flag;
	});
	$("input[name='number']").change(function(){
		var quantityVal = $.trim(this.value);
		var flag = false;
		var reg = /^\d+$/g;
	    var quantity = -1;
	    if (reg.test(quantityVal)) {
	    	quantity = parseInt(quantityVal);
	    	if (quantity>=0) {
	    		flag = true;
	    	}
	    }
	    if (!flag ) {
	        alert("Please provide valid quantity");
	        return;
	    }
	    var $tr = $(this).parent().parent();
	    var productName = $.trim($tr.find("td:first").find("a:eq(1)").text());
	    $.ajax({
	        url: "Cart",
	        type: "POST",
	        data: {
	        	"method" : "updateQuantity",
	        	"productName" : productName,
	        	"quantity":quantity
	        },
	        success: function (msg) {
	            window.location.reload();
	        },
	        error: function(){
	            console.log("error occurred while making ajax call;")
	        }
	    });    
	    
	});
	$("input[name='quantity']").change(function(){
		console.log(this.value);
		var quantityVal = $.trim(this.value);
		var flag = false;
		var reg = /^\d+$/g;
	    var quantity = -1;
	    if (reg.test(quantityVal)) {
	    	quantity = parseInt(quantityVal);
	    	if (quantity>=0) {
	    		flag = true;
	    	}
	    }
	    if (!flag ) {
	        alert("Please provide valid quantity");
	        return;
	    }
	    var $div = $(this).parent().parent();
	    var productName = $.trim($div.find("p:first").text());
	    $.ajax({
	        url: "Cart",
	        type: "POST",
	        data: {
	        	"method" : "updateQuantity",
	        	"productName" : productName,
	        	"quantity":quantity
	        },
	        success: function (msg) {
	            window.location.reload();
	        },
	        error: function(){
	            console.log("error occurred while making ajax call;")
	        }
	    });    
	    
	});
	$("button[name='del']").click(function(){
	    var $tr = $(this).parent().parent();
	    var productName = $.trim($tr.find("td:first").find("a:eq(1)").text());
		$.ajax({
	        url: "Cart",
	        type: "POST",
	        data: {
	        	"method" : "deleteProduct",
	        	"productName" : productName
	        },
	        success: function (msg) {
	            window.location.reload();
	        },
	        error: function(){
	            console.log("error occurred while making ajax call;")
	        }
	    });    
	});
	$("button[name='delete']").click(function(){
		  var $div = $(this).parent().parent();
		   var productName = $.trim($div.find("p:first").text());
		$.ajax({
	        url: "Cart",
	        type: "POST",
	        data: {
	        	"method" : "deleteProduct",
	        	"productName" : productName
	        },
	        success: function (msg) {
	            window.location.reload();
	        },
	        error: function(){
	            console.log("error occurred while making ajax call;")
	        }
	    });    
	});
});
