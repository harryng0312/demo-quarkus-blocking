package org.harryng.demo.quarkus.base.service;

import io.vertx.mutiny.core.Vertx;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.harryng.demo.quarkus.base.entity.AbstractEntity;
import org.harryng.demo.quarkus.base.persistence.BasePersistence;
import org.harryng.demo.quarkus.util.SessionHolder;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.util.Map;

// @Transactional(Transactional.TxType.NOT_SUPPORTED)
public abstract class AbstractService<Id extends Serializable, T extends AbstractEntity<Id>> implements BaseService<Id, T> {

    private Class<T> entityClass;
    @Inject
    protected ManagedExecutor managedExecutor;

    @Inject
    protected ValidatorFactory validatorFactory;

    @Inject
    protected Vertx vertx;

    @Override
    public abstract BasePersistence<Id, T> getPersistence();

    public Class<T> getEntityClass() {
        return entityClass;
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public T getById(SessionHolder sessionHolder, Id id, Map<String, Object> extras) throws RuntimeException, Exception {
        // return Uni.createFrom().item(getPersistence().selectById(id));
        return getPersistence().findById(id);
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public int add(SessionHolder sessionHolder, T obj, Map<String, Object> extras) throws RuntimeException, Exception {
        getPersistence().persist(obj);
        return 1;
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public int edit(SessionHolder sessionHolder, T obj, Map<String, Object> extras) throws RuntimeException, Exception {
        // find by id
        // merge
        // persist
        getPersistence().persist(obj);
        return 1;
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public int remove(SessionHolder sessionHolder, Id id, Map<String, Object> extras) throws RuntimeException, Exception {
        var cb = getPersistence().getEntityManager().getCriteriaBuilder();
        var criteriaDelete = cb.createCriteriaDelete(getEntityClass());
        var root = criteriaDelete.from(getEntityClass());
        criteriaDelete.where(cb.equal(root.get("id"), id));
        var query = getPersistence().getEntityManager().createQuery(criteriaDelete);
        return query.executeUpdate();
    }
}
