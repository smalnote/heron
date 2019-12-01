package com.github.smalnote.heron.log;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;

@Plugin(name = "KafkaAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE)
public class KafkaAppender extends AbstractAppender {

    public KafkaAppender(final String name, final Filter filter, final Layout<? extends Serializable> layout,
                         final boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions, null);
    }

    @Override
    public void append(LogEvent event) {
        System.out.println(format(event));
    }

    private String format(LogEvent event) {
        WkeLogMessage wkeLog;
        if (event.getMessage() instanceof WkeLogMessage) {
            wkeLog = (WkeLogMessage) event.getMessage();
        } else {
            String message = event.getMessage().toString();
            wkeLog = new WkeLogMessage();
            if (event.getLevel().compareTo(Level.ERROR) >= 0) {
                wkeLog.setErrCod("SUC0000");
            }
            wkeLog.setWkeCod("XXYYYZZZ");
            wkeLog.setErrMsg(message);
        }
        return wkeLog.toString();
    }


    // Your custom appender needs to declare a factory method
    // annotated with `@PluginFactory`. Log4j will parse the configuration
    // and call this factory method to construct an appender instance with
    // the configured attributes.
    @PluginFactory
    public static KafkaAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginElement("Filter") final Filter filter,
            @PluginAttribute("otherAttribute") String otherAttribute) {
        if (name == null) {
            LOGGER.error("No name provided for MyCustomAppenderImpl");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new KafkaAppender(name, filter, layout, true);
    }

}
