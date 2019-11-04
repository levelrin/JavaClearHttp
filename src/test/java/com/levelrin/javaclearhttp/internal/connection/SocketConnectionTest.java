/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.internal.connection;

import com.levelrin.javaclearhttp.internal.TraceableMap;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Tests.
 */
final class SocketConnectionTest {

    /**
     * For avoiding duplicated String literal.
     */
    private static final String HTTP = "HTTP";

    /**
     * For avoiding duplicated String literal.
     */
    private static final String HTTPS = "HTTPS";

    @Test
    public void shouldUseHttpSocketWhenProtocolIsHttp() {
        final SocketConnection connection = new SocketConnection(
            new TraceableMap().pair("protocol", HTTP),
            messages -> Collections.singletonList(HTTP),
            messages -> Collections.singletonList(HTTPS)
        );
        MatcherAssert.assertThat(
            connection.replies(new ArrayList<>()),
            CoreMatchers.equalTo(Collections.singletonList(HTTP))
        );
    }

    @Test
    public void shouldUseHttpsSocketWhenProtocolIsHttps() {
        final SocketConnection connection = new SocketConnection(
            new TraceableMap().pair("protocol", HTTPS),
            messages -> Collections.singletonList(HTTP),
            messages -> Collections.singletonList(HTTPS)
        );
        MatcherAssert.assertThat(
            connection.replies(new ArrayList<>()),
            CoreMatchers.equalTo(Collections.singletonList(HTTPS))
        );
    }

    @Test
    public void shouldThrowExceptionOnUnknownProtocol() {
        final SocketConnection connection = new SocketConnection(
            new TraceableMap().pair("protocol", "TEST"),
            messages -> Collections.singletonList(HTTP),
            messages -> Collections.singletonList(HTTPS)
        );
        Assertions.assertThrows(
            IllegalStateException.class,
            () -> connection.replies(new ArrayList<>())
        );
    }

}
