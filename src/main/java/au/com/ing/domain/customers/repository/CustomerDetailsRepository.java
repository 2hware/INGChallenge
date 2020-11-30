package au.com.ing.domain.customers.repository;

import au.com.ing.domain.customers.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerDetailsRepository extends CrudRepository<User,Long>{}
