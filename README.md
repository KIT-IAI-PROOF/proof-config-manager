# Which endpoints are available

Only the endpoints needed in the applications are implemented.
A few entities have no own controller because they are never used or managed without their parent, for example the
source and target handles of a block.

# About request and return JSON data

The endpoints allow to save all nested object properties which the current class owns.
For example a block is part of a workflow: You can save a workflow with one or multiple blocks nested in the request,
but you cannot save a block with a nested workflow property.

The returned entities include all properties which the endpoint is designed for. In most cases the owning side of a
relation includes the mapped side analog to the request.
An exception is for example the execution object: Executions are not included in the workflow object, but the workflow
parent is included in the execution object.