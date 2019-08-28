/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package [=javaPackageBase].it;

import java.net.URI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.jboss.logging.Logger;

@Path("/[=artifactIdBase]")
@ApplicationScoped
public class [=artifactIdBaseCamelCase]Resource {

    private static final Logger log = Logger.getLogger([=artifactIdBaseCamelCase]Resource.class);

    @Inject
    ProducerTemplate producerTemplate;

    @Inject
    ConsumerTemplate consumerTemplate;

    @Path("/get")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() throws Exception {
        final String message = consumerTemplate.receiveBodyNoWait("[=artifactIdBase]:--fix-me--", String.class);
        log.infof("Received from [=artifactIdBase]: %s", message);
        return message;
    }

    @Path("/post")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response post(String message) throws Exception {
        log.infof("Sending to [=artifactIdBase]: %s", message);
        final String response = producerTemplate.requestBody("[=artifactIdBase]:--fix-me--", message, String.class);
        log.infof("Got response from [=artifactIdBase]: %s", response);
        return Response
                .created(new URI("https://camel.apache.org/"))
                .entity(response)
                .build();
    }
}
