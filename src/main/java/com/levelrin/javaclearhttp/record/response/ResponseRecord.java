/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.record.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The record of HTTP response.
 */
public final class ResponseRecord implements ResRecordType {

    /**
     * Raw HTTP response messages.
     */
    private final List<String> rawReplies;

    /**
     * Constructor.
     * @param rawReplies See {@link ResponseRecord#rawReplies}.
     */
    public ResponseRecord(final List<String> rawReplies) {
        this.rawReplies = rawReplies;
    }

    @Override
    public StatusType status() {
        return new Status(this.rawReplies.get(0));
    }

    @Override
    public Map<String, String> headers() {
        final List<String> copy = new ArrayList<>(this.rawReplies);
        final Map<String, String> headers = new HashMap<>();
        for (int index = 1; index < copy.size(); index = index + 1) {
            if (copy.get(index).isEmpty()) {
                break;
            }
            final String[] pair = copy.get(index).split(": ", 2);
            headers.put(pair[0], pair[1]);
        }
        return headers;
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
