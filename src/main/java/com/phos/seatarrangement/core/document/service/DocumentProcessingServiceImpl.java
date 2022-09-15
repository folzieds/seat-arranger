package com.phos.seatarrangement.core.document.service;

import com.phos.seatarrangement.core.document.exception.DocumentNotFoundException;
import com.phos.seatarrangement.core.event.domain.Event;
import com.phos.seatarrangement.core.event.exception.EventNotFoundException;
import com.phos.seatarrangement.core.event.repository.EventRepository;
import com.phos.seatarrangement.core.exception.PlatformDataIntegrityException;
import com.phos.seatarrangement.core.guest.domain.Guest;
import com.phos.seatarrangement.core.guest.repository.GuestRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class DocumentProcessingServiceImpl implements DocumentProcessingService{

    private final EventRepository eventRepository;
    private final GuestRepository guestRepository;

    @Autowired
    public DocumentProcessingServiceImpl(EventRepository eventRepository, GuestRepository guestRepository) {
        this.eventRepository = eventRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public ResponseEntity processFile(String eventCode, MultipartFile file) {
        try{
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.rowIterator();

            List<Guest> guests = new ArrayList<>();

            Optional<Event> optionalEvent = eventRepository.findByEventCode(eventCode);

            if(optionalEvent.isEmpty()){
                throw new EventNotFoundException("error.msg.event.not.found",
                        String.format("The event with code %s was not found", eventCode));
            }

            Event event = optionalEvent.get();

            while(rows.hasNext()){

                Row row = rows.next();
                // skip the header
                if(row.getRowNum() == 0){
                    continue;
                }

                String firstName = row.getCell(0).getStringCellValue();
                String lastName = row.getCell(1).getStringCellValue();
                Double number = row.getCell(2).getNumericCellValue();

                String tableNumber = String.valueOf(number.intValue());

                if(!firstName.isEmpty() && !lastName.isEmpty()){
                    Guest guest = Guest.build(firstName,lastName,tableNumber,event);
                    guests.add(guest);
                }
            }

            guestRepository.saveAll(guests);

            return ResponseEntity.ok()
                    .body(Map.of("status","done",
                            "count",guests.size(),
                            "message","The file has been processed successfully"));
        }catch(IOException | IllegalStateException ex){
            throw new PlatformDataIntegrityException("error.msg.upload.document",
                    "An error occurred while processing the document.");
        }
    }

    @Override
    public ResponseEntity downloadTemplate(String eventCode) {
        try {

            File file = new File("samples/template.xlsx");
            String absolutePath = file.getAbsolutePath();
            Path path = Paths.get( absolutePath);
            Resource resource = new UrlResource(path.toUri());
            if(!resource.exists() || !resource.isReadable()) {
                throw new DocumentNotFoundException("error.msg.document.not.found",
                        "The template file could not be found");
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (MalformedURLException e) {
            throw new PlatformDataIntegrityException("error.msg.document.process",
                    "An error occurred while fetching the template file.");
        }
    }


}
