$(document).ready(function(){

    $('.addFavorite').on('click', function(){
        manageFavorite("POST", $(this));
    });

    $('.removeFavorite').on('click', function(){
        manageFavorite("DELETE", $(this));
    })


});

function manageFavorite(type, self){

    var id = trimByHyphen(self.prop('id'));
    var params = JSON.stringify({
        marvelId : id
    });
    console.log(id);
    $.ajax({
        type: type,
        dataType: 'json',
        data: params,
        contentType: "application/json; charset=utf-8",
        url: "/api/users/favorites",
        success: function(response){
            console.log(response);
        },
        error: function(e) {
            console.log(e);
        }
    });
    location.reload();
}

function trimByHyphen(val){
    return val.substr(val.indexOf("-") + 1);
}