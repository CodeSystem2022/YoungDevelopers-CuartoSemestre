from psycopg2 import pool
# Primero importamos la librería psycopg2 y la renombramos como bd.
from logger_base import log
# Luego importamos el modulo log directamente desde logger_base, creado anteriormente.
import sys
# Nuevamente, importamos sys (system), que sirve para proporcionar acceso a variables y
# funciones que interactuan con varios aspectos del interprete y del sistema de python.
class Conexion:
    _DATABASE = 'test_bd'
    _USERNAME = 'postgres'
    _PASSWORD = 'admin'
    _DB_PORT = '5432'
    _HOST = '127.0.0.1'
    _MIN_CON = 1
    _MAX_CON = 5
    _pool = None

    # Se definen los atributos que representan la información necesaria para establecer una
    # conexión con la base de datos.
    # Estos son los atributos, sacados desde postgreSQL.

    @classmethod
    def obtenerConexion(cls):
        conexion = cls.obtenerPool().getconn()
        log.debug(f'conexion obtenida del pool: {conexion}')
        return conexion
    # Realizamos una conexión del pool de objetos.

    @classmethod
    # @classmethod indica que el siguiente método, es un método de clase, el método "obtenerCursor", es un
    # método de clase.
    def obtenerCursor(cls):
        pass

    @classmethod
    def obtenerPool(cls):
    # Definimos la clase obtenerPool
        if cls._pool is None:
        # Indicamos que si el atributo ._pool es none, entonces el pool no se ha creado
            try:
            # Creamos el pool de conexiones
                cls._pool = pool.SimpleConnectionPool(cls._MIN_CON,
                                                      cls._MAX_CON,
                                                      host=cls._HOST,
                                                      user=cls._USERNAME,
                                                      password=cls._PASSWORD,
                                                      port=cls._DB_PORT,
                                                      database=cls._DATABASE)
                log.debug(f'creación del pool exitosa: {cls._pool}')
                return cls._pool
            except Exception as e:
                log.error(f'Ocurrió un error al obtener el pool: {e}')
                sys.exit()
        else:
            return cls._pool
            # Si todo sale correctamente, el return, vuelve nuemanete a la clase obtener pool.

    @classmethod
    def liberarConexion(cls, conexion):
        cls.obtenerPool().putconn(conexion)
        log.debug(f'Regresamos la conexion del Pool: {conexion}')
    # Se libera la conexión del pool

    @classmethod
    def cerrarConexiones(cls):
        cls.obtenerPool().closeall()
    # Se cierra la conexión del pool




if __name__ == '__main__':
    conexion1 = Conexion.obtenerConexion()
    Conexion.liberarConexion(conexion1)
    conexion2 = Conexion.obtenerConexion()
    Conexion.liberarConexion(conexion2)
    conexion3 = Conexion.obtenerConexion()
    Conexion.liberarConexion(conexion3)
    #conexion4 = Conexion.obtenerConexion()
    #conexion5 = Conexion.obtenerConexion()
    #conexion6 = Conexion.obtenerConexion()
