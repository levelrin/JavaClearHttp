/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.http;

import com.levelrin.javaclearhttp.connection.IgnoredConnection;
import com.levelrin.javaclearhttp.method.Method;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class HttpTest {

    @Test
    public void getMethodShouldReturnMethod() {
        MatcherAssert.assertThat(
            new Http(
                "https://www.levelrin.com/get",
                new IgnoredConnection()
            ).get(),
            CoreMatchers.instanceOf(Method.class)
        );
    }

    @Test
    public void headMethodShouldReturnMethod() {
        MatcherAssert.assertThat(
            new Http(
                "https://www.levelrin.com/head",
                new IgnoredConnection()
            ).head(),
            CoreMatchers.instanceOf(Method.class)
        );
    }

    @Test
    public void postMethodShouldReturnMethod() {
        MatcherAssert.assertThat(
            new Http(
                "https://www.levelrin.com/post",
                new IgnoredConnection()
            ).post(),
            CoreMatchers.instanceOf(Method.class)
        );
    }

    @Test
    public void putMethodShouldReturnMethod() {
        MatcherAssert.assertThat(
            new Http(
                "https://www.levelrin.com/put",
                new IgnoredConnection()
            ).put(),
            CoreMatchers.instanceOf(Method.class)
        );
    }

    @Test
    public void deleteMethodShouldReturnMethod() {
        MatcherAssert.assertThat(
            new Http(
                "https://www.levelrin.com/delete",
                new IgnoredConnection()
            ).delete(),
            CoreMatchers.instanceOf(Method.class)
        );
    }

    @Test
    public void connectMethodShouldReturnMethod() {
        MatcherAssert.assertThat(
            new Http(
                "https://www.levelrin.com/connect",
                new IgnoredConnection()
            ).connect(),
            CoreMatchers.instanceOf(Method.class)
        );
    }

    @Test
    public void optionsMethodShouldReturnMethod() {
        MatcherAssert.assertThat(
            new Http(
                "https://www.levelrin.com/options",
                new IgnoredConnection()
            ).options(),
            CoreMatchers.instanceOf(Method.class)
        );
    }

    @Test
    public void traceMethodShouldReturnMethod() {
        MatcherAssert.assertThat(
            new Http(
                "https://www.levelrin.com/trace",
                new IgnoredConnection()
            ).trace(),
            CoreMatchers.instanceOf(Method.class)
        );
    }

    @Test
    public void patchMethodShouldReturnMethod() {
        MatcherAssert.assertThat(
            new Http(
                "https://www.levelrin.com/patch",
                new IgnoredConnection()
            ).patch(),
            CoreMatchers.instanceOf(Method.class)
        );
    }

    @Test
    public void invalidUrlShouldThrowException() {
        Assertions.assertThrows(
            IllegalStateException.class,
            () -> new Http("www.levelrin.com/invalid").get()
        );
    }

}
