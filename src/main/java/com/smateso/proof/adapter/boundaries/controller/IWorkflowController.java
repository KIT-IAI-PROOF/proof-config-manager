package com.smateso.proof.adapter.boundaries.controller;

import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.Block;
import edu.kit.iai.webis.proofmodels.Edge;
import edu.kit.iai.webis.proofmodels.Workflow;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Workflow Controller")
@RestController
@RequestMapping("/v1/workflow")
public interface IWorkflowController {

    @Operation(
            summary = "Retrieve workflows",
            description = "Retrieve workflows",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @PostMapping(value = "search", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Workflow>> searchWorkflows(@RequestBody final Request request);

    @Operation(
            summary = "Retrieve workflows",
            description = "Retrieve workflows",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @GetMapping(consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Workflow>> getWorkflows();

    @Operation(
            summary = "Retrieve workflow",
            description = "Retrieve workflow",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @GetMapping(value = "{id}", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Workflow> getWorkflow(@PathVariable(value = "id") final String id);

    @Operation(
            summary = "Create workflow",
            description = "Create workflow",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Create successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Create incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Workflow> createWorkflow(@RequestBody final Workflow workflow, @RequestParam final String sessionKey, final Authentication authentication);

    @Operation(
            summary = "Update workflow",
            description = "Update workflow",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Update successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Update incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @PostMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Workflow> updateWorkflow(@PathVariable(value = "id") final String id, @RequestBody final Workflow workflow, @RequestParam final String sessionKey, final Authentication authentication);

    @Operation(
            summary = "Delete workflow",
            description = "Delete workflow",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Delete successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Delete incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @DeleteMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteWorkflow(@PathVariable(value = "id") final String id, @RequestParam final String sessionKey, final Authentication authentication);

    @GetMapping(value = "{id}/blocks", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Block>> getBlocksByWorkflow(@PathVariable(value = "id") @NonNull final String id);

    @GetMapping(value = "{id}/edges", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Edge>> getEdgesByWorkflow(@PathVariable(value = "id") @NonNull final String id);

}
