/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.info;

import com.levelrin.javaclearhttp.header.Header;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Request information for {@link Header}.
 * It decorates the previous request information, which should be {@link MethodInfo}.
 */
public final class HeaderInfo implements ReqInfoType {

    /**
     * We will decorate this.
     * It should be {@link MethodInfo}.
     */
    private final ReqInfoType origin;

    /**
     * HTTP header name.
     */
    private final String name;

    /**
     * HTTP header value.
     */
    private final String value;

    /**
     * Constructor.
     * @param origin See {@link HeaderInfo#origin}.
     * @param name See {@link HeaderInfo#name}.
     * @param value See {@link HeaderInfo#value}.
     */
    public HeaderInfo(final ReqInfoType origin, final String name, final String value) {
        this.origin = origin;
        this.name = name;
        this.value = value;
    }

    @Override
    public String host() {
        return this.origin.host();
    }

    @Override
    public int port() {
        return this.origin.port();
    }

    @Override
    public Map<String, String> headers() {
        final Map<String, String> copy = new HashMap<>(this.origin.headers());
        copy.put(this.name, this.value);
        return copy;
    }

    @Override
    public List<String> messages() {
        final List<String> messages = new ArrayList<>();
        messages.add(this.origin.messages().get(0));
        this.headers().forEach((key, val) -> messages.add(key + ": " + val));
        messages.add("");
        return messages;
    }

    @Override
    public String toString() {
        final StringJoiner description = new StringJoiner("\n")
            .add(
                this.origin.toString().substring(
                    0,
                    this.origin.toString().indexOf("\n\n")
                )
            )
            .add("")
            .add("Messages:");
        this.messages().forEach(description::add);
        return description.toString();
    }

}
