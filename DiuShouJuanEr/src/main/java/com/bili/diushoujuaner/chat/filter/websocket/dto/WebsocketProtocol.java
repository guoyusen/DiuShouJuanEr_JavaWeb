package com.bili.diushoujuaner.chat.filter.websocket.dto;

import java.util.HashMap;
import java.util.Map;

public enum WebsocketProtocol {
    RFC6455("13"), HYBI_07_12("8"), HIXIE76("");

    private String websocketVersion;
    private static Map<String, WebsocketProtocol> protocols;

    static {
        protocols = new HashMap<>();
        for (WebsocketProtocol websocketProtocol : WebsocketProtocol.values()) {
            protocols.put(websocketProtocol.websocketVersion, websocketProtocol);
        }
    }

    WebsocketProtocol(String websocketVersion) {
        this.websocketVersion = websocketVersion;
    }

    public static WebsocketProtocol get(String websocketVersion) {
        return protocols.get(websocketVersion);
    }
}
