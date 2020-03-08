/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.connection;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import com.levelrin.javaclearhttp.info.ReqInfoType;
import com.levelrin.javaclearhttp.record.Record;
import com.levelrin.javaclearhttp.record.RecordType;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * Socket connection for HTTPS protocol, which opens the port 443.
 * It uses {@link SSLSocketFactory}.
 * It's intended to be used in {@link SocketConnection}.
 */
public final class HttpsSocket implements ConnectionType {

    @Override
    @SuppressFBWarnings(value = "RCN_REDUNDANT_NULLCHECK_WOULD_HAVE_BEEN_A_NPE", justification = "False-positive.")
    public RecordType record(final ReqInfoType info) {
        try (
            Socket socket = SSLSocketFactory
                .getDefault()
                .createSocket(
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
