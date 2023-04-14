package com.sebastian_daschner.examples;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.JsonFactory;

import java.io.IOException;
import java.io.InputStreamReader;

public class ClientSecretsLoader {

    public GoogleClientSecrets loadClientSecrets(JsonFactory jsonFactory) throws IOException {
        return GoogleClientSecrets.load(jsonFactory, new InputStreamReader(ClientSecretsLoader.class.getResourceAsStream("/client_secret_522831148255-3nr373di6n7qh7egjtjcjvj5ujsqag7g.apps.googleusercontent.com.json")));
    }
}
