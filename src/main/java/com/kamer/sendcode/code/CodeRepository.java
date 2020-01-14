package com.kamer.sendcode.code;

import com.kamer.sendcode.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on December, 2019
 *
 * @author kamer
 */
@Repository
public interface CodeRepository extends JpaRepository<Code, String> {

}
