

var green;
var yellow;
var orange;
var red;
var clusters;
var naselja;


$.getJSON("naselja.json", function(json) {
	naselja=json;
    console.log(json); 
}); 

function getClusters(){
    $.ajax({
        url: 'http://localhost:8080/api/filter',
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type':'application/json'
        },
        method: 'GET',
        data: '',
        success: function(data){
        	green=data.green;
        	yellow=data.yellow;
        	orange=data.orange;
        	red=data.red;
        	clusters=data.clusters;
            console.log('green: '+green);
            console.log('yellow: '+yellow);
            console.log('orange: '+orange);
            console.log('red: '+red);
       
            var map = L.map('map').setView([45.267, 19.833], 14);

            L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoidmFsZW50aW5ha3lmeHJ0ZGZpeWt1dHlzIiwiYSI6ImNqd256dmp6dTA5aGczem55MjVzYWF5eGsifQ.ZNvZnQeTL7wK7RpIOmizLw' , {
                id: 'mapbox.streets',
                attribution: ''
            }).addTo(map);
            L.geoJson(naselja, {style: style}).addTo(map);
        }
    });
}
function style(naselje) {
	var test=getColor(naselje);
	return {
        fillColor: test,
        weight: 2,
        opacity: 1,
        color: 'white',
        dashArray: '3',
        fillOpacity: 0.3
    }
}

function getColor(naselje){
	 var i;
	 for(i=0; i<clusters.length; i++){
	  	var cluster=clusters[i];
	  	if(clusters[i].numberOfAccidents<=green && clusters[i].name===naselje.properties.name)
	  		return'green';
	    else if(clusters[i].numberOfAccidents<=yellow && clusters[i].name===naselje.properties.name)
		    return'yellow';
	    else if(clusters[i].numberOfAccidents<=orange && clusters[i].name===naselje.properties.name)
		    return  'orange';
	    else if(clusters[i].numberOfAccidents<=red && clusters[i].name===naselje.properties.name)
		    return 'red';
	    
	  }
}

function dangerZones(cluster, color){
	
    var circle = L.circle([cluster.lon, cluster.lat], {
        color: color,
        fillColor: color,
        fillOpacity: 0.5,
        radius: 450
    }).addTo(map);
}

getClusters();