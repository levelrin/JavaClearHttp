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
 * You can inject messages via constructor.
 * It will simply return the injected object on its method call.
 *
 * For other methods, you can inject any {@link ReqInfoType} you want.
 * It will simply use the encapsulated object.
 * By default, it decorates {@link IgnoredInfo}.
 */
public final class LeakedMessagesInfo implements ReqInfoType {

    /**
     * For other methods, we will use this.
     */
    private final ReqInfoType origin;

    /**
     * We will simply return this.
     */
    private final List<String> fakeMessages;

    /**
     * Secondary constructor.
     * It encapsulates {@link IgnoredInfo} for other methods.
     * @param fakeMessages See {@link LeakedMessagesInfo#fakeMessages}.
     */
    public LeakedMessagesInfo(final List<String> fakeMessages) {
        this(
            new IgnoredInfo(),
            fakeMessages
        );
    }

    /**
     * Primary constructor.
     * @param origin See {@link LeakedMessagesInfo#origin}.
     * @param fakeMessages See {@link LeakedMessagesInfo#fakeMessages}.
     */
    public LeakedMessagesInfo(final ReqInfoType origin, final List<String> fakeMessages) {
        this.origin = origin;
        this.fakeMessages = fakeMessages;
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
        return this.fakeMessages;
    }

    @Override
    public String toString() {
        return this.origin.toString();
    }

}
