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
package org.eclipse.sirius.components.collaborative.dto;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.UUID;

import org.eclipse.sirius.components.core.api.IPayload;

/**
 * The payload returned by the rename object mutation.
 *
 * @author arichard
 */
public final class RenameObjectSuccessPayload implements IPayload {

    private final UUID id;

    private final String objectId;

    private final String newName;

    public RenameObjectSuccessPayload(UUID id, String objectId, String newName) {
        this.id = Objects.requireNonNull(id);
        this.objectId = Objects.requireNonNull(objectId);
        this.newName = Objects.requireNonNull(newName);
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public String getNewName() {
        return this.newName;
    }

    @Override
    public String toString() {
        String pattern = "{0} '{'id: {1}, objectId: {2}, newName: {3}'}'"; //$NON-NLS-1$
        return MessageFormat.format(pattern, this.getClass().getSimpleName(), this.id, this.objectId, this.newName);
    }
}
