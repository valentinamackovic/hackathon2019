

var green;
var yellow;
var orange;
var red;
var clusters;
var naselja;
var geojson;
var map;
var info;

$.getJSON("naselja.json", function(json) {
	naselja=json;
    console.log(json); 
}); 

function getClusters(){


    //-----------CONTROL

    info = L.control();

    info.onAdd = function (map) {
        this._div = L.DomUtil.create('div', 'info'); // create a div with a class "info"
        this.update();
        return this._div;
    };

    // method that we will use to update the control based on feature properties passed
    info.update = function (props) {
    
        if(props != null){

            var cluster;
            clusters.forEach(function(entry) {
                if (props.name.startsWith(entry.keyword))
                    cluster = entry;
            });

            if (cluster != null) {
                var infoString  = " <h4> " + cluster.name + " </h4> \n";
                infoString += " <h6>Заступљеност<sup>1</sup>: " + cluster.riskProcent.toFixed(2) + "%</h6>\n ";
                infoString += " <h6>Број преступа<sup>2</sup>: "+ cluster.numberOfAccidents +"</h6>\n ";
                this._div.innerHTML = infoString;
            } else {
                var infoString  = " <h4> " + props.name.replace("banatic", "Банатић").replace("rotkvarija", "Роткварија").replace("?", "Слана Бара").replace("adamovicevo", "Адамовићево") + " </h4> \n";
                infoString += " <h6>Заступљеност: ускоро</h6>\n ";
                infoString += " <h6>Учесталост: ускоро</h6>\n ";
                this._div.innerHTML = infoString;
            }
        }else{
            this._div.innerHTML = '';
        }

};

    

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
       
            map = L.map('map').setView([45.267, 19.833], 12);
            info.addTo(map);

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

            var div = L.DomUtil.create('div', 'legend'),
            grades = [green, yellow, orange,red],
            labels = [];

            // loop through our density intervals and generate a label with a colored square for each interval
            for (var i = 0; i < grades.length; i++) {
                var prev;
                if (i == 0)
                    prev = 0;
                else
                    prev = grades[i-1];
                div.innerHTML +=
                    '<i style="background:' + getColor(grades[i] + 1) + '"></i> ' +
                    prev + '&ndash;' + grades[i] + '<br>';
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
   
    console.log(layer.feature.properties);
    info.update(layer.feature.properties);
    
}
function resetHighlight(e) {
    geojson.resetStyle(e.target);
   info.update();
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