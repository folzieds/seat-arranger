package com.phos.seatarrangement.document.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentProcessingService {

    ResponseEntity processFile(String eventCode, MultipartFile file);

    ResponseEntity downloadTemplate(String eventCode);
}
