
// var divMap=document.getElementById("map");
var map=L.map('map').setView([45.267, 19.833], 14);

L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox.streets',
    accessToken: 'pk.eyJ1IjoidmFsZW50aW5ha3lmeHJ0ZGZpeWt1dHlzIiwiYSI6ImNqd256dmp6dTA5aGczem55MjVzYWF5eGsifQ.ZNvZnQeTL7wK7RpIOmizLw'
}).addTo(map);  

function setPrekrsaji(){
    $.ajax({
        url: 'http://localhost:8080/articles/getPrekrsaji',
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type':'application/json'
        },
        method: 'GET',
        data: '',
        success: function(data){
            console.log('succes: '+data);
        }
    });
}

function setLaksaKrivicnaDela(){
    
}
// dangerZones(45.247050, 19.832450);
// dangerZones(45.254410, 19.842550);

function dangerZones(longitude, latitude, color){

    var circle = L.circle([longitude, latitude], {
        color: 'red',
        fillColor: '#f03',
        fillOpacity: 0.5,
        radius: 400
    }).addTo(map);
}

$.get(  'http://nominatim.openstreetmap.org/search?format=json&q='+"Novi Sad, Gogoljeva 30", function(data){
       console.log(data);
});

setPrekrsaji();