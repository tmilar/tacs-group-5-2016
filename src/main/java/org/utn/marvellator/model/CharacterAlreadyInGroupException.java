package org.utn.marvellator.model;

public class CharacterAlreadyInGroupException extends Throwable {
    public CharacterAlreadyInGroupException(MarvelCharacter character, Group group) {
        super(character + " already in " + group + " !");
    }
}
