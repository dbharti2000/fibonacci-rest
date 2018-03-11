package academy.dd.fibonacci.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Created by stevewalton on 20/07/2017.
 */
public class EmbeddedJetty {

    private Server jettyServer;

    public EmbeddedJetty(int portNumber) {
        jettyServer = new Server(portNumber);
    }

    public void start() throws Exception {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages("academy.dd.fibonacci.rest");


        ServletHolder restServlet = new ServletHolder(new ServletContainer(resourceConfig));

        ServletContextHandler restContext = new ServletContextHandler(jettyServer, "/*");
        restContext.addServlet(restServlet, "/*");

        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.stop();
        }
    }
}
