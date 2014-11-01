$('.dropdown-toggle').dropdown();
var mapProp, map, myCenter, marker, infowindow;
var urlBase = '../../maplocation/';
function initialize(){
mapProp = {
  center:new google.maps.LatLng(-3.735746, -38.499307),
  zoom:12,
  mapTypeId:google.maps.MapTypeId.ROADMAP
  };
map=new google.maps.Map($("#googleMap").get(0), mapProp);
infowindow = new google.maps.InfoWindow({
	  content:"Hello World!"
	});
}
google.maps.event.addDomListener(window, 'load', initialize);


$('#selectLocation').on('change', function(){
	var idLocation = $("#selectLocation").val();
	$.ajax({  
          url:urlBase+'location/obtem/'+idLocation,
          dataType:'json',
          success:function(data){
        	  plotaPontoDa(data.location);
          },
          error:function(){
             alert('erro');
          }
    });
});

function plotaPontoDa(location){
	myCenter=new google.maps.LatLng(location.latitude, location.longitude);
	marker=new google.maps.Marker({
	  	position:myCenter,
	});
	marker.setMap(map);
	addClickListener(location);
};

function addClickListener(location){
	google.maps.event.addListener(marker, 'click', function() {
		$.ajax({  
	        url:urlBase+'location/getAddress?latitude='+location.latitude+'&longitude='+location.longitude,
	        dataType:'json',
	        success:function(data){
	      	  exibe(data.address, marker);
	        },
	        error:function(){
	           alert('erro');
	        }
	  });
});
	
function exibe(address, marker ){
	infowindow = new google.maps.InfoWindow({
		  content:address
		});
	infowindow.open(map,marker);
};
}