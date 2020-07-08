/*
 * Copyright (c) 2020 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.regression.header;

import com.levelrin.javaclearhttp.connection.FakeConnection;
import com.levelrin.javaclearhttp.http.Http;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Checking the default headers.
 */
public final class DefaultHeadersTest {

    @Test
    public void requestHeadersShouldHaveContentLengthAsDefaultWhenBodyExists() {
        MatcherAssert.assertThat(
            new Http(
                "https://levelrin.com",
                new FakeConnection()
            ).post()
                .body("content")
                .send()
                .request()
                .headers(),
            Matchers.hasKey("Content-Length")
        );
    }

}
