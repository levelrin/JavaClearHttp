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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Tests.
 */
final class MessagesTest {

    @Test
    public void withoutHeadersAndBody() {
        final Messages messages = new Messages(
            new TraceableMap()
                .pair("method", "HTTP")
                .pair("host", "www.levelrin.com")
                .pair("path", "/")
        );
        MatcherAssert.assertThat(
            messages.list(),
            CoreMatchers.equalTo(
                Arrays.asList(
                    "HTTP / HTTP/1.1",
                    "Host: www.levelrin.com",
                    "User-Agent: JavaClearHttp",
                    "Accept: */*",
                    "Connection: close",
                    ""
                )
            )
        );
    }

    @Test
    public void withBody() {
        final Messages messages = new Messages(
            new TraceableMap()
                .pair("method", "HTTP")
                .pair("host", "www.levelrin.com")
                .pair("path", "/")
                .pair("body", "content")
        );
        MatcherAssert.assertThat(
            messages.list(),
            CoreMatchers.equalTo(
                Arrays.asList(
                    "HTTP / HTTP/1.1",
                    "Host: www.levelrin.com",
                    "User-Agent: JavaClearHttp",
                    "Accept: */*",
                    "Content-Length: 7",
                    "Content-Type: application/x-www-form-urlencoded",
                    "Connection: close",
                    "",
                    "content"
                )
            )
        );
    }

    @Test
    public void overrideDefaultHeader() {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Host", "www.github.com");
        final Messages messages = new Messages(
            new TraceableMap()
                .pair("method", "HTTP")
                .pair("host", "www.levelrin.com")
                .pair("path", "/"),
            headers
        );
        MatcherAssert.assertThat(
            messages.list(),
            CoreMatchers.equalTo(
                Arrays.asList(
                    "HTTP / HTTP/1.1",
                    "Host: www.github.com",
                    "User-Agent: JavaClearHttp",
                    "Accept: */*",
                    "Connection: close",
                    ""
                )
            )
        );
    }

}
