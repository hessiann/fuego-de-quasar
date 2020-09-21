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
