  # Casos de prueba /topsecret/
  
  ### Prueba exitosa en /topsecret/
  
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
  Respuesta esperada:
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
---
  ### Prueba errónea en /topsecret/ -> name viaja vacío, no se puede determinar las coordenadas
  
``` JSON
{
	"satellites": [
			{
			"name": "",
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
  Respuesta esperada:
``` JSON
  RESPONSE CODE: 404
{
    "timestamp": "2020-09-21T10:18:02.367",
    "message": "Coordinates not found"
}
```
---
  ### Prueba errónea en /topsecret/ -> Falta información en message y no se puede armar el mensaje
  
``` JSON
{
	"satellites": [
			{
			"name": "kenobi",
			"distance": 5.0,
			"message": ["", "", "", "mensaje", ""]
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
  Respuesta esperada:
``` JSON
  RESPONSE CODE: 404
{
    "timestamp": "2020-09-21T10:19:40.372",
    "message": "Message not found"
}
```
---

  ### Prueba errónea en /topsecret/ -> La información del mensaje perdió integridad, no es confiable
  
``` JSON
{
	"satellites": [
			{
			"name": "kenobi",
			"distance": 5.0,
			"message": ["no", "", "", "mensaje", ""]
			},
			{
			"name": "skywalker",
			"distance": 5.0,
			"message": ["", "es", "", "", "secreto"]
			},
			{
			"name": "sato",
			"distance": 13.0,
			"message": ["si", "", "un", "", ""]
			}
	]
}
```
  Respuesta esperada:
``` JSON
  RESPONSE CODE: 404
{
    "timestamp": "2020-09-21T10:19:40.372",
    "message": "Message not found"
}
```
---

  ### Prueba errónea en /topsecret/ -> La información del mensaje perdió integridad, no es confiable
  
``` JSON
{
	"satellites": [
			{
			"name": "kenobi",
			"distance": 5.0,
			"message": ["este"]
			},
			{
			"name": "skywalker",
			"distance": 5.0,
			"message": ["", "es", "", "", "secreto"]
			},
			{
			"name": "sato",
			"distance": 13.0,
			"message": ["este", "", "un", "", ""]
			}
	]
}
```
  Respuesta esperada:
``` JSON
  RESPONSE CODE: 404
{
    "timestamp": "2020-09-21T10:19:40.372",
    "message": "Message not found"
}
```
---

 ### Prueba errónea en /topsecret/ -> Falta un mensaje completo
  
``` JSON
{
	"satellites": [
			{
			"name": "kenobi",
			"distance": 5.0

			},
			{
			"name": "skywalker",
			"distance": 5.0,
			"message": ["", "es", "", "", "secreto"]
			},
			{
			"name": "sato",
			"distance": 13.0,
			"message": ["este", "", "un", "", ""]
			}
	]
}
```
  Respuesta esperada:
``` JSON
  RESPONSE CODE: 404
{
    "timestamp": "2020-09-21T10:19:40.372",
    "message": "Message not found"
}
```
---

 ### Prueba errónea en /topsecret/ -> No viajan satélites
  
``` JSON
{
	"satellites": [

	]
}
```
  Respuesta esperada:
``` JSON
  RESPONSE CODE: 404
{
    "timestamp": "2020-09-21T10:45:30.151",
    "message": "Satellites not found"
}
```
---

 ### Prueba errónea en /topsecret/ -> El mensaje está roto y no cumple un formato JSON
  
``` JSON
{
	"satellites": [

	
}
```
  Respuesta esperada:
``` JSON
  RESPONSE CODE: 400
{
    "timestamp": "2020-09-21T10:46:24.164",
    "message": "Error parsing JSON"
}
```
---

 ### Prueba errónea en /topsecret/ -> El mensaje está roto y no cumple un formato JSON
  
``` JSON

Hello world

```
  Respuesta esperada:
``` JSON
  RESPONSE CODE: 400
{
    "timestamp": "2020-09-21T10:47:39.171",
    "message": "Error parsing JSON"
}
```
---

  # Casos de prueba /topsecret_split/
  
  ### Prueba POST exitosa en /topsecret_split/ -> Primer mensaje
  
``` JSON
 POST -> http://localhost:8080/topsecret_split/kenobi
 
{
		"distance": 5.0,
		"message": ["este", "", "", "mensaje", ""]
}
``` 
  Respuesta esperada:
``` JSON 
 RESPONSE CODE: 200 
{
    "message": "Message received"
}
``` 
---

### Prueba POST exitosa en /topsecret_split/ -> Segundo mensaje
  
``` JSON
 POST -> http://localhost:8080/topsecret_split/skywalker
 
{
		"distance": 5.0,
		"message": ["", "es", "", "", "secreto"]
}
``` 
  Respuesta esperada:
``` JSON 
 RESPONSE CODE: 200 
{
    "message": "Message received"
}
``` 
---

### Prueba POST exitosa en /topsecret_split/ -> Tercer mensaje
  
``` JSON
 POST -> http://localhost:8080/topsecret_split/sato
 
{
		"distance": 13.0,
		"message": ["", "", "un", "", ""]
}
``` 
  Respuesta esperada:
``` JSON 
 RESPONSE CODE: 200 
{
    "message": "Message received"
}
``` 
---

### Prueba GET exitosa en /topsecret_split/ -> Ya existe información sobre los tres satélites
  
``` JSON
 GET -> http://localhost:8080/topsecret_split/
 

``` 
  Respuesta esperada:
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
---

### Prueba GET exitosa en /topsecret_split/removeAll -> Borra toda la información enviada por la nave
  
``` JSON
 GET -> http://localhost:8080/topsecret_split/removeAll
 

``` 
  Respuesta esperada:
``` JSON 
 RESPONSE CODE: 200 
{
  "message": "List deleted"
}
``` 
---

### Prueba GET errónea en /topsecret_split/ -> No existe información suficiente
  
``` JSON
 GET -> http://localhost:8080/topsecret_split/
 

``` 
  Respuesta esperada:
``` JSON 
 RESPONSE CODE: 404 
{
    "timestamp": "2020-09-21T14:37:58.896",
    "message": "Information not sufficient"
}
``` 

  