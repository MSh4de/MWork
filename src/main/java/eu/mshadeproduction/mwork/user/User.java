package eu.mshadeproduction.mwork.user;


import eu.mshadeproduction.mwork.guild.Guild;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private Token token;
    private String name;
    private final List<Guild> guilds = new ArrayList<>();

    public User(String name) {
        this.name = name;
        this.token = new Token(-1);
    }

    public List<Guild> getGuilds() {
        return guilds;
    }

    public Token getToken() {
        return token;
    }

    public String getName() {
        return name;
    }


    @Override
    public int hashCode() {
        return Objects.hash(token, name, guilds);
    }

    @Override
    public String toString() {
        return "User{" +
                "token=" + token +
                ", name='" + name + '\'' +
                ", guilds=" + guilds +
                '}';
    }
}
