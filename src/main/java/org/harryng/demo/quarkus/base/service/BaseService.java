package org.harryng.demo.quarkus.base.service;

import org.harryng.demo.quarkus.base.entity.BaseEntity;
import org.harryng.demo.quarkus.base.persistence.BasePersistence;
import org.harryng.demo.quarkus.util.SessionHolder;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Map;

public interface BaseService<Id extends Serializable, T extends BaseEntity<Id>> {
    public static final String TRANS_SESSION = "transSession";
    public static final String TRANS_STATELESS_SESSION = "transStatelessSession";
    public static final String TRANSACTION = "transaction";
    public static final String HTTP_COOKIES = "cookies";
    public static final String HTTP_HEADERS = "headers";

    public BasePersistence<Id, T> getPersistence();

    public T getById(SessionHolder sessionHolder, Id id, Map<String, Object> extras) throws RuntimeException, Exception;
    public int add(SessionHolder sessionHolder, T obj, Map<String, Object> extras) throws RuntimeException, Exception;
    public int edit(SessionHolder sessionHolder, T obj, Map<String, Object> extras) throws RuntimeException, Exception;
    public int remove(SessionHolder sessionHolder, Id id, Map<String, Object> extras) throws RuntimeException, Exception;
}
