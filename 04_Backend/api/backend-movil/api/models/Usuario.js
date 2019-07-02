/**
 * Usuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    nombreAtributo: {
      type:'string'
    },
    nombre: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 60,
    },
    cedula: {
      type: 'string',
      required: true,
      unique: true,
      minLength: 10,
      maxLength: 25,
    },
    username: {
      type: 'string',
      required: true,
      unique: true
    },
    fechaNacimiento: {
      type: 'string'
    },
    sueldo: {
      type: 'number',
      min: 100.00,
      max: 5000,
      defaultsTo: 100.00
    },
    estaCasado: {
      type: 'boolean',
      defaultsTo: false
    },
    latitudCasa: {
      type: 'string'
    },
    longitudCasa: {
      type: 'string'
    },
    tipoUsuario: {
      type: 'string',
      enum: ['normal', 'pendiente', 'premium']
    },
    correo: {
      type: 'string',
      isEmail: true
    },

    // Configuración del papá
    serviciosDeUsuario: { // Nombre de atributo de la relación.
      collection: 'servicio', // Nombre del modelo a relacionar
      via: 'fkUsuario' // Nombre del atributo FK del otro modelo, es el campo para hacer la relación
    },

    //Configuracion del hijo
    fkEmpresa: {  // Nombre del FK para la relacion
      model: 'empresa', // Nombre del modelo a relacionar (papa)
      // required: true // OPCIONAL -> Siempre ingresar el FK
    }




  },
};

/*
 Insertar
 http://localhost:1337/usuario
 Estándar restful:
 Método HTTP: POST
 Bpdy Params: usuario

 Actualizar
 http://localhost:1337/usuario/:1
 Estándar restful:
 Método HTTP: PUT
 Bpdy Params: usuario

 Borrar
 http://localhost:1337/usuario/:1
 Estándar restful:
 Método HTTP: DELETE
 Bpdy Params: usuario

 Buscar por ID
 http://localhost:1337/usuario/:1
 Estándar restful:
 Método HTTP: DELETE
 Bpdy Params: GET

 Búsqueda con parámetros
 1) Un parámetro
 http://localhost:1337/usuario?nombre=Criss
 2) dos parámetros
  http://localhost:1337/usuario?nombre=Criss&cedula=1723464465
 3- 4) Traer los primeros 5 (Útil para paginacion)
 http://localhost:1337/usuario?limit=5&kip=10

 5) Ordenamiento, traer registros ordenads por nombre:
 http://localhost:1337/usuario?sort=nombre
 http://localhost:1337/usuario?sort=nombre DESC
 http://localhost:1337/usuario?sort=nombre ASC

 6) Traer los registros que contengan en el nombre la letra a:
 http://localhost:1337/usuario?where={"nombre":{"contains":"a"}}

7) Otro ejemplos
 http://localhost:1337/usuario?where={"sueldo":{"<=":3000}}
 http://localhost:1337/usuario?where={"fechaNacimiento":{"<=":"2018-01-01"}}
 http://localhost:1337/usuario?where={"correo":{"endsWith":"@gmail.com"}}

*/

