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
package org.eclipse.sirius.components.charts.hierarchy;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

import org.eclipse.sirius.components.representations.IRepresentation;
import org.eclipse.sirius.components.representations.ISemanticRepresentation;

/**
 * Root concept of the hierarchy representation.
 *
 * @author sbegaudeau
 */
public class Hierarchy implements IRepresentation, ISemanticRepresentation {

    private String id;

    private String descriptionId;

    private String targetObjectId;

    private String label;

    private String kind;

    private List<HierarchyNode> children;

    public Hierarchy() {
        // Used by Jackson
    }

    public Hierarchy(String id, String descriptionId, String targetObjectId, String label, String kind, List<HierarchyNode> children) {
        this.id = Objects.requireNonNull(id);
        this.descriptionId = Objects.requireNonNull(descriptionId);
        this.targetObjectId = Objects.requireNonNull(targetObjectId);
        this.label = Objects.requireNonNull(label);
        this.kind = Objects.requireNonNull(kind);
        this.children = Objects.requireNonNull(children);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getDescriptionId() {
        return this.descriptionId;
    }

    @Override
    public String getTargetObjectId() {
        return this.targetObjectId;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String getKind() {
        return this.kind;
    }

    public List<HierarchyNode> getChildren() {
        return this.children;
    }

    @Override
    public String toString() {
        String pattern = "{0} '{'id: {1}, targetObjectId: {2}, descriptionId: {3}, label: {4}, kind: {5}'}'"; //$NON-NLS-1$
        return MessageFormat.format(pattern, this.getClass().getSimpleName(), this.id, this.targetObjectId, this.descriptionId, this.label, this.kind);
    }

}
