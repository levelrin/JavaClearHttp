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
 * You can inject host via constructor.
 * It will simply return the injected object on its method call.
 *
 * For other methods, you can inject any {@link ReqInfoType} you want.
 * It will simply use the encapsulated object.
 * By default, it decorates {@link IgnoredInfo}.
 */
public final class LeakedHostInfo implements ReqInfoType {

    /**
     * For other methods, we will use this.
     */
    private final ReqInfoType origin;

    /**
     * We will simply return this.
     */
    private final String fakeHost;

    /**
     * Secondary constructor.
     * It encapsulates {@link IgnoredInfo} for other methods.
     * @param fakeHost See {@link LeakedHeadersInfo#host}.
     */
    public LeakedHostInfo(final String fakeHost) {
        this(
            new IgnoredInfo(),
            fakeHost
        );
    }

    /**
     * Primary constructor.
     * @param origin See {@link LeakedHostInfo#origin}.
     * @param fakeHost See {@link LeakedHostInfo#fakeHost}.
     */
    public LeakedHostInfo(final ReqInfoType origin, final String fakeHost) {
        this.origin = origin;
        this.fakeHost = fakeHost;
    }

    @Override
    public String host() {
        return this.fakeHost;
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
        return this.origin.messages();
    }

    @Override
    public String toString() {
        return this.origin.toString();
    }

}
