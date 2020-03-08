/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.body;

import com.levelrin.javaclearhttp.header.HeaderType;
import com.levelrin.javaclearhttp.record.RecordType;

/**
 * Represents HTTP body.
 * Usually, it comes after {@link HeaderType}.
 */
public interface BodyType {

    /**
     * Send the HTTP request.
     * @return Record of HTTP request.
     */
    RecordType send();

}
