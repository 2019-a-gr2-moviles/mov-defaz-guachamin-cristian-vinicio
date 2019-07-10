/**
 * Color.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    colorName: {
      type: 'string'
    },

    // Config papá
    diamanteColor: {
      collection: 'diamond',
      via: 'fkColor'
    }
  },
};

