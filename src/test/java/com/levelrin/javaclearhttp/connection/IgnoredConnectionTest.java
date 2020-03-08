/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.connection;

import com.levelrin.javaclearhttp.info.IgnoredInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class IgnoredConnectionTest {

    @Test
    public void shouldThrowExceptionOnMethodCall() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new IgnoredConnection()
                .record(
                    new IgnoredInfo()
                )
        );
    }

}
