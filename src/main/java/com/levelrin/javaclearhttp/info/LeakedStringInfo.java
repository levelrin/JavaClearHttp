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
 * You can inject string via constructor.
 * It will simply return the injected object on its method call.
 *
 * For other methods, you can inject any {@link ReqInfoType} you want.
 * It will simply use the encapsulated object.
 * By default, it decorates {@link IgnoredInfo}.
 */
public final class LeakedStringInfo implements ReqInfoType {

    /**
     * For other methods, we will use this.
     */
    private final ReqInfoType origin;

    /**
     * We will simply return this.
     */
    private final String string;

    /**
     * Secondary constructor.
     * It encapsulates {@link IgnoredInfo} for other methods.
     * @param string See {@link LeakedStringInfo#string}.
     */
    public LeakedStringInfo(final String string) {
        this(
            new IgnoredInfo(),
            string
        );
    }

    /**
     * Primary constructor.
     * @param origin See {@link LeakedStringInfo#origin}.
     * @param string See {@link LeakedStringInfo#string}.
     */
    public LeakedStringInfo(final ReqInfoType origin, final String string) {
        this.origin = origin;
        this.string = string;
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
        return this.origin.messages();
    }

    @Override
    public String toString() {
        return this.string;
    }

}
