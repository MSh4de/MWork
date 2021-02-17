package eu.mshadeproduction.mwork.guild;

import eu.mshadeproduction.mwork.application.Application;
import eu.mshadeproduction.mwork.application.ClientApp;
import eu.mshadeproduction.mwork.application.ServerApp;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public enum GuildType {

    SERVER(ServerApp.class),
    CLIENT(ClientApp.class);

    private final Class<? extends Application> aClass;
    private final static Map<Class<? extends Application>, GuildType> CLASS_APPLICATION_TYPE_MAP = new HashMap<>();
    private final static Map<String, GuildType> STRING_APPLICATION_TYPE_MAP = new HashMap<>();

    static {
        for (GuildType applicationType : GuildType.values()) {
            CLASS_APPLICATION_TYPE_MAP.put(applicationType.getApplicationClass(), applicationType);
            STRING_APPLICATION_TYPE_MAP.put(applicationType.name(), applicationType);
        }
    }

    GuildType(Class<? extends Application> aClass) {
        this.aClass = aClass;
    }

    public Class<? extends Application> getApplicationClass() {
        return aClass;
    }

    public static GuildType getApplicationType(Class<? extends Application> applicationClass){
        return CLASS_APPLICATION_TYPE_MAP.get(applicationClass);
    }


}
