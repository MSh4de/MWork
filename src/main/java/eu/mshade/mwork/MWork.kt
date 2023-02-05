package eu.mshade.mwork

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import eu.mshade.mwork.binarytag.BinaryTagDriver
import org.slf4j.LoggerFactory
import java.util.*

object MWork {

    private val objectMapper = ObjectMapper()
    private val binaryTagDriver = BinaryTagDriver()

    init {
        binaryTagDriver.registerMarshal(UUID::class.java, UUIDBinaryTagMarshal())

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        objectMapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
    }

    fun getBinaryTagDriver(): BinaryTagDriver {
        return binaryTagDriver
    }

    fun getObjectMapper(): ObjectMapper {
        return objectMapper
    }

}