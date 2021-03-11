package com.plohoy.generator.controller;

import com.plohoy.generator.service.SourceService;
import com.plohoy.generator.view.request.SourceRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code")
@RequiredArgsConstructor
public class SourceController {
    private final SourceService service;

    @PostMapping(value = "/generate")
    @Operation(summary = "Generate Java code by custom entities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Code was successfully generated"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public ResponseEntity<String> buildSource(
            @RequestBody
//            @io.swagger.v3.oas.annotations.parameters.RequestBody(
//                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = {
//                            @ExampleObject(name = "author", value = "Alex Plohoy"),
//                            @ExampleObject(name = "sourcePath", value = "C://generated/"),
//                            @ExampleObject(name = "groupName", value = "com.plohoy"),
//                            @ExampleObject(name = "artifactName", value = "null"),
//                            @ExampleObject(name = "description", value = "null"),
//                            @ExampleObject(name = "entities", value = "null"),
//                            @ExampleObject(name = "tools", value = "null"),
//                            @ExampleObject(name = "jdkVersion", value = "11"),
//                            @ExampleObject(name = "architecture", value = "REST_SIMPLE"),
//                            @ExampleObject(name = "dtoModuleExists", value = "true"),
//                            @ExampleObject(name = "archive", value = "false")
//                        }
//                    ))

                    SourceRequest request
    ) {
        return service.buildSource(request);
    }
}
