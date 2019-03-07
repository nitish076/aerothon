var mongoose = require('mongoose'); 


var _flight_requirement_schema = mongoose.Schema({

    flight_requirement:{
    _id: mongoose.Schema.Types.ObjectId,
    _msn_no: {type: String, index: true},
    _flight_no: {type: String, index: true},
    _max_altitute_reached:String,
    _fuel_quantity_right:String,
    _fuel_quantity_left:String,
    _room_temp:String,
    _atmospheric_pressure:String,
    _gross_weight:Number
}

});

var _flight_requirement = mongoose.model('FlightRequirement', _flight_requirement_schema);

_flight_requirement.ensureIndexes(function(err) {
if (err)
    console.log(err);
else
    console.log('create profile index successfully');
});
//add export command

  
module.exports = _flight_requirement;
 
