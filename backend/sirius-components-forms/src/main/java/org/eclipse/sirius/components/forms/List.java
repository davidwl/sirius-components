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
package org.eclipse.sirius.components.forms;

import java.text.MessageFormat;
import java.util.Objects;

import org.eclipse.sirius.components.annotations.Immutable;
import org.eclipse.sirius.components.forms.validation.Diagnostic;

/**
 * The list widget.
 *
 * @author sbegaudeau
 */
@Immutable
public final class List extends AbstractWidget {
    private java.util.List<ListItem> items;

    private List() {
        // Prevent instantiation
    }

    public java.util.List<ListItem> getItems() {
        return this.items;
    }

    public static Builder newList(String id) {
        return new Builder(id);
    }

    @Override
    public String toString() {
        String pattern = "{0} '{'id: {1}, label: {2}, items: {3}'}'"; //$NON-NLS-1$
        return MessageFormat.format(pattern, this.getClass().getSimpleName(), this.getId(), this.label, this.items);
    }

    /**
     * The builder used to create the list.
     *
     * @author sbegaudeau
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public static final class Builder {
        private String id;

        private String label;

        private String iconURL;

        private java.util.List<ListItem> items;

        private java.util.List<Diagnostic> diagnostics;

        private Builder(String id) {
            this.id = Objects.requireNonNull(id);
        }

        public Builder label(String label) {
            this.label = Objects.requireNonNull(label);
            return this;
        }

        public Builder iconURL(String iconURL) {
            this.iconURL = Objects.requireNonNull(iconURL);
            return this;
        }

        public Builder items(java.util.List<ListItem> items) {
            this.items = Objects.requireNonNull(items);
            return this;
        }

        public Builder diagnostics(java.util.List<Diagnostic> diagnostics) {
            this.diagnostics = Objects.requireNonNull(diagnostics);
            return this;
        }

        public List build() {
            List list = new List();
            list.id = Objects.requireNonNull(this.id);
            list.label = Objects.requireNonNull(this.label);
            list.iconURL = this.iconURL;
            list.items = Objects.requireNonNull(this.items);
            list.diagnostics = Objects.requireNonNull(this.diagnostics);
            return list;
        }
    }
}
