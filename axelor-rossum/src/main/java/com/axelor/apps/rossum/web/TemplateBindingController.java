package com.axelor.apps.rossum.web;

import com.axelor.apps.rossum.db.TemplateBinding;
import com.axelor.apps.rossum.service.template.binding.TemplateBindingService;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class TemplateBindingController {

  public void updateTemplateBindingList(ActionRequest request, ActionResponse response) {
    TemplateBinding templateBinding = request.getContext().asType(TemplateBinding.class);

    Beans.get(TemplateBindingService.class).updateTemplateBindingLineList(templateBinding);

    response.setValues(templateBinding);
  }
}
