var appRouter = function (app) {

    var __globals = require('./../js/globals');
    var multer = require('multer');
    var bodyParser = require("body-parser");

    var exphbs = require('express-handlebars');
    var mongoose = require('mongoose');
    var session = require('express-session');
    var _AircraftDetails = require(__globals.rootDir + '/database/AircraftDetails.js');
    var _FlightRequirement = require(__globals.rootDir + '/database/FlightRequirement.js');
    var _FlightInfo = require(__globals.rootDir + '/database/FlightInfo.js');
    var each = require('foreach');
    var url = require('url');
    
    app.get("/data", function (req, res, next) {
        console.log('get:/');

        mongoose.connect(__globals.dbUrl, function (err) {
            if (err) {
            console.log('db connection error: ' + __globals.dbUrl);
            return res.status(421).send("db connection error");
            throw err;
            }
                

    var _AircraftDetails_schema_320 = new _AircraftDetails();
    _AircraftDetails_schema_320.aircraft_detail._msn_no = '1234';
    _AircraftDetails_schema_320.aircraft_detail._harness_no = 'A';//Random Generated
    _AircraftDetails_schema_320.aircraft_detail._fuel_capacity_left = '500L';
    _AircraftDetails_schema_320.aircraft_detail._fuel_capacity_right = '200L';
    _AircraftDetails_schema_320.aircraft_detail._aircraft = 'A320';
   

//save the new project schema //  
_AircraftDetails_schema_320.save(function(err){
    if(err)
    console.log(err);
 
})


  

res.send("Data loaded")

})

    })


    app.get("/data", function (req, res, next) {
        console.log('get:/');

        mongoose.connect(__globals.dbUrl, function (err) {
            if (err) {
            console.log('db connection error: ' + __globals.dbUrl);
            return res.status(421).send("db connection error");
            throw err;
            }
                

    var _AircraftDetails_schema_320 = new _AircraftDetails();
    _AircraftDetails_schema_320.aircraft_detail._msn_no = '1234';
    _AircraftDetails_schema_320.aircraft_detail._harness_no = 'A';//Random Generated
    _AircraftDetails_schema_320.aircraft_detail._fuel_capacity_left = '500L';
    _AircraftDetails_schema_320.aircraft_detail._fuel_capacity_right = '200L';
    _AircraftDetails_schema_320.aircraft_detail._aircraft = 'A320';
   
   

_AircraftDetails_schema_320.save(function(err){
    if(err)
    console.log(err);

})






})

    })









    app.get("/", function (req, res, next) {
        console.log('get:/');
        res.redirect('/dashboard');
        sess = req.session;

        });

    //main dashboard page with public data (coming from minio public bucket)//
    //*************************************************************************************************//
    app.get("/dashboard", function (req, res, next) {
        console.log('get:/dashboard');

        console.log(sess);

        
        res.send( [{ Means: 'NavelNews', Headlines: 'Headlines of NavelNews', MainNews:'MainNews of Navel' },
        { Means: 'AircraftNews', Headlines: 'Headlines of AircraftNews', MainNews:'MainNews of Aircraft' }
             ,{ Means: 'TrainNews', Headlines: 'Headlines of TrainNews', MainNews:'MainNews of Train' },
             { Means: 'TrainNews', Headlines: 'Headlines of TrainNews', MainNews:'MainNews of Train' }]);
        



        });




        

//APPLY BUTTON CLICK...................................................................//
        app.post("/check_MSN", function (req, res, next) {
            console.log('get:/check_MSN');
            sess = req.session;
            console.log(req.body)
            mongoose.connect(__globals.dbUrl, function (err) {
                if (err) {
                console.log('db connection error: ' + __globals.dbUrl);
                return res.status(421).send("db connection error");
                throw err;
                }

                _AircraftDetails.findOne({ 'aircraft_detail._msn_no' : req.body.msnNo}, function(err, details){

                    
                    console.log(details.aircraft_detail._msn_no)
                    console.log(details.aircraft_detail._harness_no)
                    console.log(details.aircraft_detail._fuel_capacity_left)
                    console.log(details.aircraft_detail._fuel_capacity_right)



                    res.send({msnNo : details.aircraft_detail._msn_no,harnessLength : details.aircraft_detail._harness_no,
                                fuelCapacityLeft:details.aircraft_detail._fuel_capacity_left,
                                fuelCapacityRight:details.aircraft_detail._fuel_capacity_right})

                

            })



                res.send("Data Successfully Uploaded")
     
        
    

            });

});



//SUBMIT BUTTON CLICK...................................................................//
app.post("/flight_details_submit", function (req, res, next) {
    console.log('post:/flight_details_submit');
    sess = req.session;

    console.log(sess);
    console.log(req.body)
    mongoose.connect(__globals.dbUrl, function (err) {
        if (err) {
        console.log('db connection error: ' + __globals.dbUrl);
        return res.status(421).send("db connection error");
        throw err;
        }
                        var FlightNumber = "abcd";
                        var _flight_requirement_schema = new _FlightRequirement();
                        _flight_requirement_schema.flight_requirement._msn_no = req.body.msnNo;
                        _flight_requirement_schema.flight_requirement._flight_no = FlightNumber;//Random Generated
                        _flight_requirement_schema.flight_requirement._max_altitute_reached = req.body.maxAltReached;
                        _flight_requirement_schema.flight_requirement._fuel_quantity_right = req.body.fuelQuantityRight;
                        _flight_requirement_schema.flight_requirement._fuel_quantity_left = req.body.fuelQuantityLeft;
                        _flight_requirement_schema.flight_requirement._room_temp = req.body.roomTemp;
                        _flight_requirement_schema.flight_requirement._atmospheric_pressure  = req.body.atmPressure;
                        _flight_requirement_schema.flight_requirement._gross_weight  = req.body.grossWeight ;

                        
                        var _FlightInfo_schema = new _FlightInfo();
                        _FlightInfo_schema.flight_info._msn_no = req.body.msnNo;
                        _FlightInfo_schema.flight_info._flight_no = FlightNumber;
                        _FlightInfo_schema.flight_info._arrival_airport = req.body.arrivalAirport;
                        _FlightInfo_schema.flight_info._departure_airport = req.body.departureAirport;
                        _FlightInfo_schema.flight_info._arrival_date = req.body.arrivalDate;
                        _FlightInfo_schema.flight_info._departure_date = req.body.departureDate;
                        _FlightInfo_schema.flight_info._economy_seats_available  = req.body.economySeatsAvaliable;
                        _FlightInfo_schema.flight_info._business_seats_available  = req.body.businessSeatsAvaliable ;



                        //save the new project schema //  
                        _flight_requirement_schema.save(function(err){
                            if(err)
                            console.log(err);
                            _FlightInfo_schema.save(function(err){
                                if(err)
                                console.log(err);

                                res.send("Data Successfully Uploaded")
                         
                            })
                            
                        })
                        

                    




    });

});

app.post("/search_details", function (req, res, next) {
    console.log('post:/flight_details_submit');

    console.log(sess);
    console.log(req.body)
    mongoose.connect(__globals.dbUrl, function (err) {
        if (err) {
        console.log('db connection error: ' + __globals.dbUrl);
        return res.status(421).send("db connection error");
        throw err;
        }

            flight_ID = req.body.flightId
            msn_No = req.body.msnNo
                        
            arrival =req.body.arrival
            departure = req.body.departure
            date_departure = req.body.dateDeparture
            date_arrival = req.body.date_arrival

            
            var search_result=[]
            
            
            if(flight_ID!=undefined){

                _FlightInfo.findOne({ 'flight_info._flight_no' : flight_ID}, function(err, details){

                        console.log("FlightInfo_details", details);
                   
                        var msn = details.flight_info.msn_no
                        var fno = details.flight_info._flight_no
                        var arrairport = details.flight_info._arrival_airport 
                        var depairport =details.flight_info._departure_airport
                        var arr_date= details.flight_info._arrival_date;
                        var dep_date = details.flight_info._departure_date ;
                        var eco_seat_avl = details.flight_info._economy_seats_available;
                        var bus_seat_avl = details.flight_info._business_seats_available;

                        search_result.push({msnNo:msn,flighNo:fno,airportArrival:arrairport,airportDeparture:depairport,arrivalDate:arr_date
                            ,departureDate:dep_date,economySeats:eco_seat_avl,businessSeats:bus_seat_avl})
                       

            })

            

            _FlightInfo.findOne({ 'flight_info._flight_no' : flight_ID}, function(err, details){

                console.log("FlightInfo_details", details);
           
               var altitute_reached = flight_requirement._max_altitute_reached;
               var quantity_right = flight_requirement._fuel_quantity_right;
               var quantity_left = flight_requirement._fuel_quantity_left;
               var room_temp = flight_requirement._room_temp;
               var atmospheric_pressure = flight_requirement._atmospheric_pressure;
               var gross_weight = flight_requirement._gross_weight;

                search_result.push({altitudeReached:altitute_reached,fuelQuantityLeft:quantity_left,fuelQuantityRight:quantity_right
                    ,roomTemp:room_temp
                    ,atmosphericPressure:atmospheric_pressure,grossWeight:gross_weight})
               

    })

        res.send(search_result)


            }
                    


    });

});






}

module.exports = appRouter;