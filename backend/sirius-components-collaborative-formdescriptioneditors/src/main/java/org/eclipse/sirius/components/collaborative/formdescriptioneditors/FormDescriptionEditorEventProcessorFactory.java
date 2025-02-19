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
package org.eclipse.sirius.components.collaborative.formdescriptioneditors;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.sirius.components.collaborative.api.IRepresentationConfiguration;
import org.eclipse.sirius.components.collaborative.api.IRepresentationEventProcessor;
import org.eclipse.sirius.components.collaborative.api.IRepresentationEventProcessorFactory;
import org.eclipse.sirius.components.collaborative.api.IRepresentationRefreshPolicyRegistry;
import org.eclipse.sirius.components.collaborative.api.IRepresentationSearchService;
import org.eclipse.sirius.components.collaborative.api.ISubscriptionManagerFactory;
import org.eclipse.sirius.components.collaborative.formdescriptioneditors.api.FormDescriptionEditorConfiguration;
import org.eclipse.sirius.components.collaborative.formdescriptioneditors.api.IFormDescriptionEditorCreationService;
import org.eclipse.sirius.components.collaborative.formdescriptioneditors.api.IFormDescriptionEditorEventHandler;
import org.eclipse.sirius.components.collaborative.formdescriptioneditors.api.IFormDescriptionEditorEventProcessor;
import org.eclipse.sirius.components.core.api.IEditingContext;
import org.eclipse.sirius.components.core.api.IRepresentationDescriptionSearchService;
import org.eclipse.sirius.components.formdescriptioneditors.FormDescriptionEditor;
import org.springframework.stereotype.Service;

/**
 * Used to create the form description editor event processors.
 *
 * @author arichard
 */
@Service
public class FormDescriptionEditorEventProcessorFactory implements IRepresentationEventProcessorFactory {

    private final IRepresentationDescriptionSearchService representationDescriptionSearchService;

    private final IFormDescriptionEditorCreationService formDescriptionEditormCreationService;

    private final IRepresentationSearchService representationSearchService;

    private final List<IFormDescriptionEditorEventHandler> formDescriptionEditorEventHandlers;

    private final ISubscriptionManagerFactory subscriptionManagerFactory;

    private final IRepresentationRefreshPolicyRegistry representationRefreshPolicyRegistry;

    public FormDescriptionEditorEventProcessorFactory(IRepresentationDescriptionSearchService representationDescriptionSearchService,
            IFormDescriptionEditorCreationService formDescriptionEditormCreationService, IRepresentationSearchService representationSearchService,
            List<IFormDescriptionEditorEventHandler> formDescriptionEditorEventHandlers, ISubscriptionManagerFactory subscriptionManagerFactory,
            IRepresentationRefreshPolicyRegistry representationRefreshPolicyRegistry) {
        this.representationDescriptionSearchService = Objects.requireNonNull(representationDescriptionSearchService);
        this.formDescriptionEditormCreationService = Objects.requireNonNull(formDescriptionEditormCreationService);
        this.representationSearchService = Objects.requireNonNull(representationSearchService);
        this.formDescriptionEditorEventHandlers = Objects.requireNonNull(formDescriptionEditorEventHandlers);
        this.subscriptionManagerFactory = Objects.requireNonNull(subscriptionManagerFactory);
        this.representationRefreshPolicyRegistry = Objects.requireNonNull(representationRefreshPolicyRegistry);
    }

    @Override
    public <T extends IRepresentationEventProcessor> boolean canHandle(Class<T> representationEventProcessorClass, IRepresentationConfiguration configuration) {
        return IFormDescriptionEditorEventProcessor.class.isAssignableFrom(representationEventProcessorClass) && configuration instanceof FormDescriptionEditorConfiguration;
    }

    @Override
    public <T extends IRepresentationEventProcessor> Optional<T> createRepresentationEventProcessor(Class<T> representationEventProcessorClass, IRepresentationConfiguration configuration,
            IEditingContext editingContext) {
        if (IFormDescriptionEditorEventProcessor.class.isAssignableFrom(representationEventProcessorClass) && configuration instanceof FormDescriptionEditorConfiguration) {
            FormDescriptionEditorConfiguration formDescriptionEditorConfiguration = (FormDescriptionEditorConfiguration) configuration;

            Optional<FormDescriptionEditor> optionalFormDescriptionEditor = this.representationSearchService.findById(editingContext, formDescriptionEditorConfiguration.getId(),
                    FormDescriptionEditor.class);
            if (optionalFormDescriptionEditor.isPresent()) {
                FormDescriptionEditor formDescriptionEditor = optionalFormDescriptionEditor.get();
                FormDescriptionEditorContext formDescriptionEditorContext = new FormDescriptionEditorContext(formDescriptionEditor);
                // @formatter:off
                IRepresentationEventProcessor formDescriptionEditorEventProcessor = new FormDescriptionEditorEventProcessor(editingContext, formDescriptionEditorContext, this.formDescriptionEditorEventHandlers,
                        this.subscriptionManagerFactory.create(), this.formDescriptionEditormCreationService, this.representationDescriptionSearchService, this.representationRefreshPolicyRegistry);

                return Optional.of(formDescriptionEditorEventProcessor).filter(representationEventProcessorClass::isInstance).map(representationEventProcessorClass::cast);
                // @formatter:on
            }
        }
        return Optional.empty();
    }
}
