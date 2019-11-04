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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tests.
 */
final class ResponseRecordTest {

    @Test
    public void checkHeaders() {
        final ResponseRecordType responseRecord = new ResponseRecord(this.rawReplies());
        final Map<String, String> expectedHeaders = new HashMap<>();
        expectedHeaders.put("Server", "GitHub.com");
        expectedHeaders.put("Cache-Control", "public, max-age=60, s-maxage=60");
        expectedHeaders.put("Vary", "Accept");
        MatcherAssert.assertThat(
            responseRecord.headers(),
            CoreMatchers.equalTo(expectedHeaders)
        );
    }

    @Test
    public void checkBody() {
        final ResponseRecordType responseRecord = new ResponseRecord(this.rawReplies());
        MatcherAssert.assertThat(
            responseRecord.body(),
            CoreMatchers.equalTo("content")
        );
    }

    @Test
    public void checkAllReplies() {
        final ResponseRecordType responseRecord = new ResponseRecord(this.rawReplies());
        MatcherAssert.assertThat(
            responseRecord.replies(),
            CoreMatchers.equalTo(this.rawReplies())
        );
    }

    @Test
    public void checkStatus() {
        final ResponseRecordType responseRecord = new ResponseRecord(this.rawReplies());
        MatcherAssert.assertThat(
            responseRecord.status().toString(),
            CoreMatchers.equalTo(
                "HTTP/1.1 200 OK"
            )
        );
    }

    /**
     * Dummy replies.
     * @return List that contains dummy replies.
     */
    private List<String> rawReplies() {
        return Arrays.asList(
            "HTTP/1.1 200 OK",
            "Server: GitHub.com",
            "Cache-Control: public, max-age=60, s-maxage=60",
            "Vary: Accept",
            "",
            "content"
        );
    }

}
