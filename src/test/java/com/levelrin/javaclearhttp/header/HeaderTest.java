/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.header;

import com.levelrin.javaclearhttp.internal.TraceableMap;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;

/**
 * Tests.
 */
final class HeaderTest {

    @Test
    public void defineHeaders() {
        final HeaderType header = new Header(
            new TraceableMap()
                .pair("host", "www.levelrin.com")
                .pair("path", "/")
                .pair("protocol", "HTTP")
                .pair("method", "GET"),
            Collections.singletonMap("Accept", "*/*")
        ).header("Authentication", "Bearer token").header("ETag", "tag");
        MatcherAssert.assertThat(
            header.toString(),
            CoreMatchers.equalTo(
                "Host: www.levelrin.com\n"
                    + "Path: /\n"
                    + "Protocol: HTTP\n"
                    + "Method: GET\n"
                    + "\nHeaders:\n"
                    + "Accept: */*\n"
                    + "ETag: tag\n"
                    + "Authentication: Bearer token"
            )
        );
    }

    @Test
    public void defineBody() {
        final HeaderType header = new Header(
            new TraceableMap()
                .pair("host", "www.levelrin.com")
                .pair("path", "/")
                .pair("protocol", "HTTP")
                .pair("method", "GET"),
            Collections.singletonMap(
                "Content-Type",
                "application/x-www-form-urlencoded;charset='utf-8'"
            )
        );
        MatcherAssert.assertThat(
            header.body("content").toString(),
            CoreMatchers.equalTo(
                "Host: www.levelrin.com\n"
                    + "Path: /\n"
                    + "Protocol: HTTP\n"
                    + "Method: GET\n"
                    + "\nHeaders:\n"
                    + "Content-Type: application/x-www-form-urlencoded;charset='utf-8'\n"
                    + "\nBody:\n"
                    + "content"
            )
        );
    }

    @Test
    public void checkIfMessagesAreCorrectlyConstructed() {
        new Header(
            new TraceableMap()
                .pair("host", "www.levelrin.com")
                .pair("path", "/")
                .pair("protocol", "HTTP")
                .pair("method", "GET"),
            Collections.singletonMap("Accept", "*/*"),
            messages -> {
                MatcherAssert.assertThat(
                    messages,
                    CoreMatchers.equalTo(
                        Arrays.asList(
                            "GET / HTTP/1.1",
                            "Accept: */*",
                            "Host: www.levelrin.com",
                            "User-Agent: JavaClearHttp",
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
