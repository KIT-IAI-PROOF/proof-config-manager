package com.smateso.proof.adapter.boundaries.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.WorkflowRequestDto;
import edu.kit.iai.webis.proofmodels.dto.WorkflowResponseDto;
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

import static edu.kit.iai.webis.proofmodels.utils.Views.Detail;
import static edu.kit.iai.webis.proofmodels.utils.Views.Listing;
import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Workflow Controller")
@RestController
@RequestMapping("/v1/workflow")
public interface IWorkflowController {

    @JsonView(Listing.class)
    @Operation(
            summary = "Retrieve workflows",
            description = "Retrieve workflows",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @PostMapping(value = "search", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<WorkflowResponseDto>> searchWorkflows(@RequestBody final Request request);

    @JsonView(Listing.class)
    @Operation(
            summary = "Retrieve workflows",
            description = "Retrieve workflows",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @GetMapping(consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<WorkflowResponseDto>> getWorkflows();

    @JsonView(Detail.class)
    @Operation(
            summary = "Retrieve workflow",
            description = "Retrieve workflow",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @GetMapping(value = "{id}", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<WorkflowResponseDto> getWorkflow(@PathVariable(value = "id") final String id);

    @JsonView(Detail.class)
    @Operation(
            summary = "Create workflow",
            description = "Create workflow",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Create successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Create incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<WorkflowResponseDto> createWorkflow(@RequestBody final WorkflowRequestDto workflow, @RequestParam final String sessionKey, final Authentication authentication);

    @JsonView(Detail.class)
    @Operation(
            summary = "Update workflow",
            description = "Update workflow",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Update successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Update incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @PostMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<WorkflowResponseDto> updateWorkflow(@PathVariable(value = "id") final String id, @RequestBody final WorkflowRequestDto workflow, @RequestParam final String sessionKey, final Authentication authentication);

    @JsonView(Detail.class)
    @Operation(
            summary = "Delete workflow",
            description = "Delete workflow",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Delete successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Delete incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @DeleteMapping(value = "{id}", consumes = ALL_VALUE, produces = ALL_VALUE)
    ResponseEntity<Void> deleteWorkflow(@PathVariable(value = "id") final String id, @RequestParam final String sessionKey, final Authentication authentication);

}
