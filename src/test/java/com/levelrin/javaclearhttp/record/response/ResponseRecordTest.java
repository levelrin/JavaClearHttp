/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.record.response;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Tests.
 */
final class ResponseRecordTest {

    @Test
    public void statusMethodShouldReturnStatus() {
        MatcherAssert.assertThat(
            new ResponseRecord(
                Collections.singletonList("")
            ).status(),
            CoreMatchers.instanceOf(Status.class)
        );
    }

    @Test
    public void headersShouldComeFromReplies() {
        final Map<String, String> expected = new HashMap<>();
        expected.put("Date", "Fri, 08 Nov 2019 15:37:41 GMT");
        expected.put("Content-Type", "application/json; charset=utf-8");
        MatcherAssert.assertThat(
            new ResponseRecord(
                Arrays.asList(
                    "HTTP/1.1 200 OK",
                    "Date: Fri, 08 Nov 2019 15:37:41 GMT",
                    "Content-Type: application/json; charset=utf-8",
                    "",
                    "content"
                )
            ).headers(),
            CoreMatchers.equalTo(expected)
        );
    }

    @Test
    public void bodyShouldComeFromReplies() {
        MatcherAssert.assertThat(
            new ResponseRecord(
                Arrays.asList(
                    "HTTP/1.1 201 Created",
                    "Content-Type: application/json",
                    "",
                    "content"
                )
            ).body(),
            CoreMatchers.equalTo("content")
        );
    }

    @Test
    public void repliesShouldBeSameAsRawReplies() {
        MatcherAssert.assertThat(
            new ResponseRecord(
                Collections.singletonList("one")
            ).replies(),
            CoreMatchers.equalTo(
                Collections.singletonList("one")
            )
        );
    }

    @Test
    public void toStringShouldBeStringifiedReplies() {
        MatcherAssert.assertThat(
            new ResponseRecord(
                Arrays.asList(
                    "one",
                    "two",
                    "three"
                )
            ).toString(),
            CoreMatchers.equalTo("one\ntwo\nthree\n")
        );
    }

}
