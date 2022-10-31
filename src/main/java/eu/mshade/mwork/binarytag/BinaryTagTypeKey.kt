package eu.mshade.mwork.binarytag

import kotlin.reflect.KClass

interface BinaryTagTypeKey {

    fun getIdentifier(): Int

    fun getClazz(): KClass<*>

    companion object {
        fun from(id: Int, clazz: KClass<*>) = DefaultBinaryTagTypeKey(id, clazz)
    }
}

class DefaultBinaryTagTypeKey(private val identifier: Int, private val clazz: KClass<*>) : BinaryTagTypeKey {

    override fun getIdentifier(): Int {
        return identifier
    }

    override fun getClazz(): KClass<*> {
        return clazz
    }

    override fun toString(): String {
        return "DefaultBinaryTagTypeKey(identifier=$identifier, clazz=$clazz)"
    }

}