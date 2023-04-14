package com.sebastian_daschner.examples;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.GmailScopes;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Set;

public class CredentialBuilder {

    public Credential buildCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory, GoogleClientSecrets clientSecrets)
            throws IOException {
        GoogleAuthorizationCodeFlow flow = buildAuthorizationFlow(httpTransport, jsonFactory, clientSecrets);
        return authorize(flow);
    }

    private GoogleAuthorizationCodeFlow buildAuthorizationFlow(NetHttpTransport httpTransport, GsonFactory jsonFactory, GoogleClientSecrets clientSecrets) throws IOException {
        return new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, Set.of(GmailScopes.GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline")
                .build();
    }

    private Credential authorize(GoogleAuthorizationCodeFlow flow) throws IOException {
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
}
