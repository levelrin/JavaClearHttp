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
 * No methods should be used from this object.
 * It will throw exceptions if any method is called.
 * You can use this when you need something just to fill out the parameters.
 */
public final class IgnoredInfo implements ReqInfoType {

    /**
     * We will throw this exception.
     */
    private final RuntimeException exception;

    /**
     * Secondary constructor.
     * We will use {@link UnsupportedOperationException} exception as default.
     */
    public IgnoredInfo() {
        this(
            new UnsupportedOperationException(
                "You should not call this method."
            )
        );
    }

    /**
     * Primary constructor.
     * @param exception See {@link IgnoredInfo#exception}.
     */
    public IgnoredInfo(final RuntimeException exception) {
        this.exception = exception;
    }

    @Override
    public String host() {
        throw this.exception;
    }

    @Override
    public int port() {
        throw this.exception;
    }

    @Override
    public Map<String, String> headers() {
        throw this.exception;
    }

    @Override
    public List<String> messages() {
        throw this.exception;
    }

    @Override
    public String toString() {
        throw this.exception;
    }

}
