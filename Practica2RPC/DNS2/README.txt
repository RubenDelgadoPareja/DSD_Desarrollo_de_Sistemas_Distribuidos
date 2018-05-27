//José María Gómez García A2 DSD

Compilar:

rpcgen -NCa ServicioBasicoDeRegistro2.x

g++ ServicioBasicoDeRegistro2_client.c ServicioBasicoDeRegistro2_clnt.c -o clienteDNS2 -lnsl

g++ ServicioBasicoDeRegistro2_server.c ServicioBasicoDeRegistro2_svc.c -o DNS2 -lnsl

Utilización:

./clienteDNS2 <ipservidorDNS2> registra <n_equipo>
./clienteDNS2 <ipservidorDNS2> borra <n_equipo>
./clienteDNS2 <ipservidorDNS2> buscaip <n_equipo>
./clienteDNS2 <ipservidorDNS2> visualizartabladns
