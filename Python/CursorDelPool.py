from laboratorio_usuarios.logger_base import log
from laboratorio_usuarios.conexion import Conexion

class CursorDelPool:
    def __init__(self):
        self._conexion = None
        self._cursor = None

    def __enter__(self):
        log.debug('inicio del metodo with __enter__')
        self._conexion = Conexion.obtenerConexion()
        self._cursor = self._conexion.cursor()
        return self._cursor

    def __exit__(self,tipo_excepcion,valor_excepcion,detalle_excepcion):
        log.debug(('se ejecuta metodo __exit__'))
        if valor_excepcion:
            self._conexion.rollback()
            log.error(f'ocurrio una excepcion, se hace roolback:{valor_excepcion}{tipo_excepcion}{detalle_excepcion}')
        else:
            self._conexion.commit()
            log.debug('commit de la transaccion')
        self._cursor.close()
        Conexion.liberarConexion(self._conexion)

if __name__ == '__main__':
   with CursorDelPool() as cursor:
       log.debug('dentro del bloque with')
       cursor.execute('SELECT * FROM persona')
       log.debug(cursor.fetchall())
       
  
