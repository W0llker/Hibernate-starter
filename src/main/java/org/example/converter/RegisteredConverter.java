package org.example.converter;

import lombok.SneakyThrows;
import org.example.entity.Registered;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.util.Optional;

public class RegisteredConverter implements AttributeConverter<Registered, Date> {
    @Override
    public Date convertToDatabaseColumn(Registered registered) {
        return Optional.ofNullable(registered)
                .map(Registered::registrationDate)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public Registered convertToEntityAttribute(Date dbData) {
        return Optional.ofNullable(dbData)
                .map(Date::toLocalDate)
                .map(Registered::new)
                .orElse(null);
    }
}
