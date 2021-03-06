/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.body;

import com.levelrin.javaclearhttp.connection.LeakedConnection;
import com.levelrin.javaclearhttp.info.IgnoredInfo;
import com.levelrin.javaclearhttp.record.IgnoredRecord;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

/**
 * Tests.
 */
final class BodyTest {

    @Test
    public void sendShouldReturnRecordFromConnection() {
        MatcherAssert.assertThat(
            new Body(
                new IgnoredInfo(),
                new LeakedConnection(
                    new IgnoredRecord()
                )
            ).send(),
            CoreMatchers.instanceOf(IgnoredRecord.class)
        );
    }

}
