package eu.mshadeproduction.mwork.user;

import java.util.Objects;
import java.util.UUID;

public class Token {

    private UUID uuid = UUID.randomUUID();
    private long life;

    private Token(){}
    public Token(long life) {
        this.life = life;
    }

    public UUID getUuid() {
        return uuid;
    }

    public long getLife() {
        return life;
    }

    public boolean isExpired(){
        if (life < 0) return false;
        return life <= System.currentTimeMillis();
    }


    @Override
    public int hashCode() {
        return Objects.hash(uuid, life);
    }

    @Override
    public String toString() {
        return "Token{" +
                "uuid=" + uuid +
                ", life=" + life +
                '}';
    }
}
