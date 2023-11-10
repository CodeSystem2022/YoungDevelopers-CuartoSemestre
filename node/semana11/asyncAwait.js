//La palabra async no es necesaria en las funciones porque ya son asincronas
// Igual proyectan una sincronia visual
async function hola(nombre){
    return new Promise(function (resolve, reject){
        setTimeout( function () {
            console.log('Hola '+ nombre);
            resolve(nombre);
        }, 1000);
    });
}

async function hablar(nombre){  
    return new Promise((resolve, reject) => { //usamos la sintaxis ES6
            setTimeout( function() {
            console.log('bla bla bla bla');
            resolve(nombre);
        }, 1000);
    })
}

async function adios(nombre) {
    return new Promise((resolve, reject) => {
            setTimeout( function() {
                //validamos el error o aprobacion
                console.log('Adios '+ nombre);
                //if(err) reject('Hay un error'); 
                resolve();
        }, 1000);
    })   
}
