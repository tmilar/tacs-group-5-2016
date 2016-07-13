package org.utn.marvellator.model;

public class CharacterAlreadyFavoritedException extends Throwable {

    public CharacterAlreadyFavoritedException(MarvelCharacter character){

        super(character + " already favorited");
    }

}
