/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.connection;

import com.levelrin.javaclearhttp.info.ReqInfoType;
import com.levelrin.javaclearhttp.record.RecordType;

/**
 * It's for testing.
 * No methods should be used from this object.
 * It will throw exceptions if any method is called.
 * You can use this when you need something just to fill out the parameters.
 */
public final class IgnoredConnection implements ConnectionType {

    @Override
    public RecordType record(final ReqInfoType info) {
        throw new UnsupportedOperationException(
            "You should not call this method."
        );
    }

}
