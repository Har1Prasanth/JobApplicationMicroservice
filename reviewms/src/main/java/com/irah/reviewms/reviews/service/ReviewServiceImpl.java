package com.irah.reviewms.reviews.service;


import com.irah.reviewms.reviews.entity.Review;
import com.irah.reviewms.reviews.mapper.ReviewMapper;
import com.irah.reviewms.reviews.model.ReviewDto;
import com.irah.reviewms.reviews.repository.ReviewRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepo reviewRepo;

    @Override
    public List<ReviewDto> getAllReviews(Long CompanyId) {

        List<Review> reviews = reviewRepo.findByCompanyId(CompanyId);
        return reviews
                .stream()
                .map(review -> ReviewMapper.mapToReviewDto(review))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteReviewById( Long reviewId) {
        Review review=reviewRepo.findById(reviewId).orElse(null);

        if(review!=null){
            reviewRepo.deleteById(reviewId);
            return true;

        }
        return false;
    }

    @Override
    public boolean updateReview(Long reviewId, ReviewDto updatedReviewDto) {
        Review review=reviewRepo.findById(reviewId).orElse(null);
        if (review!=null) {
            review.setTitle(updatedReviewDto.getTitle());
            review.setDescription(updatedReviewDto.getDescription());
            review.setRating(updatedReviewDto.getRating());
            review.setCompanyId(updatedReviewDto.getCompanyId());
            reviewRepo.save(review);
            return true;

        }
        return false;
    }

    @Override
    public ReviewDto getReviewById(Long reviewId) {
        Review review=reviewRepo.findById(reviewId).orElse(null);
        return ReviewMapper.mapToReviewDto(review);

    }

    @Override
    public boolean addReview(Long companyId, ReviewDto reviewDto) {
        if (companyId!=null && reviewDto!=null) {
            reviewDto.setCompanyId(companyId);
            reviewRepo.save(ReviewMapper.mapToReview(reviewDto));
            return true;
        }
        return false;
    }
//
//    @Override
//    public List<CompanyDto> getAllCompanies() {
//        List<Company> companies= companyRepo.findAll();
//
//        return companies
//                .stream()
//                .map(company -> CompanyMapper.mapToCompanyDto(company))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Optional<CompanyDto> updateCompany(Long companyId, CompanyDto companyDto) {
//
//        Optional<Company> company = companyRepo.findById(companyId);
//        if (company.isPresent()) {
//            Company existingCompany = company.get();
//            existingCompany.setDescription(companyDto.getDescription());
//            existingCompany.setName(companyDto.getName());
//            existingCompany.setJobs(companyDto.getJobs());
//            companyRepo.save(existingCompany);
//            return Optional.of(CompanyMapper.mapToCompanyDto(existingCompany));
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public CompanyDto addCompany(CompanyDto companyDto) {
//        Company company=companyRepo.save(CompanyMapper.mapToCompany(companyDto));
//        return CompanyMapper.mapToCompanyDto(company);
//    }
//
//    @Override
//    public boolean deleteCompanyById(Long companyId) {
//        if(companyRepo.existsById(companyId)){
//            companyRepo.deleteById(companyId);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Optional<CompanyDto> findCompanyById(Long companyId) {
//        Optional<Company> company = companyRepo.findById(companyId);
//
//        if(company.isPresent())
//        {
//             return Optional.of(CompanyMapper.mapToCompanyDto(company.get()));
//        }
//        return Optional.empty();
//    }


}
