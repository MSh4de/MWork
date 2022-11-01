package eu.mshade.mwork.binarytag

import kotlin.reflect.KClass

interface BinaryTagKey {

    fun getIdentifier(): Int

    fun getName(): String

    fun getClazz(): KClass<*>

    companion object {
        fun from(id: Int, name: String, clazz: KClass<*>) = DefaultBinaryTagKey(id, name, clazz)
    }
}

class DefaultBinaryTagKey(private val identifier: Int, private val name: String, private val clazz: KClass<*>) : BinaryTagKey {

    override fun getIdentifier(): Int {
        return identifier
    }

    override fun getName(): String {
        return name
    }

    override fun getClazz(): KClass<*> {
        return clazz
    }

    override fun toString(): String {
        return "DefaultBinaryTagKey(identifier=$identifier, clazz=$clazz)"
    }

}