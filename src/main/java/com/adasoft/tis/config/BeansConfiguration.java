package com.adasoft.tis.config;

import com.adasoft.tis.domain.Proposal;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.dto.proposal.CreateProposalDTO;
import com.adasoft.tis.dto.proposal.ProposalResponseDTO;
import com.adasoft.tis.dto.proposal.UpdateProposalDTO;
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

        modelMapper.addMappings(new PropertyMap<Proposal, ProposalResponseDTO>() {
            @Override
            protected void configure() {
                map().setCreatedById(source.getCreatedBy());
            }
        });

        modelMapper.addMappings(new PropertyMap<CreateProposalDTO, Proposal>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                map().setCreatedBy(source.getCreatedById());
            }
        });

        modelMapper.addMappings(new PropertyMap<UpdateProposalDTO, Proposal>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });

        return modelMapper;
    }

    @Bean("reviewMapper")
    public ModelMapper reviewMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        modelMapper.addMappings(new PropertyMap<Review, ReviewResponseDTO>() {
            @Override
            protected void configure() {
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
            }
        });

        return modelMapper;
    }
}
