/*******************************************************************************
 * Copyright (c) 2022 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.components.collaborative.diagrams;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import org.eclipse.sirius.components.collaborative.api.ChangeDescription;
import org.eclipse.sirius.components.collaborative.api.ChangeKind;
import org.eclipse.sirius.components.collaborative.api.IRepresentationRefreshPolicyRegistry;
import org.eclipse.sirius.components.collaborative.diagrams.api.IDiagramContext;
import org.eclipse.sirius.components.collaborative.diagrams.api.IDiagramCreationService;
import org.eclipse.sirius.components.collaborative.diagrams.dto.DiagramEventInput;
import org.eclipse.sirius.components.collaborative.diagrams.dto.DiagramRefreshedEventPayload;
import org.eclipse.sirius.components.collaborative.representations.SubscriptionManager;
import org.eclipse.sirius.components.core.api.IEditingContext;
import org.eclipse.sirius.components.core.api.IInput;
import org.eclipse.sirius.components.core.api.IPayload;
import org.eclipse.sirius.components.core.api.IRepresentationDescriptionSearchService;
import org.eclipse.sirius.components.diagrams.Diagram;
import org.eclipse.sirius.components.diagrams.Position;
import org.eclipse.sirius.components.diagrams.Size;
import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;

/**
 * Unit tests of the diagram event processor.
 *
 * @author sbegaudeau
 */
public class DiagramEventProcessorTests {

    private static final String DIAGRAM_ID = UUID.randomUUID().toString();

    private static final String DIAGRAM_DESCRIPTION_ID = UUID.randomUUID().toString();

    // @formatter:off
    private static final Diagram INITIAL_TEST_DIAGRAM = Diagram.newDiagram(DIAGRAM_ID)
            .descriptionId(DIAGRAM_DESCRIPTION_ID)
            .label(String.valueOf(0))
            .targetObjectId("targetObjectId") //$NON-NLS-1$
            .position(Position.UNDEFINED)
            .size(Size.UNDEFINED)
            .nodes(List.of())
            .edges(List.of())
            .build();
    // @formatter:on

    private final IDiagramCreationService diagramCreationService = new MockDiagramCreationService(INITIAL_TEST_DIAGRAM);

    private final IDiagramContext diagramContext = new IDiagramContext.NoOp() {
        @Override
        public Diagram getDiagram() {
            return INITIAL_TEST_DIAGRAM;
        }
    };

    private Predicate<IPayload> getRefreshDiagramEventPayloadPredicate(int count) {
        return representationEventPayload -> {
            if (representationEventPayload instanceof DiagramRefreshedEventPayload) {
                DiagramRefreshedEventPayload payload = (DiagramRefreshedEventPayload) representationEventPayload;
                return payload.getDiagram() != null && payload.getDiagram().getLabel().equals(String.valueOf(count));
            }
            return false;
        };
    }

    @Test
    public void testEmitDiagramOnSubscription() {
        IInput input = new DiagramEventInput(UUID.randomUUID(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        DiagramEventProcessor diagramEventProcessor = new DiagramEventProcessor(new IEditingContext.NoOp(), this.diagramContext, List.of(), new SubscriptionManager(),
                new IRepresentationDescriptionSearchService.NoOp(), new IRepresentationRefreshPolicyRegistry.NoOp(), this.diagramCreationService);

        // @formatter:off
        StepVerifier.create(diagramEventProcessor.getOutputEvents(input))
                .expectNextMatches(this.getRefreshDiagramEventPayloadPredicate(1))
                .thenCancel()
                .verify();
        // @formatter:on
    }

    @Test
    public void testEmitDiagramOnRefresh() {
        DiagramEventInput input = new DiagramEventInput(UUID.randomUUID(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        DiagramEventProcessor diagramEventProcessor = new DiagramEventProcessor(new IEditingContext.NoOp(), this.diagramContext, List.of(), new SubscriptionManager(),
                new IRepresentationDescriptionSearchService.NoOp(), new IRepresentationRefreshPolicyRegistry.NoOp(), this.diagramCreationService);

        Runnable performRefresh = () -> diagramEventProcessor.refresh(new ChangeDescription(ChangeKind.SEMANTIC_CHANGE, input.getDiagramId(), input));

        // @formatter:off
        StepVerifier.create(diagramEventProcessor.getOutputEvents(input))
                .expectNextMatches(this.getRefreshDiagramEventPayloadPredicate(1))
                .then(performRefresh)
                .expectNextMatches(this.getRefreshDiagramEventPayloadPredicate(2))
                .thenCancel()
                .verify();
        // @formatter:on
    }

    @Test
    public void testUpdateInitialDiagramForNewSubscription() {
        DiagramEventInput input = new DiagramEventInput(UUID.randomUUID(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        DiagramEventProcessor diagramEventProcessor = new DiagramEventProcessor(new IEditingContext.NoOp(), this.diagramContext, List.of(), new SubscriptionManager(),
                new IRepresentationDescriptionSearchService.NoOp(), new IRepresentationRefreshPolicyRegistry.NoOp(), this.diagramCreationService);

        Runnable performRefresh = () -> diagramEventProcessor.refresh(new ChangeDescription(ChangeKind.SEMANTIC_CHANGE, input.getDiagramId(), input));

        // @formatter:off
        StepVerifier.create(diagramEventProcessor.getOutputEvents(input))
                .expectNextMatches(this.getRefreshDiagramEventPayloadPredicate(1))
                .then(performRefresh)
                .expectNextMatches(this.getRefreshDiagramEventPayloadPredicate(2))
                .thenCancel()
                .verify();
        // @formatter:on

        // @formatter:off
        StepVerifier.create(diagramEventProcessor.getOutputEvents(input))
                .expectNextMatches(this.getRefreshDiagramEventPayloadPredicate(2))
                .thenCancel()
                .verify();
        // @formatter:on
    }

    @Test
    public void testCompleteOnDispose() {
        DiagramEventInput input = new DiagramEventInput(UUID.randomUUID(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        DiagramEventProcessor diagramEventProcessor = new DiagramEventProcessor(new IEditingContext.NoOp(), this.diagramContext, List.of(), new SubscriptionManager(),
                new IRepresentationDescriptionSearchService.NoOp(), new IRepresentationRefreshPolicyRegistry.NoOp(), this.diagramCreationService);

        Runnable disposeDiagramEventProcessor = () -> diagramEventProcessor.dispose();

        // @formatter:off
        StepVerifier.create(diagramEventProcessor.getOutputEvents(input))
                .expectNextMatches(this.getRefreshDiagramEventPayloadPredicate(1))
                .then(disposeDiagramEventProcessor)
                .expectComplete()
                .verify();
        // @formatter:on
    }
}
