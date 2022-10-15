package com.phos.seatarrangement.document.api;

import com.phos.seatarrangement.document.service.DocumentProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("event/{eventCode}/document")
public class DocumentApiResource {

    private final DocumentProcessingService documentProcessingService;

    @Autowired
    public DocumentApiResource(DocumentProcessingService documentProcessingService) {
        this.documentProcessingService = documentProcessingService;
    }

    @PostMapping("process")
    public ResponseEntity process(@PathVariable("eventCode") String eventCode, @RequestParam("file") MultipartFile file){

        return documentProcessingService.processFile(eventCode, file);
    }

    @GetMapping("download")
    public ResponseEntity downloadTemplate(@PathVariable("eventCode") String eventCode){
        return documentProcessingService.downloadTemplate(eventCode);
    }
}
