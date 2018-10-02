const mongoose = require('mongoose');

const grocerySchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    discount: {type:String},
    name: {type:String},
    photo: {type:String},
    price: {type:String},
    region: {type:String}
});

module.exports = mongoose.model('Grocery',grocerySchema);