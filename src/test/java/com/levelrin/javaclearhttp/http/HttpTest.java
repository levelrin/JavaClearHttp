/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.http;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringJoiner;

/**
 * Tests.
 */
final class HttpTest {

    @Test
    public void selectGet() throws MalformedURLException {
        MatcherAssert.assertThat(
            new Http(
                new URL("https://www.levelrin1.com")
            ).get().toString(),
            CoreMatchers.equalTo(
                this.expectedString(
                    "www.levelrin1.com",
                    "/",
                    "GET"
                )
            )
        );
    }

    @Test
    public void selectHead() {
        final String url = "https://www.levelrin2.com/";
        MatcherAssert.assertThat(
            new Http(url).head().toString(),
            CoreMatchers.equalTo(
                this.expectedString(
                    "www.levelrin2.com",
                    "/",
                    "HEAD"
                )
            )
        );
    }

    @Test
    public void selectPost() {
        final String url = "https://www.levelrin3.com/test3";
        MatcherAssert.assertThat(
            new Http(url).post().toString(),
            CoreMatchers.equalTo(
                this.expectedString(
                    "www.levelrin3.com",
                    "/test3",
                    "POST"
                )
            )
        );
    }

    @Test
    public void selectPut() {
        final String url = "https://www.levelrin4.com/test4";
        MatcherAssert.assertThat(
            new Http(url).put().toString(),
            CoreMatchers.equalTo(
                this.expectedString(
                    "www.levelrin4.com",
                    "/test4",
                    "PUT"
                )
            )
        );
    }

    @Test
    public void selectDelete() {
        final String url = "https://www.levelrin5.com/test5";
        MatcherAssert.assertThat(
            new Http(url).delete().toString(),
            CoreMatchers.equalTo(
                this.expectedString(
                    "www.levelrin5.com",
                    "/test5",
                    "DELETE"
                )
            )
        );
    }

    @Test
    public void selectConnect() {
        final String url = "https://www.levelrin6.com/test6";
        MatcherAssert.assertThat(
            new Http(url).connect().toString(),
            CoreMatchers.equalTo(
                this.expectedString(
                    "www.levelrin6.com",
                    "/test6",
                    "CONNECT"
                )
            )
        );
    }

    @Test
    public void selectOptions() {
        final String url = "https://www.levelrin7.com/test7";
        MatcherAssert.assertThat(
            new Http(url).options().toString(),
            CoreMatchers.equalTo(
                this.expectedString(
                    "www.levelrin7.com",
                    "/test7",
                    "OPTIONS"
                )
            )
        );
    }

    @Test
    public void selectTrace() {
        final String url = "https://www.levelrin8.com/test8";
        MatcherAssert.assertThat(
            new Http(url).trace().toString(),
            CoreMatchers.equalTo(
                this.expectedString(
                    "www.levelrin8.com",
                    "/test8",
                    "TRACE"
                )
            )
        );
    }

    @Test
    public void selectPatch() {
        final String url = "https://www.levelrin9.com/test9";
        MatcherAssert.assertThat(
            new Http(url).patch().toString(),
            CoreMatchers.equalTo(
                this.expectedString(
                    "www.levelrin9.com",
                    "/test9",
                    "PATCH"
                )
            )
        );
    }

    @Test
    public void exceptionOnInvalidUrl() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new Http("www.levelrin10.com")
        );
    }

    /**
     * It constructs expected String for tests.
     * This method is created to reduce the duplicated code.
     * @param host Host.
     * @param path Path.
     * @param method HTTP method.
     * @return Expected output of the test case.
     */
    private String expectedString(final String host, final String path, final String method) {
        return new StringJoiner("\n")
            .add("Host: " + host)
            .add("Path: " + path)
            .add("Protocol: " + "HTTPS")
            .add("Method: " + method)
            .toString();
    }

}
