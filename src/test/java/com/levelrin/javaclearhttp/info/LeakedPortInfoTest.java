/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.info;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
@SuppressFBWarnings(value = "RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT", justification = "exception expected on toString")
final class LeakedPortInfoTest {

    @Test
    public void hostShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedPortInfo(0).host()
        );
    }

    @Test
    public void portShouldReturnInjectedPort() {
        final int port = 80;
        MatcherAssert.assertThat(
            new LeakedPortInfo(port).port(),
            CoreMatchers.sameInstance(port)
        );
    }

    @Test
    public void headersShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedPortInfo(0).headers()
        );
    }

    @Test
    public void messagesShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedPortInfo(0).messages()
        );
    }

    @Test
    public void toStringShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedPortInfo(0).toString()
        );
    }

    @Test
    public void hostShouldComeFromOrigin() {
        final String host = "host";
        MatcherAssert.assertThat(
            new LeakedPortInfo(
                new LeakedHostInfo(host),
                0
            ).host(),
            CoreMatchers.equalTo(host)
        );
    }

    @Test
    public void headersShouldComeFromOrigin() {
        final Map<String, String> headers = new HashMap<>();
        MatcherAssert.assertThat(
            new LeakedPortInfo(
                new LeakedHeadersInfo(headers),
                0
            ).headers(),
            CoreMatchers.equalTo(headers)
        );
    }

    @Test
    public void messagesShouldComeFromOrigin() {
        final List<String> messages = new ArrayList<>();
        MatcherAssert.assertThat(
            new LeakedPortInfo(
                new LeakedMessagesInfo(messages),
                0
            ).messages(),
            CoreMatchers.equalTo(messages)
        );
    }

    @Test
    public void toStringShouldComeFromOrigin() {
        final String info = "info";
        MatcherAssert.assertThat(
            new LeakedPortInfo(
                new LeakedStringInfo(info),
                0
            ).toString(),
            CoreMatchers.equalTo(info)
        );
    }

}
