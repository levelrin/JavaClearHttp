/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.method;

import com.levelrin.javaclearhttp.body.BodyType;
import com.levelrin.javaclearhttp.header.HeaderType;
import com.levelrin.javaclearhttp.record.RecordType;
import com.levelrin.javaclearhttp.http.HttpType;

/**
 * Represents HTTP method.
 * Usually, it comes after {@link HttpType}.
 */
public interface MethodType {

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
     * Send HTTP request.
     * @return Record of request.
     */
    RecordType send();

}
