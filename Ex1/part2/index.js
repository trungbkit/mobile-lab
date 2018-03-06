const express = require('express')
const app = express()

function getDistanceFromLatLonInKm(lat1,lon1,lat2,lon2) {
  var R = 6371;
  var dLat = deg2rad(lat2-lat1);
  var dLon = deg2rad(lon2-lon1); 
  var a = 
    Math.sin(dLat/2) * Math.sin(dLat/2) +
    Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
    Math.sin(dLon/2) * Math.sin(dLon/2)
    ; 
  var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
  var d = R * c;
  return d;
}

function deg2rad(deg) {
  return deg * (Math.PI/180)
}

app.get('/', (req, res) => res.send('OK! It works!'))
app.get('/distance', (req, res) => {
  let [lat1, lon1] = req.query.src.split(',');
  let [lat2, lon2] = req.query.dest.split(',');
  let distance = getDistanceFromLatLonInKm(lat1, lon1, lat2, lon2);
  res.send({
    distance,
    status: "OK"
  });
});
app.listen(3000, () => console.log('Server is listening on port 3000'));
