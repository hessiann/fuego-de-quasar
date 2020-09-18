# fuego-de-quasar
Operacion Fuego De Quasar Challenge

El siguiente programa recibe mensajes de tipo POST con el siguiente formato JSON:

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
  
  Si los datos son correctos, la aplicación devolverá una respuesta similar a la que se muestra a continuación:
  
  {
    "message": "este es un mensaje secreto ",
    "position": {
        "x": 1.0,
        "y": 1.0
    }
}
