package eu.mshadeproduction.mwork.application;

import eu.mshadeproduction.mwork.guild.Guild;
import org.eclipse.jetty.websocket.api.Session;

public class ClientApp extends Application {

    public ClientApp(Guild guild, Session session) {
        super(guild, session);
    }
}
