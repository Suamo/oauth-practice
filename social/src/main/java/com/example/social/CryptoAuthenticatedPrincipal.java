package com.example.social;

import java.util.Map;

public interface CryptoAuthenticatedPrincipal {

    String getUserName();
    Map<String, Object> getAttributes();

}
