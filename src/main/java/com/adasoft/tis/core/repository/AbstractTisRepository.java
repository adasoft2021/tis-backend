package com.adasoft.tis.core.repository;

import com.adasoft.tis.core.domain.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public abstract class AbstractTisRepository<
    Entity extends BaseEntity<? extends Serializable>, ID extends Serializable>
    implements TisRepository<Entity, ID>{
    @PersistenceContext
    protected EntityManager entityManager;
    private Class<Entity> entityClass;

    protected AbstractTisRepository(EntityManager em, Class<Entity> clazz) {
        entityManager = em;
        entityClass = clazz;
    }

    /**
     * Verifica si la entidad existe recibiendo el id
     *
     * @param id identificador de la entidad
     * @return TRUE si existe y FALSE sino existe
     */
    @Override
    public boolean exists(final ID id) {
        var query = String.format("SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END " +
            "FROM %s e " +
            "WHERE e.id = :id ", entityClass.getSimpleName());
        return entityManager.createQuery(query, Boolean.class)
            .setParameter("id", id).getSingleResult();
    }

    /**
     * Se optiene la entidad por el id recibido. Sino existe entoces retorna {@link Optional} vacio.
     *
     * @param id identificador de la entidad
     * @return {@link Optional} de la entidad
     */
    @Override
    public Optional<Entity> findById(final ID id) {
        Optional<Entity> response = Optional.empty();
        Entity foundEntity = entityManager.find(entityClass, id);
        if (foundEntity != null) {
            response = Optional.of(foundEntity);
        }
        return response;
    }

    /**
     * Guarda nueva entidad a la base de datos basado en la entidad recibida
     *
     * @param entity la nueva entidad a guardar
     * @return la entidad guardada
     */
    @Transactional
    @Override
    public Entity save(final Entity entity) {
        entityManager.persist(entity);
        return entity;
    }

    /**
     * Actualiza una entidad ya existente basado en la entidad recibida
     *
     * @param entity la entidad a actualizar
     * @return la entidad actualizada
     */
    @Transactional
    @Override
    public Entity update(final Entity entity) {
        return entityManager.merge(entity);
    }

    /**
     * Actualiza los registros existentes con toda la nueva información pasada en la colección como parámetro.
     *
     * @param entities las entidades con la nueva información.
     * @return las entidades actualizadas.
     */
    @Transactional
    @Override
    public Collection<Entity> updateAll(final Collection<Entity> entities) {
        try {
            for (Entity entity : entities) {
                entityManager.merge(entity);
            }
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            throw e;
        } finally {
            entityManager.close();
        }
        return entities;
    }

    /**
     * Elimina una entidad existente
     *
     * @param entity la entidad a eliminar
     */
    @Transactional
    @Override
    public void delete(final Entity entity) {
        entityManager.remove(entity);
    }

    /**
     * Elimina una entidad existente con el id recibido
     *
     * @param id identificador de la entidad a eliminar
     */
    @Transactional
    @Override
    public void deleteById(final ID id) {
        Entity foundEntity = entityManager.find(entityClass, id);
        if (foundEntity != null) {
            entityManager.remove(foundEntity);
        }
    }

}
