/**
 * Empresa.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nombre: {
      type:'string',
      required:true
    },

    // CONFIGURACION DEL PAPA
    usuariosDeEmpresa: { // Nombre atributo de la relacion
      collection: 'usuario', // Nombre del modelo a relacionar
      via: 'fkEmpresa'  // Nombre atributo FK del otro modelo
    },

  },

};

