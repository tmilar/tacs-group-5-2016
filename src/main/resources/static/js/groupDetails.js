$(document).ready(function(){
	window.charactersForGroupPage = 0;
	loadGroupCharacters();
	loadAvailableCharactersForGroup();
	$(".previousPage").click(function(){
		if (window.charactersForGroupPage > 0)
		window.charactersForGroupPage--;
		loadAvailableCharactersForGroup();
	});

	$(".nextPage").click(function(){
		window.charactersForGroupPage++;
		loadAvailableCharactersForGroup();
	});
});

function getCurrentGroup() {
	return window.location.pathname.split("/")[2];
}
function addCharacterToGroup(character){
	var params = JSON.stringify(character);
	sendByAjax("PUT", params, "/api/groups/" + getCurrentGroup(), reload);
}
function reload() {
	loadAvailableCharactersForGroup();
	loadGroupCharacters();
}

function deleteCharacterFromGroup(character){
	var params = JSON.stringify(character);
	sendByAjax("DELETE", params, "/api/groups/" + getCurrentGroup(), reload);
}


function loadAvailableCharactersForGroup(){
	if (window.charactersForGroupPage === 0)
		sendByAjax("GET", null, "/api/groups/" + getCurrentGroup() +  "/availableCharacters", updateDOMAvailableCharactersForGroup);
	else
		sendByAjax("GET", null, "/api/groups/" + getCurrentGroup() +  "/availableCharacters/" + window.charactersForGroupPage, updateDOMAvailableCharactersForGroup);
}

function loadGroupCharacters() {
	sendByAjax("GET", null, "/api/groups/" + getCurrentGroup() +  "/characters", updateDOMGroupCharacters);
}

function updateDOMGroupCharacters(characters){
	$(".currentCharactersTableBody").empty();
	if (characters.length === 0){
		var row = $("<tr/>");
		$("<td colspan='4'/>").html("No characters added yet ").appendTo(row);
		$(".currentCharactersTableBody").append(row);
		return;
	}

	$.each(characters, function(i, character) {

		var row = $("<tr/>").addClass("trCharacter");
		$(".currentCharactersTableBody").append(row);
		$("<td/>").html(i).appendTo(row);
		$("<td/>").html(character.marvelId).appendTo(row);
		$("<td/>").html(character.name).appendTo(row);
		var borrar_td = $("<td/>").appendTo(row);
		$("<button/>").html("Eliminar").click(function(evt) {
			deleteCharacterFromGroup({marvelId: character.marvelId, name: character.name});
		}).appendTo(borrar_td);
	});
}

function updateDOMAvailableCharactersForGroup(characters) {
	$(".character-group-container").find(".charactersForGroupBody").empty();
	$.each(characters, function(i, character) {

		var row = $("<tr/>").addClass("trCharacter");
		$(".charactersForGroupBody").append(row);
		$("<td/>").html(character.marvelId).appendTo(row);
		$("<td/>").html(character.name).appendTo(row);

		var agregar_td = $("<td/>").appendTo(row);
		$("<button/>").html("Agregar").click(function(evt) {
			addCharacterToGroup({marvelId: character.marvelId, name: character.name});
		}).appendTo(agregar_td);
	});
	$(".character-group-container").find(".page").empty().html(window.charactersForGroupPage);
}