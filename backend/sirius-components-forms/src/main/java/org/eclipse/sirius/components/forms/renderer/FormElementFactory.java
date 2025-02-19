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
package org.eclipse.sirius.components.forms.renderer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.sirius.components.charts.IChart;
import org.eclipse.sirius.components.charts.barchart.BarChart;
import org.eclipse.sirius.components.charts.barchart.BarChartEntry;
import org.eclipse.sirius.components.charts.barchart.elements.BarChartElementProps;
import org.eclipse.sirius.components.charts.piechart.PieChart;
import org.eclipse.sirius.components.charts.piechart.PieChartEntry;
import org.eclipse.sirius.components.charts.piechart.elements.PieChartElementProps;
import org.eclipse.sirius.components.forms.AbstractWidget;
import org.eclipse.sirius.components.forms.ChartWidget;
import org.eclipse.sirius.components.forms.Checkbox;
import org.eclipse.sirius.components.forms.FlexboxContainer;
import org.eclipse.sirius.components.forms.Form;
import org.eclipse.sirius.components.forms.Group;
import org.eclipse.sirius.components.forms.Link;
import org.eclipse.sirius.components.forms.List.Builder;
import org.eclipse.sirius.components.forms.MultiSelect;
import org.eclipse.sirius.components.forms.Page;
import org.eclipse.sirius.components.forms.Radio;
import org.eclipse.sirius.components.forms.Select;
import org.eclipse.sirius.components.forms.Textarea;
import org.eclipse.sirius.components.forms.Textfield;
import org.eclipse.sirius.components.forms.elements.ChartWidgetElementProps;
import org.eclipse.sirius.components.forms.elements.CheckboxElementProps;
import org.eclipse.sirius.components.forms.elements.FlexboxContainerElementProps;
import org.eclipse.sirius.components.forms.elements.FormElementProps;
import org.eclipse.sirius.components.forms.elements.GroupElementProps;
import org.eclipse.sirius.components.forms.elements.LinkElementProps;
import org.eclipse.sirius.components.forms.elements.ListElementProps;
import org.eclipse.sirius.components.forms.elements.MultiSelectElementProps;
import org.eclipse.sirius.components.forms.elements.PageElementProps;
import org.eclipse.sirius.components.forms.elements.RadioElementProps;
import org.eclipse.sirius.components.forms.elements.SelectElementProps;
import org.eclipse.sirius.components.forms.elements.TextareaElementProps;
import org.eclipse.sirius.components.forms.elements.TextfieldElementProps;
import org.eclipse.sirius.components.forms.validation.Diagnostic;
import org.eclipse.sirius.components.forms.validation.DiagnosticElementProps;
import org.eclipse.sirius.components.representations.IElementFactory;
import org.eclipse.sirius.components.representations.IProps;

/**
 * Used to instantiate the elements of the form.
 *
 * @author sbegaudeau
 */
public class FormElementFactory implements IElementFactory {

    @Override
    public Object instantiateElement(String type, IProps props, List<Object> children) {
        Object object = null;
        if (FormElementProps.TYPE.equals(type) && props instanceof FormElementProps) {
            object = this.instantiateForm((FormElementProps) props, children);
        } else if (PageElementProps.TYPE.equals(type) && props instanceof PageElementProps) {
            object = this.instantiatePage((PageElementProps) props, children);
        } else if (GroupElementProps.TYPE.equals(type) && props instanceof GroupElementProps) {
            object = this.instantiateGroup((GroupElementProps) props, children);
        } else if (CheckboxElementProps.TYPE.equals(type) && props instanceof CheckboxElementProps) {
            object = this.instantiateCheckbox((CheckboxElementProps) props, children);
        } else if (ListElementProps.TYPE.equals(type) && props instanceof ListElementProps) {
            object = this.instantiateList((ListElementProps) props, children);
        } else if (RadioElementProps.TYPE.equals(type) && props instanceof RadioElementProps) {
            object = this.instantiateRadio((RadioElementProps) props, children);
        } else if (SelectElementProps.TYPE.equals(type) && props instanceof SelectElementProps) {
            object = this.instantiateSelect((SelectElementProps) props, children);
        } else if (MultiSelectElementProps.TYPE.equals(type) && props instanceof MultiSelectElementProps) {
            object = this.instantiateMultiSelect((MultiSelectElementProps) props, children);
        } else if (TextareaElementProps.TYPE.equals(type) && props instanceof TextareaElementProps) {
            object = this.instantiateTextarea((TextareaElementProps) props, children);
        } else if (TextfieldElementProps.TYPE.equals(type) && props instanceof TextfieldElementProps) {
            object = this.instantiateTextfield((TextfieldElementProps) props, children);
        } else if (DiagnosticElementProps.TYPE.equals(type) && props instanceof DiagnosticElementProps) {
            object = this.instantiateDiagnostic((DiagnosticElementProps) props, children);
        } else if (LinkElementProps.TYPE.equals(type) && props instanceof LinkElementProps) {
            object = this.instantiateLink((LinkElementProps) props, children);
        } else if (ChartWidgetElementProps.TYPE.equals(type) && props instanceof ChartWidgetElementProps) {
            object = this.instantiateChartWidget((ChartWidgetElementProps) props, children);
        } else if (BarChartElementProps.TYPE.equals(type) && props instanceof BarChartElementProps) {
            object = this.instantiateBarChart((BarChartElementProps) props);
        } else if (PieChartElementProps.TYPE.equals(type) && props instanceof PieChartElementProps) {
            object = this.instantiatePieChart((PieChartElementProps) props);
        } else if (FlexboxContainerElementProps.TYPE.equals(type) && props instanceof FlexboxContainerElementProps) {
            object = this.instantiateFlexboxContainer((FlexboxContainerElementProps) props, children);
        }

        return object;
    }

    public Object instantiateBarChart(BarChartElementProps props) {
        BarChartElementProps barChartElementProps = props;
        List<BarChartEntry> entries = this.getBarChartEntries(barChartElementProps);
        return new BarChart(barChartElementProps.getId(), barChartElementProps.getDescriptionId(), barChartElementProps.getLabel(), entries);
    }

    public Object instantiatePieChart(PieChartElementProps props) {
        PieChartElementProps pieChartElementProps = props;
        List<PieChartEntry> entries = this.getPieChartEntries(pieChartElementProps);
        // The label is not used for pieChart. This attribute is inherited from IRepresentation
        String label = "pieChart"; //$NON-NLS-1$
        return new PieChart(pieChartElementProps.getId(), pieChartElementProps.getDescriptionId(), label, entries);
    }

    private List<BarChartEntry> getBarChartEntries(BarChartElementProps barChartElementProps) {
        List<BarChartEntry> entries = new ArrayList<>();
        List<String> keys = barChartElementProps.getKeys();
        List<Number> values = barChartElementProps.getValues();
        if (values.size() == keys.size()) {
            for (int i = 0; i < values.size(); i++) {
                entries.add(new BarChartEntry(keys.get(i), values.get(i)));
            }
        }
        return entries;
    }

    private List<PieChartEntry> getPieChartEntries(PieChartElementProps pieChartElementProps) {
        List<PieChartEntry> entries = new ArrayList<>();
        List<String> keys = pieChartElementProps.getKeys();
        List<Number> values = pieChartElementProps.getValues();
        if (values.size() == keys.size()) {
            for (int i = 0; i < values.size(); i++) {
                entries.add(new PieChartEntry(keys.get(i), values.get(i)));
            }
        }
        return entries;
    }

    private Form instantiateForm(FormElementProps props, List<Object> children) {
        // @formatter:off
        List<Page> pages = children.stream()
                .filter(Page.class::isInstance)
                .map(Page.class::cast)
                .collect(Collectors.toList());

        return Form.newForm(props.getId())
                .label(props.getLabel())
                .targetObjectId(props.getTargetObjectId())
                .descriptionId(props.getDescriptionId())
                .pages(pages)
                .build();
        // @formatter:on
    }

    private Page instantiatePage(PageElementProps props, List<Object> children) {
        // @formatter:off
        List<Group> groups = children.stream()
                .filter(Group.class::isInstance)
                .map(Group.class::cast)
                .collect(Collectors.toList());

        return Page.newPage(props.getId())
                .label(props.getLabel())
                .groups(groups)
                .build();
        //@formatter:on
    }

    private Group instantiateGroup(GroupElementProps props, List<Object> children) {
        // @formatter:off
        List<AbstractWidget> widgets = children.stream()
                .filter(AbstractWidget.class::isInstance)
                .map(AbstractWidget.class::cast)
                .collect(Collectors.toList());

        return Group.newGroup(props.getId())
                .label(props.getLabel())
                .displayMode(props.getDisplayMode())
                .widgets(widgets)
                .build();
        // @formatter:on
    }

    private Checkbox instantiateCheckbox(CheckboxElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);

        // @formatter:off
        Checkbox.Builder checkboxBuilder = Checkbox.newCheckbox(props.getId())
                .label(props.getLabel())
                .value(props.isValue())
                .newValueHandler(props.getNewValueHandler())
                .diagnostics(diagnostics);

        if (props.getIconURL() != null) {
            checkboxBuilder.iconURL(props.getIconURL());
        }
        if (props.getStyle() != null) {
            checkboxBuilder.style(props.getStyle());
        }

        return checkboxBuilder.build();
        // @formatter:on
    }

    private org.eclipse.sirius.components.forms.List instantiateList(ListElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);

        // @formatter:off
        Builder listBuilder = org.eclipse.sirius.components.forms.List.newList(props.getId())
                .label(props.getLabel())
                .iconURL(props.getIconURL())
                .items(props.getItems())
                .diagnostics(diagnostics);
        // @formatter:on
        if (props.getIconURL() != null) {
            listBuilder.iconURL(props.getIconURL());
        }
        return listBuilder.build();
    }

    private Radio instantiateRadio(RadioElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);

        // @formatter:off
        Radio.Builder radioBuilder = Radio.newRadio(props.getId())
                .label(props.getLabel())
                .options(props.getOptions())
                .newValueHandler(props.getNewValueHandler())
                .diagnostics(diagnostics);

        if (props.getIconURL() != null) {
            radioBuilder.iconURL(props.getIconURL());
        }
        if (props.getStyle() != null) {
            radioBuilder.style(props.getStyle());
        }

        return radioBuilder.build();
        // @formatter:on
    }

    private Select instantiateSelect(SelectElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);

        // @formatter:off
        Select.Builder selectBuilder = Select.newSelect(props.getId())
                .label(props.getLabel())
                .options(props.getOptions())
                .value(props.getValue())
                .newValueHandler(props.getNewValueHandler())
                .diagnostics(diagnostics);

        if (props.getIconURL() != null) {
            selectBuilder.iconURL(props.getIconURL());
        }
        if (props.getStyle() != null) {
            selectBuilder.style(props.getStyle());
        }

        return selectBuilder.build();
        // @formatter:on
    }

    private MultiSelect instantiateMultiSelect(MultiSelectElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);

        // @formatter:off
        MultiSelect.Builder multiSelectBuilder = MultiSelect.newMultiSelect(props.getId())
                .label(props.getLabel())
                .options(props.getOptions())
                .values(props.getValues())
                .newValuesHandler(props.getNewValuesHandler())
                .diagnostics(diagnostics);

        if (props.getIconURL() != null) {
            multiSelectBuilder.iconURL(props.getIconURL());
        }
        if (props.getStyle() != null) {
            multiSelectBuilder.style(props.getStyle());
        }

        return multiSelectBuilder.build();
        // @formatter:on
    }

    private Textarea instantiateTextarea(TextareaElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);

        // @formatter:off
        Textarea.Builder textareaBuilder = Textarea.newTextarea(props.getId())
                .label(props.getLabel())
                .value(props.getValue())
                .newValueHandler(props.getNewValueHandler())
                .diagnostics(diagnostics);

        if (props.getIconURL() != null) {
            textareaBuilder.iconURL(props.getIconURL());
        }
        if (props.getStyle() != null) {
            textareaBuilder.style(props.getStyle());
        }

        return textareaBuilder.build();
        // @formatter:on
    }

    private Textfield instantiateTextfield(TextfieldElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);

        // @formatter:off
        Textfield.Builder textfieldBuilder = Textfield.newTextfield(props.getId())
                .label(props.getLabel())
                .value(props.getValue())
                .newValueHandler(props.getNewValueHandler())
                .diagnostics(diagnostics);

        if (props.getIconURL() != null) {
            textfieldBuilder.iconURL(props.getIconURL());
        }
        if (props.getStyle() != null) {
            textfieldBuilder.style(props.getStyle());
        }

        return textfieldBuilder.build();
        // @formatter:on
    }

    private Object instantiateDiagnostic(DiagnosticElementProps props, List<Object> children) {
        // @formatter:off
        return Diagnostic.newDiagnostic(props.getId())
                .kind(props.getKind())
                .message(props.getMessage())
                .build();
        // @formatter:on
    }

    private Link instantiateLink(LinkElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);

        // @formatter:off
        Link.Builder linkbuilder = Link.newLink(props.getId())
                 .label(props.getLabel())
                 .url(props.getUrl())
                 .diagnostics(diagnostics);
       // @formatter:on

        if (props.getIconURL() != null) {
            linkbuilder.iconURL(props.getIconURL());
        }
        return linkbuilder.build();
    }

    private Object instantiateChartWidget(ChartWidgetElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);
        // @formatter:off
        var chart = children.stream()
                .filter(IChart.class::isInstance)
                .map(IChart.class::cast)
                .findFirst()
                .orElse(null);

        ChartWidget.Builder chartBuilder = ChartWidget.newChartWidget(props.getId())
                .label(props.getLabel())
                .chart(chart)
                .diagnostics(diagnostics);
        // @formatter:on
        if (props.getIconURL() != null) {
            chartBuilder.iconURL(props.getIconURL());
        }
        return chartBuilder.build();
    }

    private FlexboxContainer instantiateFlexboxContainer(FlexboxContainerElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);

        // @formatter:off
        List<AbstractWidget> widgets = children.stream()
                .filter(AbstractWidget.class::isInstance)
                .map(AbstractWidget.class::cast)
                .collect(Collectors.toList());

        return FlexboxContainer.newFlexboxContainer(props.getId())
                 .label(props.getLabel())
                 .flexDirection(props.getFlexDirection().toString())
                 .flexWrap("wrap") //$NON-NLS-1$
                 .flexGrow(1)
                 .children(widgets)
                 .diagnostics(diagnostics)
                 .build();
       // @formatter:on
    }

    private List<Diagnostic> getDiagnosticsFromChildren(List<Object> children) {
        // @formatter:off
        return children.stream()
                .filter(Diagnostic.class::isInstance)
                .map(Diagnostic.class::cast)
                .collect(Collectors.toList());
        // @formatter:on
    }

}
