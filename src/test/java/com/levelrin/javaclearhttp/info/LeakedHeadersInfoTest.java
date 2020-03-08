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
import java.util.HashMap;
import java.util.Map;

/**
 * Tests.
 */
@SuppressFBWarnings(value = "RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT", justification = "exception expected on toString")
final class LeakedHeadersInfoTest {

    @Test
    public void hostShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedHeadersInfo(
                new HashMap<>()
            ).host()
        );
    }

    @Test
    public void portShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedHeadersInfo(
                new HashMap<>()
            ).port()
        );
    }

    @Test
    public void headersShouldReturnInjectedHeaders() {
        final Map<String, String> headers = new HashMap<>();
        MatcherAssert.assertThat(
            new LeakedHeadersInfo(headers).headers(),
            CoreMatchers.sameInstance(headers)
        );
    }

    @Test
    public void messagesShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedHeadersInfo(
                new HashMap<>()
            ).messages()
        );
    }

    @Test
    public void toStringShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedHeadersInfo(
                new HashMap<>()
            ).toString()
        );
    }

    @Test
    public void hostShouldComeFromOrigin() {
        final String host = "host1";
        MatcherAssert.assertThat(
            new LeakedHeadersInfo(
                new LeakedHostInfo(host),
                new HashMap<>()
            ).host(),
            CoreMatchers.equalTo(host)
        );
    }

    @Test
    public void portShouldComeFromOrigin() {
        final int port = 80;
        MatcherAssert.assertThat(
            new LeakedHeadersInfo(
                new LeakedPortInfo(port),
                new HashMap<>()
            ).port(),
            CoreMatchers.equalTo(port)
        );
    }

}
