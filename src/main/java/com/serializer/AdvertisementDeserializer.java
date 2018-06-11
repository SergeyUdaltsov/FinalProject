package com.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdvertisementDeserializer extends StdDeserializer<LocalDate> {

    public AdvertisementDeserializer() {
        this(null);
    }

    public AdvertisementDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {

        String date = parser.getText();

        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return LocalDate.parse(date);
        }catch (Exception e){
            return null;
        }
    }


}
