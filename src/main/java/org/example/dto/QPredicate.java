package org.example.dto;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QPredicate {
    private List<Predicate> predicates;

    public static QPredicate builder() {
        return new QPredicate();
    }

    public <T> QPredicate add(T object, Function<T, Predicate> function) {
        if (object != null)
            predicates.add(function.apply(object));
        return this;
    }

    //для и
    public Predicate buildAnd() {
        return ExpressionUtils.allOf(predicates);
    }

    //для или
    public Predicate buildOr() {
        return ExpressionUtils.anyOf(predicates);
    }
}
