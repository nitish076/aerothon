var mongoose = require('mongoose'); 

var _flight_info_schema = mongoose.Schema({

    flight_info:{
    _id: mongoose.Schema.Types.ObjectId,
    _msn_no: {type: String, index: true},
    _flight_no: {type: String, index: true},
    _arrival_airport:{type: String, index: true},
    _departure_airport:{type: String, index: true},
    _arrival_date:{type: String, index: true},//format will be dd-mm-yyyy
    _departure_date:{type: String, index: true},//format will be dd-mm-yyyy
    _economy_seats_available:Number,
    _business_seats_available:Number
}

});

var _flight_info = mongoose.model('FlightInfo', _flight_info_schema);

_flight_info.ensureIndexes(function(err) {
if (err)
    console.log(err);
else
    console.log('create profile index successfully');
});
//add export command



  
module.exports = _flight_info;
 
