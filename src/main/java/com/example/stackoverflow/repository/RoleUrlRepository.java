package com.example.stackoverflow.repository;

import com.example.stackoverflow.model.RoleUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleUrlRepository extends JpaRepository<RoleUrl, Integer>, JpaSpecificationExecutor<RoleUrl> {

    @Query(value = "select u.url_id, ru.role_id, ru.created_time, ru.updated_time \n" +
            "from url u inner join role_url ru on u.url_id = ru.url_id\n" +
            "where ru.role_id = ?1 and u.\"action\" = ?2 and u.url = ?3 ", nativeQuery = true)
    List<RoleUrl> findByRoleIdAndActionAndUrl(int roleId, String action, String url);
}