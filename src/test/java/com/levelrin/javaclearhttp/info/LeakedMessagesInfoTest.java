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
final class LeakedMessagesInfoTest {

    @Test
    public void hostShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedMessagesInfo(
                new ArrayList<>()
            ).host()
        );
    }

    @Test
    public void portShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedMessagesInfo(
                new ArrayList<>()
            ).port()
        );
    }

    @Test
    public void headersShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedMessagesInfo(
                new ArrayList<>()
            ).headers()
        );
    }

    @Test
    public void messagesShouldReturnInjectedObject() {
        final List<String> messages = new ArrayList<>();
        MatcherAssert.assertThat(
            new LeakedMessagesInfo(messages).messages(),
            CoreMatchers.sameInstance(messages)
        );
    }

    @Test
    public void toStringShouldThrowExceptionByDefault() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new LeakedMessagesInfo(
                new ArrayList<>()
            ).toString()
        );
    }

    @Test
    public void hostShouldComeFromOrigin() {
        final String host = "host";
        MatcherAssert.assertThat(
            new LeakedMessagesInfo(
                new LeakedHostInfo(host),
                new ArrayList<>()
            ).host(),
            CoreMatchers.equalTo(host)
        );
    }

    @Test
    public void portShouldComeFromOrigin() {
        final int port = 80;
        MatcherAssert.assertThat(
            new LeakedMessagesInfo(
                new LeakedPortInfo(port),
                new ArrayList<>()
            ).port(),
            CoreMatchers.equalTo(port)
        );
    }

    @Test
    public void headersShouldComeFromOrigin() {
        final Map<String, String> headers = new HashMap<>();
        headers.put("one", "uno");
        MatcherAssert.assertThat(
            new LeakedMessagesInfo(
                new LeakedHeadersInfo(headers),
                new ArrayList<>()
            ).headers(),
            CoreMatchers.equalTo(headers)
        );
    }

}
