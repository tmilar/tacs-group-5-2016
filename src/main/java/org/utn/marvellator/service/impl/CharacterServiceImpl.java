package org.utn.marvellator.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.net.URLEncoder;
import java.net.URLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;
import org.json.*;
import org.utn.marvellator.model.*;
import org.utn.marvellator.service.CharacterService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Admin on 22/04/2016.
 */
@Service
public class CharacterServiceImpl implements CharacterService {

    @Override
    public List<MarvelCharacter> getCharacters(){
        String url = "http://gateway.marvel.com/v1/public/characters";
        String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
        String timestamp = String.valueOf(new java.util.Date().getTime());
        String publicKey = "d631b2feff8604a9ba624fb565a6099a";
        String privateKey = "b5369ef29519aadd53a5f180e148579d80ee2e19";
        String paramToDigets = timestamp + privateKey + publicKey;
        byte[] bytesOfMessage = new byte[0];
        try {
            bytesOfMessage = paramToDigets.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] thedigest = md.digest(bytesOfMessage);
        char[] encoded = Hex.encodeHex(thedigest);
        String decoded = new String(encoded);

        String querystring = null;
        try {
            querystring = String.format("ts=%s&apikey=%s&hash=%s",
                    URLEncoder.encode(timestamp, charset),
                    URLEncoder.encode(publicKey, charset),
                    URLEncoder.encode(decoded, charset));
        } catch (Exception ex){

        }

        URLConnection connection = null;
        try {
            connection = new URL(url + "?" + querystring).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.setRequestProperty("Accept-Charset", charset);
        InputStream response = null;
        try {
            response = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(response);
        String responseBody = scanner.useDelimiter("\\A").next();
        JSONObject jsonObj = new JSONObject(responseBody);
        JSONArray characterJsonArray = jsonObj.getJSONObject("data").getJSONArray("results");
        ArrayList<MarvelCharacter> listaFinal = new ArrayList<MarvelCharacter>();
        for (Object characterjSON : characterJsonArray){
            MarvelCharacter c = new MarvelCharacter();
            JSONObject personaje = (JSONObject)characterjSON;
            c.setId(String.valueOf(personaje.get("id")));
            c.setName((String)personaje.get("name"));
            listaFinal.add(c);
        }
        return listaFinal;
    }

    @Override
    public MarvelCharacter getCharacterById(Integer characterId) {
        // TODO get a marvel character by id
        throw new NotImplementedException();
    }
}
