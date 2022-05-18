package org.harryng.demo.quarkus.user.persistence;

import org.harryng.demo.quarkus.base.persistence.BasePersistence;
import org.harryng.demo.quarkus.user.entity.UserImpl;

public interface UserPersistence extends BasePersistence<Long, UserImpl> {
}
