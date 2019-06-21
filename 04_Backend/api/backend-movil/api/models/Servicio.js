/**
 * Servicio.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nombre: {
      type:'string'
    },

    // Configuración del hijo
    fkUsuario: { // Nombre del FK para la relación
      model: 'usuario' // Nombre del modelo a relacionar (papá)
     // required: true // Opcional -> Siempre ingresar el FK
    }

  },

};

