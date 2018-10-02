const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');

const Lifestyle = require('../models/lifestyle');
const Produce = require('../models/produce');
const Grocery = require('../models/grocery');

/* GET requests for lifestyle discounts. */
router.get('/lifeStyleDiscounts', function(req, res) {
  Lifestyle.find()
      .exec().then(data=>{
        console.log('reading data back' , data);
      res.status(200).json({data});
  }).catch(err =>{
      console.log("Logging error loading lifestyle discounts! ", err);
      res.status(500).json({error:err});
  })
  //res.render('index', { title: 'Express' });
});

/* GET requests for grocery discounts. */
router.get('/groceryDiscounts', function(req, res) {
    Grocery.find()
        .exec().then(data=>{
        console.log('reading data back' , data);
        res.status(200).json({data});
    }).catch(err =>{
        console.log("Logging error loading lifestyle discounts! ", err);
        res.status(500).json({error:err});
    })
    //res.render('index', { title: 'Express' });
});

/* GET requests for produce discounts. */
router.get('/produceDiscounts', function(req, res) {
    Produce.find()
        .exec().then(data=>{
        console.log('reading data back' , data);
        res.status(200).json({data});
    }).catch(err =>{
        console.log("Logging error loading lifestyle discounts! ", err);
        res.status(500).json({error:err});
    })
    //res.render('index', { title: 'Express' });
});

/* DELETE requests for a given id from Lifestyle*/
router.delete('/:id',(req,res)=>{
  const id = req.params.id;
  Lifestyle.remove({_id:id}).exec()
      .then(result => {
        console.log('product deleted',result);
        res.status(200).json({
            message:"Data deleted"
        });
      }).catch(err=>{
     console.log(err);
     res.status(500).json({
         error:err
     });
  });
});

/* POST requests for lifestyle discounts. */
router.post('/addLifestyle',(req,res,next)=>{
  console.log('request body is ' , req.body);
  const lifestyle = new Lifestyle({
      _id:new mongoose.Types.ObjectId,
      discount: req.body.discount,
      name: req.body.name,
      photo: req.body.photo,
      price: req.body.price,
      region: req.body.region
  });

    lifestyle.save().then(data => {
      console.log(data);
        res.status(200).json(data);
    }).catch(err => {
        console.log(err);
        res.status(500).json({
            error:err
        });
    });
});

/* POST requests for produce discounts. */
router.post('/addProduce',(req,res,next)=>{
    console.log('request body is ' , req.body);
    const produce = new Produce({
        _id:new mongoose.Types.ObjectId,
        discount: req.body.discount,
        name: req.body.name,
        photo: req.body.photo,
        price: req.body.price,
        region: req.body.region
    });

    produce.save().then(data => {
        console.log(data);
        res.status(200).json(data);
    }).catch(err => {
        console.log(err);
        res.status(500).json({
            error:err
        });
    });
});

/* POST requests for grocery discounts. */
router.post('/addGrocery',(req,res,next)=>{
    console.log('request body is ' , req.body);
    const grocery = new Grocery({
        _id:new mongoose.Types.ObjectId,
        discount: req.body.discount,
        name: req.body.name,
        photo: req.body.photo,
        price: req.body.price,
        region: req.body.region
    });

    grocery.save().then(data => {
        console.log(data);
        res.status(200).json(data);
    }).catch(err => {
        console.log(err);
        res.status(500).json({
            error:err
        });
    });
});

module.exports = router;
