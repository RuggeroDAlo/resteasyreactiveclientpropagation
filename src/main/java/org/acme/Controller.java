package org.acme;

import io.smallrye.mutiny.Uni;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.logging.MDC;

@Path("")
public class Controller {

  @Inject
  Logger logger;

  private final AtomicInteger atomicInteger = new AtomicInteger(0);

  @RestClient
  @Inject
  Client client;

  @GET
  @Path("/static")
  public Uni<Void> whatever() {
    return Uni.createFrom().voidItem();
  }

  @GET
  @Path("/")
  public Uni<Void> test() {
    final int id = atomicInteger.addAndGet(1);
    MDC.put("id", id);
    logger.infof("%s started", id);
    return client.staticCall()
      .onItem()
      .invoke(() -> logger.infof("%s End", id)).replaceWithVoid();
  }

}
