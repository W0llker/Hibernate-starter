package org.example;

import lombok.SneakyThrows;
import org.example.entity.Customers;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;


class HibernateRunnerTest {
//    @Test
//    @SneakyThrows
//    /*
//    Так работает Hibernate
//     */
//    public void testHibernateAPI() {
//        var customers = Customers.builder()
//                .fullName("Машка Сашка")
//                .registeredAt(LocalDate.of(2024, 1, 12))
//                .city("Гомель")
//                .email("rekwl@gmail.com")
//                .id(19L)
//                .build();
//
//        var sql = """
//                insert into
//                %s
//                (%s)
//                values
//                (%s)
//                """;
//
//        var tableName = Optional.ofNullable(customers.getClass().getAnnotation(Table.class))
//                .map(table -> table.name())
//                .orElse(customers.getClass().getName());
//
//        Field[] fields = customers.getClass().getDeclaredFields();
//
//        var columnNames = Arrays.stream(fields)
//                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
//                        .map(Column::name)
//                        .orElse(field.getName())
//                ).collect(Collectors.joining(", "));
//
//        var columnValues = Arrays.stream(fields)
//                .map(field -> "?")
//                .collect(Collectors.joining(", "));
//
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
//                "postgres",
//                "admin");
//        PreparedStatement statement = connection
//                .prepareStatement(sql.formatted(tableName, columnNames, columnValues));
//
//        for (int i = 0; i < fields.length; i++) {
//            //для работы с приватными полями
//            fields[i].setAccessible(true);
//            statement.setObject(i + 1, fields[i].get(customers));
//        }
//        System.out.println(statement);
//        statement.executeUpdate();
//        statement.close();
//        connection.close();
//    }
}