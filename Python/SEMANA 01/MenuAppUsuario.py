from laboratorio_usuarios.usuarioDAO import UsuarioDAO
from laboratorio_usuarios.Usuario import Usuario
from laboratorio_usuarios.logger_base import log
opcion=None
while opcion !=5:

        print('*** Bienvenidos ***')
        print('1-Listar usuarios')
        print('2-Agregar usuario')
        print('3-Actualizar usuario')
        print('4-Eliminar usuario')
        print('5-Salir')
        opcion=int(input('ingrese la opcion deseada:'))
        
        if opcion == 1:
            usuarios = usuarioDAO.seleccionar()
            for usuario in usuarios:
               log.info(usuario) 
        elif opcion == 2:
            username_var = input('Digite el nombre de usuario:')
            password_var = input('digite su contraseña:')
            usuario = Usuario(username= username_var,password=password_var)
            usuario_insertado= usuarioDAO.insertar(usuario)
            log.info(f'usuario insertado:{usuario_insertado}')      
         
        elif opcion == 3: 
            id_usuario_var = int(input('ingrese el id del usuario a modificar:'))
            username_var = input('Digite el nombre del usuario a modificar:')
            password_var = input('Digite la contraseña del usuario =')
            usuario = Usuario(id_usuario=id_usuario_var,username=username_var,password=password_var)
            usuario_actualizado= usuarioDAO.actualizar(usuario_actualizado)
            log.info(f'usuario actualizado: {usuario_actualizado}')
            
        elif opcion == 4:
           id_usuario_var = int(input('Digite el id del usuario a eliminar:'))
           usuario = Usuario(id_usuario=id_usuario_var)
           usuario_eliminado= usuarioDAO.eliminar(usuario)
           log.info(f'usuario eliminado:{usuario_eliminado}')     
else:
            log.info('salimos de la aplicacion, hasta pronto')
    

                
        