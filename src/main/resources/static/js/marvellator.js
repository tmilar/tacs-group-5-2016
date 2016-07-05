$(document).ready(function(){

    $('.addFavorite').on('click', function(ev){
		var id = trimByHyphen($(this).prop('id'));
		var name = $(ev.target).closest("tr").find(".character-name").html();
		var params = JSON.stringify({
			marvelId: id,
			name: name
		});
        manageFavorite("POST", params);
    });

    $('.removeFavorite').on('click', function(ev){
		var id = trimByHyphen($(this).prop('id'));
		var name = $(ev.target).closest("tr").find(".character-name").html();
		var params = JSON.stringify({
			marvelId: id,
			name: name
		});
        manageFavorite("DELETE", params);
    });

    $('#createGroup').on('click', function(){
        var name = $('#group-name').val();
        var params = JSON.stringify({
            name  : name
        });
       manageGroup("POST", params);
    });

    $('.removeGroup').on('click', function(){
        var id = $(this).prop('id');
        var params = JSON.stringify({
            id: id
        });
        manageGroup("DELETE", params);
    });
});

function manageGroup(type, params){
	var callback = function() {
		location.reload();
	}
    sendByAjax(type,params,"/api/groups/", callback);


}

function manageFavorite(type, params){
	var callback = function() {
		location.reload();
	}
    sendByAjax(type, params, "/api/favorites", callback);
}

function trimByHyphen(val){
    return val.substr(val.indexOf("-") + 1);
}

function sendByAjax(type, params, url, callbackFunction){
    $.ajax({
        type: type,
        dataType: 'json',
        data: params,
        contentType: "application/json; charset=utf-8",
        url: url,
        success: function(response){
            console.log(response);
			if (callbackFunction)
				callbackFunction(response);
        },
        error: function(e) {
            console.log(e);
        }
    });

}
