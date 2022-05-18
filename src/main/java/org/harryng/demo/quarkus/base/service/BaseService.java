package org.harryng.demo.quarkus.base.service;

import org.harryng.demo.quarkus.base.entity.BaseEntity;
import org.harryng.demo.quarkus.base.persistence.BasePersistence;
import org.harryng.demo.quarkus.util.SessionHolder;

import java.io.Serializable;
import java.util.Map;

public interface BaseService<Id extends Serializable, T extends BaseEntity<Id>> {
    String TRANS_SESSION = "transSession";
    String TRANS_STATELESS_SESSION = "transStatelessSession";
    String TRANSACTION = "transaction";
    String HTTP_COOKIES = "cookies";
    String HTTP_HEADERS = "headers";

    BasePersistence<Id, T> getPersistence();

    T getById(SessionHolder sessionHolder, Id id, Map<String, Object> extras) throws RuntimeException, Exception;

    int add(SessionHolder sessionHolder, T obj, Map<String, Object> extras) throws RuntimeException, Exception;

    int edit(SessionHolder sessionHolder, T obj, Map<String, Object> extras) throws RuntimeException, Exception;

    int remove(SessionHolder sessionHolder, Id id, Map<String, Object> extras) throws RuntimeException, Exception;
}
