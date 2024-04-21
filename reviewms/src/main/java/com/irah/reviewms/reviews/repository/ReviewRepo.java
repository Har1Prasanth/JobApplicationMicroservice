package com.irah.reviewms.reviews.repository;

import com.irah.reviewms.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    List<Review> findByCompanyId(Long companyId);
}
