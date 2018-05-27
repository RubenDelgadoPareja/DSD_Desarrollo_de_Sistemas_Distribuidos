José María Gómez García

nodejs servidor.js

sensores :	http://localhost:8080/sensores.html
agente :	http://localhost:8080/agente.html
usuario :	http://localhost:8080/ ó http://localhost:8080/usuario.html

Los sensores modifican los valores de luminosidad, temperatura y nº de alimentos de la nevera además de mostrar el estado de los actuadores.

El agente contiene todos los umbrales de las variables medidas por los sensores para notificar con alarmas cuando se cumpla:

	- Temperatura actual inferior a la mínima.
	- Temperatura actual superior a la máxima.
	- Luminosidad actual inferior a la mínima.
	- Luminosidad actual superior a la máxima.
	- Nº de alimentos en la nevera inferior al máximo y temperatura inferior a la máxima para que se establezca el modo bajo consumo de la nevera.

Además de modificar el estado de los actuadores:

	- Cierra la persiana cuando la temperatura y la luminosidad superen a la vez el máximo.
	- Modo bajo consumo de la nevera cuando el nº de alimentos y la temperatura sen inferior al máximo establecido.
	- Nevera normal cuando no se cumplen las condiciones de bajo consumo.

Y muestra todos los umbrales y los valores actuales de los sensores.

El usuario muestra todos los valores de los sensores, las alarmas y el estado de los actuadores además de poder modificarlo pulsando su respectivo botón.

El servidor contiene el estado de los actuadores, guarda todos los eventos de los sensores en una base de datos mongodb y es el encargado de gestionar el paso de mensajes entre las distinas interfaces.
