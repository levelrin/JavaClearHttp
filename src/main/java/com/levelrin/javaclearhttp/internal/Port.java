/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.internal;

/**
 * It's responsible for providing port out of HTTP protocol.
 * HTTP is 80, and HTTPS is 443.
 */
public final class Port {

    /**
     * It can be either HTTP or HTTPS.
     */
    private final String protocol;

    /**
     * Primary constructor.
     * @param protocol Info at {@link this#protocol}.
     */
    public Port(final String protocol) {
        this.protocol = protocol;
    }

    /**
     * Return corresponding port number.
     * @return Port number.
     */
    @SuppressWarnings("MagicNumber")
    public int toInt() {
        final int port;
        switch (this.protocol) {
            case "HTTP":
                port = 80;
                break;
            case "HTTPS":
                port = 443;
                break;
            default:
                throw new IllegalStateException(
                    "Unknown protocol: " + this.protocol + ". Only HTTP or HTTPS are allowed."
                );
        }
        return port;
    }

    @Override
    public String toString() {
        return Integer.toString(this.toInt());
    }

}
