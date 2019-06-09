

var green;
var yellow;
var orange;
var red;
var clusters;
var naselja;
var geojson;
var map;

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
            best5=clusters.sort(function(a, b){return a.riskPoints-b.riskPoints}).slice(0, 5);
            worst5=clusters.sort(function(a, b){return b.riskPoints-a.riskPoints}).slice(0, 5);
            console.log('green: '+green);
            console.log('yellow: '+yellow);
            console.log('orange: '+orange);
            console.log('red: '+red);
       
            map = L.map('map').setView([45.267, 19.833], 14);

            L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoidmFsZW50aW5ha3lmeHJ0ZGZpeWt1dHlzIiwiYSI6ImNqd256dmp6dTA5aGczem55MjVzYWF5eGsifQ.ZNvZnQeTL7wK7RpIOmizLw' , {
                id: 'mapbox.streets',
                attribution: ''
            }).addTo(map);
            geojson=L.geoJson(naselja, {style: style,onEachFeature: onEachFeature}).addTo(map);
            console.log('best: '+best5);
            console.log('worst: '+worst5);
            
            var worstEl = $('#worst5list');
            worst5.forEach(function(entry) {
                worstEl.append('<li class="center">' + entry.name + '</li>');
            });

            var bestEl = $('#best5list');
            best5.forEach(function(entry) {
                bestEl.append('<li class="center">' + entry.name + '</li>');
            });
            //-----LEGEND

            function getColor(d) {
                return  d > red   ? 'red':
                        d > orange   ? 'orange' :
                        d > yellow   ? 'yellow' :
                                    'green';
            }

            var legend = L.control({position: 'bottomright'});

            legend.onAdd = function (map) {

            var div = L.DomUtil.create('div', 'info legend'),
            grades = [green, yellow, orange,red],
            labels = [];

            // loop through our density intervals and generate a label with a colored square for each interval
            for (var i = 0; i < grades.length; i++) {
                div.innerHTML +=
                '<i style="background:' + getColor(grades[i] + 1) + '"></i> ' +
                grades[i] + (grades[i + 1] ? '&ndash;' + grades[i + 1] + '<br>' : '+');
        }

    return div;
};

legend.addTo(map);

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
	  	if(clusters[i].numberOfAccidents<=green && naselje.properties.name.includes(clusters[i].keyword))
	  		return'green';
	    else if(clusters[i].numberOfAccidents<=yellow && naselje.properties.name.includes(clusters[i].keyword))
		    return'yellow';
	    else if(clusters[i].numberOfAccidents<=orange && naselje.properties.name.includes(clusters[i].keyword))
		    return  'orange';
	    else if(clusters[i].numberOfAccidents<=red && naselje.properties.name.includes(clusters[i].keyword))
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

function highlightFeature(e) {

    var layer = e.target;

    layer.setStyle({
        weight: 2,
        color: '#666',
        dashArray: '',
        fillOpacity: 0.5
    });

    if (!L.Browser.ie && !L.Browser.opera && !L.Browser.edge) {
        layer.bringToFront();
    }
}
function resetHighlight(e) {
    geojson.resetStyle(e.target);
//    info.update();
}
function zoomToFeature(e) {
//    map.fitBounds(e.target.getBounds());
}

function onEachFeature(feature, layer) {
    layer.on({
        mouseover: highlightFeature,
        mouseout: resetHighlight,
        click: zoomToFeature
    });
}

getClusters();