package eu.mshade.mwork.binarytag

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException

interface BinaryTagBuffer {

    fun write(binaryTagDriver: BinaryTagDriver, outputStream: DataOutputStream, binaryTag: BinaryTag<*>)

    fun read(binaryTagDriver: BinaryTagDriver, inputStream: DataInputStream): BinaryTag<*>

}