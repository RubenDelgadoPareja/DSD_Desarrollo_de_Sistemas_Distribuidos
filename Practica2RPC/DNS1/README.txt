//José María Gómez García A2 DSD

Compilar:

rpcgen -NCa ServicioBasicoDeRegistro.x

g++ ServicioBasicoDeRegistro_client.c ServicioBasicoDeRegistro_clnt.c -o clienteDNS1 -lnsl

g++ ServicioBasicoDeRegistro_server.c ServicioBasicoDeRegistro_svc.c ../DNS2/ServicioBasicoDeRegistro2_clnt.c -o DNS1 -lnsl

Utilización:

./clienteDNS1 <ipservidorDNS1> registra <n_equipo>
./clienteDNS1 <ipservidorDNS1> borra <n_equipo>
./clienteDNS1 <ipservidorDNS1> buscaip <n_equipo>
./clienteDNS1 <ipservidorDNS1> visualizartabladns
./clienteDNS1 <ipservidorDNS1> vtabla <num_dns (1 o 2)>
