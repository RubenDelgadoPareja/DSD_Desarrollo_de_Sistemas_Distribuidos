
program SERVICIOBASICODEREGISTRO {

	version DNS {
		
		string REGISTRA (string) = 1;

		int BORRA (string) = 2;

		string BUSCAIP (string) = 3;

		string VISUALIZARTABLADNS () = 4;

		string VTABLA (string) = 5;
	} = 1;

} = 0x20000001;
