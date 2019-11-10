/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.body;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import com.levelrin.javaclearhttp.internal.Messages;
import com.levelrin.javaclearhttp.internal.TraceableMap;
import com.levelrin.javaclearhttp.internal.connection.ConnectionType;
import com.levelrin.javaclearhttp.internal.connection.SocketConnection;
import com.levelrin.javaclearhttp.record.Record;
import com.levelrin.javaclearhttp.record.RecordType;

/**
 * Represents HTTP body.
 * It's responsible for sending a HTTP request.
 */
public final class Body implements BodyType {

    /**
     * A map to store HTTP request configuration.
     * It should already have the following: host, path, protocol, method, and body.
     */
    private final TraceableMap map;

    /**
     * A map that contains HTTP headers.
     */
    private final Map<String, String> headers;

    /**
     * An object that is responsible for making HTTP connection.
     */
    private final ConnectionType connection;

    /**
     * Secondary constructor.
     * This object will use {@link SocketConnection}.
     * @param map Info at {@link Body#map}.
     * @param headers Info at {@link Body#headers}.
     */
    public Body(final TraceableMap map, final Map<String, String> headers) {
        this(map, headers, new SocketConnection(map));
    }

    /**
     * Primary constructor.
     * Usually, it's for testing.
     * @param map Info at {@link Body#map}.
     * @param headers Info at {@link Body#headers}.
     * @param connection Info at {@link Body#connection}.
     */
    public Body(final TraceableMap map, final Map<String, String> headers, final ConnectionType connection) {
        this.map = map;
        this.headers = headers;
        this.connection = connection;
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
            .add("\nBody:")
            .add(this.map.value("body"))
            .toString();
    }

    /**
     * Returns the information about headers.
     * It will be used for {@link this#toString()}.
     * @return Header information.
     */
    private String headerInfo() {
        final StringJoiner info = new StringJoiner("\n")
            .add("\nHeaders:");
        this.headers.forEach((name, value) -> info.add(name + ": " + value));
        return info.toString();
    }

}
