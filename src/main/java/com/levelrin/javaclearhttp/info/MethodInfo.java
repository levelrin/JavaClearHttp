/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.info;

import com.levelrin.javaclearhttp.method.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Request information for {@link Method}.
 */
public final class MethodInfo implements ReqInfoType {

    /**
     * The request URL.
     */
    private final URL url;

    /**
     * HTTP method such as GET, POST, and others.
     */
    private final String method;

    /**
     * Constructor.
     * @param url See {@link MethodInfo#url}.
     * @param method See {@link MethodInfo#method}.
     */
    public MethodInfo(final URL url, final String method) {
        this.url = url;
        this.method = method;
    }

    @Override
    public String host() {
        return this.url.getHost();
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public int port() {
        final int port;
        switch (this.url.getProtocol().toUpperCase(Locale.US)) {
            case "HTTP":
                port = 80;
                break;
            case "HTTPS":
                port = 443;
                break;
            default:
                throw new IllegalStateException(
                    "Unknown protocol: " + this.protocol() + ". Only HTTP or HTTPS are allowed."
                );
        }
        return port;
    }

    @Override
    public Map<String, String> headers() {
        final Map<String, String> given = new LinkedHashMap<>();
        given.put("Host", this.host());
        given.put("User-Agent", "JavaClearHttp");
        given.put("Accept", "*/*");
        given.put("Connection", "close");
        return given;
    }

    @Override
    public List<String> messages() {
        final List<String> messages = new ArrayList<>();
        final String filePath;
        if (this.url.getFile().isEmpty()) {
            filePath = this.url.getFile() + "/";
        } else {
            filePath = this.url.getFile();
        }
        messages.add(this.method + " " + filePath + " HTTP/1.1");
        this.headers().forEach((key, value) -> messages.add(key + ": " + value));
        messages.add("");
        return messages;
    }

    @Override
    public String toString() {
        final StringJoiner description = new StringJoiner("\n")
            .add("URL: " + this.url)
            .add("Host: " + this.host())
            .add("Method: " + this.method)
            .add("Protocol: " + this.protocol())
            .add("Port: " + this.port())
            .add("")
            .add("Messages:");
        this.messages().forEach(description::add);
        return description.toString();
    }

    /**
     * Request protocol.
     * @return It should be either HTTP or HTTPS.
     */
    private String protocol() {
        return this.url.getProtocol().toUpperCase(Locale.US);
    }

}
