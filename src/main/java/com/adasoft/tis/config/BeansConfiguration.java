package com.adasoft.tis.config;

import com.adasoft.tis.domain.Qualification;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.dto.qualification.CreateQualificationDTO;
import com.adasoft.tis.dto.qualification.QualificationResponseDTO;
import com.adasoft.tis.dto.qualification.UpdateQualificationDTO;
import com.adasoft.tis.dto.review.CreateReviewDTO;
import com.adasoft.tis.dto.review.ReviewResponseDTO;
import com.adasoft.tis.dto.review.UpdateReviewDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    @Bean("proposalMapper")
    public ModelMapper proposalMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        return modelMapper;
    }

    @Bean("reviewMapper")
    public ModelMapper reviewMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        modelMapper.addMappings(new PropertyMap<Review, ReviewResponseDTO>() {
            @Override
            protected void configure() {
                skip(destination.getQualifications());
                map().setCreatedById(source.getCreatedBy());
            }
        });

        modelMapper.addMappings(new PropertyMap<CreateReviewDTO, Review>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                map().setCreatedBy(source.getCreatedById());
            }
        });

        modelMapper.addMappings(new PropertyMap<UpdateReviewDTO, Review>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getQualifications());
            }
        });

        return modelMapper;
    }

    @Bean("qualificationMapper")
    public ModelMapper qualificationMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        modelMapper.addMappings(new PropertyMap<Qualification, QualificationResponseDTO>() {
            @Override
            protected void configure() {
                map().setDescription(source.getBaseQualification().getDescription());
                map().setMaxScore(source.getBaseQualification().getMaxScore());
            }
        });

        modelMapper.addMappings(new PropertyMap<CreateQualificationDTO, Qualification>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getReview());
            }
        });

        modelMapper.addMappings(new PropertyMap<UpdateQualificationDTO, Qualification>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getReview());
            }
        });

        return modelMapper;
    }
}
