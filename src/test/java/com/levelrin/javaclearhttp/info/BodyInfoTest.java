/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.info;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Tests.
 */
final class BodyInfoTest {

    @Test
    public void hostShouldComeFromOrigin() {
        final ReqInfoType origin = new LeakedHostInfo("host");
        MatcherAssert.assertThat(
            new BodyInfo(
                origin,
                "content1"
            ).host(),
            CoreMatchers.equalTo(
                origin.host()
            )
        );
    }

    @Test
    public void portShouldComeFromOrigin() {
        final ReqInfoType origin = new LeakedPortInfo(443);
        MatcherAssert.assertThat(
            new BodyInfo(
                origin,
                "content2"
            ).port(),
            CoreMatchers.equalTo(
                origin.port()
            )
        );
    }

    @Test
    public void headersShouldComeFromOriginPlusContentLength() {
        final ReqInfoType origin = new LeakedHeadersInfo(
            new HashMap<>()
        );
        MatcherAssert.assertThat(
            new BodyInfo(
                new LeakedHeadersInfo(
                    new HashMap<>()
                ),
                "content3"
            ).headers(),
            CoreMatchers.equalTo(
                origin.headers()
            )
        );
    }

    @Test
    public void messagesShouldReturnOriginPlusContent() {
        final ReqInfoType origin = new LeakedMessagesInfo(
            Arrays.asList(
                "One",
                "Two"
            )
        );
        MatcherAssert.assertThat(
            new BodyInfo(
                origin,
                "content4"
            ).messages(),
            Matchers.containsInRelativeOrder(
                "One",
                "Two",
                "content4"
            )
        );
    }

    @Test
    public void toStringShouldReturnOriginPlusContentWithSpaceBetweenThem() {
        final ReqInfoType origin = new LeakedStringInfo("leaked");
        MatcherAssert.assertThat(
            new BodyInfo(
                origin,
                "content5"
            ).toString(),
            CoreMatchers.equalTo("leaked\ncontent5")
        );
    }

}
