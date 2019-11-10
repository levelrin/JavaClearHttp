/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.record.response;

import com.levelrin.javaclearhttp.internal.Headers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The record of HTTP response.
 */
public final class ResponseRecord implements ResponseRecordType {

    /**
     * A list that contains the replies from the server.
     */
    private final List<String> rawReplies;

    /**
     * Primary constructor.
     * @param replies Info at {@link ResponseRecord#rawReplies}.
     */
    public ResponseRecord(final List<String> replies) {
        this.rawReplies = replies;
    }

    @Override
    public StatusType status() {
        return new Status(this.rawReplies.get(0));
    }

    @Override
    public Map<String, String> headers() {
        return new Headers(this.rawReplies).map();
    }

    @Override
    public String body() {
        final StringBuilder body = new StringBuilder();
        this.rawReplies.listIterator(this.rawReplies.indexOf("")).forEachRemaining(body::append);
        return body.toString();
    }

    @Override
    public List<String> replies() {
        return new ArrayList<>(this.rawReplies);
    }

    @Override
    public String toString() {
        final StringBuilder report = new StringBuilder();
        this.rawReplies.forEach(reply -> report.append(reply).append('\n'));
        return report.toString();
    }

}
