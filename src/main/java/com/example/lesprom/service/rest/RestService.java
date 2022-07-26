package com.example.lesprom.service.rest;

import com.example.lesprom.entity.Employee;
import org.springframework.data.domain.Page;

/**
 * Интерфейс стандартного рест сервиса
 * @param <E> дженерик сущности, для которой будет реализовываться интерфейс
 */
public interface RestService<E> {

    /**
     * Метод получения списка сущностей
     *
     * @return список сущностей
     */
    Page<E> list(Integer pageNo, Integer pageSize, String sortBy);

    /**
     * Метод получения одной сущности по ID
     * @param id идентификатор сущности
     * @return найденная сущность
     */
    E getById(Long id);

    /**
     * Метод создания сущности в БД
     * @param item сущность
     * @return сохраненная сущность
     */
    E create(E item);

    /**
     * Метод обновления существующей в БД сущности
     * @param id идентификатор существующей в БД сущности
     * @param item сущность, которая обновит старую сущность в БД, id текущей сущности не учитывается
     * @return обновленная сущность
     */
    E update(Long id, E item);

    /**
     * Метод удаления сущности из БД
     * @param id идентификатор удаляемой сущности
     */
    void delete(Long id);

}
