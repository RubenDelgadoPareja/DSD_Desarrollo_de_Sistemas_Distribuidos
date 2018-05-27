José María Gómez García A2

Mi código está estructurado por dos interfaces:

- interfazSS.java: Es la interfaz para la comunicación entre servidores.
- interfazCS.java: Es la interfaz para la comunicación entre cliente y servidor.
- auxInterfazSS.java: Contiene la clase abstracta auxInterfazSS que implementa los métodos de la interfaz entre servidores.
- servidor_1.java y servidor_2.java: Contiene el código de los servidores 1 y 2 respectivamente. Ambos implementan la interfaz cliente-servidor y heredan de la clase abstracta auxInterfazCS.
- cliente_1.java y cliente_2.java: Contiene el código de los clientes de los servidores 1 y 2 respectivamente.

En mi solución al ejercicio entendí que totaldonado devolvía el total donado del cliente en específico que llama al servidor. Para realizar el exámen he deshecho ese anterior método y creado uno nuevo que simplemente devuelva el total donado de dicho servidor. Al cambiar esto también he tenido que crear una nueva variable de tipo Integer que guarde el totaldonado en cada servidor. Esta variable está declarada en la clase abstracta auxInterfazServidor, de las cuales los servidores son extendendidos por lo que ambos tienen esta variable pero en el caso del servidor_2 esta se mantiene siempre en un valor de 0.

Cada vez que un cliente hace una donación en el servidor_2 y si está registrado en este; el servidor_2 llamará al método de sumtotaldonadoSS() del ervidor_1 con la cantidad que ha donado el cliente en el servidor_2. Si fuese el caso de que la petición de donar la ha recibido desde la interfazSS del servidor_1 entonces el servidor_2 simplemente actualizará la donación en el registro de dicho cliente y en el servidor_1 se actualizará el valor de su totaldonado. Si el servidor_1 recibe una petición de donación del servidor_2 este también le pedirá que sume lo donado con el método sumtotaldonadoSS().

Si el servidor_2  recibe una petición de totaldonadoCS() de cualquier cliente comprueba si está registrado en el servidor_2 o en el servidor_1 y si ha donado algo alguna vez. Si este no es el caso devolverá siempre 0 pero si cumple alguno de los dos requisitos siempre llamará al método del servidor_1 gettotaldonadoSS(). Si es el servidor_1 el que recibe la petición de obtener el total donado este comprobará que el cliente esté registrado en el propio servidor_1 o en el servidor_2 y si ha donado algo alguna vez; si no es el caso, como en el servidor_2, este devolverá 0 pero si se cumplen ambas condiciones devolverá el valor de su variable totaldonado independientemente del servidor en el que estuviese registrado el cliente.
