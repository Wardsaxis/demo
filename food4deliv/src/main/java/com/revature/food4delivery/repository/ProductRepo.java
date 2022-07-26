package com.revature.food4delivery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@RepositoryRestResource(collectionResourceRel = "products",path = "products")
@CrossOrigin(origins = "http://localhost:4200")
public interface ProductRepo extends CrudRepository<Product,Long>
{

}
