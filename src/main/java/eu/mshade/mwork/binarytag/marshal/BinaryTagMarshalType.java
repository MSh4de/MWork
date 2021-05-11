package eu.mshade.mwork.binarytag.marshal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BinaryTagTypeAdaptor {

    Class<? extends BinaryTagAdaptor<?>> value();

}
