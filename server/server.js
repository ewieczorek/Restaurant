/**
 * Required Modules
 */
var express = require("express");
var app = express();
var bodyParser = require('body-parser');

var DataBase = require('./dataBase.js');

var mysql = require("mysql");
/**
 * Setup
 */
//use bodyParser() to let us get the data from a POST
app.use(bodyParser.json());
var port = 3019;
app.use(bodyParser.urlencoded({ 
   extended: true 
}));
var router = express.Router();
var path = __dirname + '/views/';
var data_base = new DataBase(mysql,'localhost','shane','devPassword');
app.use("/",router);
app.listen(port,function(){
  console.log("Live at Port " + port);
});

/**
 * TCP calls to be used in diration of operation
 */
router.use(function (req,res,next) {
  console.log("/" + req.method);
  next();
});

router.post("/createAccount", function(req,res){
  data_base.setUser(req.body, function(resp){
    res.json({user:resp});
  });
});

router.post("/getAccount", function(req,res){
  console.log(req.body.name)
  if(req.body.name){
  var userName = req.body.name;
  console.log(userName);
  /*Parse String */
  var lastName="";
  var firstName="";
  var ID="";
  var state=0;
  var buffer;
  for(var x=0;x<userName.length;x++){
    buffer = userName.charAt(x);
    if(buffer==="_"){
      state++;
    }else{
      if(state==0){
        lastName+=buffer;
      }
      if(state==1){
        firstName+=buffer;
      }
      if(state==2){
        ID+=buffer;
    }
    }
    
  }
  
  var input = {
    fname: firstName,
    lname: lastName,
    id:ID
  };
  
  data_base.getUser(input,function(resp){
    res.json(resp);
  });
  
}else{
    console.log("No Input")
  }
  
});


//json_user.lname + " AND F_NAME = " json_user.fname + "AND EMPLOYEE_ID = " json_user.id;

//userFormat -> SMITH_BOB_1