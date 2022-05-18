package org.harryng.demo.quarkus.base.persistence;

import io.quarkus.hibernate.orm.PersistenceUnit;
import org.harryng.demo.quarkus.base.entity.BaseEntity;

import java.io.Serializable;

@PersistenceUnit("primary_pu")
public class CommonPersistence<Id extends Serializable, T extends BaseEntity<Id>> implements BasePersistence<Id, T> {
}
