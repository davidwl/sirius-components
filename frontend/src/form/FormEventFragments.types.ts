/*******************************************************************************
 * Copyright (c) 2021, 2022 Obeo.
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
import { GQLChart } from 'charts/Charts.types';
export interface GQLFormEventSubscription {
  formEvent: GQLFormEventPayload;
}
export interface GQLFormEventPayload {
  __typename: string;
}

export interface GQLPropertiesEventVariables {
  input: GQLPropertiesEventInput;
}

export interface GQLPropertiesEventInput {
  id: string;
  editingContextId: string;
  objectIds: string[];
}

export interface GQLPropertiesEventSubscription {
  propertiesEvent: GQLPropertiesEventPayload;
}

export interface GQLPropertiesEventPayload {
  __typename: string;
}

export interface GQLRepresentationsEventSubscription {
  representationsEvent: GQLRepresentationsEventPayload;
}

export interface GQLRepresentationsEventPayload {
  __typename: string;
}

export interface GQLSubscribersUpdatedEventPayload
  extends GQLFormEventPayload,
    GQLPropertiesEventPayload,
    GQLRepresentationsEventPayload {
  id: string;
  subscribers: GQLSubscriber[];
}

export interface GQLWidgetSubscriptionsUpdatedEventPayload
  extends GQLFormEventPayload,
    GQLPropertiesEventPayload,
    GQLRepresentationsEventPayload {
  id: string;
  widgetSubscriptions: GQLWidgetSubscription[];
}

export interface GQLWidgetSubscription {
  widgetId: string;
  subscribers: GQLSubscriber[];
}

export interface GQLSubscriber {
  username: string;
}

export interface GQLFormRefreshedEventPayload
  extends GQLFormEventPayload,
    GQLPropertiesEventPayload,
    GQLRepresentationsEventPayload {
  id: string;
  form: GQLForm;
}

export interface GQLRepresentationMetadata {
  id: string;
  label: string;
  kind: string;
  description: GQLRepresentationDescription;
}

export interface GQLRepresentationDescription {
  id: string;
}

export interface GQLForm {
  id: string;
  metadata: GQLRepresentationMetadata;
  pages: GQLPage[];
}

export interface GQLPage {
  id: string;
  label: string;
  groups: GQLGroup[];
}

export interface GQLGroup {
  id: string;
  label: string;
  displayMode: GQLGroupDisplayMode;
  widgets: GQLWidget[];
}

export type GQLGroupDisplayMode = 'LIST' | 'TOGGLEABLE_AREAS';

export interface GQLWidget {
  id: string;
  label: string;
  __typename: string;
  diagnostics: GQLDiagnostic[];
}

export interface GQLDiagnostic {
  id: string;
  kind: string;
  message: string;
}

export interface GQLTextfield extends GQLWidget {
  stringValue: string;
  style: GQLTextfieldStyle;
}

export interface GQLTextfieldStyle {
  backgroundColor: string | null;
  foregroundColor: string | null;
  fontSize: number | null;
  italic: boolean | null;
  bold: boolean | null;
  underline: boolean | null;
  strikeThrough: boolean | null;
}

export interface GQLTextarea extends GQLWidget {
  stringValue: string;
  style: GQLTextareaStyle;
}

export interface GQLTextareaStyle {
  backgroundColor: string | null;
  foregroundColor: string | null;
  fontSize: number | null;
  italic: boolean | null;
  bold: boolean | null;
  underline: boolean | null;
  strikeThrough: boolean | null;
}

export interface GQLCheckbox extends GQLWidget {
  booleanValue: boolean;
  style: GQLCheckboxStyle;
}

export interface GQLCheckboxStyle {
  color: string | null;
}

export interface GQLSelect extends GQLWidget {
  value: string;
  options: GQLSelectOption[];
  style: GQLSelectStyle;
}

export interface GQLSelectStyle {
  backgroundColor: string | null;
  foregroundColor: string | null;
  fontSize: number | null;
  italic: boolean | null;
  bold: boolean | null;
  underline: boolean | null;
  strikeThrough: boolean | null;
}

export interface GQLMultiSelect extends GQLWidget {
  values: string[];
  options: GQLSelectOption[];
  style: GQLMultiSelectStyle;
}

export interface GQLMultiSelectStyle {
  backgroundColor: string | null;
  foregroundColor: string | null;
  fontSize: number | null;
  italic: boolean | null;
  bold: boolean | null;
  underline: boolean | null;
  strikeThrough: boolean | null;
}

export interface GQLSelectOption {
  id: string;
  label: string;
}

export interface GQLRadio extends GQLWidget {
  options: GQLRadioOption[];
  style: GQLRadioStyle;
}

export interface GQLRadioStyle {
  color: string | null;
  fontSize: number | null;
  italic: boolean | null;
  bold: boolean | null;
  underline: boolean | null;
  strikeThrough: boolean | null;
}

export interface GQLRadioOption {
  id: string;
  label: string;
  selected: boolean;
}

export interface GQLList extends GQLWidget {
  items: GQLListItem[];
}

export interface GQLListItem {
  id: string;
  imageURL: string;
  deletable: Boolean;
}

export interface GQLLink extends GQLWidget {
  url: string;
}

export interface GQLChartWidget extends GQLWidget {
  label: string;
  chart: GQLChart;
}

export interface GQLFlexboxContainer extends GQLWidget {
  label: string;
  flexDirection: GQLFlexDirection;
  flexWrap: GQLFlexWrap;
  flexGrow: number;
  children: GQLWidget[];
}

export type GQLFlexDirection = 'row' | 'row-reverse' | 'column' | 'column-reverse';

export type GQLFlexWrap = 'nowrap' | 'wrap' | 'wrap-reverse';
