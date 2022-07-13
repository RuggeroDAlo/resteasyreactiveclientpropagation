package org.acme;

import io.smallrye.mutiny.Uni;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "client-api")
public interface Client {
  @GET
  @Path("/static")
  Uni<Void> staticCall();
}