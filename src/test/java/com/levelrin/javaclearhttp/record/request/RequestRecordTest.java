/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.record.request;

import com.levelrin.javaclearhttp.internal.TraceableMap;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Tests.
 */
final class RequestRecordTest {

    @Test
    public void checkHeaders() {
        final RequestRecordType record = new RequestRecord(
            new TraceableMap(),
            Arrays.asList(
                "GET / HTTP/1.1",
                "Host: www.levelrin.com",
                "User-Agent: JavaClearHttp",
                "Accept: */*",
                "",
                "content1"
            )
        );
        final Map<String, String> expectedHeaders = new HashMap<>();
        expectedHeaders.put("Host", "www.levelrin.com");
        expectedHeaders.put("User-Agent", "JavaClearHttp");
        expectedHeaders.put("Accept", "*/*");
        MatcherAssert.assertThat(
            record.headers(),
            CoreMatchers.equalTo(expectedHeaders)
        );
    }

    @Test
    public void checkBody() {
        final RequestRecordType record = new RequestRecord(
            new TraceableMap()
                .pair("host", "www.levelrin.com")
                .pair("path", "/")
                .pair("protocol", "HTTPS")
                .pair("method", "POST")
                .pair("body", "content2"),
            new ArrayList<>()
        );
        MatcherAssert.assertThat(
            record.body(),
            CoreMatchers.equalTo("content2")
        );
    }

    @Test
    public void checkMessages() {
        final List<String> messages = Arrays.asList(
            "GET / HTTP/1.1",
            "Host: www.levelrin.com",
            "User-Agent: JavaClearHttp",
            "Accept: */*",
            "",
            "content3"
        );
        final RequestRecordType record = new RequestRecord(
            new TraceableMap(),
            messages
        );
        MatcherAssert.assertThat(
            record.messages(),
            CoreMatchers.equalTo(messages)
        );
    }

}
