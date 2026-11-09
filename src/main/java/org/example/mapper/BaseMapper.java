package org.example.mapper;

public interface BaseMapper<E, RESP, REQ> {
    RESP getRESP(E e);

    E getE(REQ r);
}
