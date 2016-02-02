package com.bili.diushoujuaner.chat.filter.websocket.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bili.diushoujuaner.chat.filter.websocket.exceptions.IncompleteHandshakePacket;
import com.bili.diushoujuaner.chat.filter.websocket.exceptions.NotWebsocketHandshakeRequest;
import com.bili.diushoujuaner.chat.filter.websocket.exceptions.UnsupportedWebsocketProtocol;
import com.bili.diushoujuaner.chat.filter.websocket.util.WSUtil;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;

public class WebsocketOpeningHandshakeHeader {

    private static final Logger logger = LoggerFactory.getLogger(WebsocketOpeningHandshakeHeader.class);

    public static final Pattern RAW_VALUE_PATTERN = Pattern.compile("\\r\\n\\r\\n");

    public static final Pattern REQUEST_LINE_PATTERN = Pattern.compile(" ");

    public static final Pattern HEADERS_BODY_PATTERN = Pattern.compile("\\r\\n");

    public static final Pattern HEADER_VALUE_PATTERN = Pattern.compile(": ");

    public static final Pattern CONNECTION_TOKENS_PATTERN = Pattern.compile(",");

    private String host;

    private String port;

    private String resource;

    private String origin;

    private WebsocketProtocol protocol;

    private String subProtocol;

    private String key;

    private String version;

    private String extensions;

    private String key1;

    private String key2;

    private byte[] key3;

    private Map<String, String> httpRequestHeaders;

    private WebsocketOpeningHandshakeHeader(Map<String, String> httpRequestHeaders, byte[] body) throws UnsupportedWebsocketProtocol {
        this.httpRequestHeaders = httpRequestHeaders;
        key = httpRequestHeaders.get("sec-websocket-key");
        key1 = httpRequestHeaders.get("sec-websocket-key1");
        key2 = httpRequestHeaders.get("sec-websocket-key2");
        host = httpRequestHeaders.get("host");
        resource = httpRequestHeaders.get("resource");
        origin = httpRequestHeaders.get("origin");
        subProtocol = httpRequestHeaders.get("sec-websocket-protocol");
        extensions = httpRequestHeaders.get("sec-websocket-extensions");

        if (null != key && !key.isEmpty()) {
            // hybi
            version = httpRequestHeaders.get("sec-websocket-version");
            protocol = WebsocketProtocol.get(version);
            if (WebsocketProtocol.HYBI_07_12 == protocol) {
                origin = httpRequestHeaders.get("sec-websocket-origin");
            } else if (null == protocol || WebsocketProtocol.HIXIE76 == protocol) {
                throw new UnsupportedWebsocketProtocol("Client websocket protocol not supported");
            }
        } else if (null != key1 && !key1.isEmpty() &&
                null != key2 && !key2.isEmpty()) {
            // hixie
            protocol = WebsocketProtocol.HIXIE76;
            key3 = body;
        } else {
            throw new UnsupportedWebsocketProtocol("Client websocket protocol not supported");
        }
    }

    public static WebsocketOpeningHandshakeHeader getWebsocketOpeningHandshakeHeader(ByteBuffer buffer) throws IncompleteHandshakePacket, NotWebsocketHandshakeRequest, UnsupportedWebsocketProtocol {
        final String handshakeHeaderAndBody = new String(buffer.array(), 0, buffer.limit(), UTF_8);
        logger.debug("websocket request : {}", handshakeHeaderAndBody);

        final String[] headersAndBody = RAW_VALUE_PATTERN.split(handshakeHeaderAndBody, -1);

        if (headersAndBody.length <= 1) {
            throw new IncompleteHandshakePacket("Header is not complete");
        }

        Map<String, String> httpRequestHeaders = new HashMap<>();

        String[] headerFields = HEADERS_BODY_PATTERN.split(headersAndBody[0]);
        headerFields = WSUtil.trimStringArray(headerFields);

        int i = 0;

        String requestLine = headerFields[i++];

        final String[] elements = REQUEST_LINE_PATTERN.split(requestLine);

        // validate request line
        if (null == elements || elements.length != 3 ||
                null == elements[0] || !elements[0].equals("GET") &&
                null == elements[1] ||
                null == elements[2] || elements[2].isEmpty()) {
            throw new NotWebsocketHandshakeRequest("Request line is invalid");
        }

        httpRequestHeaders.put("resource", elements[1]);

        for (; i < headerFields.length; i++) {
            final String[] header = HEADER_VALUE_PATTERN.split(headerFields[i]);
            httpRequestHeaders.put(header[0].toLowerCase(), header[1]);
        }

        String connectionHeader = httpRequestHeaders.get("connection").trim();

        if (null == connectionHeader || connectionHeader.isEmpty()) {
            throw new NotWebsocketHandshakeRequest("Connection or Upgrade header not as per websocket handshake protocol");
        }

        List<String> connectionTokens = Arrays.asList(CONNECTION_TOKENS_PATTERN.split(connectionHeader.toLowerCase()));

        for(int k = 0; k < connectionTokens.size(); k++){
        	String tmpToken = connectionTokens.get(k);
        	connectionTokens.set(k, tmpToken.trim());
        }
        
        // basic header validation
        if (!connectionTokens.contains("upgrade") ||
                !"websocket".equalsIgnoreCase(httpRequestHeaders.get("upgrade"))) {
            throw new NotWebsocketHandshakeRequest("Connection or Upgrade header not as per websocket handshake protocol");
        }

        buffer.position(headersAndBody[0].length() + 4);

        int bodyLength = buffer.limit() - buffer.position();
        byte[] body = new byte[bodyLength];

        if (bodyLength > 0)
            buffer.get(body, 0, bodyLength);

        return new WebsocketOpeningHandshakeHeader(httpRequestHeaders, body);
    }

    public String getHttpRequestHeader(String param) {
        return httpRequestHeaders.get(param);
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getResource() {
        return resource;
    }

    public String getOrigin() {
        return origin;
    }

    public WebsocketProtocol getProtocol() {
        return protocol;
    }

    public String getSubProtocol() {
        return subProtocol;
    }

    public String getKey() {
        return key;
    }

    public String getVersion() {
        return version;
    }

    public String getExtensions() {
        return extensions;
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }

    public byte[] getKey3() {
        return key3;
    }
}
