package com.serializer;

import com.domain.Author;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class AuthorSerializer extends StdSerializer<Author> {


    public AuthorSerializer(Class<Author> t) {
        super(t);
    }

    public AuthorSerializer() {
        this(null);
    }

    @Override
    public void serialize(Author author, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();

            gen.writeObjectField("email", author.getEmail());
            gen.writeObjectField("password", author.getPassword());

        gen.writeEndObject();
    }
}
