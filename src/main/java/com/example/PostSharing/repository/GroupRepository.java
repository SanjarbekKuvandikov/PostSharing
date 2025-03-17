package com.example.PostSharing.repository;

import com.example.PostSharing.entity.GroupsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupsEntity, Long> {
}
