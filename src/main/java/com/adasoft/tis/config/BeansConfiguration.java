package com.adasoft.tis.config;

import com.adasoft.tis.core.utils.CodeGenerator;
import com.adasoft.tis.domain.*;
import com.adasoft.tis.dto.adviser.AdviserResponseDTO;
import com.adasoft.tis.dto.adviser.CreateAdviserDTO;
import com.adasoft.tis.dto.adviser.UpdateAdviserDTO;
import com.adasoft.tis.dto.classCode.ClassCodeResponseDTO;
import com.adasoft.tis.dto.company.CompanyResponseDTO;
import com.adasoft.tis.dto.company.CreateCompanyDTO;
import com.adasoft.tis.dto.company.UpdateCompanyDTO;
import com.adasoft.tis.dto.observation.ObservationResponseDTO;
import com.adasoft.tis.dto.observation.UpdateObservationDTO;
import com.adasoft.tis.dto.proposal.CreateProposalDTO;
import com.adasoft.tis.dto.proposal.ProposalResponseDTO;
import com.adasoft.tis.dto.proposal.UpdateProposalDTO;
import com.adasoft.tis.dto.publication.CreatePublicationDTO;
import com.adasoft.tis.dto.publication.PublicationResponseDTO;
import com.adasoft.tis.dto.publication.UpdatePublicationDTO;
import com.adasoft.tis.dto.qualification.CreateQualificationDTO;
import com.adasoft.tis.dto.qualification.QualificationResponseDTO;
import com.adasoft.tis.dto.qualification.UpdateQualificationDTO;
import com.adasoft.tis.dto.review.*;
import com.adasoft.tis.dto.semester.SemesterResponseDTO;
import com.adasoft.tis.dto.space.CreateSpaceDTO;
import com.adasoft.tis.dto.space.SpaceCompactResponseDTO;
import com.adasoft.tis.dto.space.SpaceResponseDTO;
import com.adasoft.tis.dto.spaceAnswer.CreateSpaceAnswerDTO;
import com.adasoft.tis.dto.spaceAnswer.SpaceAnswerResponseDTO;
import com.adasoft.tis.dto.user.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class BeansConfiguration {
    @Bean("reviewMapper")
    public ModelMapper reviewMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        modelMapper.addMappings(new PropertyMap<Review, ReviewResponseDTO>() {
            @Override
            protected void configure() {
                skip(destination.getQualifications());
                skip(destination.getObservations());
                skip(destination.getSpaces());
                map().setCompanyName(source.getCompany().getName());


            }
        });
        modelMapper.addMappings(new PropertyMap<Review, ReviewFilesResponseDTO>() {
            @Override
            protected void configure() {
                skip(destination.getQualifications());
                skip(destination.getObservations());
                skip(destination.getSpaces());
                skip(destination.getSpaceAnswers());
                map().setCompanyName(source.getCompany().getName());

            }
        });

        modelMapper.addMappings(new PropertyMap<Review, ReviewCompactResponseDTO>() {
            @Override
            protected void configure() {

            }
        });

        modelMapper.addMappings(new PropertyMap<CreateReviewDTO, Review>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getCompany());
                skip(destination.getCreatedBy());
                skip(destination.getQualifications());
                skip(destination.getObservations());
                skip(destination.getSpaces());
                map().setPublished(false);
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

    @Bean("proposalMapper")
    public ModelMapper proposalMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        modelMapper.addMappings(new PropertyMap<Proposal, ProposalResponseDTO>() {
            @Override
            protected void configure() {

                map().setCreatedById(source.getCreatedBy());
                map().setReviewId(source.getReview().getId());
                map().setAdviserId(source.getAdviser());
            }
        });

        modelMapper.addMappings(new PropertyMap<CreateProposalDTO, Proposal>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                map().setCreatedBy(source.getCreatedById());
                map().setAdviser(source.getAdviserId());
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

    @Bean("observationMapper")
    public ModelMapper observationMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        modelMapper.addMappings(new PropertyMap<Observation, ObservationResponseDTO>() {
            @Override
            protected void configure() {
                map().setReviewId(source.getReview().getId());
            }
        });


        modelMapper.addMappings(new PropertyMap<UpdateObservationDTO, Observation>() {
            @Override
            protected void configure() {
                skip(destination.getId());

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

    @Bean("adviserMapper")
    public ModelMapper adviserMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<Adviser, AdviserResponseDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setFisrtName(source.getFirstName());
                map().setLastName(source.getLastName());

            }
        });
        modelMapper.addMappings(new PropertyMap<CreateAdviserDTO, Adviser>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                map().setFirstName(source.getFirstName());
                map().setLastName(source.getLastName());
            }
        });
        modelMapper.addMappings(new PropertyMap<UpdateAdviserDTO, Adviser>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
        return modelMapper;
    }

    @Bean("companyMapper")
    public ModelMapper companyMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<Company, CompanyResponseDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());

            }
        });
        modelMapper.addMappings(new PropertyMap<CreateCompanyDTO, Company>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getAdviser());
            }
        });
        modelMapper.addMappings(new PropertyMap<UpdateCompanyDTO, Company>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getAdviser());
            }
        });
        modelMapper.addMappings(new PropertyMap<Company, UserResponseDTO>() {


            @Override
            protected void configure() {
                skip(destination.getToken());
                skip(destination.getUserType());
                map().setUserName(source.getName());

            }
        });

        return modelMapper;
    }

    @Bean("publicationMapper")
    public ModelMapper publicationMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        modelMapper.addMappings(new PropertyMap<CreatePublicationDTO, Publication>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getSemester());
            }
        });

        modelMapper.addMappings(new PropertyMap<UpdatePublicationDTO, Publication>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getSemester());
            }
        });

        modelMapper.addMappings(new PropertyMap<Publication, PublicationResponseDTO>() {
            @Override
            protected void configure() {
                map().setSemester(source.getSemester().getSemester());
            }
        });

        return modelMapper;
    }

    @Bean("classCodeMapper")
    public ModelMapper classCodeMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<ClassCode, ClassCodeResponseDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
            }
        });
        return modelMapper;
    }

    @Bean("spaceAnswerMapper")
    public ModelMapper spaceAnswerMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<SpaceAnswer, SpaceAnswerResponseDTO>() {
            @Override
            protected void configure() {
                map().setCompanyName(source.getCreatedBy().getName());
            }
        });
        modelMapper.addMappings(new PropertyMap<CreateSpaceAnswerDTO, SpaceAnswer>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });

        return modelMapper;
    }

    @Bean("spaceMapper")
    public ModelMapper spaceMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<Space, SpaceResponseDTO>() {
            @Override
            protected void configure() {
                map().setProjectId(source.getProject().getId());
            }
        });
        modelMapper.addMappings(new PropertyMap<Space, SpaceCompactResponseDTO>() {
            @Override
            protected void configure() {
                map().setProjectId(source.getProject().getId());
            }
        });
        modelMapper.addMappings(new PropertyMap<CreateSpaceDTO, Space>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getProject());
                skip(destination.getCreatedBy());
            }
        });

        return modelMapper;
    }

    @Bean("semesterMapper")
    public ModelMapper semesterMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<Semester, SemesterResponseDTO>() {
            @Override
            protected void configure() {
            }
        });
        return modelMapper;
    }


    @Bean("codeGenerator")
    @Scope("singleton")
    public CodeGenerator codeGenerator() {
        return new CodeGenerator();
    }
}
