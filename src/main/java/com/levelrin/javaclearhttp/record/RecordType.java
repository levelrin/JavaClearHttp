/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.record;

import com.levelrin.javaclearhttp.record.request.RequestRecordType;
import com.levelrin.javaclearhttp.record.response.ResponseRecordType;

/**
 * Represents the result of HTTP request.
 */
public interface RecordType {

    /**
     * The information of HTTP request.
     * @return HTTP request record.
     */
    RequestRecordType request();

    /**
     * The information of HTTP response.
     * @return HTTP response record.
     */
    ResponseRecordType response();

}
