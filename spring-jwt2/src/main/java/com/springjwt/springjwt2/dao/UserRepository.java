package com.springjwt.springjwt2.dao;

import com.springjwt.springjwt2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends   JpaRepository<User,Integer>{

       User  findByEmail(String email);

   Optional<User>  findUserByEmail(String email);
       User findUserById(int id);



}
