/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.connection;

import com.levelrin.javaclearhttp.info.ReqInfoType;
import com.levelrin.javaclearhttp.record.RecordType;

/**
 * It uses Sockets from {@link java.net}.
 */
public final class SocketConnection implements ConnectionType {

    /**
     * It's responsible for making HTTP connection.
     * This will be used when the protocol is HTTP.
     */
    private final ConnectionType httpSocket;

    /**
     * It's responsible for making HTTPS connection.
     * This will be used when the protocol is HTTPS.
     */
    private final ConnectionType httpsSocket;

    /**
     * Constructor.
     * @param httpSocket See {@link SocketConnection#httpSocket}.
     * @param httpsSocket See {@link SocketConnection#httpsSocket}.
     */
    public SocketConnection(final ConnectionType httpSocket, final ConnectionType httpsSocket) {
        this.httpSocket = httpSocket;
        this.httpsSocket = httpsSocket;
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public RecordType record(final ReqInfoType info) {
        final int port = info.port();
        final RecordType record;
        if (port == 80) {
            record = this.httpSocket.record(info);
        } else if (port == 443) {
            record = this.httpsSocket.record(info);
        } else {
            throw new IllegalStateException(
                "Unsupported port: " + port + ". It should be either 80 or 443."
            );
        }
        return record;
    }

}
