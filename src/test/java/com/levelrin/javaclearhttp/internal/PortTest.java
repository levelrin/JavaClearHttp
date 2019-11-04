/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.internal;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class PortTest {

    @Test
    @SuppressWarnings("MagicNumber")
    public void http() {
        MatcherAssert.assertThat(
            new Port("HTTP").toInt(),
            CoreMatchers.equalTo(80)
        );
    }

    @Test
    @SuppressWarnings("MagicNumber")
    public void https() {
        MatcherAssert.assertThat(
            new Port("HTTPS").toInt(),
            CoreMatchers.equalTo(443)
        );
    }

    @Test
    public void throwExceptionForUnknownProtocol() {
        String errorMessage = "";
        try {
            new Port("test").toInt();
        } catch (final IllegalStateException ex) {
            errorMessage = ex.getMessage();
        }
        MatcherAssert.assertThat(
            errorMessage,
            CoreMatchers.equalTo("Unknown protocol: test. Only HTTP or HTTPS are allowed.")
        );
    }

}
