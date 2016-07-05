$(document).ready(function(){

    $('.addFavorite').on('click', function(){
        manageFavorite("POST", $(this));
    });

    $('.removeFavorite').on('click', function(){
        manageFavorite("DELETE", $(this));
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

function manageFavorite(type, self){
    var id = trimByHyphen(self.prop('id'));
    var params = JSON.stringify({
        marvelId : id
    });
	var callback = function() {
		location.reload();
	}
    sendByAjax(type, params, "/api/users/favorites", callback);
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
