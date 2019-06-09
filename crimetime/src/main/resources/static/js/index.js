
// var divMap=document.getElementById("map");
var map=L.map('map').setView([45.267, 19.833], 14);
var green;
var yellow;
var orange;
var red;
var clusters;

L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox.streets',
    accessToken: 'pk.eyJ1IjoidmFsZW50aW5ha3lmeHJ0ZGZpeWt1dHlzIiwiYSI6ImNqd256dmp6dTA5aGczem55MjVzYWF5eGsifQ.ZNvZnQeTL7wK7RpIOmizLw'
}).addTo(map);  

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
            best5=clusters.sort(function(a, b){return a.riskPoints-b.riskPoints}).slice(0, 5);
            worst5=clusters.sort(function(a, b){return b.riskPoints-a.riskPoints}).slice(0, 5);
            console.log('green: '+green);
            console.log('yellow: '+yellow);
            console.log('orange: '+orange);
            console.log('red: '+red);
            console.log('best: '+best5);
            console.log('worst: '+worst5);
            
            var i;
            for(i=0; i<clusters.length; i++){
            	var cluster=clusters[i];
            	if(clusters[i].numberOfAccidents<=green)
            		dangerZones(cluster, '#18ad1d');
            	else if(clusters[i].numberOfAccidents<=yellow)
            		dangerZones(cluster, '#dbdb30');
            	else if(clusters[i].numberOfAccidents<=orange)
            		dangerZones(cluster, '#db9930');
            	else if(clusters[i].numberOfAccidents<=red)
            		dangerZones(cluster, '#ce3227');
            }

            var worstEl = $('#worst5list');
            worst5.forEach(function(entry) {
                worstEl.append('<li>' + entry.name + '</li>');
            });

            var bestEl = $('#best5list');
            best5.forEach(function(entry) {
                bestEl.append('<li>' + entry.name + '</li>');
            });

        }
    });
}

function dangerZones(cluster, color){
    var circle = L.circle([cluster.lon, cluster.lat], {
        color: color,
        fillColor: color,
        fillOpacity: 0.5,
        radius: 450
    }).addTo(map);
}

//$.get(  'http://nominatim.openstreetmap.org/search?format=json&q='+"Novi Sad, Grbavica", function(data){
//       console.log(data[0]);
//       var pol = L.rectangle([[data[0].boundingbox[0],data[0].boundingbox[2]], 
//    	   [data[0].boundingbox[1],data[0].boundingbox[3]]], {
//           color: '#db9930',
//           fillColor: '#db9930',
//           fillOpacity: 0.5,
//           radius: 400
//       }).addTo(map);
//});

getClusters();