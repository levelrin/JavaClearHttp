/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.internal.connection;

import com.levelrin.javaclearhttp.internal.Port;
import com.levelrin.javaclearhttp.internal.TraceableMap;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Socket connection for HTTPS protocol, which opens the port 443.
 * It uses {@link SSLSocketFactory}.
 * It's intended to be used in {@link SocketConnection}.
 */
public final class HttpsSocket implements ConnectionType {

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
    public HttpsSocket(final TraceableMap map) {
        this.map = map;
    }

    @Override
    @SuppressFBWarnings(value = "RCN_REDUNDANT_NULLCHECK_WOULD_HAVE_BEEN_A_NPE", justification = "False-positive.")
    public List<String> replies(final List<String> messages) {
        try (
            Socket socket = SSLSocketFactory
                .getDefault()
                .createSocket(
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
