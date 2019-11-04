/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.http;

import com.levelrin.javaclearhttp.method.MethodType;

/**
 * Represents HTTP protocol.
 * Usually, it's the entry point of HTTP communication.
 * It's responsible for selecting the HTTP method.
 */
public interface HttpType {

    /**
     * Select HTTP GET method.
     * @return Next step.
     */
    MethodType get();

    /**
     * Select HTTP HEAD method.
     * @return Next step.
     */
    MethodType head();

    /**
     * Select HTTP POST method.
     * @return Next step.
     */
    MethodType post();

    /**
     * Select HTTP PUT method.
     * @return Next step.
     */
    MethodType put();

    /**
     * Select HTTP DELETE method.
     * @return Next step.
     */
    MethodType delete();

    /**
     * Select HTTP CONNECT method.
     * @return Next step.
     */
    MethodType connect();

    /**
     * Select HTTP OPTIONS method.
     * @return Next step.
     */
    MethodType options();

    /**
     * Select HTTP TRACE method.
     * @return Next step.
     */
    MethodType trace();

    /**
     * Select HTTP PATCH method.
     * @return Next step.
     */
    MethodType patch();

}
