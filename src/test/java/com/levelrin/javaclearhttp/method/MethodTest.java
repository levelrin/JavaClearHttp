/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.method;

import com.levelrin.javaclearhttp.internal.TraceableMap;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

/**
 * Tests.
 */
final class MethodTest {

    @Test
    public void defineHeaders() {
        final MethodType method = new Method(
            new TraceableMap()
                .pair("host", "www.levelrin.com")
                .pair("path", "/")
                .pair("protocol", "HTTP")
                .pair("method", "GET")
        );
        MatcherAssert.assertThat(
            method
                .header("Authentication", "Bearer token")
                .header("Accept", "*/*")
                .toString(),
            CoreMatchers.equalTo(
                "Host: www.levelrin.com\n"
                    + "Path: /\n"
                    + "Protocol: HTTP\n"
                    + "Method: GET\n"
                    + "\nHeaders:\n"
                    + "Accept: */*\n"
                    + "Authentication: Bearer token"
            )
        );
    }

    @Test
    public void checkIfMessagesAreCorrectlyConstructed() {
        new Method(
            new TraceableMap()
                .pair("host", "www.levelrin.com")
                .pair("path", "/")
                .pair("protocol", "HTTP")
                .pair("method", "GET"),
            messages -> {
                MatcherAssert.assertThat(
                    messages,
                    CoreMatchers.equalTo(
                        Arrays.asList(
                            "GET / HTTP/1.1",
                            "Host: www.levelrin.com",
                            "User-Agent: JavaClearHttp",
                            "Accept: */*",
                            "Connection: close",
                            ""
                        )
                    )
                );
                return Arrays.asList("Apple", "Banana");
            }
        ).send();
    }
}
