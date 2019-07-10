/**
 * Country.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    countryName: {
      type: 'string'
    },

    // Config papá
    diamanteCountry: {
      collection: 'diamond',
      via: 'fkCountry'
    }
  },
};

