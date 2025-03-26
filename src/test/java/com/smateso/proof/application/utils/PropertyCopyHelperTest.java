package com.smateso.proof.application.utils;

import edu.kit.iai.webis.proofmodels.dao.BlockDao;
import edu.kit.iai.webis.proofmodels.dto.Block;
import edu.kit.iai.webis.proofmodels.dto.Position;
import edu.kit.iai.webis.proofmodels.dto.Program;
import org.junit.jupiter.api.Test;

import java.util.List;

class PropertyCopyHelperTest {

    @Test
    void deepCopyEntityProperties() {
        final var blockDao = new BlockDao();
        final var program = Program
                .builder()
                .id("program")
                .build();
        final var position = Position
                .builder()
                .x(1)
                .y(2)
                .build();
        final var block = Block
                .builder()
                .id("block")
                .description("description")
                .position(position)
                .program(program)
                .build();

        PropertyCopyHelper.deepCopyEntityProperties(block, blockDao, false, List.of(), List.of("program", "position"));

        assert blockDao.getProgram() != null;
        assert blockDao.getProgram().getId() != null;
        assert blockDao.getProgram().getId().equals("program");

        assert blockDao.getPosition() != null;
        assert blockDao.getPosition().getX() != null;
        assert blockDao.getPosition().getY() != null;
        assert blockDao.getPosition().getX().equals(1);
        assert blockDao.getPosition().getY().equals(2);

    }

}