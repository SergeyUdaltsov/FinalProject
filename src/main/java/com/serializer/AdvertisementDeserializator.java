package com.serializer;

import com.domain.Advertisement;
import com.domain.Author;
import com.domain.Rubric;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class AdvertisementDeserializator extends StdDeserializer<Advertisement> {

    public AdvertisementDeserializator() {
        this(null);
    }

    public AdvertisementDeserializator(Class<?> vc) {
        super(vc);
    }

    @Override
    public Advertisement deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        Advertisement advertisement = new Advertisement();

        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        JsonNode temp = node.get("date");

        if (Objects.nonNull(temp)) {
            LocalDate date = LocalDate.parse(temp.asText());
            advertisement.setDate(date);
        }

        temp = node.get("id");

        if(Objects.nonNull(temp)){
            advertisement.setId(temp.asInt());
        }

        advertisement.setClosed(node.get("isClosed").asBoolean());
        advertisement.setTitle(node.get("title").asText());
        advertisement.setText(node.get("text").asText());
        advertisement.setPrice(node.get("price").asDouble());

        temp = node.get("rubric");
        Rubric rubric = new Rubric();
        rubric.setId(temp.get("id").asInt());

        advertisement.setRubric(rubric);

        temp = node.get("author");
        Author tempAuthor = new Author();
        tempAuthor.setId(temp.get("id").asInt());

        advertisement.setAuthor(tempAuthor);

        return advertisement;
    }


}
