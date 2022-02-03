package lt.karolis.demo.TaskManagementSystem.persistance;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;
import java.util.stream.Stream;

@Converter (autoApply = true)
public class PriorityConverter implements AttributeConverter<Priority, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Priority attribute) {
        return Optional.ofNullable(attribute)
                .map(Priority::getPriorityNo)
                .orElse(null);
    }

    @Override
    public Priority convertToEntityAttribute(Integer dbData) {
        return Stream.of(Priority.values())
                .filter(a->a.getPriorityNo() == (dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
