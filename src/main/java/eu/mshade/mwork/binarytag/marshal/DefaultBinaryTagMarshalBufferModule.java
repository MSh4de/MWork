package eu.mshade.mwork.binarytag.marshal;

public class DefaultBinaryTagMarshalBufferModule implements BinaryTagMarshalBufferModule{

    private BinaryTagMarshal binaryTagMarshal;
    private BinaryTagMarshalBuffer<?> binaryTagMarshalBuffer;
    public DefaultBinaryTagMarshalBufferModule(BinaryTagMarshalBuffer<?> binaryTagMarshalBuffer, BinaryTagMarshal binaryTagMarshal) {
        this.binaryTagMarshalBuffer = binaryTagMarshalBuffer;
        this.binaryTagMarshal = binaryTagMarshal;
    }

    @Override
    public BinaryTagMarshalBufferModule registerSubTypes(Class<?>... aClass) {
        for (Class<?> type : aClass) {
            binaryTagMarshal.registerAdaptor(type, binaryTagMarshalBuffer);
        }
        return this;
    }

}
