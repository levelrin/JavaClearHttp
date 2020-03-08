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
 * You can inject port via constructor.
 * It will simply return the injected port on its method call.
 *
 * For other methods, you can inject any {@link ReqInfoType} you want.
 * It will simply use the encapsulated object.
 * By default, it decorates {@link IgnoredInfo}.
 */
public final class LeakedPortInfo implements ReqInfoType {

    /**
     * For other methods, we will use this.
     */
    private final ReqInfoType origin;

    /**
     * We will simply return this.
     */
    private final int fakePort;

    /**
     * Secondary constructor.
     * It encapsulates {@link IgnoredInfo} for other methods.
     * @param fakePort See {@link LeakedPortInfo#fakePort}.
     */
    public LeakedPortInfo(final int fakePort) {
        this(
            new IgnoredInfo(),
            fakePort
        );
    }

    /**
     * Primary constructor.
     * @param origin See {@link LeakedPortInfo#origin}.
     * @param fakePort See {@link LeakedPortInfo#fakePort}.
     */
    public LeakedPortInfo(final ReqInfoType origin, final int fakePort) {
        this.origin = origin;
        this.fakePort = fakePort;
    }

    @Override
    public String host() {
        return this.origin.host();
    }

    @Override
    public int port() {
        return this.fakePort;
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
