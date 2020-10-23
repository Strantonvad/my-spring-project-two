package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}