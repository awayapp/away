package com.awayapp.core.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMapper<E, D> {

    public abstract E toEntity(D dto);

    public abstract D toDto(E entity);

    public List<E> toEntities(List<D> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());

//        List<E> entities = new ArrayList<>();
//        for (D dto : dtos) {
//            entities.add(toEntity(dto));
//        }
//        return entities;
    }

    public List<D> toDtos(List<E> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
