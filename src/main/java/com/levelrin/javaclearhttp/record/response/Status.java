/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.record.response;

/**
 * Simple implementation of {@link StatusType}.
 */
public final class Status implements StatusType {

    /**
     * The HTTP response status line.
     * Ex) HTTP/1.1 200 OK
     */
    private final String statusLine;

    /**
     * Primary constructor.
     * @param statusLine Info at {@link Status#statusLine}.
     */
    public Status(final String statusLine) {
        this.statusLine = statusLine;
    }

    @Override
    public int code() {
        return Integer.parseInt(this.statusLine.split(" ")[1]);
    }

    @Override
    @SuppressWarnings("MagicNumber")
    public String reason() {
        return this.statusLine.split(" ", 3)[2];
    }

    @Override
    public String toString() {
        return this.statusLine;
    }

}
