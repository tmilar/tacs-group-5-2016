package org.utn.marvellator.service.impl;

import java.io.IOException;
import java.io.InputStream;
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
    public List<MarvelCharacter> getCharactersPage(int offset, int limit) throws NoSuchAlgorithmException, IOException {
        JSONObject responseJson = callMarvelCharactersApi(offset, limit);

        List<MarvelCharacter> characters = parseJsonCharactersToMarvelCharactersList(responseJson);
        return characters;
    }

    /**
     * Parse Marvel API json object result to the respective Collection<MarvelCharacter>
     *
     * @param jsonObj
     * @return collection of marvel characters
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    protected List<MarvelCharacter> parseJsonCharactersToMarvelCharactersList(JSONObject jsonObj) throws IOException, NoSuchAlgorithmException {

        JSONArray characterJsonArray = jsonObj.getJSONArray("results");
        List<MarvelCharacter> characters = new ArrayList<>();

        for (Object characterjSON : characterJsonArray) {
            JSONObject character = (JSONObject) characterjSON;

            MarvelCharacter c = new MarvelCharacter();
            c.setMarvelId(String.valueOf(character.get("id")));
            c.setName((String) character.get("name"));

            characters.add(c);
            System.out.println("Read character: " + c);
        }

        return characters;
    }

    /**
     * Call marvel characters api to obtain the characters JSON object including metadata etc.
     *
     * @param offset - the initial position of the marvel characters list
     * @param limit - quantity of characters to get after offset (max 100)
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    private JSONObject callMarvelCharactersApi(int offset, int limit) throws NoSuchAlgorithmException, IOException {
        String timestamp = String.valueOf(new java.util.Date().getTime());
        String paramToDigets = timestamp + privateKey + publicKey;

        byte[] bytesOfMessage = paramToDigets.getBytes(charset);

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(bytesOfMessage);
        char[] encoded = Hex.encodeHex(thedigest);
        String decoded = new String(encoded);

        String querystring = String.format("offset=%s&limit=%s&ts=%s&apikey=%s&hash=%s",
                URLEncoder.encode(String.valueOf(offset), charset),
                URLEncoder.encode(String.valueOf(limit), charset),
                URLEncoder.encode(timestamp, charset),
                URLEncoder.encode(publicKey, charset),
                URLEncoder.encode(decoded, charset));

        URLConnection connection = new URL(url + "?" + querystring).openConnection();
        connection.setRequestProperty("Accept-Charset", charset);

        InputStream response = connection.getInputStream();
        Scanner scanner = new Scanner(response);
        String responseBody = scanner.useDelimiter("\\A").next();

        JSONObject jsonResponse = new JSONObject(responseBody).getJSONObject("data");

        return jsonResponse ;
    }

    @Override
    public MarvelCharacter getCharacterById(Integer characterId) {
        // TODO get a marvel character by id
        throw new NotImplementedException();
    }
}
