/*
 * Copyright (c) 2019 Rin (https://www.levelrin.com)
 *
 * This file has been created under the terms of the MIT License.
 * See the details at https://github.com/levelrin/JavaClearHttp/blob/master/LICENSE
 */

package com.levelrin.javaclearhttp.http;

import com.levelrin.javaclearhttp.connection.ConnectionType;
import com.levelrin.javaclearhttp.connection.HttpSocket;
import com.levelrin.javaclearhttp.connection.HttpsSocket;
import com.levelrin.javaclearhttp.connection.SocketConnection;
import com.levelrin.javaclearhttp.info.MethodInfo;
import com.levelrin.javaclearhttp.method.Method;
import com.levelrin.javaclearhttp.method.MethodType;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Represents HTTP request.
 * HTTP method can be selected via this.
 */
public final class Http implements HttpType {

    /**
     * Request URL.
     * Make sure to include the scheme.
     */
    private final String rawUrl;

    /**
     * An object that is responsible for making an actual HTTP connection.
     */
    private final ConnectionType connection;

    /**
     * Secondary constructor.
     * @param url See {@link Http#rawUrl}.
     */
    public Http(final String url) {
        this(
            url,
            new SocketConnection(
                new HttpSocket(),
                new HttpsSocket()
            )
        );
    }

    /**
     * Primary constructor.
     * @param url See {@link Http#rawUrl}.
     * @param connection See {@link Http#connection}.
     */
    public Http(final String url, final ConnectionType connection) {
        this.rawUrl = url;
        this.connection = connection;
    }

    @Override
    public MethodType get() {
        return new Method(
            new MethodInfo(
                this.url(),
                "GET"
            ),
            this.connection
        );
    }

    @Override
    public MethodType head() {
        return new Method(
            new MethodInfo(
                this.url(),
                "HEAD"
            ),
            this.connection
        );
    }

    @Override
    public MethodType post() {
        return new Method(
            new MethodInfo(
                this.url(),
                "POST"
            ),
            this.connection
        );
    }

    @Override
    public MethodType put() {
        return new Method(
            new MethodInfo(
                this.url(),
                "PUT"
            ),
            this.connection
        );
    }

    @Override
    public MethodType delete() {
        return new Method(
            new MethodInfo(
                this.url(),
                "DELETE"
            ),
            this.connection
        );
    }

    @Override
    public MethodType connect() {
        return new Method(
            new MethodInfo(
                this.url(),
                "CONNECT"
            ),
            this.connection
        );
    }

    @Override
    public MethodType options() {
        return new Method(
            new MethodInfo(
                this.url(),
                "OPTIONS"
            ),
            this.connection
        );
    }

    @Override
    public MethodType trace() {
        return new Method(
            new MethodInfo(
                this.url(),
                "TRACE"
            ),
            this.connection
        );
    }

    @Override
    public MethodType patch() {
        return new Method(
            new MethodInfo(
                this.url(),
                "PATCH"
            ),
            this.connection
        );
    }

    /**
     * Instantiating the {@link URL} object may throw the {@link MalformedURLException}.
     * It is tedious to handle the checked exception.
     * That's why we created this method to avoid the code duplication.
     * @return URL object from the {@link java.net} package.
     * @throws IllegalStateException This can be thrown if the url is malformed.
     */
    private URL url() {
        try {
            return new URL(this.rawUrl);
        } catch (final MalformedURLException ex) {
            throw new IllegalStateException("Invalid URL: " + this.rawUrl, ex);
        }
    }

}
