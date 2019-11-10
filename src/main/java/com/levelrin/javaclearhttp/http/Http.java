/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.http;

import com.levelrin.javaclearhttp.internal.TraceableMap;
import com.levelrin.javaclearhttp.method.Method;
import com.levelrin.javaclearhttp.method.MethodType;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.StringJoiner;

/**
 * Represents HTTP request.
 * HTTP method can be selected via this.
 */
public final class Http implements HttpType {

    /**
     * The url of HTTP request.
     */
    private final URL url;

    /**
     * Secondary constructor.
     * @param url This will be used to instantiate {@link URL}.
     * @throws IllegalArgumentException This can be thrown if the url is malformed.
     */
    public Http(final String url) {
        try {
            this.url = new URL(url);
        } catch (final MalformedURLException ex) {
            throw new IllegalArgumentException("Invalid url: " + url, ex);
        }
    }

    /**
     * Primary constructor.
     * @param url Info at {@link Http#url}.
     */
    public Http(final URL url) {
        this.url = url;
    }

    @Override
    public MethodType get() {
        return new Method(
            this.map("GET", "get()")
        );
    }

    @Override
    public MethodType head() {
        return new Method(
            this.map("HEAD", "head()")
        );
    }

    @Override
    public MethodType post() {
        return new Method(
            this.map("POST", "post()")
        );
    }

    @Override
    public MethodType put() {
        return new Method(
            this.map("PUT", "put()")
        );
    }

    @Override
    public MethodType delete() {
        return new Method(
            this.map("DELETE", "delete()")
        );
    }

    @Override
    public MethodType connect() {
        return new Method(
            this.map("CONNECT", "connect()")
        );
    }

    @Override
    public MethodType options() {
        return new Method(
            this.map("OPTIONS", "options()")
        );
    }

    @Override
    public MethodType trace() {
        return new Method(
            this.map("TRACE", "trace()")
        );
    }

    @Override
    public MethodType patch() {
        return new Method(
            this.map("PATCH", "path()")
        );
    }

    /**
     * Create a map that contains HTTP configuration.
     * This method is created to remove duplicated code.
     * @param method HTTP method name.
     * @param callerMethod The method name of caller.
     * @return Map that contains HTTP configuration.
     */
    private TraceableMap map(final String method, final String callerMethod) {
        return new TraceableMap()
            .pair("host", this.url.getHost())
            .pair("path", this.url.getPath().replaceFirst("^$", "/"))
            .pair("protocol", this.url.getProtocol().toUpperCase(Locale.US))
            .pair("method", method.toUpperCase(Locale.US))
            .footprint(
                this,
                callerMethod,
                new StringJoiner("\n\t")
                    .add("Insert following pairs:")
                    .add("host: " + this.url.getHost())
                    .add("path: " + this.url.getPath())
                    .add("protocol: " + this.url.getProtocol().toUpperCase(Locale.US))
                    .add("method: " + method.toUpperCase(Locale.US))
                    .toString()
            );
    }

}
