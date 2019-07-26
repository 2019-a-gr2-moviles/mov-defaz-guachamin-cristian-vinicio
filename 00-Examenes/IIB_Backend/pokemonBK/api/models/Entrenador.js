/**
 * Entrenador.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nombreEntrenador: {
      type: 'String',
      required: true
    },
    
    apellidoEntrenador: {
      type: 'String',
      required: true
    },

    fechaNac: {
      type: 'String',
      required: true
    },

    medallas: {
      type: 'number',
      min: 0,
      required: true
    },

    campeonActual: {
      type: 'boolean',
      defaultsTo: false,
      allowNull: true
    },

    pokeEntrenador: {
      collection: 'pokemon',
      via: 'fkEntrenador'
    }
  },

};

