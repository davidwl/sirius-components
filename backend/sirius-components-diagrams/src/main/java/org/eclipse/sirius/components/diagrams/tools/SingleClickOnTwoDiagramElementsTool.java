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
package org.eclipse.sirius.components.diagrams.tools;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import org.eclipse.sirius.components.annotations.Immutable;
import org.eclipse.sirius.components.representations.IStatus;
import org.eclipse.sirius.components.representations.VariableManager;

/**
 * A tool triggered by a single click on two diagram elements thanks to the palette.
 *
 * @author pcdavid
 * @author hmarchadour
 */
@Immutable
public final class SingleClickOnTwoDiagramElementsTool implements ITool {
    private String id;

    private String imageURL;

    private String label;

    private Function<VariableManager, IStatus> handler;

    private List<SingleClickOnTwoDiagramElementsCandidate> candidates;

    private SingleClickOnTwoDiagramElementsTool() {
        // Prevent instantiation
    }

    public List<SingleClickOnTwoDiagramElementsCandidate> getCandidates() {
        return this.candidates;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getImageURL() {
        return this.imageURL;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public Function<VariableManager, IStatus> getHandler() {
        return this.handler;
    }

    @Override
    public String toString() {
        String pattern = "{0} '{'id: {1}, label: {2}, imageURL: {3}'}'"; //$NON-NLS-1$
        return MessageFormat.format(pattern, this.getClass().getSimpleName(), this.id, this.label, this.imageURL);
    }

    public static Builder newSingleClickOnTwoDiagramElementsTool(String id) {
        return new Builder(id);
    }

    /**
     * The builder used to create a tool.
     *
     * @author pcdavid
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public static final class Builder {
        private String id;

        private String imageURL;

        private String label;

        private Function<VariableManager, IStatus> handler;

        private List<SingleClickOnTwoDiagramElementsCandidate> candidates;

        private Builder(String id) {
            this.id = Objects.requireNonNull(id);
        }

        public Builder imageURL(String imageURL) {
            this.imageURL = Objects.requireNonNull(imageURL);
            return this;
        }

        public Builder label(String label) {
            this.label = Objects.requireNonNull(label);
            return this;
        }

        public Builder candidates(List<SingleClickOnTwoDiagramElementsCandidate> candidates) {
            this.candidates = Objects.requireNonNull(candidates);
            return this;
        }

        public Builder handler(Function<VariableManager, IStatus> handler) {
            this.handler = Objects.requireNonNull(handler);
            return this;
        }

        public SingleClickOnTwoDiagramElementsTool build() {
            SingleClickOnTwoDiagramElementsTool tool = new SingleClickOnTwoDiagramElementsTool();
            tool.id = Objects.requireNonNull(this.id);
            tool.imageURL = Objects.requireNonNull(this.imageURL);
            tool.label = Objects.requireNonNull(this.label);
            tool.handler = Objects.requireNonNull(this.handler);
            tool.candidates = Objects.requireNonNull(this.candidates);
            return tool;
        }
    }
}
