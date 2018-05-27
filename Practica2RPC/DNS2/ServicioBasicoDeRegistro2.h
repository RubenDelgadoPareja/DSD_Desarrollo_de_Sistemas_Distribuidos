/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _SERVICIOBASICODEREGISTRO2_H_RPCGEN
#define _SERVICIOBASICODEREGISTRO2_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif


#define SERVICIOBASICODEREGISTRO2 0x20000002
#define DNS2 2

#if defined(__STDC__) || defined(__cplusplus)
#define REGISTRA 1
extern  char ** registra_2(char *, CLIENT *);
extern  char ** registra_2_svc(char *, struct svc_req *);
#define BORRA 2
extern  int * borra_2(char *, CLIENT *);
extern  int * borra_2_svc(char *, struct svc_req *);
#define BUSCAIP 3
extern  char ** buscaip_2(char *, CLIENT *);
extern  char ** buscaip_2_svc(char *, struct svc_req *);
#define VISUALIZARTABLADNS 4
extern  char ** visualizartabladns_2(CLIENT *);
extern  char ** visualizartabladns_2_svc(struct svc_req *);
extern int serviciobasicoderegistro2_2_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define REGISTRA 1
extern  char ** registra_2();
extern  char ** registra_2_svc();
#define BORRA 2
extern  int * borra_2();
extern  int * borra_2_svc();
#define BUSCAIP 3
extern  char ** buscaip_2();
extern  char ** buscaip_2_svc();
#define VISUALIZARTABLADNS 4
extern  char ** visualizartabladns_2();
extern  char ** visualizartabladns_2_svc();
extern int serviciobasicoderegistro2_2_freeresult ();
#endif /* K&R C */

#ifdef __cplusplus
}
#endif

#endif /* !_SERVICIOBASICODEREGISTRO2_H_RPCGEN */
