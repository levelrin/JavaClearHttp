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
final class LeakedHostInfoTest {

    @Test
    public void hostShouldReturnInjectedObject() {
        final String host = "host1";
        MatcherAssert.assertThat(
            new LeakedHostInfo(host).host(),
            CoreMatchers.sameInstance(host)
        );
    }

    @Test
    public void portShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedHostInfo("host2").port()
        );
    }

    @Test
    public void headersShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedHostInfo("host3").headers()
        );
    }

    @Test
    public void messagesShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedHostInfo("host4").messages()
        );
    }

    @Test
    public void toStringShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedHostInfo("host5").toString()
        );
    }

    @Test
    public void portShouldComeFromOrigin() {
        final int port = 80;
        MatcherAssert.assertThat(
            new LeakedHostInfo(
                new LeakedPortInfo(port),
                "host6"
            ).port(),
            CoreMatchers.equalTo(port)
        );
    }

    @Test
    public void headersShouldComeFromOrigin() {
        final Map<String, String> headers = new HashMap<>();
        headers.put("one", "uno");
        MatcherAssert.assertThat(
            new LeakedHostInfo(
                new LeakedHeadersInfo(headers),
                "host7"
            ).headers(),
            CoreMatchers.equalTo(headers)
        );
    }

    @Test
    public void messagesShouldComeFromOrigin() {
        final List<String> messages = new ArrayList<>();
        MatcherAssert.assertThat(
            new LeakedHostInfo(
                new LeakedMessagesInfo(messages),
                "host8"
            ).messages(),
            CoreMatchers.equalTo(messages)
        );
    }

    @Test
    public void toStringShouldComeFromOrigin() {
        final String info = "info";
        MatcherAssert.assertThat(
            new LeakedHostInfo(
                new LeakedStringInfo(info),
                "host9"
            ).toString(),
            CoreMatchers.equalTo(info)
        );
    }

}
