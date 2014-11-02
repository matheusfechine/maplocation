$('.dropdown-toggle').dropdown();
var urlBase = '../../maplocation/';
$('#btnAdicionar').on('click', function() {
	var idTag = $("#selectTags").val();
	adicionaRowPor(idTag);
});

function adicionaRowPor(idTag){
	$.ajax({
		url : urlBase + 'tag/obtem/' + idTag,
		dataType : 'json',
		success : function(data) {
			adicionaRow(data.tag);
		},
		error : function() {
			alert('erro');
		}
	});
};

function adicionaRow(tag){
	$("#tabelaDeTags").find('tbody')
	.append($('<tr>')
			.append($('<td>')
					.append($('<input>')
							.attr('type', 'hidden')
							.attr('value', tag.id)
							.attr('name', 'location.tags[].id'))
							.append(tag.id))
			.append($('<td>')
					.append($('<input>')
							.attr('type', 'hidden')
							.attr('value', tag.name)
							.attr('name', 'location.tags[].name'))
							.append(tag.name))
			.append($('<td>')
					.append($('<a>')
							.attr('href', '#')
							.attr('onClick', 'removeRow(this)').append('Excluir')))
	
	)
	rowCount++;
};

function removeRow(element){
	 var row = $(element).closest("tr"); // find <tr> parent
	    row.remove()
}