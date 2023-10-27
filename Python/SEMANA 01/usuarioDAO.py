from laboratorio_usuarios.conexion import Conexion
from laboratorio_usuarios.CursorDelPool import CursorDelPool
from laboratorio_usuarios.logger_base import log
from laboratorio_usuarios.Usuario import Usuario


class UsuarioDAO:
    '''
    DAO(Data Access Object)
    CRUD(create-read-update-delete)
    '''
    _SELECCIONAR = 'SELECT * FROM usuario ORDER BY id_usuario'
    _INSERTAR = 'INSERT INTO usuario(username,password) VALUE(%s,%s)'
    _ACTUALIZAR = 'UPDATE usuario SET username=%s,password=%s'
    _ELIMINAR = 'DELETE FROM usuario WHERE id_usuario=%s'

    @classmethod
    def seleccionar(cls):
         with CursorDelPool() as cursor:
             log.debug('seleccionando usuarios')
             cursor.execute(cls._SELECT)
             registros = cursor.fetchall()
             usuarios = []
             for registro in registros:
                  usuario= Usuario(registro[0],registro[1],registro[2])
                  usuarios.append(usuario)
             return usuarios
    @classmethod
    def insertar(cls,usuario):
        with CursorDelPool() as cursor:
            log.debug(f'Usuario a insertar:{usuario}')
            valores = (usuario.username,usuario.password)
            cursor.execute(cls._INSERTAR,valores)
            return cursor.rowcount

    @classmethod
    def actualizar(cls,usuario):
        with CursorDelPool() as cursor:
            log.debug(f'usuario a actualizar:{usuario}')
            valores =(usuario.username,usuario.password)
            cursor.execute(cls._ACTUALIZAR,valores)
            return cursor.rowcount

    @classmethod
    def eliminar(cls,usuario):
        with CursorDelPool() as cursor:
            log.debug(f'usuario a eliminar{usuario}')
            valores = (usuario.id_usuario,)
            cursor.execute(cls._ELIMINAR,valores)
            return cursor.rowcount




if __name__=='__main__':
    #eliminar usuario
    usuario = Usuario(id_persona=3)
    usuario_eliminado = UsuarioDAO.eliminar(usuario)
    log.debug(f'usuario eliminado:{usuario_eliminado}')
    
    # actualizar registro
    usuario = Usuario(id_usuario=1,username='',password='369')
    usuario_actualizado = UsuarioDAO.actualizar(usuario)
    log.debug(f'usuario actualizado:{usuario_actualizado}')

    #insertar usuario
    usuario = Usuario(username='Ale',password='123')
    usuario_insertado = UsuarioDAO.insertar(usuario)
    log.debug(f'usuario insertado: {usuario_insertado}')
    
    #Listar o seleccionar
    usuarios= UsuarioDAO.seleccionar()
    for usuario in usuarios:
        log.debug(usuario)

    

    

    