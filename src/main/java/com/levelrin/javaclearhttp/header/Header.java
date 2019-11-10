/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.header;

import com.levelrin.javaclearhttp.body.Body;
import com.levelrin.javaclearhttp.body.BodyType;
import com.levelrin.javaclearhttp.internal.connection.ConnectionType;
import com.levelrin.javaclearhttp.internal.Messages;
import com.levelrin.javaclearhttp.internal.connection.SocketConnection;
import com.levelrin.javaclearhttp.internal.TraceableMap;
import com.levelrin.javaclearhttp.record.Record;
import com.levelrin.javaclearhttp.record.RecordType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Represents HTTP header.
 * Additional header can be defined via builder pattern.
 * HTTP body can be defined via this.
 * Or, HTTP request can be sent without defining body.
 */
public final class Header implements HeaderType {

    /**
     * A map to store HTTP request configuration.
     * It should already have the following: host, path, protocol, and method.
     */
    private final TraceableMap map;

    /**
     * A map that contains headers.
     * Note, the builder pattern is used, which means some headers may exist already.
     */
    private final Map<String, String> headers;

    /**
     * A object that is responsible for making HTTP connection.
     */
    private final ConnectionType connection;

    /**
     * Secondary constructor.
     * This object will use {@link SocketConnection}.
     * @param map Info at {@link Header#map}.
     * @param headers Info at {@link Header#headers}.
     */
    public Header(final TraceableMap map, final Map<String, String> headers) {
        this(map, headers, new SocketConnection(map));
    }

    /**
     * Primary constructor.
     * Usually, it's for testing.
     * @param map Info at {@link Header#map}.
     * @param headers Info at {@link Header#headers}.
     * @param connection Info at {@link Header#connection}.
     */
    public Header(final TraceableMap map, final Map<String, String> headers, final ConnectionType connection) {
        this.map = map;
        this.headers = headers;
        this.connection = connection;
    }

    @Override
    public HeaderType header(final String name, final String value) {
        final Map<String, String> copy = new HashMap<>(this.headers);
        copy.put(name, value);
        return new Header(
            this.map.footprint(
                this,
                "header(String, String)",
                "Set header. Name: " + name + " Value: " + value
            ),
            copy,
            this.connection
        );
    }

    @Override
    public BodyType body(final String content) {
        return new Body(
            this.map
                .pair("body", content)
                .footprint(this, "body(String)", "Set body. content: " + content),
            this.headers
        );
    }

    @Override
    public RecordType send() {
        final List<String> messages = new Messages(
            this.map.footprint(this, "send()", "Instantiate Messages object."),
            this.headers
        ).list();
        return new Record(
            this.map.footprint(this, "send()", "Instantiate Record object."),
            messages,
            this.connection.replies(messages)
        );
    }

    @Override
    public String toString() {
        return new StringJoiner("\n")
            .add("Host: " + this.map.value("host"))
            .add("Path: " + this.map.value("path"))
            .add("Protocol: " + this.map.value("protocol"))
            .add("Method: " + this.map.value("method"))
            .add(this.headerInfo())
            .toString();
    }

    /**
     * Construct the description of headers as String.
     * It will be used for {@link this#toString()}.
     * @return The description of headers.
     */
    private String headerInfo() {
        final StringJoiner info = new StringJoiner("\n")
            .add("\nHeaders:");
        this.headers.forEach((name, value) -> info.add(name + ": " + value));
        return info.toString();
    }

}
