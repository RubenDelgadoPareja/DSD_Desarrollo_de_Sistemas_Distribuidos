
program SERVICIOBASICODEREGISTRO2 {

	version DNS2 {
		
		string REGISTRA (string) = 1;

		int BORRA (string) = 2;

		string BUSCAIP (string) = 3;
		
		string VISUALIZARTABLADNS () = 4;
	} = 2;

} = 0x20000002;
