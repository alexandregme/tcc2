var Model = function () {

	var _self = this;
	
	/* --- PROTOCOL --- */
	_self.Protocol = {
		status: null,
		message: null,
		data: null,
		length: null
	};
	
	/* --- CIRCULAR --- */
	_self.Circular = {
		id: null,
		parcela: null,
		srid: null,
		latitude: null,
		longitude: null,
		raio: null,
		flSistemaCoordenada: null
	};

	/* --- FOTO --- */
	_self.Foto = {
		id: null,
		fragmento: null,
		dataCadastro: null,
		descricao: null,
		caminhoOriginal: null,
		caminhoThumb: null,
		wkt: null
	};

	/* --- FRAGMENTO --- */
	_self.Fragmento = {
		id: null,
		responsavel: null,
		nome: null,
		codigo: null,
		descricao: null,
		caminhoShape: null,
		caminhoPlanilha: null,
		idCentral: null
	};

	/* --- PARCELA --- */
	_self.Parcela = {
		id: null,
		fragmento: null,
		statusParcela: null,
		numeroParcela: null,
		tempoMedicao: null,
		descricao: null,
		tabelaGeometria: null,
		idCentral: null
	};

	/* --- PERFIL --- */
	_self.Perfil = {
		id: null,
		nome: null,
		descricao: null
	};

	/* --- PERFILUSUARIO --- */
	_self.PerfilUsuario = {
		id: null,
		perfil: null,
		usuario: null
	};

	/* --- PERMISSAO --- */
	_self.Permissao = {
		id: null,
		nome: null,
		descricao: null,
		agrupamento: null
	};

	/* --- PERMISSAOPERFIL --- */
	_self.PermissaoPerfil = {
		id: null,
		permissao: null,
		perfil: null
	};

	/* --- RETANGULAR --- */
	_self.Retangular = {
		id: null,
		parcela: null,
		srid: null,
		latitudeSuperior: null,
		latitudeInferior: null,
		longitudeInferior: null,
		longitudeSuperior: null,
		area: null,
		flSistemaCoordenada: null
	};

	/* --- SPATIALREFSYS --- */
	_self.SpatialRefSys = {
		srid: null,
		authName: null,
		authSrid: null,
		srtext: null,
		proj4text: null
	};

	/* --- STATUSPARCELA --- */
	_self.StatusParcela = {
		id: null,
		nome: null
	};

	/* --- USUARIO --- */
	_self.Usuario = {
		id: null,
		nome: null,
		cpf: null,
		login: null,
		senha: null,
		telefone: null,
		email: null,
		cargo: null,
		flAtivo: null
	};

	/* --- VARIAVEL --- */
	_self.Variavel = {
		id: null,
		nome: null,
		descricao: null,
		tabelaRelacionada: null,
		flFonteDados: null,
		fonteDados: null,
		flTipo: null,
		ordem: null
	};

	/* --- VARIAVELFRAGMENTO --- */
	_self.VariavelFragmento = {
		id: null,
		fragmento: null,
		variavel: null,
		valor: null
	};

	/* --- VARIAVELPARCELA --- */
	_self.VariavelParcela = {
		id: null,
		parcela: null,
		variavel: null,
		valor: null,
		numeroLinha: null,
		idCentral: null
	};

	/* --- ASSOCIATIONMODEL --- */
	_self.AssociationModel = {
		variavel: null,
		coluna: null
	};

	/* --- AUTHORIZATIONMODEL --- */
	_self.AuthorizationModel = {
		perfil: null,
		permissoes: null
	};

	/* --- COLUMNMODEL --- */
	_self.ColumnModel = {
		indice: null,
		nome: null
	};

	/* --- FOTOMODEL --- */
	_self.FotoModel = {
		foto: null,
		wkt: null
	};

	/* --- FRAGMENTOMODEL --- */
	_self.FragmentoModel = {
		fragmento: null,
		path: null,
		variaveis: null
	};

	/* --- FRAGMENTOVARIAVELMODEL --- */
	_self.FragmentoVariavelModel = {
		id: null,
		responsavel: null,
		nome: null,
		codigo: null,
		descricao: null,
		caminhoShape: null,
		caminhoPlanilha: null,
		variaveis: null
	};

	/* --- MOBILEMODEL --- */
	_self.MobileModel = {
		listFragmento: null,
		listParcela: null,
		listVariavelParcela: null
	};

	/* --- PARCELAMODEL --- */
	_self.ParcelaModel = {
		wkt: null,
		coordenadas: null,
		parcela: null
	};

	/* --- PARCELAVARIAVELMODEL --- */
	_self.ParcelaVariavelModel = {
		coordenadas: null,
		parcela: null,
		variaveis: null
	};

	/* --- UPLOADMODEL --- */
	_self.UploadModel = {
		bytes: null,
		path: null,
		file: null,
		url: null,
		local: null,
		data: null
	};

	/* --- USERMODEL --- */
	_self.UserModel = {
		usuario: null,
		perfis: null
	};

	/* --- VARIAVELMODEL --- */
	_self.VariavelModel = {
		fragmento: null,
		numeroParcelas: null,
		path: null,
		variaveis: null
	};

};