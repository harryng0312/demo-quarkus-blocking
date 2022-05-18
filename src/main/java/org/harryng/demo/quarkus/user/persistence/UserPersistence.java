package org.harryng.demo.quarkus.user.persistence;

import org.harryng.demo.quarkus.base.persistence.CommonPersistence;
import org.harryng.demo.quarkus.user.entity.UserImpl;

import javax.inject.Singleton;

@Singleton
public class UserPersistence extends CommonPersistence<Long, UserImpl> {
}
