var viewModel = {

	/* Url */
	url: {
	
		base: window.location.protocol + "//" + window.location.host + "/"
		
	},

	/* Pagination */
	pagination: {
	
		page: ko.observable(1),
	
		size: ko.observable(15),
		
		amount: ko.observable(0),
		
		parameter: 'pagina',
		
		parameters: ko.observable(),
		
		render: function (__data) {
			
			if (__data[window.viewModel.pagination.parameter] == window.viewModel.pagination.page()) {
				return 'btn btn-success';
			} else {
				return 'btn';
			}
		
		},
		
		generate: function () {
			
			var _a = new Array();
			
			var _parameters = window.viewModel.pagination.parameters();
			
			for (var i = 0; i < Math.ceil(viewModel.pagination.amount() / viewModel.pagination.size()); i++) {
				
				var _p = $.extend(true, {}, _parameters);
				
				_p[window.viewModel.pagination.parameter] = (i + 1);
				_a.push(_p);
				
			}
			
			return _a;
			
		}
	
	},
	
	/* Message Controller */
	message: {
		
		// Variable for biding
		data: ko.observable(),
		
		// Default
		main: ko.observable('Nenhuma informação sobre essa página'),
		
		// "Converts" a response to data message
		set: function(__response) {
			
			viewModel.message.data({
				"status": __response.status,
				"message": __response.message
			});
			
			$('html, body').animate({
				scrollTop: $("#message_container").offset().top - 30
			}, 300);
			
		},
		
		// Clear the message container
		clear: function() {
			viewModel.message.data(undefined);
		},
		
		// Renderer for the message
		render: function() {
			
			if (viewModel.message.data() == undefined) {
				
				viewModel.message.data({
					message: viewModel.message.main()
				});
				
				return 'message message-info';
				
			}
			
			var _status = viewModel.message.data().status;
			
			if (_status == "s") {
				return 'message message-success';
			} else if (_status == "a") {
				return 'message message-alert'
			} else if (_status == "e") {
				return 'message message-error';
			}
			
		}
	},
	
	/* Loading */
	loading: {
	
		show: function (__cursor) {
		
			if (__cursor !== false) {
				$('html').css('cursor', 'wait');
			}
			
			$('#loading').css({
				position: 'fixed',
				backgroundColor: '#FFFFFF',
				opacity: 0.6,
				'z-index': 30,
				top: "0px",
				left: "0px",
				width: $(document).width() + "px",
				height: $(document).height() + "px",
				display: "block"
			});
			
		},
		
		hide: function () {
		
			$('html').css('cursor', '');
			
			$('#loading').css({
				position: 'fixed',
				opacity: 1,
				'z-index': -1,
				top: "0px",
				left: "0px",
				width: "0px",
				height: "0px",
				display: "none"
			});
		
		}
	
	},
	
	/* Popup */
	popup: {
	
		show: function (__content, __showClose) {
		
			if (__showClose === false) {
				$('#popup_button').css('display', 'none');
			}
		
			$('#popup_loading').css({
				position: 'fixed',
				backgroundColor: '#000000',
				opacity: 0.6,
				'z-index': 10,
				top: "0px",
				left: "0px",
				width: $(document).width() + "px",
				height: $(document).height() + "px",
				display: "block"
			});
		
			$('#popup_content').html(__content);
			
			var _css = window.viewModel.popup.calculate(30);
			_css['display'] = 'block';
			
			$('#popup').css(_css);
		
		},
		
		hide: function () {
		
			$('#popup_button').css('display', 'block');
			
			$('#popup_loading').css({
				position: 'fixed',
				opacity: 1,
				'z-index': -1,
				top: "0px",
				left: "0px",
				width: "0px",
				height: "0px",
				display: "none"
			});
		
			$('#popup_content').html(null);
			$('#popup').css('display', 'none');
		
		},
		
		calculate: function (__margin) {
			
			return {
				'top': __margin + 'px',
				'left': __margin + 'px',
				'width': ($(window).width() - (__margin * 2)) + 'px',
				'height': ($(window).height() - (__margin * 2)) + 'px'
			}
		
		}
	
	},
	
	/* Requests */
	ajax: {
	
		json: function (__url, __method, __data, __successCallback, __errorCallback, __headers) {
		
			$.ajax({
				cache: false,
				context: document.body,
				data: __data,
				type: __method,
				url: __url,
				headers: __headers || {
					'Content-type': 'application/json'
				},
				beforeSend: function () {
					viewModel.loading.show();
				},
				complete: function () {
					viewModel.loading.hide();
				},
				success: __successCallback,
				error: __errorCallback || function (__jqXHR, __textStatus, __errorThrown) {
					
					viewModel.loading.hide();
					
					if ((__jqXHR.responseText.substr(0, 1) == "{") || (__jqXHR.responseText.substr(0, 1) == "[")) {
						window.viewModel.message.set(JSON.parse(__jqXHR.responseText));
					} else {
						
						$('#popup_content').html(__jqXHR.responseText);
						$('#popup').css('display', 'block');

					}
					
				}
			});
		
		},
		
		text: function () {
		
			$.ajax({
				cache: false,
				context: document.body,
				data: __data,
				type: __method,
				url: __url,
				headers: {
					'Content-type': 'text/plain'
				},
				beforeSend: function () {
					viewModel.loading.show();
				},
				complete: function () {
					viewModel.loading.hide();
				},
				success: __successCallback,
				error: __errorCallback || function (__jqXHR, __textStatus, __errorThrown) {
					window.viewModel.message.set(JSON.parse(__jqXHR.responseText));
				}
			});
		
		},
		
		xml: function () {
		
			$.ajax({
				cache: false,
				context: document.body,
				data: __data,
				type: __method,
				url: __url,
				headers: {
					'Content-type': 'text/xml'
				},
				beforeSend: function () {
					viewModel.loading.show();
				},
				complete: function () {
					viewModel.loading.hide();
				},
				success: __successCallback,
				error: __errorCallback || function (__jqXHR, __textStatus, __errorThrown) {
					window.viewModel.message.set(JSON.parse(__jqXHR.responseText));
				}
			});
		
		}
	
	},
	
	/* Utils */
	utils: {
	
		toDouble: function (__value) {

			var r = /\d+(\,\d+)?/;
			
			if (r.test(__value)) {
				return parseFloat(__value.toString().replace(",", "."));
			} else {
				return 0.0;
			}

		},
		
		copy: function (__source, __destiny, __properties) {
		
			for (var p = 0; p < __properties.length; p++) {
				__destiny[__properties[p]] = __source[__properties[p]];
			}
			
			return __destiny;
		
		},
		
		isNullOrUndefined: function (__data) {
			return ((__data === null) || (__data === undefined));
		},
		
		isEmpty: function (__data) {
			return ((__data === null) || (__data === undefined) || (__data === ""));
		},
		
		isValid: function (__data) {
			return ((!viewModel.utils.isEmpty(__data)) && (__data !== "0"));
		}
	
	}

};

// Aliases
window._A = window.viewModel;

// UPLOAD Component (Knockout + jQuery)
var Upload = function (__id, __url) {
	
	if ((window.File && window.FileList && window.FileReader) == undefined) {
		window.alert('O seu navegador não está atualizado e não possui a funcionalidade para manipular arquivos. Atualize seu navegador para a versão mais recente.');
		throw 'Not supported';
		return;
	}
	
	var _element = document.getElementById(__id);
	
	if (_element == null) {
		return null;
	}
	
	/**
	 * Reference
	 */
	var _self = this;
	
	/**
	 * URL for Upload Service
	 */
	_self.url = __url;
	
	/**
	 * Reference Model for Binding
	 */
	_self.model = {
		'data': {
			'bytes': 0,
			'path': null,
			'file': null,
			'url': null,
			'local': null
		},
		'status': null,
		'message': null
	};
	
	/**
	 * The DOM Element
	 */
	_self.element = _element;

	/**
	 * Uploaded Data
	 */
	_self.data = ko.observable(_self.model);
	
	/**
	 * Callback used when AJAX is finished,
	 * successfully or error
	 * THIS means "The Uploader"
	 */
	_self.onFinish = undefined;
	
	/**
	 * Called before "upload" (on OnChange)
	 * before setting selected files. This function
	 * should return true or false due to the due to the continuity of the flow
	 * THIS means "The Uploader"
	 */
	_self.onStart = undefined;
	
	/**
	 * Set the message and the style of the badge
	 */
	_self.label = ko.computed(function () {
	
		switch (_self.data().status) {
			
			case 'a':
				
				return {
					'class': 'badge badge-warning',
					'message': _self.data().message
				};
				
			break;
			
			case 's':
				
				return {
					'class': 'badge badge-success',
					'message': _self.data().message
				};
				
			break;
			
			case 'e':

				return {
					'class': 'badge badge-important',
					'message': _self.data().message
				};
				
			break;
			
			default:
				
				return {
					'class': 'badge badge-warning',
					'message': 'Arquivo não enviado'
				};
				
			break;
			
		}
	
	}, viewModel);
	
	/**
	 * Clear the uploaded data
	 */
	_self.clear = function () {
		
		var _m = _self.model;
		_m.data.local = null;
		
		$(_self.element).removeClass('sent');
		
		_self.element.value = "";
		
		_self.data(_m);
		
	},
	
	/**
	 * Open, in a new window, the sent file
	 */
	_self.view = function () {
	
		if (_self.data().data.url) {
			
			var _w = window.open('about:blank', '_blank');
			_w.location.href = _self.data().data.url;
			
		} else {
			
			window.alert('Arquivo não enviado');
			
		}
		
	},
	
	/**
	 * Do the upload
	 */
	_self.upload = function () {
	
		if (_self.element.files.length == 0) {
			return;
		} 
		
		if ((_self.onStart != undefined) && (typeof(_self.onStart) == 'function')) {
			
			var _return =_self.onStart.call(_self);
			
			if (!_return) {
				_self.element.value = "";
				return;
			}
			
		}
	
		var _file = _self.element.files[0];
		
		var _formData = new FormData();
		_formData.append('attachment', _file);
		
		viewModel.loading.show();
		
		$.ajax({
			url: _self.url,
			type: "POST",
			data: _formData,
			cache: false,
			contentType: false,
			processData: false,
			dataType: 'json',
			success: function (__data, __status) {
				
				_self.data(__data);
				
				if (__data.status == 's') {
					$(_self.element).addClass('sent');
				}				
				
				viewModel.loading.hide();
				
				if ((_self.onFinish != undefined) && (typeof(_self.onFinish) == 'function')) {
					_self.onFinish.call(_self, __data, __status);
				}
				
			},
			error: function (__data, __status) {
				
				_self.data(JSON.parse(__data.responseText));
				$(_self.element).removeClass('sent');
				viewModel.loading.hide();
				
				if ((_self.onFinish != undefined) && (typeof(_self.onFinish) == 'function')) {
					_self.onFinish.call(_self, __data, __status);
				}
				
			}
		});
	
	}
	
	// Apply the callback to send file when selected
	_element.onchange = _self.upload;
	
};

// UPLOAD MULTIPLE Component (Knockout + jQuery)
var UploadMultiple = function (__id, __url) {
	
	if ((window.File && window.FileList && window.FileReader) == undefined) {
		window.alert('O seu navegador não está atualizado e não possui a funcionalidade para manipular arquivos. Atualize seu navegador para a versão mais recente.');
		throw 'Not supported';
		return;
	}
	
	var _element = document.getElementById(__id);
	
	if (_element == null) {
		return null;
	}
	
	/**
	 * Reference
	 */
	var _self = this;
	
	/**
	 * Name of the parameter that
	 * defines the POST key
	 */
	_self.parameter = 'attachment';
	
	/**
	 * Other parameters to send with
	 * FormData
	 */
	_self.parameters = undefined;
	
	/**
	 * URL for Upload Service
	 */
	_self.url = __url;
	
	/**
	 * The DOM Element
	 */
	_self.element = _element;

	/**
	 * Uploaded Data
	 */
	_self.data = ko.observableArray([]);
	
	/**
	 * Array for user's Files Reference
	 */
	_self.files = [];
	
	/**
	 * Clear the uploaded data
	 */
	_self.clear = function () {
		
		_self.element.value = "";
		_self.data = ko.observableArray([]);
		_self.files = [];
		
	};
	
	/**
	 * "Callback" used when iterating over the
	 * selected files
	 * THIS means "The File"
	 */
	_self.onLoadFile = undefined;
	
	/**
	 * Callback used when AJAX is finished,
	 * successfully or error
	 * THIS means "The Uploader"
	 */
	_self.onFinish = undefined;
	
	/**
	 * Called before "upload" (on OnChange)
	 * before setting selected files. This function
	 * should return true or false due to the due to the continuity of the flow
	 * THIS means "The Uploader"
	 */
	_self.onStart = undefined;
	
	/**
	 * Event fired when user select his files
	 */
	_self.element.onchange = function (__event) {
	
		var _length = this.files.length;
		
		if (_length == 0) {
			_self.element.value = "";
			window.alert('Selecione um ou mais arquivos...');
			return;
		}
		
		if ((_self.onStart != undefined) && (typeof(_self.onStart) == 'function')) {
			
			var _return =_self.onStart.call(_self);
			
			if (!_return) {
				_self.element.value = "";
				return;
			}
			
		}
		
		var _startIndex = _self.data().length;
		
		for (var f = 0; f < _length; f++) {
			
			var _file = this.files[f];
			_file.index = f + _startIndex;
			
			_self.files.push(_file);
			
			if ((_self.onLoadFile != undefined) && (typeof(_self.onLoadFile) == 'function')) {
				_self.onLoadFile.call(_file);
			}
			
		}
		
		if ((_self.upload != undefined) && (typeof(_self.upload) == 'function')) {
			_self.upload();
		}
	
	};
	
	/**
	 * Callback fired, if defined, when
	 * "onchange" finishes iterating over
	 * the selected file. If you want, override it
	 */
	_self.upload = function () {
	
		if (_self.files.length == 0) {
			return;
		}
		
		for (var f = 0; f < _self.files.length; f++) {
			
			var _file = this.files[f];
			
			var _formData = new FormData();
			_formData.append(_self.parameter, _file);
			
			if ((_self.parameters != undefined) && (typeof(_self.parameters) == 'object')) {
				
				for (var p = 0; p < _self.parameters.length; p++) {
					_formData.append(_self.parameters[p].key, _self.parameters[p].value);
				}
				
			}
			
			$.ajax({
				url: _self.url,
				type: "POST",
				data: _formData,
				cache: false,
				contentType: false,
				processData: false,
				dataType: 'json',
				
				success: (function (__file) {
					return function (__response, __status) {
						
						_self.data.push(__response);
		
						if ((_self.onFinish != undefined) && (typeof(_self.onFinish) == 'function')) {
							_self.onFinish.call(_self, __response, __status, __file);
						}
						
					}
				})(_file),
				
				error: (function (__file) {
					return function (__data, __error) {
						
						var _response = null;
						
						if (__data.responseText == "") {
							
							_response = {
								'status': 'e',
								'data': null,
								'message': __error + ": " + __data.statusText,
								'length': 0
							};
							
						} else {
						
							if (__data.responseText[0] == "{") {
								
								_response = JSON.parse(__data.responseText);
								
							} else {
								
								_response = {
									'status': 'e',
									'data': null,
									'message': __data.responseText,
									'length': 0
								};
								
							}
						
						}
						
						_self.data.push(_response);
		
						if ((_self.onFinish != undefined) && (typeof(_self.onFinish) == 'function')) {
							_self.onFinish.call(_self, _response, __error, __file);
						}

					}
				})(_file)
				
			});
			
		}
		
		_self.files = [];
		_self.element.value = "";
	
	};
	
};

/* The LordShark Validator (jQuery) */
var Validate = function (__node) {

	var _self = this;
	
	/**
	 * The node where the search function begins to
	 * look for the elements with data-validation property
	 */
	this.node = __node;
	
	/**
	 * Elements to be validated
	 */
	this.elements = [];
	
	/**
	 * Control add or remove the class to the element of
	 * its nth-parent
	 */
	
	this.render = function (__item, __control, __class, __parentClass, __rule) {
	
		var _element = __item.element;
		
		if (__parentClass) {
		
			while (!_element.hasClass(__parentClass)) {
				_element = _element.parent();
			}
		
		}
		
		if (__control) {
			_element.removeClass(__class);
			_element.attr('title', '');
		} else {
			_element.addClass(__class);
			_element.attr('title', __rule.mensagem);
		}
	
	};
	
	/**
	 * Clear all fields
	 */
	this.clear = function (__class, __parentClass) {
	
		for (var e = 0; e < _self.elements.length; e++) {
			this.render(_self.elements[e], true, __class, __parentClass, null);
		}
	
	};
	
	/**
	 * Run along all elements and apply the validate rule
	 */
	this.validate = function (__class, __parentClass) {
	
		var _output = true;
		
		for (var e = 0; e < _self.elements.length; e++) {
		
			var _item = _self.elements[e];
		
			var _properties = _self.Validations[_item.element.attr('data-validation')];
			
			if (_properties) {
			
				var _result = null;
				
				if (_properties.rule instanceof RegExp) {
					_result = _properties.rule.test(_item.element.val());
				} else if (_properties.rule instanceof Function) {
					_result = _properties.rule.call(null, _item.element);
				}
				
				if (_properties.rtype == false) {
					_result = (_result) ? false : true;
				} else {
					_result = (_result) ? true : false;
				}
				
				_output &= _result;
				
				_self.render(_item, _result, __class, __parentClass, _properties);
				
			} else {
				_self.render(_item, true, __class, __parentClass, null);
			}
		
		}
		
		return _output;
	
	};
	
	/**
	 * Init
	 */
	this.init = function () {
	
		var _node = $('#' + __node) || $(document.body);
		
		var _elements = $('#' + __node).find("*[data-validation]");
		
		for (var e = 0; e < _elements.length; e++) {
			
			var _element = $(_elements[e]);
			
			if (_element.attr('data-validation') != "") {
				_self.elements.push({
					'element': _element,
					'title': _element.attr('title') || 'Descrição não informada'
				});
			}
			
		}
	
	}
	
	this.init();
	
	// Rules
	this.Validations = {

		rEmpty: {
			"rule": /^$/i,
			"mensagem": "Este campo não pode ser nulo.",
			"rtype": "0",
			"maxlength": null
		},
		
		rNotEmpty: {
			"rule": /^$/i,
			"mensagem": "Este campo pode ser nulo.",
			"rtype": "1",
			"maxlength": null
		},

		rNomesOpcional: {
			"rule": /[\t\n\r\^\´\`\!\@\#\$\%\¨\&\*\(\)\~\?\:\;\[\]\{\}\'\"]+/i,
			"mensagem": "Este campo não aceita caracteres especiais como # $ % { } ´ ^ ~, porém pode ser nulo.",
			"rtype": false,
			"maxlength": null
		},

		rNomesOpcional30: {
			"rule": /[\t\n\r\^\´\`\!\@\#\$\%\¨\&\*\(\)\~\?\:\;\[\]\{\}\'\"]{1,30}/i,
			"mensagem": "Este campo não aceita caracteres especiais como # $ % { } ´ ^ ~, porém pode ser nulo - Limitação: 30 caracteres.",
			"rtype": false,
			"maxlength": null
		},
		
		rNomesOpcional60: {
			"rule": /[\t\n\r\^\´\`\!\@\#\$\%\¨\&\*\(\)\~\?\:\;\[\]\{\}\'\"]{1,60}/i,
			"mensagem": "Este campo não aceita caracteres especiais como # $ % { } ´ ^ ~, porém pode ser nulo - Limitação: 60 caracteres.",
			"rtype": false,
			"maxlength": null
		},
		
		rNomesOpcional100: {
			"rule": /[\t\n\r\^\´\`\!\@\#\$\%\¨\&\*\(\)\~\?\:\;\[\]\{\}\'\"]{1,100}/i,
			 "mensagem": "Este campo não aceita caracteres especiais como # $ % { } ´ ^ ~, porém pode ser nulo - Limitação: 100 caracteres.",
			 "rtype": false,
			 "maxlength": null
		}, 
		
		rNomes: {
			"rule": /(([\t\n\r\^\´\`\!\@\#\$\%\¨\&\*\(\)\~\?\:\;\[\]\{\}\'\"]+)|(^$))+/i,
			"mensagem": "Este campo não aceita caracteres especiais como # $ % { } ´ ^ ~. e não pode ser nulo",
			"rtype": false,
			"maxlength": null
		},
		
		rNomes10: {
			"rule": /(([\t\n\r\^\´\`\!\@\#\$\%\¨\&\*\(\)\~\?\:\;\[\]\{\}\'\"]){1,10}|(^$))/i,
			"mensagem": "Este campo não aceita caracteres especiais como # $ % { } ´ ^ ~. e não pode ser nulo - Limitação: 10 caracteres",
			"rtype": false, "maxlength": null
		},
		
		rNomes30: {
			"rule": /(([\t\n\r\^\´\`\!\@\#\$\%\¨\&\*\(\)\~\?\:\;\[\]\{\}\'\"]){1,30}|(^$))/i,
			"mensagem": "Este campo não aceita caracteres especiais como # $ % { } ´ ^ ~. e não pode ser nulo - Limitação: 30 caracteres",
			"rtype": false,
			"maxlength": null
		},
		
		rNomes50: {
			"rule": /(([\t\n\r\^\´\`\!\@\#\$\%\¨\&\*\(\)\~\?\:\;\[\]\{\}\'\"]){1,50}|(^$))/i,
			"mensagem": "Este campo não aceita caracteres especiais como # $ % { } ´ ^ ~. e não pode ser nulo - Limitação: 50 caracteres",
			"rtype": false,
			"maxlength": null
		},
		
		rNomes60: {
			"rule": /(([\t\n\r\^\´\`\!\@\#\$\%\¨\&\*\(\)\~\?\:\;\[\]\{\}\'\"]){1,60}|(^$))/i,
			"mensagem": "Este campo não aceita caracteres especiais como # $ % { } ´ ^ ~. e não pode ser nulo - Limitação: 60 caracteres",
			"rtype": false,
			"maxlength": null
		},
		
		rNomes100: {
			"rule": /(([\t\n\r\^\´\`\!\@\#\$\%\¨\&\*\(\)\~\?\:\;\[\]\{\}\'\"]){1,100}|(^$))/i,
			"mensagem": "Este campo não aceita caracteres especiais como # $ % { } ´ ^ ~. e não pode ser nulo - Limitação: 100 caracteres",
			"rtype": false,
			"maxlength": null
		},
		
		rNomes255: {
			"rule": /(([\t\n\r\^\´\`\!\@\#\$\%\¨\&\*\(\)\~\?\:\;\[\]\{\}\'\"]){1,255}|(^$))/i,
			"mensagem": "Este campo não aceita caracteres especiais como # $ % { } ´ ^ ~. e não pode ser nulo - Limitação: 255 caracteres",
			"rtype": false,
			"maxlength": null
		},
		
		rNomes1024: {
			"rule": /(([\t\n\r\^\´\`\!\@\#\$\%\¨\&\*\(\)\~\?\:\;\[\]\{\}\'\"]){1,1024}|(^$))/i,
			"mensagem": "Este campo não aceita caracteres especiais como # $ % { } ´ ^ ~. e não pode ser nulo - Limitação: 1024 caracteres",
			"rtype": false,
			"maxlength": null
		},
		
		rNumeros: {
			"rule": /^\d+$/,
			"mensagem": "Este campo só aceita caracteres numéricos.",
			"rtype": true,
			"maxlength": null
		},
		
		rNumerosOpcional: {
			"rule": /(^\d+$)|(^$)/,
			"mensagem": "Este campo só aceita caracteres numéricos, caso seja preenchido.",
			"rtype": true,
			"maxlength": null
		},
		
		rNumeros4: {
			"rule": /^\d{1,4}$/,
			"mensagem": "Este campo só aceita, no máximo, 4 caracteres numéricos.",
			"rtype": true,
			"maxlength": null
		},
		
		rNumeros6: {
			"rule": /^\d{1,6}$/,
			"mensagem": "Este campo só aceita, no máximo, 6 caracteres numéricos.",
			"rtype": true,
			"maxlength": null
		},
		
		rNumeros10: {
			"rule": /^\d{1,10}$/,
			"mensagem": "Este campo só aceita, no máximo, 10 caracteres numéricos.",
			"rtype": true,
			"maxlength": null
		},
		
		rNumerosOpcional6: {
			"rule": /^((\d{1,6})|(^$))$/,
			"mensagem": "Este campo só aceita, no máximo, 6 caracteres numéricos.",
			"rtype": true,
			"maxlength": null
		},
		
		rCep: {
			"rule": /^\d{5}-\d{3}$/,
			"mensagem": "O campo de CEP deve seguir o formato DDDDD-DDD.",
			"rtype": true,
			"maxlength": null
		},
		
		rCepOpcional: {
			"rule": /(^\d{5}-\d{3}$)|(^$)/,
			"mensagem": "O campo de CEP deve seguir o formato DDDDD-DDD, caso seja preenchido.",
			"rtype": true,
			"maxlength": null
		},
		
		rEmail: {
			"rule": /^[a-z0-9_\-\.](\.?[a-z0-9_\.\-]){1,100}@[0-9a-z_\-\.]{1,100}(\.[a-z0-9-]{2,100}){1,100}$/i, 
			"mensagem": "O E-Mail deve possuir o seguinte formato: usuario@dominio.", 
			"rtype": true,
			"maxlength": null
		},
		
		rTelefone: {
			"rule": /^\+\d{1,3}\s+\(\d{1,3}\)\s+\d{4}\-\d{4}$/,
			"mensagem": "O telefone deve possuir o seguinte formato: +DD (DD) DDDD-DDDD.",
			"rtype": true,
			"maxlength": null
		},
		
		rReal: {
			"rule": /^\d+(,\d+)?$/,
			"mensagem": "Este campo só aceita valores positivos ou decimais separados por vírgula e é obrigatório",
			"rtype": true,
			"maxlength": null
		},
		
		rRealOpcional: {
			"rule": /((^\d+(,\d+)?$)|(^$))/,
			"mensagem": "Este campo só aceita valores positivos ou decimais separados por vírgula", 
			"rtype": true,
			"maxlength": null
		},
		
		rReal2: {
			"rule": /(^100$)|(^\d{1,2}(,\d{1,2})?$)/,
			"mensagem": "Este campo só aceita valores positivos ou decimais, no formato 'XX,YY', separados por vírgula e é obrigatório",
			"rtype": true,
			"maxlength": null
		},
		
		rTextarea: {
			"rule": /^[ \t\n\r\f\v\wáéíóúàèìòùâêîôûäëïöüãõÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÃÕ\.,\/\\\- Çç\(\)\%!\?\$\&\"\#\'@\d\´\`\[\]\{\}\*\+\=\'\º\ª\:\;]{1,1000}$/i,
			"mensagem": "Este campo aceita no máximo 1000 caracteres e não pode ser nulo.", 
			"rtype": true,
			"maxlength": null
		},
		
		rTextareaOpcional: {
			"rule": /((^$)|(^[ \t\n\r\f\v\wáéíóúàèìòùâêîôûäëïöüãõÁÉÍÓÚÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÃÕ\.,\/\\\- Çç\(\)\%!\?\$\&\"\#\'@\d\´\`\[\]\{\}\*\+\=\'\º\ª\:\;]{1,1000}$))/i,
			"mensagem": "Este campo aceita no máximo 1000 caracteres.",
			"rtype": true,
			"maxlength": null
		},
		
		rSelect: {
			"rule": /^\-1$/, 
			"mensagem": "Você deve selecionar uma opção para este campo.",
			"rtype": false,
			"maxlength": null
		},
		
		rCnpj: {
			"rule": /\d{2}\.\d{3}\.\d{3}\/\d{4}\-\d{2}/,
			"mensagem": "O CNPJ deve estar no formato DD.DDD.DDD/DDDD-DD.",
			"rtype": true,
			"maxlength": null
		},
		
		rHorasDias: {
			"rule": /(^[0-9]{1}(,[0-9]{1,2})?$)|(^1[0-9]{1}(,[0-9]{1,2})?$)|(^2[0-3]{1}(,[0-9]{1,2})?$)/,
			"mensagem": "As Horas por Dia devem variar de 0 a 23,99. Os decimais devem ser separados por vírgula e no máximo 2 casas.",
			"rtype": true,
			"maxlength": null
		},
		
		rDiasMes:  {
			"rule": /((^[0-9]{1}(,[0-9]{1,2})?$)|(^1[0-9]{1}(,[0-9]{1,2})?$)|(^2[0-9]{1}(,[0-9]{1,2})?$)|^30$)/,
			"mensagem": "Os Dias por Mês devem variar de 0 a 30. Os decimais devem ser separados por vírgula e no máximo 2 casas..",
			"rtype": true,
			"maxlength": null
		},
		
		rMesesAno: {
			"rule": /((^[0-9]{1}(,[0-9]{1,2})?$)|(^1[0-1]{1}(,[0-9]{1,2})?$)|^12$)/,
			"mensagem": "Os Meses por Ano devem variar de 0 a 12. Os decimais devem ser separados por vírgula e no máximo 2 casas..",
			"rtype": true,
			"maxlength": null
		},
		
		rReal25: {
			"rule": /^(-)?(\d+(.\d+)?){2,25}$/,
			"mensagem": "Valor Inválido para coordenadas",
			"rtype": true,
			"maxlength": null
		},
		
		rRealPos25: {
			"rule": /^(\d+(.\d+)?){2,25}$/,
			"mensagem": "Valor Inválido para coordenadas",
			"rtype": true,
			"maxlength": null
		},
		
		rRealPositivo6: {
			"rule": /^(\d+(,\d+)?){1,6}$/,
			"mensagem": "Este campo só aceita valores positivos ou decimais separados por vírgula e é obrigatório", 
			"rtype": true,
			"maxlength": null
		},
		
		rRealPositivo12: {
			"rule": /^(\d+(,\d+)?){1,12}$/,
			"mensagem": "Este campo só aceita valores positivos ou decimais separados por vírgula e é obrigatório",
			"rtype": true,
			"maxlength": null
		},
		
		rData: {
			"rule": /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/,
			"mensagem": "A data deve ser no formato DD/MM/AAAA",
			"rtype": true,
			"maxlength": null
		},
		
		rDataOpcional: {
			"rule": /(^$)|(^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$)/,
			"mensagem": "A data deve ser no formato DD/MM/AAAA",
			"rtype": true,
			"maxlength": null
		},
		
		rAno: {
			"rule": /^\d{4}$/,
			"mensagem": "Digite apenas 4 números para o ano",
			"rtype": true,
			"maxlength": null
		},
		
		rAnoOpcional: {
			"rule": /(^\d{4}$)|(^$)/,
			"mensagem": "Digite apenas 4 números para o ano. Este campo pode ser vazio.",
			"rtype": true,
			"maxlength": null
		},
		
		rGrau: {
			"rule": /^((\-[0-9]{1,3})|(^$))$/,
			"mensagem": "Este campo deve seguir o formato -XXX, caso seja preenchido",
			"rtype": true,
			"maxlength": null
		},
		
		rMinuto: {
			"rule": /^((\d{2})|(^$))$/, 
			"mensagem": "Este campo só aceita 2 caracteres numéricos, caso seja preenchido",
			"rtype": true,
			"maxlength": null
		},
		
		rSegundo: {
			"rule": /^((\d{2}(,\d{2})?)|(^$))$/,
			"mensagem": "Este campo aceita valores numéricos no formato XX,XX, caso seja preenchido",
			"rtype": true,
			"maxlength": null
		},
		
		rAlfaNum: {
			"rule": /^[0-9A-Z]+$/,
			"mensagem": "Este campo aceita números ou letras maiúsculas",
			"rtype": true,
			"maxlength": null
		},
		
		rAlfaNumOpcional: {
			"rule": /(^[0-9A-Z]+$)|(^$)/,
			"mensagem": "Este campo aceita números ou letras maiúsculas, caso seja preenchido",
			"rtype": true,
			"maxlength": null
		},
		
		rUrl: {
			"rule": /(http|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:\/~\+#]*[\w\-\@?^=%&amp;\/~\+#])?/,
			"mensagem": "Url não está no formato correto",
			"rtype": true,
			"maxlength": null
		},
		
		rCoordinates: {
			"rule": /\-?\d{1,3}\.\d+/,
			"mensagem": "A Coordenada em Grau Decimal deve estar no formato -XX.XX",
			"rtype": true,
			"maxlength": null
		},
		
		rDropDown: {
			"rule": function (__element) {
				return (__element.prop('selectedIndex') > 0);
			},
			"mensagem": "Selecione uma opção!",
			"rtype": true,
			"maxlength": null
		},
		
		rFile: {
			"rule": function (__element) {
				return __element.hasClass('sent');
			},
			"mensagem": "Envie um arquivo",
			"rtype": true,
			"maxlength": null
		},
		
		rProcess: {
			"rule": /^\d{5}\/\d{4}\/\d{3}\/\d{4}$/,
			"mensagem": "O Protocolo deve possuir o seguinte formato: DDDDD/AAAA/DDD/AAAA",
			"rtype": true,
			"maxlength": null
		},
		
		rProcessOpcional: {
			"rule": /(^\d{5}\/\d{4}\/\d{3}\/\d{4}$)|(^$)/,
			"mensagem": "O Protocolo deve possuir o seguinte formato: DDDDD/AAAA/DDD/AAAA. Este campo pode ser vazio.",
			"rtype": true,
			"maxlength": null
		},
		
		rMatricula: {
			"rule": /^[0-9A-Z\/\-]+$/,
			"mensagem": "Este campo aceita números, letras maiúsculas e os caractéres / e -",
			"rtype": true,
			"maxlength": null
		}
			
	};

};

/* Select Dealer (knockout) */
var Select = function (__element, __init, __data, __datum) {

	var _self = this;
	
	if (!__element) {
		return null;
	}
	
	var _element = document.getElementById(__element);
	
	if (_element == null) {
		return null;
	}
	
	/**
	 * The DOMElement
	 */
	this.element = _element;
	
	/**
	 * Indicates if the select has a caption (index 0) or not
	 */
	this.hasCaption = true;
	
	/**
	 * Datum - The Selected Value
	 */
	this.datum = (__datum) ? ko.observable(__datum) : ko.observable(undefined);
	
	/**
	 * Data - The set of values
	 */
	this.data = (__data && __data.length > 0) ? ko.observableArray(__data) : ko.observableArray([]);
	
	/**
	 * onChange - Initially null, just to store the callback function
	 */
	this.onChange = null;
	
	/**
	 * Fill - Set the data property
	 */
	this.fill = function(__data) {
		
		if ((__data) && (__data.length > 0)) {
			_self.data(__data);
		}
		
		// Knockout behavior
		if (this.element.options.length == 0) {
			ko.applyBindings(window.viewModel, this.element);
		}
		
	};
	
	/**
	 * Clear the selected item
	 */
	this.clear = function() {
		
		if (__datum) {
			_self.datum(__datum);
		} else {
			_self.datum(undefined);
		}
		
		_self.element.selectedIndex = 0;
	
	};
	
	/**
	 * Clear datum and data
	 */
	this.dispose = function() {
		_self.datum(undefined);
		_self.data([]);
	};
	
	/**
	 * Get the selected index
	 */
	this.selectedIndex = function () {
		return _self.element.selectedIndex;
	};
	
	/**
	 * Gets the nth element in array
	 */
	this.get = function (__index) {
	
		if ((__index < 0) || (__index >= _self.data().length)) {
			return null;
		}
		
		return _self.data()[__index];
	
	};
	
	/**
	 * Sets the selectedIndex by value
	 */
	this.set = function (__property, __value) {
	
		if ((!__property) && (!__value)) {
			return;
		}
		
		var _data = _self.data();
		
		for (var _i = 0; _i < _data.length; _i++) {
		
			if (_data[_i][__property] == __value) {
				
				_self.datum(_data[_i][__property]);
				_self.element.selectedIndex = ((_self.hasCaption === true) ? _i + 1 : _i);
				
				return;
			
			}
		
		}
	
	};
	
	/**
	 * Get the selected item
	 */
	this.selectedItem = function () {
		
		if (_self.hasCaption) {
			return _self.get(_self.selectedIndex() - 1);
		} else {
			return _self.get(_self.selectedIndex());
		}
		
	};
	
	/**
	 * Reloads the select
	 */
	this.reset = function () {
	
		if (__datum) {
			_self.datum(__datum);
		} else {
			_self.datum(undefined);
		}
		
		_self.data([]);
		
		__init.call(_self, _self.element);
	
	};
	
	// Starts
	// The "this" in the callback function will be the Select()
	if ((__init != null) && (__init instanceof Function)) {
		__init.call(this, this.element);
	}
	
};

/**
 * Functions to handle dates
 */
var DateHandler = function () {

	var _self = this;
	
	/**
	 * Converts a string DD/MM/YYYY, 00:00:00 to a Data object
	 */
	this.toDate = function (__date, __hour) {

		if ((__date) && (__hour)) {
		
			var _dParts = __date.split("/");

			var _hParts = __hour.split(":");

			var _parts = {
				year: parseInt(_dParts[2]),
				month: (((parseInt(_dParts[1]) == 0) ? parseInt(_dParts[1].replace('0', '')) : parseInt(_dParts[1])) - 1),
				day: (((parseInt(_dParts[0]) == 0) ? parseInt(_dParts[0].replace('0', '')) : parseInt(_dParts[0]))),
				hour: (((parseInt(_hParts[0]) == 0) ? parseInt(_hParts[0].replace('0', '')) : parseInt(_hParts[0]))),
				minute: (((parseInt(_hParts[1]) == 0) ? parseInt(_hParts[1].replace('0', '')) : parseInt(_hParts[1])))
			};

			return new Date(_parts.year, _parts.month, _parts.day, _parts.hour, _parts.minute, 0);

		} else {
			
			return null;
			
		}

	};

};

if (window.innerPage instanceof Function) {
	innerPage();
}

ko.applyBindings(viewModel);