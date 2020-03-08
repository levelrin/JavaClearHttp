/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.info;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class IgnoredInfoTest {

    @Test
    public void hostShouldThrowException() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new IgnoredInfo().host()
        );
    }

    @Test
    public void portShouldThrowException() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new IgnoredInfo().port()
        );
    }

    @Test
    public void headersShouldThrowException() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new IgnoredInfo().headers()
        );
    }

    @Test
    public void messagesShouldThrowException() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new IgnoredInfo().messages()
        );
    }

    @Test
    public void toStringShouldThrowException() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new IgnoredInfo().toString()
        );
    }

}
