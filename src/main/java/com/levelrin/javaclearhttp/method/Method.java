/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.method;

import com.levelrin.javaclearhttp.header.Header;
import com.levelrin.javaclearhttp.header.HeaderType;
import com.levelrin.javaclearhttp.internal.connection.ConnectionType;
import com.levelrin.javaclearhttp.internal.Messages;
import com.levelrin.javaclearhttp.record.Record;
import com.levelrin.javaclearhttp.record.RecordType;
import com.levelrin.javaclearhttp.internal.TraceableMap;
import com.levelrin.javaclearhttp.internal.connection.SocketConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Represents HTTP method.
 * HTTP header can be defined via this.
 * Or, HTTP request can be sent without defining headers.
 */
public final class Method implements MethodType {

    /**
     * A map to store HTTP request configuration.
     * It should already have the following: host, path, protocol, and method.
     */
    private final TraceableMap map;

    /**
     * An object that is responsible for making HTTP connection.
     */
    private final ConnectionType connection;

    /**
     * Secondary constructor.
     * This object will use {@link SocketConnection}.
     * @param map Info at {@link Method#map}.
     */
    public Method(final TraceableMap map) {
        this(map, new SocketConnection(map));
    }

    /**
     * Primary constructor.
     * Usually, it's for testing.
     * @param map Info at {@link Method#map}.
     * @param connection Info at {@link Method#connection}.
     */
    public Method(final TraceableMap map, final ConnectionType connection) {
        this.map = map;
        this.connection = connection;
    }

    @Override
    public HeaderType header(final String name, final String value) {
        final Map<String, String> header = new HashMap<>();
        header.put(name, value);
        return new Header(
            this.map.footprint(
                this,
                "header(String, String)",
                "Set header. Name: " + name + " Value: " + value
            ),
            header
        );
    }

    @Override
    public RecordType send() {
        final List<String> messages = new Messages(this.map).list();
        return new Record(
            this.map.footprint(this, "send()", "Pass map to Record object."),
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
            .toString();
    }

}
