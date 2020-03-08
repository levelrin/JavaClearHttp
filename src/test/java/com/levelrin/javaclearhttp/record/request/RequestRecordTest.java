/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.record.request;

import com.levelrin.javaclearhttp.info.LeakedHeadersInfo;
import com.levelrin.javaclearhttp.info.LeakedMessagesInfo;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tests.
 */
final class RequestRecordTest {

    @Test
    public void headersShouldComeFromInfo() {
        final Map<String, String> headers = new HashMap<>();
        MatcherAssert.assertThat(
            new RequestRecord(
                new LeakedHeadersInfo(headers)
            ).headers(),
            CoreMatchers.sameInstance(headers)
        );
    }

    @Test
    public void bodyShouldComeFromMessages() {
        MatcherAssert.assertThat(
            new RequestRecord(
                new LeakedMessagesInfo(
                    Arrays.asList(
                        "POST /repos/levelrin/demo/issues HTTP/1.1",
                        "Authorization: Basic credentials",
                        "Content-Type: application/json",
                        "Host: api.github.com",
                        "User-Agent: JavaClearHttp",
                        "Accept: */*",
                        "Content-Length: 67",
                        "Connection: close",
                        "",
                        "content"
                    )
                )
            ).body(),
            CoreMatchers.equalTo("content")
        );
    }

    @Test
    public void messagesShouldComeFromInfo() {
        final List<String> messages = new ArrayList<>();
        MatcherAssert.assertThat(
            new RequestRecord(
                new LeakedMessagesInfo(messages)
            ).messages(),
            CoreMatchers.sameInstance(messages)
        );
    }

    @Test
    public void toStringShouldReturnStringifiedMessages() {
        MatcherAssert.assertThat(
            new RequestRecord(
                new LeakedMessagesInfo(
                    Arrays.asList(
                        "one",
                        "two",
                        "three"
                    )
                )
            ).toString(),
            CoreMatchers.equalTo("one\ntwo\nthree\n")
        );
    }

}
