/**
 * Diamond.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nombreDiamante:{
      type: 'string',
      required: true
    },

    caratDiamante:{
      type: 'Number',
      min: 0.20,
      max: 5.00
    },

    precioDiamante:{
      type: 'Number',
      min: 200.00,
      defaultsTo: 200.00
    },

    fkClarity:{
      model: 'clarity'
    },

    fkColor:{
      model: 'color'
    },

    fkCountry:{
      model: 'country'
    },

    fkCut:{
      model: 'cut'
    }
  },
};

