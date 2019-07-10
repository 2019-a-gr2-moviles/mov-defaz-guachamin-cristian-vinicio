/**
 * Cut.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    cutName: {
      type: 'string'
    },

    // Config papá
    diamanteCut: {
      collection: 'diamond',
      via: 'fkCut'
    }
  },

};

