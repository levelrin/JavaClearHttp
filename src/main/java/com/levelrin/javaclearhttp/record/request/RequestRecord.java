/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.record.request;

import com.levelrin.javaclearhttp.info.ReqInfoType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The record of HTTP request.
 */
public final class RequestRecord implements ReqRecordType {

    /**
     * Request information.
     */
    private final ReqInfoType info;

    /**
     * Constructor.
     * @param info See {@link RequestRecord#info}.
     */
    public RequestRecord(final ReqInfoType info) {
        this.info = info;
    }

    @Override
    public Map<String, String> headers() {
        return this.info.headers();
    }

    @Override
    public String body() {
        final List<String> copy = new ArrayList<>(this.info.messages());
        return copy.get(copy.indexOf("") + 1);
    }

    @Override
    public List<String> messages() {
        return this.info.messages();
    }

    @Override
    public String toString() {
        final List<String> copy = new ArrayList<>(this.info.messages());
        final StringBuilder description = new StringBuilder();
        copy.forEach(message -> description.append(message).append('\n'));
        return description.toString();
    }

}
