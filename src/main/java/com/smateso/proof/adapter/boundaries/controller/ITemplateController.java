/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package com.smateso.proof.adapter.boundaries.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.Template;
import edu.kit.iai.webis.proofmodels.dto.Workflow;
import edu.kit.iai.webis.proofmodels.paging.TemplatePagingModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.kit.iai.webis.proofmodels.utils.Views.Detail;
import static edu.kit.iai.webis.proofmodels.utils.Views.Listing;
import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Template Controller")
@RestController
@RequestMapping("/v1/template")
public interface ITemplateController {

    @JsonView(Listing.class)
    @Operation(
            summary = "Retrieve templates",
            description = "Retrieve templates",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @PostMapping(value = "search", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<TemplatePagingModel> searchTemplates(@RequestBody final Request request);

    @JsonView(Detail.class)
    @Operation(
            summary = "Retrieve templates",
            description = "Retrieve templates",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @GetMapping(consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Template>> getTemplates();

    @JsonView(Detail.class)
    @Operation(
            summary = "Export templates",
            description = "Export templates",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @GetMapping(value = "export", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Template>> exportTemplates();

    @JsonView(Detail.class)
    @Operation(
            summary = "Retrieve template",
            description = "Retrieve template",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @GetMapping(value = "{id}", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Template> getTemplate(@PathVariable(value = "id") final String id);

    @JsonView(Detail.class)
    @Operation(
            summary = "Create template",
            description = "Create template",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Create successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Create incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Template> createTemplate(@RequestBody final Template template, final Authentication authentication, @Nullable @RequestParam(required = false, defaultValue = "0") final String sessionKey);

    @JsonView(Detail.class)
    @Operation(
            summary = "Update template",
            description = "Update template",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Update successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Update incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @PostMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Template> updateTemplate(@PathVariable(value = "id") final String id, @RequestBody final Template template, final Authentication authentication, @Nullable @RequestParam(required = false, defaultValue = "0") final String sessionKey);

    @JsonView(Detail.class)
    @Operation(
            summary = "Delete template",
            description = "Delete template",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Delete successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Delete incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @DeleteMapping(value = "{id}", consumes = ALL_VALUE, produces = ALL_VALUE)
    ResponseEntity<Void> deleteTemplate(@PathVariable(value = "id") final String id, final Authentication authentication, @Nullable @RequestParam(required = false, defaultValue = "0") final String sessionKey);

    @JsonView(Detail.class)
    @Operation(
            summary = "Retrieve workflows using template",
            description = "Retrieve workflows using template",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @GetMapping(value = "{id}/workflows", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Workflow>> getWorkflowsForTemplate(@PathVariable final String id);

}
