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
final class HeadersTest {

    @Test
    public void checkHeaders() {
        final Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("Vary", "Accept-Encoding");
        expectedMap.put("Content-Length", "1256");
        MatcherAssert.assertThat(
            new Headers(
                Arrays.asList(
                    "HTTP/1.1 200 OK",
                    "Vary: Accept-Encoding",
                    "Content-Length: 1256",
                    "",
                    "Content"
                )
            ).map(),
            CoreMatchers.equalTo(expectedMap)
        );
    }

}
