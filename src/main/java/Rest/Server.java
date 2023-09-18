package Rest;

import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import java.util.logging.Logger;

public class Server {
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) {

        UndertowJaxrsServer ut = new UndertowJaxrsServer();

        RestApplication ta = new RestApplication();

        ut.deploy(ta);

        ut.start(
                Undertow.builder()
                        .addHttpListener(8080, "localhost")

        );
        logger.info("JAX-RS based micro-service running!");
    }
}
