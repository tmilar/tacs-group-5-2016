package org.utn.marvellator.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.security.*;
import java.net.URLEncoder;
import java.net.URLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.json.*;
import org.utn.marvellator.model.*;
import org.utn.marvellator.service.CharacterService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Value("${api.marvel.publicKey}")
    private String publicKey;

    @Value("${api.marvel.privateKey}")
    private String privateKey;

    @Value("${api.marvel.url}")
    private String url;

    @Value("${api.marvel.charset}")
    private String charset;  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()


    @Override
    public List<MarvelCharacter> getCharacters() throws IOException, NoSuchAlgorithmException {

        String timestamp = String.valueOf(new java.util.Date().getTime());
        String paramToDigets = timestamp + privateKey + publicKey;

        byte[] bytesOfMessage = paramToDigets.getBytes(charset);

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(bytesOfMessage);
        char[] encoded = Hex.encodeHex(thedigest);
        String decoded = new String(encoded);

        String querystring = String.format("ts=%s&apikey=%s&hash=%s",
                URLEncoder.encode(timestamp, charset),
                URLEncoder.encode(publicKey, charset),
                URLEncoder.encode(decoded, charset));

        URLConnection connection = new URL(url + "?" + querystring).openConnection();
        connection.setRequestProperty("Accept-Charset", charset);

        InputStream response = connection.getInputStream();
        Scanner scanner = new Scanner(response);
        String responseBody = scanner.useDelimiter("\\A").next();

        JSONObject jsonObj = new JSONObject(responseBody);
        JSONArray characterJsonArray = jsonObj.getJSONObject("data").getJSONArray("results");
        ArrayList<MarvelCharacter> listaFinal = new ArrayList<MarvelCharacter>();

        for (Object characterjSON : characterJsonArray) {
            MarvelCharacter c = new MarvelCharacter();
            JSONObject personaje = (JSONObject) characterjSON;
            c.setMarvelId(String.valueOf(personaje.get("id")));
            c.setName((String) personaje.get("name"));
            listaFinal.add(c);
            System.out.println("agregado " + c);
        }

        return listaFinal;
    }

    @Override
    public MarvelCharacter getCharacterById(Integer characterId) {
        // TODO get a marvel character by id
        throw new NotImplementedException();
    }
}
