/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.connection;

import com.levelrin.javaclearhttp.info.IgnoredInfo;
import com.levelrin.javaclearhttp.record.IgnoredRecord;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class LeakedConnectionTest {

    @Test
    public void shouldReturnInjectedRecord() {
        MatcherAssert.assertThat(
            new LeakedConnection(
                new IgnoredRecord()
            ).record(
                new IgnoredInfo()
            ),
            CoreMatchers.instanceOf(IgnoredRecord.class)
        );
    }

}
