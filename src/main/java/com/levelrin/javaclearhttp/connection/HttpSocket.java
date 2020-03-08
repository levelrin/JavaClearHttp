/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.connection;

import com.levelrin.javaclearhttp.record.Record;
import com.levelrin.javaclearhttp.info.ReqInfoType;
import com.levelrin.javaclearhttp.record.RecordType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * Socket connection for HTTP protocol, which opens the port 80.
 * It uses {@link Socket}.
 * It's intended to be used in {@link SocketConnection}.
 */
public final class HttpSocket implements ConnectionType {

    @Override
    public RecordType record(final ReqInfoType info) {
        try (
            Socket socket = new Socket(
                info.host(),
                info.port()
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
            info.messages().forEach(writer::println);
            return new Record(
                info,
                reader.lines().collect(Collectors.toList())
            );
        } catch (final IOException ex) {
            throw new IllegalStateException(
                info.toString(),
                ex
            );
        }
    }

}
