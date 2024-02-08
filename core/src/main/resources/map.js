// Where you want to render the map.
var element = document.getElementById('osm-map');

// Height has to be set. You can do this in CSS too.
element.style = 'height:100vh;';

// Create Leaflet map on map element.
var map = L.map(element);

// Add OSM tile layer to the Leaflet map.
L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

// Target's GPS coordinates.
var target = L.latLng(46.038646, 14.505751);

// Set map's center to target with zoom 14.
map.setView(target, 16);


// Place line on the map
fetch("http://localhost:8080/lines").then(res => {
    res.json().then(lines => {
        for (line of lines) L.polyline(line).addTo(map)
    })
})

fetch("http://localhost:8080/posts").then(res => {
    res.json().then(points => {
        for (point of points) L.marker(point).addTo(map);
    })
})
