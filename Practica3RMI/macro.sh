#!/bin/sh -e
# ejecutar = Macro para compilacion y ejecucion del programa ejemplo
# en una sola maquina Unix de nombre localhost.

echo

#killall -9 rmiregistry
#killall -9 java

echo "Lanzando el ligador de RMI ... "
rmiregistry &

echo
echo "Compilando con javac ..."
javac *.java

sleep 2

echo
echo "Lanzando el servidor 1"
java -cp . -Djava.rmi.server.codebase=file:./ -Djava.rmi.server.hostname=localhost -Djava.security.policy=server.policy servidor_1 &

sleep 5

echo
echo "Lanzando el servidor 2"
java -cp . -Djava.rmi.server.codebase=file:./ -Djava.rmi.server.hostname=localhost -Djava.security.policy=server.policy servidor_2 &

sleep 5
