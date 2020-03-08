/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.info;

import com.levelrin.javaclearhttp.body.Body;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Request information for {@link Body}.
 * It decorates the previous request information.
 */
public final class BodyInfo implements ReqInfoType {

    /**
     * We will decorate this.
     */
    private final ReqInfoType origin;

    /**
     * The content of HTTP body.
     */
    private final String content;

    /**
     * Constructor.
     * @param origin See {@link BodyInfo#origin}.
     * @param content See {@link BodyInfo#content}.
     */
    public BodyInfo(final ReqInfoType origin, final String content) {
        this.origin = origin;
        this.content = content;
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
        return this.origin.headers();
    }

    @Override
    public List<String> messages() {
        final List<String> copy = new ArrayList<>(this.origin.messages());
        copy.add(this.content);
        return copy;
    }

    @Override
    public String toString() {
        return new StringJoiner("\n")
            .add(
                this.origin.toString()
            )
            .add(this.content)
            .toString();
    }

}
