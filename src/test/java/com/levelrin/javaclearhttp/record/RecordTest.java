/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.record;

import com.levelrin.javaclearhttp.info.IgnoredInfo;
import com.levelrin.javaclearhttp.info.LeakedStringInfo;
import com.levelrin.javaclearhttp.record.request.RequestRecord;
import com.levelrin.javaclearhttp.record.response.ResponseRecord;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Tests.
 */
final class RecordTest {

    @Test
    public void requestShouldReturnRequestRecord() {
        MatcherAssert.assertThat(
            new Record(
                new IgnoredInfo(),
                new ArrayList<>()
            ).request(),
            CoreMatchers.instanceOf(RequestRecord.class)
        );
    }

    @Test
    public void responseShouldReturnResponseRecord() {
        MatcherAssert.assertThat(
            new Record(
                new IgnoredInfo(),
                new ArrayList<>()
            ).response(),
            CoreMatchers.instanceOf(ResponseRecord.class)
        );
    }

    @Test
    public void toStringShouldHaveRequestAndResponseInfo() {
        MatcherAssert.assertThat(
            new Record(
                new LeakedStringInfo("Request"),
                Collections.singletonList("Response")
            ).toString(),
            CoreMatchers.equalTo(
                "Request\n\nReplies:\nResponse"
            )
        );
    }

}
