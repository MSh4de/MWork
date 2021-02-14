package eu.mshadeproduction.mwork.application;

import eu.mshadeproduction.mwork.guild.Guild;
import org.eclipse.jetty.websocket.api.Session;

public class ServerApp extends Application {


    public ServerApp(Guild guild, Session session) {
        super(guild, session);
    }
}
