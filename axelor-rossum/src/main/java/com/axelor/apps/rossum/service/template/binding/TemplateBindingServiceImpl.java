package com.axelor.apps.rossum.service.template.binding;

import com.axelor.apps.account.db.Invoice;
import com.axelor.apps.account.db.InvoiceLine;
import com.axelor.apps.account.db.InvoiceLineTax;
import com.axelor.apps.rossum.db.Schema;
import com.axelor.apps.rossum.db.SchemaField;
import com.axelor.apps.rossum.db.TemplateBinding;
import com.axelor.apps.rossum.db.TemplateBindingLine;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.ListUtils;

public class TemplateBindingServiceImpl implements TemplateBindingService {

  @Override
  public void updateTemplateBindingLineList(TemplateBinding templateBinding) {
    templateBinding.clearTemplateBindingLineList();

    List<TemplateBindingLine> templateBindingLineList = new ArrayList<>();
    Schema schemaUrl = templateBinding.getSchemaUrl();

    if (schemaUrl != null) {
      for (SchemaField schemaField : ListUtils.emptyIfNull(schemaUrl.getSchemaFieldList())) {
        if (Boolean.TRUE.equals(schemaField.getCanExport())) {
          templateBindingLineList.add(
              this.createTemplateBindingLineObject(
                  schemaField.getSchemaFieldId(),
                  schemaField.getLabel(),
                  schemaField.getParentSchemaFieldId()));
        }
      }
    }

    templateBinding.setTemplateBindingLineList(templateBindingLineList);
  }

  protected TemplateBindingLine createTemplateBindingLineObject(
      String rossumField, String labelField, String metaFieldSelection) {

    String metaSelection = Invoice.class.getName();

    if (metaFieldSelection != null) {
      if (metaFieldSelection.equals("line_items")) {
        metaSelection = InvoiceLine.class.getName();
      } else if (metaFieldSelection.equals("tax_details")) {
        metaSelection = InvoiceLineTax.class.getName();
      }
    }

    return new TemplateBindingLine(rossumField, labelField, metaSelection);
  }
}
