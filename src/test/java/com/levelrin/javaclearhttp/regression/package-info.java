/*
 * Copyright (c) 2020 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

/**
 * This package contains test cases from the user stories.
 * In normal unit testing, we focus on one object's behavior only.
 * However, we also want to test the interactions of objects.
 * For example, we want to check if the following request contains the header 'Content-Length' as default:
 * {@code
 * new Http("URL")
 *     .post()
 *     .body("content")
 *     .send();
 * }
 * As you can see, we are interested in checking the overall effect of method chaining on object interactions.
 * We decided to name this package 'regression' because we usually want to test such user stories from the bug reports.
 */
package com.levelrin.javaclearhttp.regression;
