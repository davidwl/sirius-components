/**
 * Copyright (c) 2021, 2022 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *      Obeo - initial API and implementation
 */
package org.eclipse.sirius.components.view.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.components.view.LabelStyle;
import org.eclipse.sirius.components.view.TextareaDescriptionStyle;
import org.eclipse.sirius.components.view.ViewPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Textarea Description Style</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.components.view.impl.TextareaDescriptionStyleImpl#getFontSize <em>Font Size</em>}</li>
 * <li>{@link org.eclipse.sirius.components.view.impl.TextareaDescriptionStyleImpl#isItalic <em>Italic</em>}</li>
 * <li>{@link org.eclipse.sirius.components.view.impl.TextareaDescriptionStyleImpl#isBold <em>Bold</em>}</li>
 * <li>{@link org.eclipse.sirius.components.view.impl.TextareaDescriptionStyleImpl#isUnderline <em>Underline</em>}</li>
 * <li>{@link org.eclipse.sirius.components.view.impl.TextareaDescriptionStyleImpl#isStrikeThrough <em>Strike
 * Through</em>}</li>
 * <li>{@link org.eclipse.sirius.components.view.impl.TextareaDescriptionStyleImpl#getBackgroundColor <em>Background
 * Color</em>}</li>
 * <li>{@link org.eclipse.sirius.components.view.impl.TextareaDescriptionStyleImpl#getForegroundColor <em>Foreground
 * Color</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TextareaDescriptionStyleImpl extends WidgetDescriptionStyleImpl implements TextareaDescriptionStyle {
    /**
     * The default value of the '{@link #getFontSize() <em>Font Size</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getFontSize()
     * @generated
     * @ordered
     */
    protected static final int FONT_SIZE_EDEFAULT = 14;

    /**
     * The cached value of the '{@link #getFontSize() <em>Font Size</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getFontSize()
     * @generated
     * @ordered
     */
    protected int fontSize = FONT_SIZE_EDEFAULT;

    /**
     * The default value of the '{@link #isItalic() <em>Italic</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isItalic()
     * @generated
     * @ordered
     */
    protected static final boolean ITALIC_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isItalic() <em>Italic</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isItalic()
     * @generated
     * @ordered
     */
    protected boolean italic = ITALIC_EDEFAULT;

    /**
     * The default value of the '{@link #isBold() <em>Bold</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #isBold()
     * @generated
     * @ordered
     */
    protected static final boolean BOLD_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isBold() <em>Bold</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #isBold()
     * @generated
     * @ordered
     */
    protected boolean bold = BOLD_EDEFAULT;

    /**
     * The default value of the '{@link #isUnderline() <em>Underline</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isUnderline()
     * @generated
     * @ordered
     */
    protected static final boolean UNDERLINE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isUnderline() <em>Underline</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isUnderline()
     * @generated
     * @ordered
     */
    protected boolean underline = UNDERLINE_EDEFAULT;

    /**
     * The default value of the '{@link #isStrikeThrough() <em>Strike Through</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #isStrikeThrough()
     * @generated
     * @ordered
     */
    protected static final boolean STRIKE_THROUGH_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isStrikeThrough() <em>Strike Through</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #isStrikeThrough()
     * @generated
     * @ordered
     */
    protected boolean strikeThrough = STRIKE_THROUGH_EDEFAULT;

    /**
     * The default value of the '{@link #getBackgroundColor() <em>Background Color</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected static final String BACKGROUND_COLOR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBackgroundColor() <em>Background Color</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected String backgroundColor = BACKGROUND_COLOR_EDEFAULT;

    /**
     * The default value of the '{@link #getForegroundColor() <em>Foreground Color</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getForegroundColor()
     * @generated
     * @ordered
     */
    protected static final String FOREGROUND_COLOR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getForegroundColor() <em>Foreground Color</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getForegroundColor()
     * @generated
     * @ordered
     */
    protected String foregroundColor = FOREGROUND_COLOR_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected TextareaDescriptionStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewPackage.Literals.TEXTAREA_DESCRIPTION_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getFontSize() {
        return this.fontSize;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFontSize(int newFontSize) {
        int oldFontSize = this.fontSize;
        this.fontSize = newFontSize;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TEXTAREA_DESCRIPTION_STYLE__FONT_SIZE, oldFontSize, this.fontSize));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isItalic() {
        return this.italic;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setItalic(boolean newItalic) {
        boolean oldItalic = this.italic;
        this.italic = newItalic;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TEXTAREA_DESCRIPTION_STYLE__ITALIC, oldItalic, this.italic));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isBold() {
        return this.bold;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBold(boolean newBold) {
        boolean oldBold = this.bold;
        this.bold = newBold;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TEXTAREA_DESCRIPTION_STYLE__BOLD, oldBold, this.bold));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isUnderline() {
        return this.underline;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setUnderline(boolean newUnderline) {
        boolean oldUnderline = this.underline;
        this.underline = newUnderline;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TEXTAREA_DESCRIPTION_STYLE__UNDERLINE, oldUnderline, this.underline));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isStrikeThrough() {
        return this.strikeThrough;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setStrikeThrough(boolean newStrikeThrough) {
        boolean oldStrikeThrough = this.strikeThrough;
        this.strikeThrough = newStrikeThrough;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TEXTAREA_DESCRIPTION_STYLE__STRIKE_THROUGH, oldStrikeThrough, this.strikeThrough));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBackgroundColor(String newBackgroundColor) {
        String oldBackgroundColor = this.backgroundColor;
        this.backgroundColor = newBackgroundColor;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TEXTAREA_DESCRIPTION_STYLE__BACKGROUND_COLOR, oldBackgroundColor, this.backgroundColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getForegroundColor() {
        return this.foregroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setForegroundColor(String newForegroundColor) {
        String oldForegroundColor = this.foregroundColor;
        this.foregroundColor = newForegroundColor;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.TEXTAREA_DESCRIPTION_STYLE__FOREGROUND_COLOR, oldForegroundColor, this.foregroundColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__FONT_SIZE:
            return this.getFontSize();
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__ITALIC:
            return this.isItalic();
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__BOLD:
            return this.isBold();
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__UNDERLINE:
            return this.isUnderline();
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__STRIKE_THROUGH:
            return this.isStrikeThrough();
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__BACKGROUND_COLOR:
            return this.getBackgroundColor();
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__FOREGROUND_COLOR:
            return this.getForegroundColor();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__FONT_SIZE:
            this.setFontSize((Integer) newValue);
            return;
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__ITALIC:
            this.setItalic((Boolean) newValue);
            return;
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__BOLD:
            this.setBold((Boolean) newValue);
            return;
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__UNDERLINE:
            this.setUnderline((Boolean) newValue);
            return;
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__STRIKE_THROUGH:
            this.setStrikeThrough((Boolean) newValue);
            return;
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__BACKGROUND_COLOR:
            this.setBackgroundColor((String) newValue);
            return;
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__FOREGROUND_COLOR:
            this.setForegroundColor((String) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__FONT_SIZE:
            this.setFontSize(FONT_SIZE_EDEFAULT);
            return;
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__ITALIC:
            this.setItalic(ITALIC_EDEFAULT);
            return;
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__BOLD:
            this.setBold(BOLD_EDEFAULT);
            return;
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__UNDERLINE:
            this.setUnderline(UNDERLINE_EDEFAULT);
            return;
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__STRIKE_THROUGH:
            this.setStrikeThrough(STRIKE_THROUGH_EDEFAULT);
            return;
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__BACKGROUND_COLOR:
            this.setBackgroundColor(BACKGROUND_COLOR_EDEFAULT);
            return;
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__FOREGROUND_COLOR:
            this.setForegroundColor(FOREGROUND_COLOR_EDEFAULT);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__FONT_SIZE:
            return this.fontSize != FONT_SIZE_EDEFAULT;
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__ITALIC:
            return this.italic != ITALIC_EDEFAULT;
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__BOLD:
            return this.bold != BOLD_EDEFAULT;
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__UNDERLINE:
            return this.underline != UNDERLINE_EDEFAULT;
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__STRIKE_THROUGH:
            return this.strikeThrough != STRIKE_THROUGH_EDEFAULT;
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__BACKGROUND_COLOR:
            return BACKGROUND_COLOR_EDEFAULT == null ? this.backgroundColor != null : !BACKGROUND_COLOR_EDEFAULT.equals(this.backgroundColor);
        case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__FOREGROUND_COLOR:
            return FOREGROUND_COLOR_EDEFAULT == null ? this.foregroundColor != null : !FOREGROUND_COLOR_EDEFAULT.equals(this.foregroundColor);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == LabelStyle.class) {
            switch (derivedFeatureID) {
            case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__FONT_SIZE:
                return ViewPackage.LABEL_STYLE__FONT_SIZE;
            case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__ITALIC:
                return ViewPackage.LABEL_STYLE__ITALIC;
            case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__BOLD:
                return ViewPackage.LABEL_STYLE__BOLD;
            case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__UNDERLINE:
                return ViewPackage.LABEL_STYLE__UNDERLINE;
            case ViewPackage.TEXTAREA_DESCRIPTION_STYLE__STRIKE_THROUGH:
                return ViewPackage.LABEL_STYLE__STRIKE_THROUGH;
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == LabelStyle.class) {
            switch (baseFeatureID) {
            case ViewPackage.LABEL_STYLE__FONT_SIZE:
                return ViewPackage.TEXTAREA_DESCRIPTION_STYLE__FONT_SIZE;
            case ViewPackage.LABEL_STYLE__ITALIC:
                return ViewPackage.TEXTAREA_DESCRIPTION_STYLE__ITALIC;
            case ViewPackage.LABEL_STYLE__BOLD:
                return ViewPackage.TEXTAREA_DESCRIPTION_STYLE__BOLD;
            case ViewPackage.LABEL_STYLE__UNDERLINE:
                return ViewPackage.TEXTAREA_DESCRIPTION_STYLE__UNDERLINE;
            case ViewPackage.LABEL_STYLE__STRIKE_THROUGH:
                return ViewPackage.TEXTAREA_DESCRIPTION_STYLE__STRIKE_THROUGH;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        if (this.eIsProxy())
            return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (fontSize: "); //$NON-NLS-1$
        result.append(this.fontSize);
        result.append(", italic: "); //$NON-NLS-1$
        result.append(this.italic);
        result.append(", bold: "); //$NON-NLS-1$
        result.append(this.bold);
        result.append(", underline: "); //$NON-NLS-1$
        result.append(this.underline);
        result.append(", strikeThrough: "); //$NON-NLS-1$
        result.append(this.strikeThrough);
        result.append(", backgroundColor: "); //$NON-NLS-1$
        result.append(this.backgroundColor);
        result.append(", foregroundColor: "); //$NON-NLS-1$
        result.append(this.foregroundColor);
        result.append(')');
        return result.toString();
    }

} // TextareaDescriptionStyleImpl
