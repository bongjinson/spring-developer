package me.sbj.springdev;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloRepository extends JpaRepository<Member, Long> {
}
