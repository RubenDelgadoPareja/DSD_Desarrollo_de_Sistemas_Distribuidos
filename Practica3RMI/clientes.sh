#!/bin/sh -e
# ejecutar = Macro para compilacion y ejecucion del programa ejemplo
# en una sola maquina Unix de nombre localhost.

sleep 2

echo
echo "Cliente 1"
echo
java -cp . -Djava.security.policy=server.policy cliente_s1 localhost registra maria

echo
echo "Cliente 2"
echo
java -cp . -Djava.security.policy=server.policy cliente_s2 localhost registra carlos


echo
echo "Cliente 3"
echo
java -cp . -Djava.security.policy=server.policy cliente_s2 localhost dona maria 20

echo
echo "Cliente 4"
echo
java -cp . -Djava.security.policy=server.policy cliente_s1 localhost dona maria 10

echo
echo "Cliente 5"
echo
java -cp . -Djava.security.policy=server.policy cliente_s1 localhost dona carlos 5

echo
echo "Cliente 6"
echo
java -cp . -Djava.security.policy=server.policy cliente_s2 localhost dona carlos 1

echo
echo "Cliente 7"
echo
java -cp . -Djava.security.policy=server.policy cliente_s2 localhost totaldonado carlos
