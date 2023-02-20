package com.apistone.nextflix.repository;

import com.apistone.nextflix.entity.Zipcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Project: nextflix
 * Package: com.apistone.nextflix.repository
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 1/21/2023
 * Time: 11:55 AM
 * <p>
 * Use: Provide CRUD operation regarding zipcode.
 */

@Repository
public interface ZipcodeRepository extends JpaRepository<Zipcode, Long> {

    /**
     * Get {@link com.apistone.nextflix.entity.Zipcode} by zipcode.
     * @param zipcode zipcode of particular area.
     * @return {@link java.util.Optional} object having {@link com.apistone.nextflix.entity.Zipcode}.
     */
    Optional<Zipcode> findByZipcode(int zipcode);

}
