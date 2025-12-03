package com.smateso.proof.adapter.boundaries.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.smateso.proof.adapter.model.Request;
import edu.kit.iai.webis.proofmodels.dto.Attachment;
import edu.kit.iai.webis.proofmodels.paging.AttachmentPagingModel;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static edu.kit.iai.webis.proofmodels.utils.Views.Detail;
import static edu.kit.iai.webis.proofmodels.utils.Views.Listing;
import static org.springframework.http.MediaType.*;

@Tag(name = "Attachment Controller")
@RestController
@RequestMapping("/v1/attachment")
public interface IAttachmentController {

    @JsonView(Listing.class)
    @Operation(
            summary = "Retrieve attachments",
            description = "Retrieve attachments",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @PostMapping(value = "search", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<AttachmentPagingModel> searchAttachments(@RequestBody final Request request);

    @JsonView(Listing.class)
    @Operation(
            summary = "Retrieve attachments",
            description = "Retrieve attachments",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @GetMapping(consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Attachment>> getAttachments();

    @JsonView(Detail.class)
    @Operation(
            summary = "Export attachments",
            description = "Export attachments",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @GetMapping(value = "export", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<Attachment>> exportAttachments();

    @JsonView(Detail.class)
    @Operation(
            summary = "Retrieve attachment",
            description = "Retrieve attachment",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Retrieval successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Retrieval incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @GetMapping(value = "{id}", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Attachment> getAttachment(@PathVariable(value = "id") final String id);

    @JsonView(Detail.class)
    @Operation(
            summary = "Create attachment",
            description = "Create attachment",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Create successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Create incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Attachment> createAttachment(@RequestBody final Attachment attachment, final Authentication authentication, @Nullable @RequestParam(required = false, defaultValue = "0") final String sessionKey);

    @JsonView(Detail.class)
    @Operation(
            summary = "Update attachment",
            description = "Update attachment",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Update successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Update incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @PostMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<Attachment> updateAttachment(@PathVariable(value = "id") final String id, @RequestBody final Attachment attachment, final Authentication authentication, @Nullable @RequestParam(required = false, defaultValue = "0") final String sessionKey);

    @JsonView(Detail.class)
    @Operation(
            summary = "Delete attachment",
            description = "Delete attachment",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Delete successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Delete incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @DeleteMapping(value = "{id}", consumes = ALL_VALUE, produces = ALL_VALUE)
    ResponseEntity<Void> deleteAttachment(@PathVariable(value = "id") final String id, final Authentication authentication, @Nullable @RequestParam(required = false, defaultValue = "0") final String sessionKey);

    @JsonView(Detail.class)
    @Operation(
            summary = "Upload attachment",
            description = "Upload attachment",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Upload successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Upload incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @PostMapping(value = "upload", name = "upload", consumes = MULTIPART_FORM_DATA_VALUE, produces = TEXT_PLAIN_VALUE)
    ResponseEntity<String> uploadFile(@RequestPart(value = "file") final MultipartFile file);

    @JsonView(Detail.class)
    @Operation(
            summary = "Download attachment",
            description = "Download attachment",
            security = @SecurityRequirement(name = "oauth2"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Download successful", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "500", description = "Download incomplete", content = @Content(schema = @Schema(hidden = true))),
            })
    @GetMapping(value = "download", name = "download", consumes = ALL_VALUE)
    ResponseEntity<?> downloadFile(@RequestParam(value = "path") final String path);

}
