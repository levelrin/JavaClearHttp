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
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * Tests.
 */
final class ConnectionErrorTest {

    @Test
    public void checkErrorMessage() {
        final ConnectionError error = new ConnectionError(
            new TraceableMap()
                .pair("host", "www.levelrin.com")
                .pair("protocol", "HTTPS")
                .pair("method", "POST")
                .pair("body", "content"),
            new ArrayList<>()
        );
        MatcherAssert.assertThat(
            error.toString(),
            CoreMatchers.equalTo(
                new StringJoiner("\n")
                    .add("Failed to send the request.")
                    .add("Host: www.levelrin.com")
                    .add("Protocol: HTTPS")
                    .add("Port: 443")
                    .add("Method: POST")
                    .add("Body: content")
                    .add("\nMessages:\n")
                    .toString()
            )
        );
    }

}
