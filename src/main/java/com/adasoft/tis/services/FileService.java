package com.adasoft.tis.services;

import com.adasoft.tis.domain.File;
import com.adasoft.tis.dto.file.FileResponseDTO;
import com.adasoft.tis.dto.observation.ObservationResponseDTO;
import com.adasoft.tis.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FileService {

    private FileRepository fileRepository;
    private ModelMapper fileMapper;
    private ModelMapper observationMapper;

    public FileResponseDTO getDTO(File file, FileResponseDTO dto) {
        if (dto == null)
            dto = new FileResponseDTO();
        dto.setObservations(file.getObservations().stream()
            .map(observation -> observationMapper.map(observation, ObservationResponseDTO.class))
            .collect(Collectors.toList()));
        return dto;
    }
}
