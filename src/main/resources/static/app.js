$(document).ready(()=>{
	const ICON = {
		edit: '<i class="fas fa-edit"></i>',
		save: '<i class="fa fa-plus" aria-hidden="true"></i>'
	};
	const ALERT = {
		default: {type: 'dark hide-component', message: ''},
		
		created: {type: 'success', message: 'Criado com sucesso!'},
		error_c: {type: 'danger', message: 'Não foi possivel criar!'},
		alert_c: {type: 'warning', message: 'Campo vaziou ou menor que 6 letras.'},
		
		edited:  {type: 'success', message: 'Editado com sucesso!'},
		error_e: {type: 'danger', message: 'Não foi possivel editar!'},
		alert_c: {type: 'warning', message: 'Erro ao requisitar edição, recarregue a pagina e tente novamente.'},
		
		deleted: {type: 'success', message: 'Tarefa concluida!'},
		error_d: {type: 'danger', message: 'Não foi possivel deletar!'},
		alert_d: {type: 'warning', message: 'Erro ao requisitar, recarregue a pagina e tente novamente.'}
	};
	let ALERT_TYPE = 'default';
	const BASE_URL = "http://localhost:8080";
	
	let uri = window.location.search.substring(1);
	let params = new URLSearchParams(uri);
	let param = params.get('status');
		
		
	if(param != null && param != '') {
		ALERT_TYPE = param;
	} else {
		ALERT_TYPE = 'default';
	}
	function showAlert(){
		$('#alerta').html(`<div class="alert alert-${ALERT[ALERT_TYPE].type} mt-2">${ALERT[ALERT_TYPE].message}</div>`);			
	}
	setTimeout(()=>{
		ALERT_TYPE = 'default';
		window.history.replaceState({}, document.title, '/');
		showAlert();
	}, 3000);
	
	//padrões
	
	showAlert();
	$('.edite-btn').html(ICON['edit']);
	$('.botao-de-salvar').html(ICON['save']);
	//padrões

	//event edit
	let oldBtn = null;
	$('.edite-btn').on('click', (e)=> {
		if(oldBtn!=null) oldBtn.disabled = false;
		$('.botao-de-salvar').html('<i class="fas fa-check"></i>');
		let todo = e.target.value.split(',');
		let id = todo[0], nome = todo[1];
		$('#input-todo').val(nome);
		$('#create-save-btn').attr('value', id)
		oldBtn = e.target;
		oldBtn.disabled = true;
		
		$('#update-form').attr('action',`${BASE_URL}/update/${id}`);
		$('#update-form-ipt-id').attr('value',id);
		
	});
	//evente edit
	
	
	//event delete
	$('.delete-btn').on('click', (e)=>{
		let id = e.target.value;
		if(!id){
//(._.)
			alert("Erro ao requisitar, recarregue a pagina e tente novamente.");
			return;
		}
		$('#delete-form').attr('action',`${BASE_URL}/delete/${id}`);
		$('#delete-form-btn').click();
	});
	
	//event delete
	
	//event confirm create and delete
	$('#create-save-btn').on('click', (e)=>{
		let inputText = $('#input-todo').val();
		if(inputText == '' || inputText.length < 6){
//(._.)
			alert("Campo vaziou ou menor que 6 letras"); 
		} else {
			if(e.target.value != ''){ //edite
				$('#update-form-ipt-nome').attr('value',inputText);
				$('#update-form-btn').click();
			}else if(e.target.value == ''){ //create
				$('#create-form-ipt-nome').attr('value',inputText);
				$('#create-form-btn').click();	
			}else{
//(._.)
				alert("Erro ao requisitar edicão, recarregue a pagina e tente novamente.");
			}
		}
	});
	//event confirm create and delete
	
	//event clean
	$('#clean-btn').on('click', e => {
		$('.botao-de-salvar').html(ICON['save']);
		$('.botao-de-salvar').attr('value', '');
		$('#input-todo').val('');
		if(oldBtn!=null) oldBtn.disabled = false;
	});

});