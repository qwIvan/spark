/*
 * Copyright 2011- Per Wendel
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package spark;


/**
 *
 */
public abstract class SimpleRouteImpl implements SimpleRoute {
    static final String DEFAULT_ACCEPT_TYPE = "*/*";

    private String path;
    private String acceptType;

    /**
     * Wraps the route in SimpleRouteImpl
     *
     * @param path  the path
     * @param route the route
     * @return the wrapped route
     */
    static SimpleRouteImpl create(final String path, final SimpleRoute route) {
        return create(path, DEFAULT_ACCEPT_TYPE, route);
    }

    /**
     * Wraps the route in SimpleRouteImpl
     *
     * @param path       the path
     * @param acceptType the accept type
     * @param route      the route
     * @return the wrapped route
     */
    static SimpleRouteImpl create(final String path, String acceptType, final SimpleRoute route) {
        if (acceptType == null) {
            acceptType = DEFAULT_ACCEPT_TYPE;
        }
        return new SimpleRouteImpl(path, acceptType) {
            @Override
            public void handle(Request request, Response response) throws Exception {
                route.handle(request, response);
            }
        };
    }

    /**
     * Constructor
     *
     * @param path The route path which is used for matching. (e.g. /hello, users/:name)
     */
    protected SimpleRouteImpl(String path) {
        this(path, DEFAULT_ACCEPT_TYPE);
    }

    /**
     * Constructor
     *
     * @param path       The route path which is used for matching. (e.g. /hello, users/:name)
     * @param acceptType The accept type which is used for matching.
     */
    protected SimpleRouteImpl(String path, String acceptType) {
        this.path = path;
        this.acceptType = acceptType;
    }

    /**
     * Invoked when a request is made on this route's corresponding path e.g. '/hello'
     *
     * @param request  The request object providing information about the HTTP request
     * @param response The response object providing functionality for modifying the response
     * @throws Exception when handle fails
     */
    public abstract void handle(Request request, Response response) throws Exception;

    /**
     * @return the accept type
     */
    public String getAcceptType() {
        return acceptType;
    }

    /**
     * @return the path
     */
    String getPath() {
        return this.path;
    }

}