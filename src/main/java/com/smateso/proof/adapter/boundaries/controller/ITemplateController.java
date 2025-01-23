package com.smateso.proof.adapter.boundaries.controller;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.TemplateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Template Controller")
@RestController
@RequestMapping("/v1/template")
public interface ITemplateController {

    @Operation(
            summary = "Retrieve templates",
            description = "Retrieve templates",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @PostMapping(value = "search", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<TemplateDto>> searchTemplates(@RequestBody final Request request);

    @Operation(
            summary = "Retrieve templates",
            description = "Retrieve templates",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @GetMapping(consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<TemplateDto>> getTemplates();

    @Operation(
            summary = "Retrieve template",
            description = "Retrieve template",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @GetMapping(value = "{id}", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<TemplateDto> getTemplate(@PathVariable(value = "id") final String id);

    @Operation(
            summary = "Create template",
            description = "Create template",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Create successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Create incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<TemplateDto> createTemplate(@RequestBody final TemplateDto template, @RequestParam final String sessionKey, final Authentication authentication);

    @Operation(
            summary = "Update template",
            description = "Update template",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Update successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Update incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @PostMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<TemplateDto> updateTemplate(@PathVariable(value = "id") final String id, @RequestBody final TemplateDto template, @RequestParam final String sessionKey, final Authentication authentication);

    @Operation(
            summary = "Delete template",
            description = "Delete template",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Delete successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Delete incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @DeleteMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteTemplate(@PathVariable(value = "id") final String id, @RequestParam final String sessionKey, final Authentication authentication);

}
