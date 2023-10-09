#conexion MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/estudiantes
spring.datasource.username=root
spring.datasource.password=admin
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

#evitar que se cree el esquema nuevamente
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=false

#desactivar el tomcat
spring.main.web-aplication-type=none