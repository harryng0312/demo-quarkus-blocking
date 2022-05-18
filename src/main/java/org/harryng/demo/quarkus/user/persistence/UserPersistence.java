package org.harryng.demo.quarkus.user.persistence;

import org.harryng.demo.quarkus.base.persistence.BasePersistence;
import org.harryng.demo.quarkus.user.entity.UserImpl;

import javax.inject.Singleton;

@Singleton
public class UserPersistence implements BasePersistence<Long, UserImpl> {
}
