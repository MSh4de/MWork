package eu.mshadeproduction.mwork.guild;


import eu.mshadeproduction.mwork.application.Application;
import eu.mshadeproduction.mwork.user.Permission;
import eu.mshadeproduction.mwork.user.Token;
import org.eclipse.jetty.websocket.api.Session;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Guild {

    private String name;
    private Token token;
    private Map<Token, List<Permission>> tokens = new HashMap<>();
    private GuildType guildType;
    private List<Application> applications = new ArrayList<>();
    private final Queue<Session> links = new ConcurrentLinkedQueue<>();

    public Guild(String name, GuildType guildType) {
        this(name, -1, guildType);
    }

    public Guild(String name, int life, GuildType guildType) {
        this.name = name;
        this.guildType = guildType;
        this.token = new Token(life);
    }

    public Guild createToken(int life, Permission... permissions){
        tokens.put(new Token(life), Arrays.asList(permissions));
        return this;
    }

    public String getName() {
        return name;
    }

    public GuildType getGuildType() {
        return guildType;
    }

    public Guild setGuildType(GuildType guildType) {
        this.guildType = guildType;
        return this;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public Guild addApplication(Application application){
        this.applications.add(application);
        return this;
    }

    public Guild removeApplication(Application application){
        this.applications.remove(application);
        return this;
    }

    public Token getToken() {
        return token;
    }

    public boolean isValid(UUID token){
        return tokens.entrySet().stream().anyMatch(entry -> entry.getKey().getUuid().equals(token) && entry.getKey().isExpired());
    }

    public void link(Session session){
        this.links.add(session);
    }

    public void unLink(Session session){
        this.links.remove(session);
    }

    public boolean isLink(Session session){
        return this.links.contains(session);
    }

    public Queue<Session> getLinks() {
        return links;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, token, tokens, guildType, applications);
    }

    @Override
    public String toString() {
        return "Guild{" +
                "name='" + name + '\'' +
                ", token=" + token +
                ", tokens=" + tokens +
                ", guildType=" + guildType +
                ", applications=" + applications +
                '}';
    }
}
