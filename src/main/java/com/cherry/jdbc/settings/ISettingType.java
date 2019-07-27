package com.cherry.jdbc.settings;

import com.cherry.jdbc.serializer.BinarySerializer;

import java.io.IOException;

/**
 * 配置项类型
 */
public interface ISettingType {

    Object deserializeURL(String queryParameter);

    void serializeSetting(BinarySerializer serializer, Object value)throws IOException;

    ISettingType Int32 = new ISettingType() {
        @Override
        public Object deserializeURL(java.lang.String queryParameter) {
            return null;
        }

        @Override
        public void serializeSetting(BinarySerializer serializer, Object value) throws IOException {

        }
    };

    ISettingType String = new ISettingType() {
        public Object deserializeURL(java.lang.String queryParameter) {
            return queryParameter;
        }

        public void serializeSetting(BinarySerializer serializer, Object value) throws IOException {
            serializer.writeStringBinary(java.lang.String.valueOf(value));
        }
    };

}
