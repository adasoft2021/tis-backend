package com.adasoft.tis.core.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface TisRepository<Entity, ID extends Serializable> {
    /**
     * Verifica si la entidad existe recibiendo el id
     *
     * @param id identificador de la entidad
     * @return TRUE si existe y FALSE sino existe
     */
    boolean exists(ID id);

    /**
     * Se optiene la entidad por el id recibido. Sino existe entoces retorna {@link Optional} vacio.
     *
     * @param id identificador de la entidad
     * @return {@link Optional} de la entidad
     */
    Optional<Entity> findById(ID id);

    /**
     * Guarda nueva entidad a la base de datos basado en la entidad recibida
     *
     * @param entity la nueva entidad a guardar
     * @return la entidad guardada
     */
    Entity save(Entity entity);

    /**
     * Actualiza una entidad ya existente basado en la entidad recibida
     *
     * @param entity la entidad a actualizar
     * @return la entidad actualizada
     */
    Entity update(Entity entity);

    /**
     * Actualiza los registros existentes con toda la nueva información pasada en la colección como parámetro.
     *
     * @param entities las entidades con la nueva información.
     * @return las entidades actualizadas.
     */
    Collection<Entity> updateAll(Collection<Entity> entities);

    /**
     * Elimina una entidad existente
     *
     * @param entity la entidad a eliminar
     */
    void delete(Entity entity);

    /**
     * Elimina una entidad existente con el id recibido
     *
     * @param id identificador de la entidad a eliminar
     */
    void deleteById(ID id);

}
