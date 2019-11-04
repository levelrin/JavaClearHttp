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
    @SuppressWarnings("MagicNumber")
    public void checkStatusCode() {
        MatcherAssert.assertThat(
            new Status("HTTP/1.1 200 OK").code(),
            CoreMatchers.equalTo(200)
        );
    }

    @Test
    public void checkStatusReason() {
        MatcherAssert.assertThat(
            new Status("HTTP/1.1 201 Created").reason(),
            CoreMatchers.equalTo("Created")
        );
    }

    @Test
    public void checkString() {
        MatcherAssert.assertThat(
            new Status("HTTP/1.1 403 Forbidden").toString(),
            CoreMatchers.equalTo("HTTP/1.1 403 Forbidden")
        );
    }

}
