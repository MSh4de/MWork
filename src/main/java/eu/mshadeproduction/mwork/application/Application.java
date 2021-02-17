package eu.mshadeproduction.mwork.application;

import eu.mshadeproduction.mwork.guild.Guild;
import org.eclipse.jetty.websocket.api.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Application {

    private UUID uuid = UUID.randomUUID();
    private final Guild guild;
    private final Session session;
    private final List<Guild> linked = new ArrayList<>();

    public Application(Guild guild, Session session) {
        this.guild = guild;
        this.session = session;
    }

    public Guild getGuild() {
        return guild;
    }

    public void link(Guild guild){
        this.linked.add(guild);
    }

    public void unlink(Guild guild){
        this.linked.remove(guild);
    }

    public List<Guild> getLinked() {
        return linked;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Session getSession() {
        return session;
    }
}
