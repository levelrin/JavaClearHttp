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

/**
 * Tests.
 */
final class StatusTest {

    @Test
    public void codeShouldComeFromStatusLine() {
        final int expectedCode = 200;
        MatcherAssert.assertThat(
            new Status("HTTP/1.1 200 OK").code(),
            CoreMatchers.equalTo(expectedCode)
        );
    }

    @Test
    public void reasonShouldComeFromStatusLine() {
        MatcherAssert.assertThat(
            new Status("HTTP/1.1 201 Created").reason(),
            CoreMatchers.equalTo("Created")
        );
    }

    @Test
    public void toStringShouldBeSameAsStatusLine() {
        final String statusLine = "HTTP/1.1 400 Bad Request";
        MatcherAssert.assertThat(
            new Status(statusLine).toString(),
            CoreMatchers.equalTo(statusLine)
        );
    }

}
