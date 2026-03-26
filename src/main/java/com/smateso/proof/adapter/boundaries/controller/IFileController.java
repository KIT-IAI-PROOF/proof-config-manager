/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.boundaries.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static edu.kit.iai.webis.proofmodels.utils.Views.Detail;
import static org.springframework.http.MediaType.*;

@Tag(name = "File Controller")
@RestController
@RequestMapping("/v1/file")
public interface IFileController {

    @JsonView(Detail.class)
    @Operation(
            summary = "Upload file",
            description = "Upload file",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Upload successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Upload incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @PostMapping(value = "upload", name = "upload", consumes = MULTIPART_FORM_DATA_VALUE, produces = TEXT_PLAIN_VALUE)
    ResponseEntity<String> uploadFile(@RequestPart(value = "file") final MultipartFile file, @RequestParam(value = "type", defaultValue = "userdata", required = false) final String path);

    @JsonView(Detail.class)
    @Operation(
            summary = "Download file",
            description = "Download file",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Download successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Download incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @GetMapping(value = "download", name = "download", consumes = ALL_VALUE)
    ResponseEntity<?> downloadFile(@RequestParam(value = "path") final String path);

    @JsonView(Detail.class)
    @Operation(
            summary = "Download file",
            description = "Download file",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Download successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Download incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @GetMapping(value = "list", name = "list", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<String>> listFiles(@RequestParam(value = "type", defaultValue = "userdata", required = false) final String path);


}
