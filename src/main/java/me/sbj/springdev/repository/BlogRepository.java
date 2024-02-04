package me.sbj.springdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import me.sbj.springdev.domain.Article;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
