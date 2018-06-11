package com.serializer;

import com.domain.Advertisement;
import com.domain.Author;
import com.domain.Rubric;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public class AdvertisementSerializer extends StdSerializer<Advertisement> {

    public static final DateTimeFormatter FORMATTER = ofPattern("yyyy-MM-dd");

    public AdvertisementSerializer(Class t) {
        super(t);
    }

    public AdvertisementSerializer() {
        this(null);
    }

    @Override
    public void serialize(Advertisement advertisement, JsonGenerator gen, SerializerProvider provider) throws IOException {

        Author author = advertisement.getAuthor();

        Rubric rubric = advertisement.getRubric();

//        LocalDate date = advertisement.getDate();

        gen.writeStartObject();

        gen.writeObjectField("id", advertisement.getId());
        gen.writeStringField("title", advertisement.getTitle());
        gen.writeStringField("text", advertisement.getText());
        gen.writeObjectField("date", advertisement.getDate());
        gen.writeObjectField("price", advertisement.getPrice());
        gen.writeObjectField("closed", advertisement.isClosed());


        gen.writeObjectField("rubricId", rubric.getId());
        gen.writeObjectField("rubricName", rubric.getName());


        gen.writeObjectField("authorId", author.getId());
        gen.writeObjectField("authorFirstName", author.getFirstName());
        gen.writeObjectField("authorLastName", author.getLastName());
        gen.writeObjectField("authorCity", author.getAddress().getCity());
        gen.writeObjectField("authorEmail", author.getEmail());
        gen.writeObjectField("authorPhones", author.getPhones());


        gen.writeEndObject();

    }


}
