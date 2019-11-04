/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.internal.connection;

import com.levelrin.javaclearhttp.internal.Port;
import com.levelrin.javaclearhttp.internal.TraceableMap;
import java.util.List;
import java.util.StringJoiner;

/**
 * It's responsible for constructing error messages in case of network communication failure.
 *
 * The sockets from JDK have checked exceptions.
 * When exceptions occur, we want to attach nice error messages to the exceptions.
 * And we have at least two objects that are dealing with socket connections.
 * For example, we have {@link HttpSocket} and {@link HttpsSocket}.
 * Since those objects require same logic to construct error messages, we created this class to reduce duplicated code.
 */
public final class ConnectionError {

    /**
     * A map to store HTTP request configuration.
     * It should already have the following: host, protocol, and method.
     * Optionally, it may contain body as well.
     */
    private final TraceableMap map;

    /**
     * Raw messages for the server.
     */
    private final List<String> messages;

    /**
     * Primary constructor.
     * @param map Info at {@link this#map}.
     * @param messages Info at {@link this#messages}.
     */
    public ConnectionError(final TraceableMap map, final List<String> messages) {
        this.map = map;
        this.messages = messages;
    }

    @Override
    public String toString() {
        final StringJoiner info = new StringJoiner("\n")
            .add("Failed to send the request.")
            .add("Host: " + this.map.value("host"))
            .add("Protocol: " + this.map.value("protocol"))
            .add("Port: " + new Port(this.map.value("protocol")))
            .add("Method: " + this.map.value("method"));
        if (this.map.contains("body")) {
            info.add("Body: " + this.map.value("body"));
        }
        info.add("\nMessages:\n");
        this.messages.forEach(info::add);
        return info.toString();
    }

}
