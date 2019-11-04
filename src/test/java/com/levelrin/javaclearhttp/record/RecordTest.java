/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.record;

import com.levelrin.javaclearhttp.internal.TraceableMap;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Tests.
 */
final class RecordTest {

    @Test
    public void toStringWithoutBody() {
        final RecordType record = new Record(
            new TraceableMap()
                .pair("host", "www.levelrin.com")
                .pair("path", "/")
                .pair("protocol", "HTTP")
                .pair("method", "GET"),
            Arrays.asList(
                "GET / HTTP/1.1",
                "Host: www.levelrin.com",
                "User-Agent: JavaClearHttp",
                "Accept: */*",
                "Connection: close",
                "",
                "content"
            ),
            Arrays.asList(
                "GET / HTTP/1.1",
                "Status: 200 OK",
                "",
                "content"
            )
        );
        MatcherAssert.assertThat(
            record.toString(),
            CoreMatchers.equalTo(
                "Host: www.levelrin.com\n"
                    + "Path: /\n"
                    + "Protocol: HTTP\n"
                    + "Port: 80\n"
                    + "Method: GET\n"
                    + "\n"
                    + "Messages:\n"
                    + "GET / HTTP/1.1\n"
                    + "Host: www.levelrin.com\n"
                    + "User-Agent: JavaClearHttp\n"
                    + "Accept: */*\n"
                    + "Connection: close\n"
                    + "\n"
                    + "content\n"
                    + "\n"
                    + "Replies:\n"
                    + "GET / HTTP/1.1\n"
                    + "Status: 200 OK\n"
                    + "\n"
                    + "content\n"
            )
        );
    }

    @Test
    public void toStringWithBody() {
        final RecordType record = new Record(
            new TraceableMap()
                .pair("host", "www.levelrin1.com")
                .pair("path", "/test1")
                .pair("protocol", "HTTPS")
                .pair("method", "POST")
                .pair("body", "content"),
            new ArrayList<>(),
            new ArrayList<>()
        );
        MatcherAssert.assertThat(
            record.toString(),
            CoreMatchers.equalTo(
                "Host: www.levelrin1.com\n"
                    + "Path: /test1\n"
                    + "Protocol: HTTPS\n"
                    + "Port: 443\n"
                    + "Method: POST\n"
                    + "Body: content"
                    + "\n\n"
                    + "Messages:\n"
                    + "\n"
                    + "Replies:\n"
            )
        );
    }

    @Test
    public void createRequestRecord() {
        final RecordType record = new Record(
            new TraceableMap(),
            Arrays.asList(
                "One",
                "Two",
                "Three"
            ),
            new ArrayList<>()
        );
        MatcherAssert.assertThat(
            record.request().toString(),
            CoreMatchers.equalTo(
                "One\nTwo\nThree\n"
            )
        );
    }

    @Test
    public void createResponseRecord() {
        final RecordType record = new Record(
            new TraceableMap(),
            new ArrayList<>(),
            Arrays.asList(
                "Uno",
                "Dos",
                "Tres"
            )
        );
        MatcherAssert.assertThat(
            record.response().toString(),
            CoreMatchers.equalTo(
                "Uno\nDos\nTres\n"
            )
        );
    }

}
