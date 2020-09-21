  # Operación Fuego De Quasar Challenge

  El programa Operación Fuego de Quasar presenta un servicio /topsecret/ que recibe mensajes 
  de tipo POST con el siguiente formato JSON:
  
``` JSON
{
	"satellites": [
			{
			"name": "kenobi",
			"distance": 5.0,
			"message": ["este", "", "", "mensaje", ""]
			},
			{
			"name": "skywalker",
			"distance": 5.0,
			"message": ["", "es", "", "", "secreto"]
			},
			{
			"name": "sato",
			"distance": 13.0,
			"message": ["", "", "un", "", ""]
			}
	]
}
  ```
  Del lado del servidor deberán existir previamente los satelites que se observan en el mensaje recibido en /topsecret/
  bajo la descripción "name" con sus respectivas coordenadas en el plano R2, como se muestra a continuación:
  
``` JSON  
{
	"kenobi": [-500, 200],
	"skywalker": [100, -100],
	"sato": [500, 100]
}
```   
  Los mensajes que se envían al servicio contienen un arreglo de satelites, su distancia a una nave objetivo y 
  un mensaje secreto fraccionado que se deberá completar intentando integrar los fragmentos en caso de que esto sea posible. 
  Para el cálculo de coordenadas de la nave objetivo, se utiliza un método matemático de trilateralización, aplicando
  el algoritmo de Levenberg-Marquardt implementado en la librería Apache Commons Math.
  Para utilizar con éxito la implementación antes mencionada, agregamos en nuestro POM la siguiente dependencia:
  
  ``` HTML
  <dependency>
    <groupId>com.lemmingapex.trilateration</groupId>
    <artifactId>trilateration</artifactId>
    <version>1.0.2</version>
  </dependency>
  ```
  Para leer más sobre el método y su implementación, referirse a:
  [Lemmingapex trilateration](https://github.com/lemmingapex/trilateration/blob/master/README.md)
  
  Si los datos son correctos, la aplicación devolverá una respuesta similar a la que se muestra a continuación:
   
  
  ``` JSON
  RESPONSE CODE: 200
{
    "message": "este es un mensaje secreto",
    "position": {
        "x": 1.0,
        "y": 1.0
    }
}
```
  En el caso de que no se pudiera calcular las coordenadas de la nave objetivo o no se pudiese reconstruir el mensaje original,
  la aplicación devuelve un RESPONSE CODE: 404.
  Además, existe un segundo servicio /topsecret_split/ que acepta POST y GET y recibe información de un satélite
  a la vez, como se muestra a continuación:
  ``` JSON
  POST -> /topsecret_split/{satellite_name}
{

	"distance": 13.0,
	"message": ["", "", "un", "", ""]

}
```
  Si dicho mensaje vino por GET, la respuesta que envía el servicio es la misma que la de /topsecret/ en caso de
  que se hubiera podido descifrar el mensaje y calcular las coordenadas. Caso contrario, la aplicación responde un
  mensaje de error indicando que no hay información suficiente para realizar los cálculos.
  
  ## Casos de prueba
  
  En el archivo [Test cases](https://github.com/hessiann/fuego-de-quasar/blob/master/testcases.md) se facilitan
  algunos casos de prueba y sus respectivas respuestas esperadas.