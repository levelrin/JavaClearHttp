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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Tests.
 */
final class MethodInfoTest {

    @Test
    public void hostShouldComeFromUrl() {
        MatcherAssert.assertThat(
            new MethodInfo(
                this.url("https://www.levelrin1.com"),
                "method1"
            ).host(),
            CoreMatchers.equalTo("www.levelrin1.com")
        );
    }

    @Test
    public void portShouldBe80IfItIsHttp() {
        final int httpPort = 80;
        MatcherAssert.assertThat(
            new MethodInfo(
                this.url("http://www.levelrin2.com"),
                "method2"
            ).port(),
            CoreMatchers.equalTo(httpPort)
        );
    }

    @Test
    public void portShouldBe443IfItIsHttps() {
        final int httpsPort = 443;
        MatcherAssert.assertThat(
            new MethodInfo(
                this.url("https://www.levelrin3.com"),
                "method3"
            ).port(),
            CoreMatchers.equalTo(httpsPort)
        );
    }

    @Test
    public void shouldReturnDefaultHeaders() {
        final Map<String, String> defaultHeaders = new HashMap<>();
        defaultHeaders.put("Host", "www.levelrin4.com");
        defaultHeaders.put("User-Agent", "JavaClearHttp");
        defaultHeaders.put("Accept", "*/*");
        defaultHeaders.put("Connection", "close");
        MatcherAssert.assertThat(
            new MethodInfo(
                this.url("https://www.levelrin4.com"),
                "method4"
            ).headers(),
            CoreMatchers.equalTo(defaultHeaders)
        );
    }

    @Test
    public void filePathInRequestLineShouldBeSlashIfUrlDoesNotSpecifies() {
        MatcherAssert.assertThat(
            new MethodInfo(
                this.url("https://www.levelrin5.com"),
                "GET"
            ).messages(),
            CoreMatchers.equalTo(
                Arrays.asList(
                    "GET / HTTP/1.1",
                    "Host: www.levelrin5.com",
                    "User-Agent: JavaClearHttp",
                    "Accept: */*",
                    "Connection: close",
                    ""
                )
            )
        );
    }

    @Test
    public void filePathInRequestLineShouldIncludeQueryString() {
        MatcherAssert.assertThat(
            new MethodInfo(
                this.url("https://www.levelrin6.com/test?q1=v1&q2=v2"),
                "GET"
            ).messages(),
            CoreMatchers.equalTo(
                Arrays.asList(
                    "GET /test?q1=v1&q2=v2 HTTP/1.1",
                    "Host: www.levelrin6.com",
                    "User-Agent: JavaClearHttp",
                    "Accept: */*",
                    "Connection: close",
                    ""
                )
            )
        );
    }

    @Test
    public void toStringShouldHaveRequestInfo() {
        MatcherAssert.assertThat(
            new MethodInfo(
                this.url("https://www.levelrin7.com/"),
                "POST"
            ).toString(),
            CoreMatchers.equalTo(
                "URL: https://www.levelrin7.com/\n"
                    + "Host: www.levelrin7.com\n"
                    + "Method: POST\n"
                    + "Protocol: HTTPS\n"
                    + "Port: 443\n\n"
                    + "Messages:\n"
                    + "POST / HTTP/1.1\n"
                    + "Host: www.levelrin7.com\n"
                    + "User-Agent: JavaClearHttp\n"
                    + "Accept: */*\n"
                    + "Connection: close\n"
            )
        );
    }

    @Test
    public void unknownProtocolShouldThrowException() {
        Assertions.assertThrows(
            IllegalStateException.class,
            () -> new MethodInfo(
                this.url("ftp://www.levelrin8.com"),
                "DELETE"
            ).port()
        );
    }

    /**
     * Instantiating the {@link URL} object may throw the {@link MalformedURLException}.
     * It is tedious to handle the checked exception.
     * That's why we created this method to avoid the code duplication.
     * @param url Make sure you include the protocol.
     * @return URL object from the {@link java.net} package.
     * @throws IllegalStateException This can be thrown if the url is malformed.
     */
    private URL url(final String url) {
        try {
            return new URL(url);
        } catch (final MalformedURLException ex) {
            throw new IllegalStateException("Invalid URL: " + url, ex);
        }
    }

}
