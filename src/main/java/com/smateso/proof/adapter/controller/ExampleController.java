package com.smateso.proof.adapter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/v1")
public class ExampleController implements IExampleController {

    @Override
    public ResponseEntity<List<Object>> getAllMeasureStatus() {
        final var result = List.of(new Object());
        return ok(result);
    }

    @Override
    public ResponseEntity<Object> getByIdMeasureStatus(@NonNull final String id) {
        final var result = new Object();
        return ok(result);
    }

}
