/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.record;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class IgnoredRecordTest {

    @Test
    public void requestShouldThrowException() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new IgnoredRecord().request()
        );
    }

    @Test
    public void responseShouldThrowException() {
        Assertions.assertThrows(
            UnsupportedOperationException.class,
            () -> new IgnoredRecord().response()
        );
    }

}
