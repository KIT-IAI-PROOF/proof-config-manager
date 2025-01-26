package com.smateso.proof.application.utils;

import edu.kit.iai.webis.proofmodels.dao.*;
import edu.kit.iai.webis.proofmodels.dto.*;
import lombok.extern.slf4j.Slf4j;

import static com.smateso.proof.adapter.utils.NullAwareUtil.getNullPropertyNames;
import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
public class PropertyCopyHelper {

    public static class WorkflowPropertyCopyHelper {

        public static WorkflowDao copyWorkflowProperties(final WorkflowRequestDto source) {
            final var target = new WorkflowDao();
            copyProperties(source, target);
            target.setEdges(source.getEdges().stream().map(EdgePropertyCopyHelper::copyEdgeProperties).toList());
            target.setBlocks(source.getBlocks().stream().map(BlockPropertyCopyHelper::copyBlockProperties).toList());
            final var message = "Copied properties of %s to DTO".formatted(source.toString());
            log.debug(message);
            return target;
        }

        public static WorkflowResponseDto copyWorkflowProperties(final WorkflowDao source) {
            final var target = new WorkflowResponseDto();
            copyProperties(source, target);
            target.setEdges(source.getEdges().stream().map(EdgePropertyCopyHelper::copyEdgeProperties).toList());
            target.setBlocks(source.getBlocks().stream().map(BlockPropertyCopyHelper::copyBlockProperties).toList());
            final var message = "Copied properties of %s to DTO".formatted(source.toString());
            log.debug(message);
            return target;
        }

        public static WorkflowDao copyNonNullWorkflowProperties(final WorkflowDao source, final WorkflowDao target) {
            copyProperties(source, target, getNullPropertyNames(source));
            target.setEdges(target.getEdges());
            target.setBlocks(target.getBlocks());
            target.setExecutions(target.getExecutions());
            final var message = "Copied properties of %s to %s".formatted(source.toString(), target.toString());
            log.debug(message);
            return target;
        }

    }

    public static class BlockPropertyCopyHelper {

        public static BlockDao copyBlockProperties(final BlockDto source) {
            final var target = new BlockDao();
            copyProperties(source, target);
            target.setTargetHandles(source.getTargetHandles().stream().map(HandlePropertyCopyHelper::copyTargetHandleProperties).toList());
            target.setSourceHandles(source.getSourceHandles().stream().map(HandlePropertyCopyHelper::copySourceHandleProperties).toList());
            final var message = "Copied properties of %s to DTO".formatted(source.toString());
            log.debug(message);
            return target;
        }

        public static BlockDto copyBlockProperties(final BlockDao source) {
            final var target = new BlockDto();
            copyProperties(source, target);
            target.setTargetHandles(source.getTargetHandles().stream().map(HandlePropertyCopyHelper::copyTargetHandleProperties).toList());
            target.setSourceHandles(source.getSourceHandles().stream().map(HandlePropertyCopyHelper::copySourceHandleProperties).toList());
            final var message = "Copied properties of %s to DTO".formatted(source.toString());
            log.debug(message);
            return target;
        }

        public static BlockDao copyNonNullBlockProperties(final BlockDao source, final BlockDao target) {
            copyProperties(source, target, getNullPropertyNames(source));
            target.setSourceHandles(target.getSourceHandles());
            target.setTargetHandles(target.getTargetHandles());
            final var message = "Copied properties of %s to %s".formatted(source.toString(), target.toString());
            log.debug(message);
            return target;
        }

    }

    public static class TemplatePropertyCopyHelper {

        public static TemplateDao copyTemplateProperties(final TemplateDto source) {
            final var target = new TemplateDao();
            copyProperties(source, target);
            target.setTargetHandles(source.getTargetHandles().stream().map(HandlePropertyCopyHelper::copyTargetHandleProperties).toList());
            target.setSourceHandles(source.getSourceHandles().stream().map(HandlePropertyCopyHelper::copySourceHandleProperties).toList());
            final var message = "Copied properties of %s to DTO".formatted(source.toString());
            log.debug(message);
            return target;
        }

        public static TemplateDto copyTemplateProperties(final TemplateDao source) {
            final var target = new TemplateDto();
            copyProperties(source, target);
            target.setTargetHandles(source.getTargetHandles().stream().map(HandlePropertyCopyHelper::copyTargetHandleProperties).toList());
            target.setSourceHandles(source.getSourceHandles().stream().map(HandlePropertyCopyHelper::copySourceHandleProperties).toList());
            final var message = "Copied properties of %s to DTO".formatted(source.toString());
            log.debug(message);
            return target;
        }

        public static TemplateDao copyNonNullTemplateProperties(final TemplateDao source, final TemplateDao target) {
            copyProperties(source, target, getNullPropertyNames(source));
            target.setSourceHandles(target.getSourceHandles());
            target.setTargetHandles(target.getTargetHandles());
            final var message = "Copied properties of %s to %s".formatted(source.toString(), target.toString());
            log.debug(message);
            return target;
        }

    }

    public static class EdgePropertyCopyHelper {

        public static EdgeDao copyEdgeProperties(final EdgeDto source) {
            final var target = new EdgeDao();
            copyProperties(source, target);
            target.setPoints(source.getPoints().stream().map(PointPropertyCopyHelper::copyPointProperties).toList());
            final var message = "Copied properties of %s to DTO".formatted(source.toString());
            log.debug(message);
            return target;
        }

        public static EdgeDto copyEdgeProperties(final EdgeDao source) {
            final var target = new EdgeDto();
            copyProperties(source, target);
            target.setPoints(source.getPoints().stream().map(PointPropertyCopyHelper::copyPointProperties).toList());
            final var message = "Copied properties of %s to DTO".formatted(source.toString());
            log.debug(message);
            return target;
        }

        public static EdgeDao copyNonNullEdgeProperties(final EdgeDao source, final EdgeDao target) {
            copyProperties(source, target, getNullPropertyNames(source));
            target.setPoints(target.getPoints());
            final var message = "Copied properties of %s to %s".formatted(source.toString(), target.toString());
            log.debug(message);
            return target;
        }

    }

    public static class PointPropertyCopyHelper {

        public static PointDao copyPointProperties(final PointDto source) {
            final var target = new PointDao();
            copyProperties(source, target);
            final var message = "Copied properties of %s to DTO".formatted(source.toString());
            log.debug(message);
            return target;
        }

        public static PointDto copyPointProperties(final PointDao source) {
            final var target = new PointDto();
            copyProperties(source, target);
            final var message = "Copied properties of %s to DTO".formatted(source.toString());
            log.debug(message);
            return target;
        }

        public static PointDao copyNonNullPointProperties(final PointDao source, final PointDao target) {
            copyProperties(source, target, getNullPropertyNames(source));
            final var message = "Copied properties of %s to %s".formatted(source.toString(), target.toString());
            log.debug(message);
            return target;
        }

    }

    public static class ExecutionPropertyCopyHelper {

        public static ExecutionDao copyExecutionProperties(final ExecutionDto source) {
            final var target = new ExecutionDao();
            copyProperties(source, target);
            final var message = "Copied properties of %s to DTO".formatted(source.toString());
            log.debug(message);
            return target;
        }

        public static ExecutionDto copyExecutionProperties(final ExecutionDao source) {
            final var target = new ExecutionDto();
            copyProperties(source, target);
            final var message = "Copied properties of %s to DTO".formatted(source.toString());
            log.debug(message);
            return target;
        }

        public static ExecutionDao copyNonNullExecutionProperties(final ExecutionDao source, final ExecutionDao target) {
            copyProperties(source, target, getNullPropertyNames(source));
            final var message = "Copied properties of %s to %s".formatted(source.toString(), target.toString());
            log.debug(message);
            return target;
        }

    }

    public static class HandlePropertyCopyHelper {

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

    }

}
