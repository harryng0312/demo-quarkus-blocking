package org.harryng.demo.quarkus.util.persistence;

public class PersistenceUtil {
//    public static long countObjectByQuery(
//            EntityManager entityManager,
//            String countJpql,
//            Map<String, Serializable> params
//    ) throws RuntimeException, Exception {
//        var typedQuery = entityManager.createQuery(countJpql, Long.class);
//        if (params != null) {
//            for (var item : params.entrySet()) {
//                typedQuery.setParameter(item.getKey(), item.getValue());
//            }
//        }
//        return typedQuery.getResultList().size() > 0 ? typedQuery.getResultList().get(0) : 0L;
//    }

//    public static <T> PagedResult<T> selectObjectByQuery(
//            EntityManager entityManager,
//            Class<T> returnType,
//            String queryJpql,
//            Map<String, Serializable> params,
//            PageInfo pageInfo,
//            long total,
//            LockModeType lockModeType
//    ) throws RuntimeException, Exception {
//        var typedQuery = entityManager.createQuery(queryJpql, returnType);
//        typedQuery.setLockMode(lockModeType);
//        typedQuery.setFirstResult(pageInfo.getPageNumber() * pageInfo.getPageSize());
//        typedQuery.setMaxResults(pageInfo.getPageSize());
//        for (var item : params.entrySet()) {
//            typedQuery.setParameter(item.getKey(), item.getValue());
//        }
//        var resultList = typedQuery.getResultList();
//        long size = total >= 0 ? total : resultList.size();
//        return new PagedResult(resultList, pageInfo, size);
//    }
}