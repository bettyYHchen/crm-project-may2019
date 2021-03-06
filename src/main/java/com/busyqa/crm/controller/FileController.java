package com.busyqa.crm.controller;

import com.busyqa.crm.message.response.UploadFileResponse;
import com.busyqa.crm.services.FileStorageService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile/{newFileName}")
    public UploadFileResponse uploadFile(@PathVariable("newFileName") String newFileName, @RequestParam("file") MultipartFile file) {

        String fileNameTmp = StringUtils.cleanPath(file.getOriginalFilename());
        String[] fileNameTmpList = fileNameTmp.split("\\.");
        String newName = newFileName + "." + fileNameTmpList[1];
        System.out.println(newName);
        String fileName = fileStorageService.storeFile(file, newName);


        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }


    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
//
//    @PostMapping("/uploadPaymentPlanAgreement/{email}")
//    public UploadFileResponse uploadPaymentPlanAgreement(@PathVariable("email") String email, @RequestParam("agreement") MultipartFile agreement) {
//
//        String fileNameTmp = StringUtils.cleanPath(agreement.getOriginalFilename());
//        String[] fileNameTmpList = fileNameTmp.split("\\.");
//        String emailSignature = email.split("\\.")[0];
//        String newFileName = "PaymentPlanAgreement" + "(" + emailSignature + ")" + "." + fileNameTmpList[1];
//        System.out.println(newFileName);
//        String agreementName = fileStorageService.storeFile(agreement, newFileName);
//
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadPaymentPlanAgreement/")
//                .path(agreementName)
//                .toUriString();
//
//        return new UploadFileResponse(agreementName, fileDownloadUri,
//                agreement.getContentType(), agreement.getSize());
//    }
//
//
//    @GetMapping("/downloadPaymentPlanAgreement/{fileName:.+}")
//    public ResponseEntity<Resource> downloadPaymentPlanAgreement(@PathVariable String fileName, HttpServletRequest request) {
//        // Load file as Resource
//        Resource resource = fileStorageService.loadFileAsResource(fileName);
//
//        // Try to determine file's content type
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
//        }
//
//        // Fallback to the default content type if type could not be determined
//        if (contentType == null) {
//            contentType = "application/octet-stream";
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }
}