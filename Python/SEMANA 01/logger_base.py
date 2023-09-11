import logging as log

log.basicConfig(level=log.INFO,
                format='%(asctime)s: %(levelname)s [%(filename)s:%(lineno)s]%(message)s',
                datefmt= '%I:%M:%S %p',
                handlers=[
                    log.FileHandler('capa_datos.log',encoding='utf8'),
                    log.StreamHandler()
                ])
if __name__ == '__main__':
    log.debug('Mensaje a nivel debug')
    log.info('Mensaje nivel info')
    log.warning('Mensaje nivel warning')
    log.error('Mesaje a nivel errror')
    log.critical('Mensajea nivel critical')