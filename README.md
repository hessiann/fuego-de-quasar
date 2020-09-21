  # Operacion Fuego De Quasar Challenge

  El programa Operacion Fuego de Quasar presenta un servicio /topsecret/ que recibe mensajes 
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
  Dichos mensajes contienen un arreglo de satelites, su distancia a una nave objetivo y un mensaje secreto fraccionado que
  se deberá completar intentando integrar los fragmentos en caso de que esto sea posible. 
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
  [Lemmingapex trilateration]:https://github.com/lemmingapex/trilateration/blob/master/README.md
  
  Si los datos son correctos, la aplicación devolverá una respuesta similar a la que se muestra a continuación:
   
  
  ``` JSON
  RESPONSE CODE: 200
{
    "message": "este es un mensaje secreto ",
    "position": {
        "x": 1.0,
        "y": 1.0
    }
}
```
