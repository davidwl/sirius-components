/*******************************************************************************
 * Copyright (c) 2019, 2022 Obeo.
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
package org.eclipse.sirius.components.emf.compatibility.properties;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.sirius.components.compatibility.forms.WidgetIdProvider;
import org.eclipse.sirius.components.emf.compatibility.properties.api.IPropertiesValidationProvider;
import org.eclipse.sirius.components.forms.description.IfDescription;
import org.eclipse.sirius.components.forms.description.TextareaDescription;
import org.eclipse.sirius.components.representations.Failure;
import org.eclipse.sirius.components.representations.IStatus;
import org.eclipse.sirius.components.representations.Success;
import org.eclipse.sirius.components.representations.VariableManager;

/**
 * Provides the default description of the widget to use to support an EString feature.
 *
 * @author sbegaudeau
 */
public class EStringIfDescriptionProvider {
    private static final String IF_DESCRIPTION_ID = "EString"; //$NON-NLS-1$

    private static final String TEXTAREA_DESCRIPTION_ID = "Textarea"; //$NON-NLS-1$

    private final ComposedAdapterFactory composedAdapterFactory;

    private final IPropertiesValidationProvider propertiesValidationProvider;

    public EStringIfDescriptionProvider(ComposedAdapterFactory composedAdapterFactory, IPropertiesValidationProvider propertiesValidationProvider) {
        this.composedAdapterFactory = Objects.requireNonNull(composedAdapterFactory);
        this.propertiesValidationProvider = Objects.requireNonNull(propertiesValidationProvider);
    }

    public IfDescription getIfDescription() {
        // @formatter:off
        return IfDescription.newIfDescription(IF_DESCRIPTION_ID)
                .predicate(this.getPredicate())
                .widgetDescription(this.getTextareaDescription())
                .build();
        // @formatter:on
    }

    private Function<VariableManager, Boolean> getPredicate() {
        return variableManager -> {
            var optionalEAttribute = variableManager.get(PropertiesDefaultDescriptionProvider.ESTRUCTURAL_FEATURE, EAttribute.class);
            return optionalEAttribute.filter(eAttribute -> {
                EClassifier eType = eAttribute.getEType();
                return eType.equals(EcorePackage.Literals.ESTRING) || Objects.equals(eType.getInstanceClassName(), String.class.getName());
            }).isPresent();
        };
    }

    private TextareaDescription getTextareaDescription() {
        // @formatter:off
        return TextareaDescription.newTextareaDescription(TEXTAREA_DESCRIPTION_ID)
                .idProvider(new WidgetIdProvider())
                .labelProvider(this.getLabelProvider())
                .valueProvider(this.getValueProvider())
                .newValueHandler(this.getNewValueHandler())
                .diagnosticsProvider(this.propertiesValidationProvider.getDiagnosticsProvider())
                .kindProvider(this.propertiesValidationProvider.getKindProvider())
                .messageProvider(this.propertiesValidationProvider.getMessageProvider())
                .build();
        // @formatter:on
    }

    private Function<VariableManager, String> getLabelProvider() {
        return new EStructuralFeatureLabelProvider(PropertiesDefaultDescriptionProvider.ESTRUCTURAL_FEATURE, this.composedAdapterFactory);
    }

    private Function<VariableManager, String> getValueProvider() {
        return variableManager -> {
            var optionalEObject = variableManager.get(VariableManager.SELF, EObject.class);
            var optionalEAttribute = variableManager.get(PropertiesDefaultDescriptionProvider.ESTRUCTURAL_FEATURE, EAttribute.class);

            if (optionalEObject.isPresent() && optionalEAttribute.isPresent()) {
                EObject eObject = optionalEObject.get();
                EAttribute eAttribute = optionalEAttribute.get();

                Object value = eObject.eGet(eAttribute);
                if (value != null && !eAttribute.isMany()) {
                    return EcoreUtil.convertToString(EcorePackage.Literals.ESTRING, value);
                }
            }

            return ""; //$NON-NLS-1$
        };
    }

    private BiFunction<VariableManager, String, IStatus> getNewValueHandler() {
        return (variableManager, newValue) -> {
            var optionalEObject = variableManager.get(VariableManager.SELF, EObject.class);
            var optionalEAttribute = variableManager.get(PropertiesDefaultDescriptionProvider.ESTRUCTURAL_FEATURE, EAttribute.class);
            if (optionalEObject.isPresent() && optionalEAttribute.isPresent()) {
                EObject eObject = optionalEObject.get();
                EAttribute eAttribute = optionalEAttribute.get();

                String value = EcoreUtil.createFromString(EcorePackage.Literals.ESTRING, newValue).toString();
                eObject.eSet(eAttribute, value);

                return new Success();
            }
            return new Failure(""); //$NON-NLS-1$
        };
    }
}
