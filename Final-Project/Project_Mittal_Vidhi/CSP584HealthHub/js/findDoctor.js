 $("#findDoctor").click(function () {
        	
		    $.ajax({
		        url: "DoctorServlet",
		        type: "GET",
		        data: {
		        	method:"nearbyDoctor"
		        },
		        success: function (msg) {
		        	
		            var parsedData = $.parseJSON(msg);
		            console.log(parsedData);
		            var coords = Array();
		            var data = Array();
		            for(var i=0; i<parsedData.length; i++) {
		                var addr = parsedData[i]["address"];
		                var zip = parsedData[i]["zipcode"];
		                var longtitude = parsedData[i]["longtitude"];
		                var id = parsedData[i]["id"];
						longtitude = longtitude.trim();
		                var latitude = parsedData[i]["latitude"];
		                latitude = latitude.trim();
		                coords.push({
		                    location : new google.maps.LatLng(longtitude, latitude),
		                });
		                data.push({
		                    zipcode : zip,
		                    address : addr, 
		                    id : id
		                });
		               
		            }
		            initMap(coords, data);  
		        },
		        error: function(){
		            console.log("error occurred while making ajax call;")
		        }
		    });    
		});
        let map, heatmap;
        function initMap(coords, data) {    
        	 console.log(data);
             var css = "width:100%; height:480px";
	         document.getElementById("map").setAttribute("style", css);
	         document.getElementById("doctorArea").setAttribute("style", "display:none");
	         $("#mapBorder").removeAttr("style");
	         map = new google.maps.Map(document.getElementById("map"), {
	            zoom: 13,
	            mapTypeId: "roadmap",
	          });
	         map.setCenter(coords[0]["location"]);
	          heatmap = new google.maps.visualization.HeatmapLayer({
	            data: coords,
	            map: map,
	          });
          for(var i=0; i<coords.length; i++){
            var text = "<a href='appointment.jsp?doctorId=" + data[i]["id"] + "'>" + data[i]["address"] + ", " + data[i]["zipcode"] + "</a>";
            addMarker(coords[i]["location"],text);
          }
        }
        function addMarker(coord, data){
        	var marker = new google.maps.Marker({
            position:coord,
            map:map,
          });
          var infoWindow = new google.maps.InfoWindow({
            content : data
          });
          marker.addListener('mouseover', function(){
            infoWindow.open(map, marker);
          });
        }
        /**
 * 
 */