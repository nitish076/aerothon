var appRouter = function (app) {

    var __globals = require('./../js/globals');
    var multer = require('multer');
    var exphbs = require('express-handlebars');
    var mongoose = require('mongoose');
    var session = require('express-session');
    var _AircraftDetails = require(__globals.rootDir + '/database/AircraftDetails.js');
    var _FlightRequirement = require(__globals.rootDir + '/database/FlightRequirement.js');
    var _FlightInfo = require(__globals.rootDir + '/database/FlightInfo.js');
    var _FlightMsn = require(__globals.rootDir + '/database/FlightMsn.js');
    var each = require('foreach');
    var url = require('url');
    
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



        app.post("/check_MSN", function (req, res, next) {
            console.log('get:/check_MSN');
    
            console.log(sess);
            
            mongoose.connect(__globals.dbUrl, function (err) {
                if (err) {
                console.log('db connection error: ' + __globals.dbUrl);
                return res.status(421).send("db connection error");
                throw err;
                }



                __AircraftDetails.findOne({ '_msn_no' : req.body.MSN_No}, function(err, details){

                        console.log("Aircraft_details", details);
                        console.log(req.body)
    
                         project.basic_info._projectName = req.body.Projectname;
                          project.basic_info._projectCategory = req.body.ProjectCategory;
                           project.basic_info._projectLocality = req.body.ProjectLocality;
                            project.project_state._creationState = 1 ;
                            project.project_state._uploadState = 1 ;
                            project.project_state._overviewState  = 0 ;
                            project.project_state._draftState  = 1 ;
                            project.project_state._completeState  = 0 ;
                            project.save(function(err){
                                if(err)
                                throw err;//qq
                                return res.send("project details updated")
    
                                   })
        
    
                    
    
                })
    














                var __AircraftDetails = new _AircraftDetails();
                newProject.basic_info._projectName = req.body.Projectname;
                newProject.basic_info._projectDetails = req.body.Details;
                newProject.basic_info._projectCategory = req.body.ProjectCategory;
                newProject.basic_info._projectLocality = req.body.ProjectLocality;
                newProject.basic_info._projectBelonginguser = user._id;
                newProject._projectBelongingImageSet= _imageSetInfo._id;
                newProject.project_state._creationState = 1 ;
                newProject.project_state._uploadState = 0 ;
                newProject.project_state._overviewState  = 0 ;
                newProject.project_state._draftState  = 1 ;
                newProject.project_state._completeState  = 0 ;

            
    
    
            });

});








}

module.exports = appRouter;