/**
 * Pokemon.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nombrePokemon:{
      type: 'String',
      required: true
    },

    poderUno:{
      type: 'String',
      required: true
    },

    poderDos:{
      type: 'String',
      required: true
    },

    fechaCaptura:{
      type: 'String',
      required: true
    },

    nivel:{
      type: 'number',
      required: true
    },

    latitud:{
      type: 'String',

      minLength: 1,
      allowNull: true,
    },

    longitud:{
      type: 'String',

      minLength: 1,
      allowNull: true,
    },

    fkEntrenador:{
      model: 'entrenador'
    }
  },
};

