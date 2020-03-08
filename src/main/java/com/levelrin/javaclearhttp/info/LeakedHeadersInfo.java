/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.info;

import java.util.List;
import java.util.Map;

/**
 * It's for testing.
 *
 * You can inject headers via constructor.
 * It will simply return the injected object on its method call.
 *
 * For other methods, you can inject any {@link ReqInfoType} you want.
 * It will simply use the encapsulated object.
 * By default, it decorates {@link IgnoredInfo}.
 */
public final class LeakedHeadersInfo implements ReqInfoType {

    /**
     * For other methods, we will use this.
     */
    private final ReqInfoType origin;

    /**
     * We will simply return this.
     */
    private final Map<String, String> fakeHeaders;

    /**
     * Secondary constructor.
     * It encapsulates {@link IgnoredInfo} for other methods.
     * @param fakeHeaders See {@link LeakedHeadersInfo#fakeHeaders}.
     */
    public LeakedHeadersInfo(final Map<String, String> fakeHeaders) {
        this(
            new IgnoredInfo(),
            fakeHeaders
        );
    }

    /**
     * Primary constructor.
     * @param origin See {@link LeakedHeadersInfo#origin}.
     * @param fakeHeaders See {@link LeakedHeadersInfo#fakeHeaders}.
     */
    public LeakedHeadersInfo(final ReqInfoType origin, final Map<String, String> fakeHeaders) {
        this.origin = origin;
        this.fakeHeaders = fakeHeaders;
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
        return this.fakeHeaders;
    }

    @Override
    public List<String> messages() {
        return this.origin.messages();
    }

    @Override
    public String toString() {
        return this.origin.toString();
    }

}
