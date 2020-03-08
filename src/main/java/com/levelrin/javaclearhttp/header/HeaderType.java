/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.header;

import com.levelrin.javaclearhttp.body.BodyType;
import com.levelrin.javaclearhttp.method.MethodType;
import com.levelrin.javaclearhttp.record.RecordType;

/**
 * Represents HTTP header.
 * Usually, it comes after {@link MethodType}.
 */
public interface HeaderType {

    /**
     * Add a HTTP header.
     * @param name Name of header.
     * @param value Value of header.
     * @return HTTP header.
     */
    HeaderType header(String name, String value);

    /**
     * Attach HTTP body.
     * @param content Content of body.
     * @return HTTP body.
     */
    BodyType body(String content);

    /**
     * Skip attaching HTTP body and send the request.
     * @return Record of HTTP request.
     */
    RecordType send();

}
