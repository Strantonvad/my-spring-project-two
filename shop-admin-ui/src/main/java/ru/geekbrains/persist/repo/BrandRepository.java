package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
