/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.internal.connection;

import com.levelrin.javaclearhttp.internal.Port;
import com.levelrin.javaclearhttp.internal.TraceableMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Socket connection for HTTP protocol, which opens the port 80.
 * It uses {@link Socket}.
 * It's intended to be used in {@link SocketConnection}.
 */
public final class HttpSocket implements ConnectionType {

    /**
     * A map to store HTTP request configuration.
     * It should already have the following: host, protocol, and method.
     * Optionally, it may contain body as well.
     */
    private final TraceableMap map;

    /**
     * Primary constructor.
     * @param map Info at {@link this#map}.
     */
    public HttpSocket(final TraceableMap map) {
        this.map = map;
    }

    @Override
    public List<String> replies(final List<String> messages) {
        try (
            Socket socket = new Socket(
                this.map.value("host"),
                new Port(this.map.value("protocol")).toInt()
            );
            PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(
                    socket.getOutputStream(),
                    StandardCharsets.UTF_8
                ),
                true
            );
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream(),
                    StandardCharsets.UTF_8
                )
            )
        ) {
            messages.forEach(writer::println);
            return reader.lines().collect(Collectors.toList());
        } catch (final IOException ex) {
            throw new IllegalStateException(
                new ConnectionError(this.map, messages).toString(),
                ex
            );
        }
    }

}
