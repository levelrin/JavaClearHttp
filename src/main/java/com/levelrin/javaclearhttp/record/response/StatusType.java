/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.record.response;

/**
 * HTTP response status.
 */
public interface StatusType {

    /**
     * HTTP status code.
     * @return Status code.
     */
    int code();

    /**
     * The reason of HTTP status.
     * @return HTTP status reason.
     */
    String reason();

}
