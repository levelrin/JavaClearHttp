/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.body;

import com.levelrin.javaclearhttp.internal.TraceableMap;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * Tests.
 */
final class BodyTest {

    @Test
    public void checkIfMessagesAreCorrectlyConstructed() {
        new Body(
            new TraceableMap()
                .pair("host", "www.levelrin.com")
                .pair("path", "/")
                .pair("protocol", "HTTP")
                .pair("method", "GET")
                .pair("body", "content"),
            Collections.singletonMap("Some-Header", "Some-Value"),
            messages -> {
                MatcherAssert.assertThat(
                    messages,
                    CoreMatchers.equalTo(
                        Arrays.asList(
                            "GET / HTTP/1.1",
                            "Some-Header: Some-Value",
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
                return Arrays.asList("Apple", "Banana");
            }
        ).send();
    }

}
