package com.adasoft.tis.services;

import com.adasoft.tis.core.exceptions.DefaultTisDomainException;
import com.adasoft.tis.core.exceptions.EntityNotFoundException;
import com.adasoft.tis.domain.Adviser;
import com.adasoft.tis.domain.Company;
import com.adasoft.tis.domain.Review;
import com.adasoft.tis.domain.Space;
import com.adasoft.tis.dto.observation.ObservationResponseDTO;
import com.adasoft.tis.dto.qualification.QualificationResponseDTO;
import com.adasoft.tis.dto.qualification.UpdateQualificationDTO;
import com.adasoft.tis.dto.review.*;
import com.adasoft.tis.dto.space.SpaceCompactResponseDTO;
import com.adasoft.tis.dto.spaceAnswer.SpaceAnswerResponseDTO;
import com.adasoft.tis.repository.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.adasoft.tis.core.utils.Preconditions.checkArgument;
import static com.adasoft.tis.core.utils.Preconditions.checkUserId;

@AllArgsConstructor
@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private ModelMapper reviewMapper;

    private QualificationService qualificationService;
    private ModelMapper qualificationMapper;
    private AdviserRepository adviserRepository;
    private CompanyRepository companyRepository;
    private SpaceRepository spaceRepository;
    private ModelMapper observationMapper;
    private ModelMapper spaceMapper;
    private SpaceAnswerService spaceAnswersService;
    private SemesterRepository semesterRepository;

    private Collection<QualificationResponseDTO> updateQualifications(
        final Review review,
        final Collection<UpdateQualificationDTO> qualificationDTOS) {
        if (review.isPublished()) {
            throw new DefaultTisDomainException(
                HttpStatus.METHOD_NOT_ALLOWED,
                "Usted ya no puede hacer ningún cambio en la entidad Review.");
        }

        return qualificationService.updateAll(review, qualificationDTOS);
    }

    private ReviewResponseDTO updateReviewQualifications(
        final Review review,
        final Collection<UpdateQualificationDTO> qualificationDTOS) {
        Collection<QualificationResponseDTO> qualificationResponseDTOS =
            updateQualifications(review, qualificationDTOS);
        boolean notFull = qualificationResponseDTOS.stream().anyMatch(q -> q.getScore() != null);
        review.setStatus(notFull ? Review.Status.REVIEWED : Review.Status.QUALIFIED);
        reviewRepository.update(review);

        ReviewFilesResponseDTO responseDTO = reviewMapper.map(review, ReviewFilesResponseDTO.class);
        return getReviewResponseDTO(review, responseDTO);
    }

    public ReviewResponseDTO get(final Long reviewId) {
        checkArgument(reviewId != null, "El id de Review a actualizar no puede ser nulo.");

        Review foundReview = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new EntityNotFoundException(Review.class, reviewId));

        ReviewFilesResponseDTO responseDTO = reviewMapper.map(foundReview, ReviewFilesResponseDTO.class);

        return getReviewResponseDTO(foundReview, responseDTO);
    }

    private ReviewResponseDTO getReviewResponseDTO(Review foundReview, ReviewFilesResponseDTO responseDTO) {
        responseDTO.setAdviserName(String.format("%s %s",
            foundReview.getCreatedBy().getFirstName(),
            foundReview.getCreatedBy().getLastName()));
        Collection<QualificationResponseDTO> qualifications = foundReview.getQualifications()
            .stream().map(qualification -> qualificationMapper.map(qualification, QualificationResponseDTO.class))
            .collect(Collectors.toSet());
        responseDTO.setQualifications(new HashSet<>(qualifications));
        responseDTO.setObservations(foundReview.getObservations().stream()
            .map(observation -> observationMapper.map(observation, ObservationResponseDTO.class))
            .collect(Collectors.toSet()));
        responseDTO.setSpaces(foundReview.getSpaces().stream()
            .map(space -> spaceMapper.map(space, SpaceCompactResponseDTO.class))
            .collect(Collectors.toSet()));
        Collection<SpaceAnswerResponseDTO> spaceAnswers = new HashSet<>();
        for (Space space : foundReview.getSpaces()) {
            spaceAnswers.addAll(spaceAnswersService.getBySpaceIdAndCompanyId(
                space.getId(), foundReview.getCompany().getId()));
        }
        responseDTO.setSpaceAnswers(spaceAnswers);
        return responseDTO;
    }

    public ReviewResponseDTO create(final CreateReviewDTO reviewDTO) {
        checkArgument(reviewDTO != null, "El ReviewDTO a crear no puede ser nulo.");
        checkArgument(reviewDTO.getCompanyId() != null, "companyId no puede ser nulo.");

        Adviser foundAdviser = adviserRepository.findById(reviewDTO.getCreatedById())
            .orElseThrow(() -> new EntityNotFoundException(Adviser.class, reviewDTO.getCreatedById()));
        Company foundCompany = companyRepository.findById(reviewDTO.getCompanyId())
            .orElseThrow(() -> new EntityNotFoundException(Company.class, reviewDTO.getCompanyId()));
        Review defaultReview = reviewMapper.map(reviewDTO, Review.class);
        defaultReview.setCreatedBy(foundAdviser);
        defaultReview.setCompany(foundCompany);
        defaultReview.setStatus(Review.Status.UNREVIEWED);
        HashSet<Space> spaces = new HashSet<>();
        for (Long s : reviewDTO.getSpaces()) {
            Space foundSpace = spaceRepository.findById(s)
                .orElseThrow(() -> new EntityNotFoundException(Space.class, s));
            spaces.add(foundSpace);
        }
        defaultReview.setSpaces(spaces);
        Review persistedReview = reviewRepository.save(defaultReview);
        Collection<QualificationResponseDTO> qualificationResponseDTOS =
            qualificationService.createAll(persistedReview);
        ReviewFilesResponseDTO responseDTO = reviewMapper.map(persistedReview, ReviewFilesResponseDTO.class);
        getReviewResponseDTO(defaultReview, responseDTO);
        responseDTO.setQualifications(new HashSet<>(qualificationResponseDTOS));
        return responseDTO;
    }

    public ReviewResponseDTO update(
        final Long userId,
        final Long reviewId,
        final UpdateReviewDTO reviewDTO) {
        checkArgument(reviewId != null, "El id de Review a actualizar no puede ser nulo.");
        checkArgument(reviewDTO != null, "El ReviewDTO a actualizar no puede ser nulo.");

        Review foundReview = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new EntityNotFoundException(Review.class, reviewId));

        checkUserId(userId, foundReview.getCreatedBy().getId());

        reviewMapper.map(reviewDTO, foundReview);
        return updateReviewQualifications(foundReview, reviewDTO.getQualifications());
    }

    public Collection<ReviewCompactResponseDTO> getCompanyReviews(Long id) {
        checkArgument(id != null, "El id de Company no puede ser nulo.");

        companyRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(Company.class, id));

        Collection<Review> reviews = reviewRepository.findByCompany(id);

        return reviews.stream().map(review -> reviewMapper.map(review, ReviewCompactResponseDTO.class))
            .collect(Collectors.toSet());
    }

    public ReviewResponseDTO getCompanyReview(Long companyId, Long reviewId) {
        checkArgument(companyId != null, "El id de Company no puede ser nulo.");
        checkArgument(reviewId != null, "El id de Review no puede ser nulo.");
        Company foundCompany = companyRepository.findById(companyId)
            .orElseThrow(() -> new EntityNotFoundException(Company.class, companyId));
        Review foundReview = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new EntityNotFoundException(Review.class, reviewId));
        if (!foundReview.getCompany().equals(foundCompany) || !foundReview.isPublished())
            throw new EntityNotFoundException(Review.class, reviewId);
        ReviewFilesResponseDTO responseDTO = reviewMapper.map(foundReview, ReviewFilesResponseDTO.class);
        return getReviewResponseDTO(foundReview, responseDTO);
    }

    public ReviewResponseDTO publish(Long userId, Long id) {
        checkArgument(userId != null, "El id de Usuario no puede ser nulo.");
        checkArgument(id != null, "El id de Review no puede ser nulo.");
        Review foundReview = reviewRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(Review.class, id));
        checkUserId(userId, foundReview.getCreatedBy().getId());
        if (foundReview.isPublished()) {
            throw new DefaultTisDomainException(
                HttpStatus.METHOD_NOT_ALLOWED,
                "Usted ya no puede hacer ningún cambio en la entidad Review.");
        }
        foundReview.setStatus(Review.Status.CHANGE_ORDER);
        reviewRepository.save(foundReview);
        return getReviewResponseDTO(foundReview, reviewMapper.map(foundReview, ReviewFilesResponseDTO.class));
    }

    public Collection<ReviewCompactResponseDTO> getAdviserReviews(Long userId) {
        checkArgument(userId != null, "El ID de adviser no puede ser nulo");
        Adviser foundAdviser = adviserRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException(Adviser.class, userId));
        checkUserId(userId, foundAdviser.getId());
        Collection<Review> reviews = reviewRepository.findByAdviser(userId);
        return reviews.stream().map(review -> reviewMapper.map(review, ReviewCompactResponseDTO.class))
            .collect(Collectors.toSet());
    }

    public Collection<Collection<ReviewCompactResponseDTO>> getAdviserReviewsList(Long userId) {
        checkArgument(userId != null, "El ID de adviser no puede ser nulo");
        Adviser foundAdviser = adviserRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException(Adviser.class, userId));
        checkUserId(userId, foundAdviser.getId());
        Collection<Company> companies = companyRepository.getSemesterCompanies(
            semesterRepository.getNow().get().getSemester(),
            userId);
        Collection<Collection<ReviewCompactResponseDTO>> reviews = new HashSet<>();
        for (Company c : companies) {
            reviews.add(reviewRepository.findByCompanyAll(c.getId()).stream()
                .map(review -> reviewMapper.map(review, ReviewCompactResponseDTO.class))
                .collect(Collectors.toSet()));
        }
        return reviews;
    }

    public Collection<List<ReviewResponseDTO>> getProjectReviewsPublishedByStatus(final Long adviserId, final Long projectId) {
        Collection<List<ReviewResponseDTO>> reviewss = new HashSet<>();
        List<Review> reviews;
        for (Review.Status s : Review.Status.finalValues()) {
            reviews = reviewRepository.findByStatus(adviserId, projectId, s);
            if (!reviews.isEmpty())
                reviewss.add(reviews.stream()
                    .map(r -> reviewMapper.map(r, ReviewResponseDTO.class)).collect(Collectors.toList()));
        }
        return reviewss;
    }

    public Collection<ReviewResponseDTO> getCompanyReviewsExt(Long id) {
        checkArgument(id != null, "El id de Company no puede ser nulo.");

        companyRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(Company.class, id));

        Collection<Review> reviews = reviewRepository.findByCompany(id);

        return reviews.stream().map(review ->
                getReviewResponseDTO(review, reviewMapper.map(review, ReviewFilesResponseDTO.class)))
            .collect(Collectors.toSet());
    }
}
