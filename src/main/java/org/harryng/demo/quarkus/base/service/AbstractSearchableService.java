package org.harryng.demo.quarkus.base.service;

import io.quarkus.panache.common.Sort;
import org.harryng.demo.quarkus.base.entity.AbstractEntity;
import org.harryng.demo.quarkus.util.SessionHolder;
import org.harryng.demo.quarkus.util.page.PageInfo;
import org.harryng.demo.quarkus.util.page.PagedResult;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

// @Transactional(Transactional.TxType.NOT_SUPPORTED)
public abstract class AbstractSearchableService<Id extends Serializable, T extends AbstractEntity<Id>>
        extends AbstractService<Id, T> implements BaseSearchableService<Id, T> {

    @Override
    public long countByConditions(
            SessionHolder sessionHolder,
            String condition,
            Map<String, Object> params,
            Map<String, Object> extras
    ) throws RuntimeException, Exception {
        return getPersistence().count(condition, params);
    }

    @Override
    public List<T> findByConditions(
            SessionHolder sessionHolder,
            String condition,
            Sort sort,
            Map<String, Object> params,
            Map<String, Object> extras
    ) throws RuntimeException, Exception {
        // find
        return getPersistence().list(condition, sort, params);
    }
    @Override
    public PagedResult<T> findByConditionPaged(
            SessionHolder sessionHolder,
            String condition,
            PageInfo pageInfo,
            Map<String, Object> params,
            Map<String, Object> extras
    ) throws RuntimeException, Exception{
        var page = PagedResult.emptyPage();
        // count
        long count = countByConditions(sessionHolder, condition, params, extras);
        // find
        var query = getPersistence().find(condition, pageInfo.getSort(), params);
        var list = query.page(pageInfo).list();
        // load to page
        page = new PagedResult<>(list, pageInfo, count);
        return page;
    }
}
