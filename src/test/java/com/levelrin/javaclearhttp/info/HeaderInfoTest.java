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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Tests.
 */
final class HeaderInfoTest {

    @Test
    public void hostShouldComeFromOrigin() {
        final ReqInfoType origin = new LeakedHostInfo("host");
        MatcherAssert.assertThat(
            new HeaderInfo(
                origin,
                "name1",
                "value1"
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
            new HeaderInfo(
                origin,
                "name2",
                "value2"
            ).port(),
            CoreMatchers.equalTo(
                origin.port()
            )
        );
    }

    @Test
    public void headersShouldComeFromOriginPlusConstructor() {
        final Map<String, String> originalHeaders = new HashMap<>();
        originalHeaders.put("One", "Uno");
        final Map<String, String> expectedHeaders = new HashMap<>(originalHeaders);
        expectedHeaders.put("Two", "Dos");
        final ReqInfoType origin = new LeakedHeadersInfo(originalHeaders);
        MatcherAssert.assertThat(
            new HeaderInfo(
                origin,
                "Two",
                "Dos"
            ).headers(),
            CoreMatchers.equalTo(expectedHeaders)
        );
    }

    @Test
    public void messagesShouldComeFromOriginPlusItsHeader() {
        final Map<String, String> originalHeaders = new HashMap<>();
        originalHeaders.put("name1", "value1");
        final ReqInfoType origin = new LeakedHeadersInfo(
            new LeakedMessagesInfo(
                Collections.singletonList("1")
            ),
            originalHeaders
        );
        MatcherAssert.assertThat(
            new HeaderInfo(
                origin,
                "name3",
                "value3"
            ).messages(),
            Matchers.containsInRelativeOrder(
                "1", "name1: value1", "name3: value3", ""
            )
        );
    }

    @Test
    public void toStringShouldHaveAllInfo() {
        final Map<String, String> originalHeaders = new HashMap<>();
        originalHeaders.put("name2", "value2");
        final ReqInfoType origin = new LeakedHeadersInfo(
            new LeakedMessagesInfo(
                new LeakedStringInfo(
                    "description from origin\n\n"
                ),
                Collections.singletonList("message from origin")
            ),
            originalHeaders
        );
        MatcherAssert.assertThat(
            new HeaderInfo(
                origin,
                "name3",
                "value3"
            ).toString(),
            CoreMatchers.equalTo(
                "description from origin\n\n"
                    + "Messages:\n"
                    + "message from origin\n"
                    + "name2: value2\n"
                    + "name3: value3\n"
            )
        );
    }

}
