package com.sussanacode.productioninventory;

import com.sussanacode.productioninventory.configuration.CategoryRepository;
import com.sussanacode.productioninventory.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CategoryRepoTest {

    @Autowired
    private CategoryRepository repo;

    @Test
    public void testCreateNewCategory() {
        Category savedCategory = repo.save(new Category("Kitchen Tools"));

        assertThat(savedCategory.getId()).isGreaterThan(0);
    }

}
