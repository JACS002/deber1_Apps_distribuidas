Integrantes:

-Ahmed Puco: Responsable del desarrollo de la aplicación REST.
-Joel Cuascota: Encargado de la creación y configuración de los archivos Dockerfile.
-Anahi Andrade: Responsable del desarrollo de la aplicación JSF.

Todos los integrantes participaron en la realización de pruebas de comunicación entre contenedores y en la verificación del correcto funcionamiento del sistema en su conjunto. 

Instrucciones para descargar las imágenes Docker y ejecutar los contenedores del proyecto:

1. Descargar las imágenes desde Docker Hub

Primero, descargamos las imágenes necesarias usando los siguientes comandos:

docker pull ahmedpuco/rest-app
docker pull ahmedpuco/jsf-app

2. Crear la red Docker para la comunicación entre contenedores

Para permitir que ambos contenedores se comuniquen entre sí, creamos una red personalizada:

docker network create net_jsf_rest

3. Ejecutar los contenedores

Una vez creada la red, lanzamos los contenedores de cada imagen.

🔹 Contenedor REST
docker run -d --name rest-app --network net_jsf_rest -p 8081:8080 ahmedpuco/rest-app


Este comando ejecuta el contenedor rest-app en segundo plano, expone el puerto 8080 del contenedor en el puerto 8081 del host, y lo conecta a la red net_jsf_rest.

🔹 Contenedor JSF
docker run -d --name jsf-app --network net_jsf_rest -e REST_BASE_URL=http://rest-app:8080/rest-app/api -p 8080:8080 ahmedpuco/jsf-app


Este comando ejecuta el contenedor jsf-app, conecta también a la red net_jsf_rest y define la variable de entorno REST_BASE_URL apuntando al backend rest-app.


4. Verificar la aplicación

Una vez que ambos contenedores estén en ejecución, puedes acceder a la aplicación JSF en tu navegador web mediante la siguiente URL:

http://localhost:8080/jsf-app


Desde allí, deberías poder interactuar con la interfaz JSF que se comunica internamente con el backend REST.
 