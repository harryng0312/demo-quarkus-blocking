package org.harryng.demo.quarkus.user.mapper;

import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import org.harryng.demo.quarkus.user.dto.User;
import org.harryng.demo.quarkus.user.entity.UserImpl;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-18T17:48:39+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 11.0.15 (Oracle Corporation)"
)
@ApplicationScoped
public class UserMapperImpl implements UserMapper {

    @Override
    public UserImpl mapDtoToEntity(User dto) {
        if ( dto == null ) {
            return null;
        }

        UserImpl userImpl = new UserImpl();

        userImpl.setUsername( dto.getUsername() );
        userImpl.setPassword( dto.getPassword() );
        userImpl.setScreenName( dto.getScreenName() );
        userImpl.setDob( dto.getDob() );

        return userImpl;
    }

    @Override
    public User mapEntityToDto(UserImpl entity) {
        if ( entity == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( entity.getUsername() );
        user.setPassword( entity.getPassword() );
        user.setScreenName( entity.getScreenName() );
        user.setDob( entity.getDob() );

        return user;
    }

    @Override
    public void populateEntity(UserImpl src, UserImpl dest) {
        if ( src == null ) {
            return;
        }

        dest.setCreatedDate( src.getCreatedDate() );
        dest.setModifiedDate( src.getModifiedDate() );
        dest.setStatus( src.getStatus() );
        dest.setUsername( src.getUsername() );
        dest.setPassword( src.getPassword() );
        dest.setScreenName( src.getScreenName() );
        dest.setDob( src.getDob() );
        dest.setPasswdEncryptedMethod( src.getPasswdEncryptedMethod() );
    }
}
