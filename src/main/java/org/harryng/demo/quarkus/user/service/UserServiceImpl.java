package org.harryng.demo.quarkus.user.service;

import io.smallrye.mutiny.Uni;
import org.harryng.demo.quarkus.base.service.AbstractSearchableService;
import org.harryng.demo.quarkus.interceptor.Authenticated;
import org.harryng.demo.quarkus.interceptor.Enriched;
import org.harryng.demo.quarkus.user.entity.UserImpl;
import org.harryng.demo.quarkus.user.mapper.UserMapper;
import org.harryng.demo.quarkus.user.persistence.UserPersistence;
import org.harryng.demo.quarkus.util.SessionHolder;
import org.harryng.demo.quarkus.util.page.PageInfo;
import org.harryng.demo.quarkus.util.page.Sort;
import org.harryng.demo.quarkus.validation.ValidationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Named("userService")
@Enriched
@Authenticated
// @Transactional(Transactional.TxType.NOT_SUPPORTED)
public class UserServiceImpl extends AbstractSearchableService<Long, UserImpl> implements UserService {

    static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    protected UserPersistence userPersistence;

    @Inject
    protected Validator validator;

    @Inject
    protected UserMapper userMapper;

    @Override
    public UserPersistence getPersistence() {
        return this.userPersistence;
    }

    @Override
    public UserImpl getById(SessionHolder sessionHolder, Long id, Map<String, Object> extras) throws RuntimeException, Exception {
//        var user = getPersistence().selectById(id);
        // var transSession = (Uni<Mutiny.Session>) extras.get(TRANS_SESSION);
//        var transSession = (Mutiny.StatelessSession) extras.get(TRANS_STATELESS_SESSION);
//         return transSession.flatMap(Unchecked.function(
        //     session -> getReactivePersistence().selectById(session, id)));
//        return transSession.get(getReactivePersistence().getEntityClass(), id);
        return getPersistence().findById(id);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public int add(SessionHolder sessionHolder, UserImpl user, Map<String, Object> extras) throws RuntimeException, Exception {
        return super.add(sessionHolder, user, extras);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public int edit(SessionHolder sessionHolder, UserImpl user, Map<String, Object> extras) throws RuntimeException, Exception {
        logger.info("edit user");
        var valRs = validator.validate(user);
        var validationResult = ValidationResult.getInstance(valRs, sessionHolder.getLocale());
        if (!validationResult.isSuccess()) {
            throw new Exception(validationResult.getMessagesInJson());
        }

        var oldUser = getById(sessionHolder, user.getId(), extras);
        getPersistence().getEntityManager().lock(oldUser, LockModeType.PESSIMISTIC_WRITE);
        userMapper.populateEntity(user, oldUser);
        oldUser.setModifiedDate(LocalDateTime.now());
        getPersistence().persist(oldUser);
        return 1;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public int remove(SessionHolder sessionHolder, Long id, Map<String, Object> extras) throws RuntimeException, Exception {
        return getPersistence().deleteById(id) ? 1 : 0;
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public Uni<UserImpl> getByUsername(SessionHolder sessionHolder, String username, Map<String, Object> extras) throws RuntimeException, Exception {
        UserImpl result = null;
        var pageInfo = new PageInfo(0, 5, 0, Sort.by(Sort.Direction.ASC, "id")); //PageRequest.of(0, 5, Sort.Direction.ASC, "id");
        var jpql = "select u from " + UserImpl.class.getCanonicalName() + " u where u.username = :username";
        var params = new HashMap<String, Object>();
        params.put("username", username);
        return getPersistence().find(jpql, params).singleResult();
    }
}
