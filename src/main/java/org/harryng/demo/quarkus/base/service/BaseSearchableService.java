package org.harryng.demo.quarkus.base.service;

import io.quarkus.panache.common.Sort;
import org.harryng.demo.quarkus.base.entity.BaseEntity;
import org.harryng.demo.quarkus.util.SessionHolder;
import org.harryng.demo.quarkus.util.page.PagedResult;
import org.harryng.demo.quarkus.util.page.PageInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseSearchableService<Id extends Serializable, T extends BaseEntity<Id>> extends BaseService<Id, T> {

    long countByConditions(
            SessionHolder sessionHolder,
            String condition,
            Map<String, Object> params,
            Map<String, Object> extras
    ) throws RuntimeException, Exception;

    List<T> findByConditions(
            SessionHolder sessionHolder,
            String condition,
            Sort sort,
            Map<String, Object> params,
            Map<String, Object> extras
    ) throws RuntimeException, Exception;

    PagedResult<T> findByConditionPaged(
            SessionHolder sessionHolder,
            String condition,
            PageInfo pageInfo,
            Map<String, Object> params,
            Map<String, Object> extras
    ) throws RuntimeException, Exception;
}
