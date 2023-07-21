package com.bp.cbe.service;

import java.util.List;

public interface GenericService<L, T> {


    List<T> getAll();

    T getById(L id);

    T save(T data);

    T update(L id, T data);

    void delete(L id);

}