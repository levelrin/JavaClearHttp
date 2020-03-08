/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.info;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tests.
 */
final class LeakedStringInfoTest {

    @Test
    public void hostShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedStringInfo("info1").host()
        );
    }

    @Test
    public void portShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedStringInfo("info2").port()
        );
    }

    @Test
    public void headersShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedStringInfo("info3").headers()
        );
    }

    @Test
    public void messagesShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedStringInfo("info4").messages()
        );
    }

    @Test
    public void toStringShouldReturnInjectedObject() {
        final String info = "info5";
        MatcherAssert.assertThat(
            new LeakedStringInfo(info).toString(),
            CoreMatchers.sameInstance(info)
        );
    }

    @Test
    public void hostShouldComeFromOrigin() {
        final String host = "host";
        MatcherAssert.assertThat(
            new LeakedStringInfo(
                new LeakedHostInfo(host),
                "info6"
            ).host(),
            CoreMatchers.equalTo(host)
        );
    }

    @Test
    public void portShouldComeFromOrigin() {
        final int port = 80;
        MatcherAssert.assertThat(
            new LeakedStringInfo(
                new LeakedPortInfo(port),
                "info7"
            ).port(),
            CoreMatchers.equalTo(port)
        );
    }

    @Test
    public void headersShouldComeFromOrigin() {
        final Map<String, String> headers = new HashMap<>();
        MatcherAssert.assertThat(
            new LeakedStringInfo(
                new LeakedHeadersInfo(headers),
                "info8"
            ).headers(),
            CoreMatchers.equalTo(headers)
        );
    }

    @Test
    public void messagesShouldComeFromOrigin() {
        final List<String> messages = new ArrayList<>();
        MatcherAssert.assertThat(
            new LeakedStringInfo(
                new LeakedMessagesInfo(messages),
                "info9"
            ).messages(),
            CoreMatchers.equalTo(messages)
        );
    }

}
