package eu.mshade.mwork.binarytag.marshal;

import eu.mshade.mwork.binarytag.BinaryTagType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BinaryTagProperty {

    BinaryTagType value();

}
