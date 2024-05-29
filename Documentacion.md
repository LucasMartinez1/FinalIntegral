Este es el trabajo final de la capacitación dictada por Integral Software en los primeros meses del año 2024,
con la finalidad de realizar un API RESTful implementando Spring Boot.

Antes de comenzar, se debe configurar la base de datos con la que va a trabajar en el "application.properties" p
ara poder correr el programa de forma local.

Dependencias utilizadas:
spring-boot-starter-data-jpa
spring-boot-starter-thymeleaf
spring-boot-starter-web
spring-security-test
hibernate-validator
postgresql
spring-boot-starter-security
spring-boot-starter-test

-Codificación

ACLARACIÓN IMPORTANTE: En este proyecto se optó por utilizar la creación de objetos en tiempo de ejecución
(Usuarios y Telemetrías) con el fin de simplificar el desarrollo de la aplicación, pero se entiende que en un entorno
de trabajo se deberían contrastar los datos contra una base de datos.

/authenticate:
Este endpoint se encarga de realizar la validación de usuario y contraseña. En primer lugar, se recibe un
authorizationHeader, el cual cuenta con los datos de usuario y contraseña encriptados. Una vez controlado que este
campo no sea nulo, se procede a desencriptar y separar los respectivos valores. Luego, se procede a crear un objeto de
tipo usuario con los valores correctos de usuario y contraseña, el cual será comparado con los valores que acabamos de
desencriptar. Una vez desencriptados los valores recibidos y creado el objeto Usuario, se procede a comparar los
usuarios y contraseñas. Si la verificación es exitosa, utilizamos una bandera de tipo booleana "Verificado", la cual
cambia a "True" para indicar que el usuario se ha autenticado con éxito. De lo contrario, "Verificado" queda en estado
de "False" y se arroja un 401 UNAUTHORIZED. El uso de una bandera en este punto es para poder tener un control y exigir
al usuario ingresar primero por /authenticate para luego poder ingresar a los demás endpoints. El nivel de acceso de la
bandera solo es público en este caso, con el fin de poder realizar los tests, pero en un entorno de trabajo debería ser
privado para asegurar la seguridad de la aplicación.

/instructions:
Este endpoint se encarga de enviar instrucciones a la nave, la cual debe recibir, interpretar y devolver un resultado.
Para enviar una instrucción, se envía un JSON desde Postman con la instrucción deseada. Luego de verificar que no sea
nula y que el estado de "Verificado" esté en "True", se procede a procesar la instrucción y posteriormente guardarla en
la base de datos.

/telemetry:
Este endpoint se encarga de enviar datos desde la nave a la Tierra. Para ello, luego de controlar el estado de
"Verificado", se muestran los datos de una telemetría previamente creada, informando velocidad, presión y temperatura.



Martinez Lucas Maximiliano.