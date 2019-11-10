/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.internal.connection;

import com.levelrin.javaclearhttp.internal.TraceableMap;
import java.util.List;

/**
 * It uses Sockets from {@link java.net}.
 */
public final class SocketConnection implements ConnectionType {

    /**
     * A map to store HTTP request configuration.
     * It should already have the following: host, protocol, and method.
     * Optionally, it may contain body as well.
     */
    private final TraceableMap map;

    /**
     * It's responsible for making HTTP connection.
     * This will be used when the protocol is HTTP in the {@link this#map}.
     */
    private final ConnectionType httpSocket;

    /**
     * It's responsible for making HTTPS connection.
     * This will be used when the protocol is HTTPS in the {@link SocketConnection#map}.
     */
    private final ConnectionType httpsSocket;

    /**
     * Secondary constructor.
     * @param map Info at {@link SocketConnection#map}.
     */
    public SocketConnection(final TraceableMap map) {
        this(map, new HttpSocket(map), new HttpsSocket(map));
    }

    /**
     * Primary constructor.
     * @param map Info at {@link SocketConnection#map}.
     * @param httpSocket Info at {@link SocketConnection#httpSocket}.
     * @param httpsSocket Info at {@link SocketConnection#httpsSocket}.
     */
    public SocketConnection(final TraceableMap map, final ConnectionType httpSocket, final ConnectionType httpsSocket) {
        this.map = map;
        this.httpSocket = httpSocket;
        this.httpsSocket = httpsSocket;
    }

    @Override
    public List<String> replies(final List<String> messages) {
        final String protocol = this.map.value("protocol");
        final List<String> replies;
        if ("HTTP".equals(protocol)) {
            replies = this.httpSocket.replies(messages);
        } else if ("HTTPS".equals(protocol)) {
            replies = this.httpsSocket.replies(messages);
        } else {
            throw new IllegalStateException(
                "Unknown protocol: " + protocol + ". It should be either HTTP or HTTPS."
            );
        }
        return replies;
    }

}
