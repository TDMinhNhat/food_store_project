package dev.skyherobrine.backend.controllers.admins;

import org.springframework.http.ResponseEntity;

import java.util.*;

public interface IManagement<S, P> {
    ResponseEntity<Object> add(S s) throws Exception;

    ResponseEntity<Object> addMany(List<S> list) throws Exception;

    ResponseEntity<Object> update(P p, S s) throws Exception;

    ResponseEntity<Object> delete(P p) throws Exception;

    ResponseEntity<Object> getById(P p) throws Exception;

    ResponseEntity<Object> getAll() throws Exception;

    ResponseEntity<Object> getAllByPage(Integer page, Integer size) throws Exception;
}
