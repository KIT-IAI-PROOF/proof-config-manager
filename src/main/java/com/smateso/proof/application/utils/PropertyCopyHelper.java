package com.smateso.proof.application.utils;

import edu.kit.iai.webis.proofmodels.dao.*;
import edu.kit.iai.webis.proofmodels.dto.*;

import static org.springframework.beans.BeanUtils.copyProperties;

public class PropertyCopyHelper {

    public static BlockDao copyBlockProperties(final BlockDto source) {
        final var target = new BlockDao();
        copyProperties(source, target);
        target.setSourceHandles(source.getSourceHandles() != null ? source.getSourceHandles().stream().map(PropertyCopyHelper::copySourceHandleProperties).toList() : null);
        target.setTargetHandles(source.getTargetHandles() != null ? source.getTargetHandles().stream().map(PropertyCopyHelper::copyTargetHandleProperties).toList() : null);
        target.setPosition(copyPositionProperties(source.getPosition()));
        return target;
    }

    public static BlockDto copyBlockProperties(final BlockDao source) {
        final var target = new BlockDto();
        copyProperties(source, target);
        target.setSourceHandles(source.getSourceHandles() != null ? source.getSourceHandles().stream().map(PropertyCopyHelper::copySourceHandleProperties).toList() : null);
        target.setTargetHandles(source.getTargetHandles() != null ? source.getTargetHandles().stream().map(PropertyCopyHelper::copyTargetHandleProperties).toList() : null);
        target.setPosition(copyPositionProperties(source.getPosition()));
        return target;
    }

    public static EdgeDao copyEdgeProperties(final EdgeDto source) {
        final var target = new EdgeDao();
        copyProperties(source, target);
        target.setPoints(source.getPoints() != null ? source.getPoints().stream().map(PropertyCopyHelper::copyPointProperties).toList() : null);
        return target;
    }

    public static EdgeDto copyEdgeProperties(final EdgeDao source) {
        final var target = new EdgeDto();
        copyProperties(source, target);
        target.setPoints(source.getPoints() != null ? source.getPoints().stream().map(PropertyCopyHelper::copyPointProperties).toList() : null);
        return target;
    }

    public static TemplateDao copyTemplateProperties(final TemplateDto source) {
        final var target = new TemplateDao();
        copyProperties(source, target);
        target.setSourceHandles(source.getSourceHandles() != null ? source.getSourceHandles().stream().map(PropertyCopyHelper::copySourceHandleProperties).toList() : null);
        target.setTargetHandles(source.getTargetHandles() != null ? source.getTargetHandles().stream().map(PropertyCopyHelper::copyTargetHandleProperties).toList() : null);
        return target;
    }

    public static TemplateDto copyTemplateProperties(final TemplateDao source) {
        final var target = new TemplateDto();
        copyProperties(source, target);
        target.setSourceHandles(source.getSourceHandles() != null ? source.getSourceHandles().stream().map(PropertyCopyHelper::copySourceHandleProperties).toList() : null);
        target.setTargetHandles(source.getTargetHandles() != null ? source.getTargetHandles().stream().map(PropertyCopyHelper::copyTargetHandleProperties).toList() : null);
        return target;
    }

    public static WorkflowDao copyWorkflowProperties(final WorkflowDto source) {
        final var target = new WorkflowDao();
        copyProperties(source, target);
        target.setEdges(source.getEdges().stream().map(PropertyCopyHelper::copyEdgeProperties).toList());
        target.setBlocks(source.getBlocks().stream().map(PropertyCopyHelper::copyBlockProperties).toList());
        target.setExecutions(source.getExecutions().stream().map(PropertyCopyHelper::copyExecutionProperties).toList());
        return target;
    }

    public static WorkflowDto copyWorkflowProperties(final WorkflowDao source) {
        final var target = new WorkflowDto();
        copyProperties(source, target);
        target.setEdges(source.getEdges() != null ? source.getEdges().stream().map(PropertyCopyHelper::copyEdgeProperties).toList() : null);
        target.setBlocks(source.getBlocks() != null ? source.getBlocks().stream().map(PropertyCopyHelper::copyBlockProperties).toList() : null);
        target.setExecutions(source.getExecutions() != null ? source.getExecutions().stream().map(PropertyCopyHelper::copyExecutionProperties).toList() : null);
        return target;
    }

    public static ExecutionDao copyExecutionProperties(final ExecutionDto source) {
        final var target = new ExecutionDao();
        copyProperties(source, target);
        return target;
    }

    public static ExecutionDto copyExecutionProperties(final ExecutionDao source) {
        final var target = new ExecutionDto();
        copyProperties(source, target);
        return target;
    }

    public static SourceHandleDto copySourceHandleProperties(final SourceHandleDao source) {
        final var target = new SourceHandleDto();
        copyProperties(source, target);
        return target;
    }

    public static SourceHandleDao copySourceHandleProperties(final SourceHandleDto source) {
        final var target = new SourceHandleDao();
        copyProperties(source, target);
        return target;
    }

    public static TargetHandleDto copyTargetHandleProperties(final TargetHandleDao source) {
        final var target = new TargetHandleDto();
        copyProperties(source, target);
        return target;
    }

    public static TargetHandleDao copyTargetHandleProperties(final TargetHandleDto source) {
        final var target = new TargetHandleDao();
        copyProperties(source, target);
        return target;
    }

    public static PointDto copyPointProperties(final PointDao source) {
        final var target = new PointDto();
        copyProperties(source, target);
        return target;
    }

    public static PointDao copyPointProperties(final PointDto source) {
        final var target = new PointDao();
        copyProperties(source, target);
        return target;
    }

    public static PositionDao copyPositionProperties(final PositionDto source) {
        final var target = new PositionDao();
        copyProperties(source, target);
        return target;
    }

    public static PositionDto copyPositionProperties(final PositionDao source) {
        final var target = new PositionDto();
        copyProperties(source, target);
        return target;
    }

}
