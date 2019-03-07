var mongoose = require('mongoose'); 


var _aircraft_schema = mongoose.Schema({

    aircraft_detail:{
    _id: mongoose.Schema.Types.ObjectId,
    _msn_no: {type: String, index: true},
    _harness_no:String,
    _fuel_capacity_left:String,
    _fuel_capacity_right:String,
    _aircraft:String,
    _flight_detail: [{
            _flight_requirement: {type: mongoose.Schema.Types.ObjectId, ref: 'FlightRequirement' },
            _flight_info : {type: mongoose.Schema.Types.ObjectId, ref: 'FlightInfo' }
    }]
}

});

var _aircraftDetail = mongoose.model('AircraftDetail', _aircraft_schema);

_aircraftDetail.ensureIndexes(function(err) {
if (err)
    console.log(err);
else
    console.log('create profile index successfully');
});


  
module.exports = _aircraftDetail;
 
