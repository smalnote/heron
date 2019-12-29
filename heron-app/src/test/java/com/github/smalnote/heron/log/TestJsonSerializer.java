package com.github.smalnote.heron.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestJsonSerializer {

    private static final Logger LOG = LogManager.getLogger();

    @Test
    public void testJsonSerializer() throws JsonProcessingException {
        WkeLogMessage msg = new WkeLogMessage();
        msg.setWkeCod("XXYYYZZZ");
        msg.setErrCod("XX00001");
        msg.setErrMsg("This is a test error message!");
        WkeLogMessageSerializer serializer = new WkeLogMessageSerializer();
        byte[] bytes = serializer.serialize(null, msg);
        LOG.info(new String(bytes, StandardCharsets.UTF_8));
        WkeLogMessageDeserializer deserializer = new WkeLogMessageDeserializer();
        WkeLogMessage deserializedMsg = deserializer.deserialize(null, bytes);
        assertThat("wkeCod must be equal", deserializedMsg.getWkeCod(), is(msg.getWkeCod()));
        assertThat("errCod must be equal", deserializedMsg.getErrCod(), is(msg.getErrCod()));
        assertThat("errMsg must be equal", deserializedMsg.getErrMsg(), is(msg.getErrMsg()));
    }

}

class WkeLogMessageSerializer implements Serializer<WkeLogMessage> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public byte[] serialize(String s, WkeLogMessage wkeLogMessage) {
        Map<String, String> map = new HashMap<>();
        map.put("wkeCod", wkeLogMessage.getWkeCod());
        map.put("errCod", wkeLogMessage.getErrCod());
        map.put("errMsg", wkeLogMessage.getErrMsg());
        try {
            return MAPPER.writeValueAsBytes(map);
        } catch (JsonProcessingException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}

class WkeLogMessageDeserializer implements Deserializer<WkeLogMessage> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public WkeLogMessage deserialize(String s, byte[] bytes) {
        try {
            Map<String, String> map = MAPPER.readValue(bytes, Map.class);
            WkeLogMessage msg = new WkeLogMessage();
            msg.setWkeCod(map.get("wkeCod"));
            msg.setErrCod(map.get("errCod"));
            msg.setErrMsg(map.get("errMsg"));
            return msg;
        } catch (IOException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
